package com.inventory.dto;

import com.inventory.enums.Status;
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
public class DealerDto {

    private String dealerId;
    private String companyName;
    private List<String> avaliableProducts;
    private String state;
    private String district;
    private Address address;
    private List<Contacts> contacts;
    private Status status;
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;
    private double balance;
    private double totalTxnAmount;
    private List<PaymentsDto> payments;
    private List<DealerBillsDto> bills;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Address{
        private String street;
        private String city;
        private String district;
        private String state;
        private String postalCode;
        private String pinCode;
        private String email;
        private String phone;
        private String alternateNumber;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Contacts{
         private String nameTitle;
         private String firstName;
         private String middleName;
         private String lastName;
         private String gender;
         private String dob;
         private String age;
         private String mobile;
         private String email;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DealerBillsDto{
        private String billId;
        private String productId;
        private String productName;
        private String quantity;
        private double price;
        private double discount;
        private double gst;
        private double total;
        private Date purchasedDate;
        private Date createdAt;
        private Date updatedAt;

    }
}
