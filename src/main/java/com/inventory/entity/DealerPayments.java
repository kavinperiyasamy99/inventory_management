package com.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.constants.MessageCodes;
import com.inventory.enums.PaymentMode;
import com.inventory.util.RandomIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "dealer_paymets")
@NoArgsConstructor
@AllArgsConstructor
public class DealerPayments {

    @Id
    @Builder.Default
    @Column(name = "payment_id")
    private String paymentId= MessageCodes.PAYMENT+RandomIdGenerator.generateRandomAplhaNumericString(7);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "dealer_id")
    @JsonBackReference
    private Dealer dealer;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "transaction_id")
    private String transactionId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
