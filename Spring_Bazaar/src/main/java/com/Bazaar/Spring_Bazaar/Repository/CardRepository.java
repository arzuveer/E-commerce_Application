package com.Bazaar.Spring_Bazaar.Repository;

import com.Bazaar.Spring_Bazaar.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
