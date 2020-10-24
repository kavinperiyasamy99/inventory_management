package com.inventory.dto;

import com.inventory.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentsDto {
        private String paymentId;
        private double paymentAmount;
        private PaymentMode paymentMode;
        private Date paymentDate;
        private String transactionId;
        private Date createdAt;
        private Date updatedAt;

}
