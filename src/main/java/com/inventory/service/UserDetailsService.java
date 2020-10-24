package com.inventory.service;

import com.inventory.dto.UserDto;
import com.inventory.io.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService {

    public BaseResponse processLogin(UserDto request) throws Exception;
    public BaseResponse processRetrieveUser(Long userId) throws Exception;
    public BaseResponse processRetrieveUsers() throws Exception;
    public BaseResponse processCreateUser(UserDto request) throws Exception;
    public BaseResponse processUpdateUser(UserDto request,Long userId) throws Exception;
    public BaseResponse processDeleteUser(Long userId) throws Exception;

    }
