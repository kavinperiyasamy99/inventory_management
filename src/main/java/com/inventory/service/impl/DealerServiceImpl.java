package com.inventory.service.impl;

import com.inventory.constants.MessageCodes;
import com.inventory.dto.DealerDto;
import com.inventory.dto.PaymentsDto;
import com.inventory.entity.Dealer;
import com.inventory.entity.DealerBills;
import com.inventory.entity.DealerPayments;
import com.inventory.io.BaseResponse;
import com.inventory.io.StatusMessage;
import com.inventory.repository.DealerBillRepository;
import com.inventory.repository.DealerPaymentRepository;
import com.inventory.repository.DealerRepository;
import com.inventory.service.DealerService;
import com.inventory.util.CommonUtils;
import com.inventory.util.RandomIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DealerServiceImpl implements DealerService {

    @Autowired
    DealerRepository repository;
    @Autowired
    DealerBillRepository dealerBillRepository;
    @Autowired
    DealerPaymentRepository dealerPaymentRepository;

    @Override
    @Transactional
    public BaseResponse processCreateDealer(DealerDto request) throws Exception {
        log.info("DealerServiceImpl :: processCreateDealer() :: Init ");
        if(repository.existsByCompanyName(request.getCompanyName())) {
            throw new IllegalArgumentException(request.getCompanyName()+" already exists");
        }
        Dealer dealer=new Dealer();
        BeanUtils.copyProperties(request,dealer);
        dealer.setDealerId(MessageCodes.DEALER+ RandomIdGenerator.generateRandomAplhaNumericString(7));
        repository.save(dealer);
        log.info("DealerServiceImpl :: processCreateDealer() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(request)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processUpdateDealer(DealerDto request, String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processUpdateDealer() :: Init ");
        Dealer dealer=repository.findByDealerId(dealerId);
        if(CommonUtils.checkIsNullOrEmpty(dealer))
            throw new IllegalArgumentException(MessageCodes.BAD_REQUEST_DESC);
        BeanUtils.copyProperties(request, dealer, CommonUtils.getNullPropertyNames(request));
        repository.save(dealer);
        BeanUtils.copyProperties(dealer,request, CommonUtils.getNullPropertyNames(dealer));
        log.info("DealerServiceImpl :: processUpdateDealer() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(request)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealer(String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealer() :: Init ");
        Dealer dealer=repository.findByDealerId(dealerId);
        if(CommonUtils.checkIsNullOrEmpty(dealer))
            throw new IllegalArgumentException(MessageCodes.BAD_REQUEST_DESC);
        dealer.setBills(null);
        dealer.setPayments(null);
        DealerDto dealerDto=new DealerDto();
        BeanUtils.copyProperties(dealer,dealerDto);
        log.info("DealerServiceImpl :: processRetrieveDealer() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(dealerDto)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealers() throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealers() :: Init ");
        List<Dealer> dealers=repository.findAll();
        dealers.stream().forEach(f-> f.setPayments(null));
        dealers.stream().forEach(f-> f.setBills(null));
        log.info("DealerServiceImpl :: processRetrieveDealers() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(dealers)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processDeleteDealer(String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processDeleteDealer() :: Init ");
        repository.delete(repository.findByDealerId(dealerId));
        log.info("DealerServiceImpl :: processDeleteDealer() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .build();
    }

    @Override
    public BaseResponse processFilterDealer(String comapnyName) throws Exception {
        log.info("DealerServiceImpl :: processFilterDealer() :: Init ");
        List<Dealer> dealers=repository.findByCompanyNameContaining(comapnyName);
        if(!CommonUtils.checkIsNullOrEmpty(dealers))
        dealers.stream().forEach(f-> f.setPayments(null));
        dealers.stream().forEach(f-> f.setBills(null));
        log.info("DealerServiceImpl :: processFilterDealer() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(dealers)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processAddDealerBill(DealerDto req, String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processAddDealerBill() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        List<DealerBills> oldBill=dealer.getBills();
        List<DealerPayments> oldPayments=dealer.getPayments();

        DealerBills newBill= null;
        DealerPayments newPayment=null;
        for(DealerDto.DealerBillsDto dealerBillsDto:req.getBills()){
            newBill=new DealerBills();
            BeanUtils.copyProperties(dealerBillsDto,newBill);
            newBill.setBillId(MessageCodes.BILL+RandomIdGenerator.generateRandomAplhaNumericString(7));
            newBill.setDealer(dealer);
            oldBill.add(newBill);
        }
        dealer.setBills(oldBill);
        if(!CommonUtils.checkIsNullOrEmpty(req.getPayments())) {
            for (PaymentsDto paymentsDto : req.getPayments()) {
                newPayment = new DealerPayments();
                BeanUtils.copyProperties(paymentsDto, newPayment);
                newPayment.setPaymentId(MessageCodes.PAYMENT + RandomIdGenerator.generateRandomAplhaNumericString(7));
                newPayment.setDealer(dealer);
                oldPayments.add(newPayment);
            }
            dealer.setPayments(oldPayments);
        }
        repository.save(dealer);
        log.info("DealerServiceImpl :: processAddDealerBill() :: Ends ");
        return null;
    }

    @Override
    public BaseResponse processUpdateDealerBill(DealerDto.DealerBillsDto req, String dealerId, String billId) throws Exception {
        log.info("DealerServiceImpl :: processUpdateDealerBill() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        DealerBills dealerBills=dealerBillRepository.findByBillId(billId);
        double amount=dealerBills.getTotal();
        BeanUtils.copyProperties(req, dealerBills, CommonUtils.getNullPropertyNames(req));
        dealerBillRepository.save(dealerBills);
        BeanUtils.copyProperties(dealerBills,req);
        log.info("DealerServiceImpl :: processUpdateDealerBill() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(req)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealerBills(String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealerBills() :: Init ");
        List<DealerBills> dealerBills=dealerBillRepository.findByDealerDealerId(dealerId);
        List<DealerDto.DealerBillsDto> bills=new ArrayList<>();
        BeanUtils.copyProperties(dealerBills,bills);
        log.info("DealerServiceImpl :: processRetrieveDealerBills() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(dealerBills)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealerBill(String dealerId, String billId) throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealerBill() :: Init ");
       DealerBills dealerBill=dealerBillRepository.findByBillId(billId);
        DealerDto.DealerBillsDto bill=new DealerDto.DealerBillsDto();
        BeanUtils.copyProperties(dealerBill,bill);
       log.info("DealerServiceImpl :: processRetrieveDealerBill() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(bill)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processDeleteDealerBill(String dealerId, String billId) throws Exception {
        log.info("DealerServiceImpl :: processDeleteDealerBill() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        DealerBills dealerBills=dealerBillRepository.findByBillId(billId);
        double amount=dealerBills.getTotal();
        dealerBillRepository.delete(dealerBills);
        log.info("DealerServiceImpl :: processDeleteDealerBill() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processAddDealerPayment(PaymentsDto req, String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processAddDealerPayment() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        double amount=req.getPaymentAmount();
        DealerPayments newPayment=null;
        if(!CommonUtils.checkIsNullOrEmpty(req)) {
                newPayment = new DealerPayments();
                BeanUtils.copyProperties(req, newPayment);
                newPayment.setPaymentId(MessageCodes.PAYMENT + RandomIdGenerator.generateRandomAplhaNumericString(7));
                newPayment.setDealer(dealer);
        }
        dealerPaymentRepository.save(newPayment);
        BeanUtils.copyProperties(newPayment,req);
        log.info("DealerServiceImpl :: processAddDealerPayment() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(req)
                .build();
    }

    @Override
    public BaseResponse processUpdateDealerPayment(PaymentsDto req, String dealerId, String paymentId) throws Exception {
        log.info("DealerServiceImpl :: processUpdateDealerPayment() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        DealerPayments dealerPayments=dealerPaymentRepository.findByPaymentId(paymentId);
        double amount=dealerPayments.getPaymentAmount();
        BeanUtils.copyProperties(req, dealerPayments, CommonUtils.getNullPropertyNames(req));
        dealerPaymentRepository.save(dealerPayments);
        BeanUtils.copyProperties(dealerPayments,req);
        log.info("DealerServiceImpl :: processUpdateDealerPayment() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(req)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealerPayments(String dealerId) throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealerPayments() :: Init ");
        List<DealerPayments> dealerPayments=dealerPaymentRepository.findByDealerDealerId(dealerId);
        List<PaymentsDto> paymentsDto=new ArrayList<>();
        BeanUtils.copyProperties(dealerPayments,paymentsDto);
        log.info("DealerServiceImpl :: processRetrieveDealerPayments() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(dealerPayments)
                .build();
    }

    @Override
    public BaseResponse processRetrieveDealerPayment(String dealerId, String paymentId) throws Exception {
        log.info("DealerServiceImpl :: processRetrieveDealerPayment() :: Init ");
        DealerPayments dealerPayments=dealerPaymentRepository.findByPaymentId(paymentId);
        PaymentsDto paymentsDto=new PaymentsDto();
        BeanUtils.copyProperties(dealerPayments,paymentsDto);
        log.info("DealerServiceImpl :: processRetrieveDealerPayment() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(paymentsDto)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processDeleteDealerPayment(String dealerId, String paymentId) throws Exception {
        log.info("DealerServiceImpl :: processDeleteDealerPayment() :: Init ");
        if(!repository.existsByDealerId(dealerId)) {
            throw new IllegalArgumentException("DealerId " + dealerId + " not found");
        }
        Dealer dealer=repository.findByDealerId(dealerId);
        DealerPayments dealerPayments=dealerPaymentRepository.findByPaymentId(paymentId);
        double amount=dealerPayments.getPaymentAmount();
        dealerPaymentRepository.delete(dealerPayments);
        log.info("DealerServiceImpl :: processDeleteDealerPayment() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .build();
    }

}
