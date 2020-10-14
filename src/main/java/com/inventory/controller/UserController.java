package com.inventory.controller;

import com.inventory.constants.NameSpaceConstants;
import com.inventory.dto.UserDto;
import com.inventory.io.BaseResponse;
import com.inventory.service.UserDetailsService;
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

    @PostMapping(value = NameSpaceConstants.UserRequestMapping.CREATE_USER)
    public ResponseEntity<BaseResponse> processCreateUser(@RequestBody UserDto req) throws Exception {
        log.info("UserController :: processCreateUser() :: Init ");
        BaseResponse response = service.processCreateUser(req);
        log.info("UserController :: processCreateUser() :: Ends :: Response :: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = NameSpaceConstants.LOGIN)
    public ResponseEntity<BaseResponse> processLogin(@RequestBody UserDto req) throws Exception {
        log.info("UserController :: processLogin() :: Init ");
        BaseResponse response = service.processLogin(req);
        log.info("UserController :: processLogin() :: Response :: " + response);
        return ResponseEntity.ok(response);
    }

}
