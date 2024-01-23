package com.Bazaar.Spring_Bazaar.Repository;

import com.Bazaar.Spring_Bazaar.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByPanNo(String panNo);

    @Query(value = "select * from seller s where s.age=:age ", nativeQuery = true)
    public List<Seller> findByAge(int age);

}
