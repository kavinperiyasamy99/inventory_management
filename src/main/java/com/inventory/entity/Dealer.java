package com.inventory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.inventory.dto.DealerDto;
import com.inventory.enums.Status;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "dealer")
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Dealer implements Serializable {
    @Id
    @Column(name = "dealer_id")
    private String dealerId;
    @Column(name = "company_name",unique = true)
    private String companyName;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json", name = "avaliable_products")
    private List<String> avaliableProducts;

    @Column(name = "district")
    private String district;
    @Column(name = "state")
    private String state;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json", name = "address")
    private DealerDto.Address address;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json", name = "contacts")
    private List<DealerDto.Contacts> contacts;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status=Status.Active;;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "balance")
    private double balance;

    @Column(name = "total_txn_amount")
    private double totalTxnAmount;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @JsonManagedReference
    private List<DealerPayments> payments;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @JsonManagedReference
    private List<DealerBills> bills;
}
