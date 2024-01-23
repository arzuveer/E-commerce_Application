package com.Bazaar.Spring_Bazaar.Repository;

import com.Bazaar.Spring_Bazaar.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
