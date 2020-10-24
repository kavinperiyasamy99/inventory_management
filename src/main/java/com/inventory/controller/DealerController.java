package com.inventory.controller;

import com.inventory.constants.NameSpaceConstants;
import com.inventory.dto.DealerDto;
import com.inventory.dto.PaymentsDto;
import com.inventory.io.BaseResponse;
import com.inventory.service.DealerService;
import com.inventory.util.CommonUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "DealerController" , produces = MediaType.APPLICATION_JSON_VALUE)
public class DealerController {

    @Autowired
    DealerService service;

    @PostMapping(value = NameSpaceConstants.CREATE_DEALER)
    public ResponseEntity<BaseResponse> processCreateDealer(@RequestBody DealerDto req) throws Exception {
        log.info("DealerController :: processCreateDealer() :: Init ");
        BaseResponse response = service.processCreateDealer(req);
        log.info("DealerController :: processCreateDealer() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_DEALERS)
    public ResponseEntity<BaseResponse> processRetrieveDealers() throws Exception {
        log.info("DealerController :: processRetrieveDealers() :: Init ");
        BaseResponse response = service.processRetrieveDealers();
        log.info("DealerController :: processRetrieveUsers() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_DEALER)
    public ResponseEntity<BaseResponse> processRetrieveDealer(@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processRetrieveDealer() :: Init ");
        BaseResponse response = service.processRetrieveDealer(dealerId);
        log.info("DealerController :: processRetrieveDealer() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = NameSpaceConstants.UPDATE_DEALER)
    public ResponseEntity<BaseResponse> processUpdateDealer(@RequestBody DealerDto req,@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processUpdateDealer() :: Init ");
        BaseResponse response = service.processUpdateDealer(req,dealerId);
        log.info("DealerController :: processUpdateDealer() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = NameSpaceConstants.DELETE_DEALER)
    public ResponseEntity<BaseResponse> processDeleteDealer(@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processDeleteDealer() :: Init ");
        BaseResponse response = service.processDeleteDealer(dealerId);
        log.info("DealerController :: processDeleteDealer() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.FILTER_DEALER)
    public ResponseEntity<BaseResponse> processFilterDealer(@RequestParam String companyname) throws Exception {
        log.info("DealerController :: processFilterDealer() :: Init ");
        BaseResponse response = service.processFilterDealer(companyname);
        log.info("DealerController :: processFilterDealer() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = NameSpaceConstants.Dealer.ADD_BILL)
    public ResponseEntity<BaseResponse> processAddDealerBill(@RequestBody DealerDto req,@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processAddDealerBill() :: Init ");
        BaseResponse response = service.processAddDealerBill(req,dealerId);
        log.info("DealerController :: processAddDealerBill() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = NameSpaceConstants.Dealer.UPDATE_BILL)
    public ResponseEntity<BaseResponse> processUpdateDealerBill(@RequestBody DealerDto.DealerBillsDto req,@PathVariable String dealerId,@PathVariable String billId) throws Exception {
        log.info("DealerController :: processUpdateDealerBill() :: Init ");
        BaseResponse response = service.processUpdateDealerBill(req,dealerId,billId);
        log.info("DealerController :: processUpdateDealerBill() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.Dealer.RETRIEVE_BILLS)
    public ResponseEntity<BaseResponse> processRetrieveDealerBills(@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processRetrieveDealerBills() :: Init ");
        BaseResponse response = service.processRetrieveDealerBills(dealerId);
        log.info("DealerController :: processRetrieveDealerBills() :: Ends ");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.Dealer.RETRIEVE_BILL)
    public ResponseEntity<BaseResponse> processRetrieveDealerBill(@PathVariable String dealerId,@PathVariable String billId) throws Exception {
        log.info("DealerController :: processRetrieveDealerBill() :: Init ");
        BaseResponse response = service.processRetrieveDealerBill(dealerId,billId);
        log.info("DealerController :: processRetrieveDealerBill() :: Ends");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = NameSpaceConstants.Dealer.DELETE_BILL)
    public ResponseEntity<BaseResponse> processDeleteDealerBill(@PathVariable String dealerId,@PathVariable String billId) throws Exception {
        log.info("DealerController :: processDeleteDealerBill() :: Init ");
        BaseResponse response = service.processDeleteDealerBill(dealerId,billId);
        log.info("DealerController :: processDeleteDealerBill() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = NameSpaceConstants.Dealer.ADD_PAYMENT)
    public ResponseEntity<BaseResponse> processAddDealerPayment(@RequestBody PaymentsDto req, @PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processAddDealerPayment() :: Init ");
        BaseResponse response = service.processAddDealerPayment(req,dealerId);
        log.info("DealerController :: processAddDealerPayment() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = NameSpaceConstants.Dealer.UPDATE_PAYMENT)
    public ResponseEntity<BaseResponse> processUpdateDealerPayment(@RequestBody PaymentsDto req,@PathVariable String dealerId,@PathVariable String paymentId) throws Exception {
        log.info("DealerController :: processUpdateDealerPayment() :: Init ");
        BaseResponse response = service.processUpdateDealerPayment(req,dealerId,paymentId);
        log.info("DealerController :: processUpdateDealerPayment() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.Dealer.RETRIEVE_PAYMENTS)
    public ResponseEntity<BaseResponse> processRetrieveDealerPayments(@PathVariable String dealerId) throws Exception {
        log.info("DealerController :: processRetrieveDealerPayments() :: Init ");
        BaseResponse response = service.processRetrieveDealerPayments(dealerId);
        log.info("DealerController :: processRetrieveDealerPayments() :: Ends");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.Dealer.RETRIEVE_PAYMENT)
    public ResponseEntity<BaseResponse> processRetrieveDealerPayment(@PathVariable String dealerId,@PathVariable String paymentId) throws Exception {
        log.info("DealerController :: processUpdateDealerPayment() :: Init ");
        BaseResponse response = service.processRetrieveDealerPayment(dealerId,paymentId);
        log.info("DealerController :: processUpdateDealerPayment() :: Ends");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = NameSpaceConstants.Dealer.DELETE_PAYMENT)
    public ResponseEntity<BaseResponse> processDeleteDealerPayment(@PathVariable String dealerId,@PathVariable String paymentId) throws Exception {
        log.info("DealerController :: processDeleteDealerPayment() :: Init ");
        BaseResponse response = service.processDeleteDealerPayment(dealerId,paymentId);
        log.info("DealerController :: processDeleteDealerPayment() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

}
