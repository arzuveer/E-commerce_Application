package com.Bazaar.Spring_Bazaar.Service;

import com.Bazaar.Spring_Bazaar.Enum.ProductStatus;
import com.Bazaar.Spring_Bazaar.Exception.CustomerNotFoundException;
import com.Bazaar.Spring_Bazaar.Exception.InsufficientQuantityException;
import com.Bazaar.Spring_Bazaar.Exception.ProductNotFoundException;
import com.Bazaar.Spring_Bazaar.Model.*;
import com.Bazaar.Spring_Bazaar.Repository.CustomerRepository;
import com.Bazaar.Spring_Bazaar.Repository.ItemRepository;
import com.Bazaar.Spring_Bazaar.Repository.ProductRepository;
import com.Bazaar.Spring_Bazaar.RequestDTO.OrderRequestDto;
import com.Bazaar.Spring_Bazaar.ResponseDTO.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    JavaMailSender  emailSender;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired OrderService orderService;

    public String addToCart(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException {
        Customer customer;
        try{
            customer= customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("invalid customer id");
        }
        Product product;
        try{
            product= productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("invalid product id");
        }
        if(product.getQuantity() < orderRequestDto.getRequiredQuantity())
            throw new InsufficientQuantityException("sorry!,required quantity is not available right now");

        Cart cart= customer.getCart();

        int newCost= cart.getCartTotal() + orderRequestDto.getRequiredQuantity()* product.getPrice();
        cart.setCartTotal(newCost);

        // add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        itemRepository.save(item);

        customerRepository.save(customer);

        return "item has been added to cart!!";
    }

    public List<OrderResponseDto> checkoutCart(int customerId) throws CustomerNotFoundException, InsufficientQuantityException, ProductNotFoundException {

        Customer customer;
        try{
            customer= customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw  new CustomerNotFoundException("Sorry invalid customerId");
        }
        List<OrderResponseDto> orderResponseDtos= new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();

        Cart cart= customer.getCart();
        for(Item item: cart.getItems())
        {
            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);
            // prepare response dto
            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getName())
                    .orderedDate(order.getOrderedDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }
        cart.setCartTotal(0);
        cart.setItems(new ArrayList<>());
        customerRepository.save(customer);

        String text = "Congrats your order with total value "+ totalCost +" has been placed!!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arzubackend443@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification from Spring_Bazaar");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDtos;
    }

}
