package com.inventory.repository;

import com.inventory.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,String> {
    Dealer findByDealerId(String dealerId);
    boolean existsByCompanyName(String companyName);
    boolean existsByDealerId(String dealerrId);
    List<Dealer> findByCompanyNameContaining(String companyName);
    Dealer findByCompanyName(String companyName);
}
