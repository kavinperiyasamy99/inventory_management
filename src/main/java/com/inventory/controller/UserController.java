package com.inventory.controller;

import com.inventory.constants.NameSpaceConstants;
import com.inventory.dto.UserDto;
import com.inventory.io.BaseResponse;
import com.inventory.service.UserDetailsService;
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
@Api(value = "UserController" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserDetailsService service;

    @PostMapping(value = NameSpaceConstants.CREATE_USER)
    public ResponseEntity<BaseResponse> processCreateUser(@RequestBody UserDto req) throws Exception {
        log.info("UserController :: processCreateUser() :: Init ");
        BaseResponse response = service.processCreateUser(req);
        log.info("UserController :: processCreateUser() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = NameSpaceConstants.LOGIN)
    public ResponseEntity<BaseResponse> processLogin(@RequestBody UserDto req) throws Exception {
        log.info("UserController :: processLogin() :: Init ");
        BaseResponse response = service.processLogin(req);
        log.info("UserController :: processLogin() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_USERS)
    public ResponseEntity<BaseResponse> processRetrieveUsers() throws Exception {
        log.info("UserController :: processRetrieveUsers() :: Init ");
        BaseResponse response = service.processRetrieveUsers();
        log.info("UserController :: processRetrieveUsers() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_USER)
    public ResponseEntity<BaseResponse> processRetrieveUser(@PathVariable Long userId) throws Exception {
        log.info("UserController :: processRetrieveUser() :: Init ");
        BaseResponse response = service.processRetrieveUser(userId);
        log.info("UserController :: processRetrieveUser() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = NameSpaceConstants.UPDATE_USER)
    public ResponseEntity<BaseResponse> processUpdateUser(@RequestBody UserDto req,@PathVariable Long userId) throws Exception {
        log.info("UserController :: processUpdateUser() :: Init ");
        BaseResponse response = service.processUpdateUser(req,userId);
        log.info("UserController :: processUpdateUser() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = NameSpaceConstants.DELETE_USER)
    public ResponseEntity<BaseResponse> processDeleteUser(@PathVariable Long userId) throws Exception {
        log.info("UserController :: processDeleteUser() :: Init ");
        BaseResponse response = service.processDeleteUser(userId);
        log.info("UserController :: processDeleteUser() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

}
