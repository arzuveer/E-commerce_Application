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
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;


@Service
public class OrderService {

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;


    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException {
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

        // now we can place order

//
        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);

        Card card = customer.getCards().get(0);

        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';

        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrdered(order);
        order.getItems().add(item);
        order.setCustomer(customer);

       // itemRepository.save(item);

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        productRepository.save(product);



        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);
        product.getItems().add(savedOrder.getItems().get(0));

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderedDate(savedOrder.getOrderedDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        String text = "Congrats your order with total charges "+ order.getTotalCost()+" has been placed on Spring_Bazaar.!!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arzubackend443@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification");
        message.setText(text);
        emailSender.send(message);




        return orderResponseDto;
    }
}
