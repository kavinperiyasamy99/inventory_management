package com.inventory.service;

import com.inventory.dto.DealerDto;
import com.inventory.dto.PaymentsDto;
import com.inventory.dto.UserDto;
import com.inventory.io.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface DealerService {

    public BaseResponse processRetrieveDealer(String dealerId) throws Exception;
    public BaseResponse processRetrieveDealers() throws Exception;
    public BaseResponse processCreateDealer(DealerDto request) throws Exception;
    public BaseResponse processUpdateDealer(DealerDto request,String dealerId) throws Exception;
    public BaseResponse processDeleteDealer(String dealerId) throws Exception;
    public BaseResponse processFilterDealer(String comapanyName) throws Exception;

    public BaseResponse processAddDealerBill(DealerDto req,String dealerId) throws Exception;
    public BaseResponse processUpdateDealerBill(DealerDto.DealerBillsDto req,String dealerId,String billId) throws Exception;
    public BaseResponse processRetrieveDealerBills(String dealerId) throws Exception;
    public BaseResponse processRetrieveDealerBill(String dealerId,String billId) throws Exception;
    public BaseResponse processDeleteDealerBill(String dealerId,String billId) throws Exception;

    public BaseResponse processAddDealerPayment(PaymentsDto req, String dealerId) throws Exception;
    public BaseResponse processUpdateDealerPayment(PaymentsDto req,String dealerId,String paymentId) throws Exception;
    public BaseResponse processRetrieveDealerPayments(String dealerId) throws Exception;
    public BaseResponse processRetrieveDealerPayment(String dealerId,String paymentId) throws Exception;
    public BaseResponse processDeleteDealerPayment(String dealerId,String paymentId) throws Exception;
}
