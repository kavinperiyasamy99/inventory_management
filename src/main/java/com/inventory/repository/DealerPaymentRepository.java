package com.inventory.repository;


import com.inventory.entity.Dealer;
import com.inventory.entity.DealerBills;
import com.inventory.entity.DealerPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerPaymentRepository extends JpaRepository<DealerPayments,String> {
    DealerPayments findByPaymentId(String paymentId);
    List<DealerPayments> findByDealerDealerId(String dealerId);
}
