package com.Bazaar.Spring_Bazaar.Repository;

import com.Bazaar.Spring_Bazaar.Enum.Category;
import com.Bazaar.Spring_Bazaar.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    //@Query(value = "select * from product p where p.category=:category", nativeQuery = true)
    public List<Product> findAllByCategory(Category category);
}
