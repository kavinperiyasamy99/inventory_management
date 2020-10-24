package com.inventory.repository;

import com.inventory.entity.DealerBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerBillRepository extends JpaRepository<DealerBills,String> {
    DealerBills findByBillId(String billId);
    List<DealerBills> findByDealerDealerId(String dealerId);
}
