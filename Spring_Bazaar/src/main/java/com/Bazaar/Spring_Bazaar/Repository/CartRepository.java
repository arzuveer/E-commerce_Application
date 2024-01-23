package com.Bazaar.Spring_Bazaar.Repository;

import com.Bazaar.Spring_Bazaar.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
