package com.inventory.service;

import com.inventory.dto.UserDto;
import com.inventory.io.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService {

    public BaseResponse processLogin(UserDto request) throws Exception;
    public BaseResponse processCreateUser(UserDto request) throws Exception;

    }
