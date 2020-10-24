package com.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.constants.MessageCodes;
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
@Table(name = "dealer_bill")
@NoArgsConstructor
@AllArgsConstructor
public class DealerBills {

    @Id
    @Builder.Default
    @Column(name = "bill_id")
    private String billId= MessageCodes.BILL+ RandomIdGenerator.generateRandomAplhaNumericString(7);

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "gst")
    private double gst;

    @Column(name = "total")
    private double total;

    @Column(name = "purchased_date")
    private Date purchasedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealer_id",referencedColumnName = "dealer_id")
    @JsonBackReference
    private Dealer dealer;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
