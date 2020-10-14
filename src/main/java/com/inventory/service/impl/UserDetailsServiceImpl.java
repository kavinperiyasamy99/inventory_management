package com.inventory.service.impl;

import com.inventory.constants.MessageCodes;
import com.inventory.dto.UserDto;
import com.inventory.entity.UserDetails;
import com.inventory.io.BaseResponse;
import com.inventory.io.StatusMessage;
import com.inventory.repository.UserDetailsRepository;
import com.inventory.service.UserDetailsService;
import com.inventory.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository repository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public BaseResponse processLogin(UserDto request) throws Exception {
        log.info("UserDetailsServiceImpl :: processLogin() :: Init ");
        UserDetails userDetails=repository.findByUserName(request.getUserName());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (Exception ex) {
            throw new IllegalArgumentException("inavalid username/password");
        }
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);
        userDto.setPassword(null);
        userDto.setToken(jwtUtil.generateToken(request.getUserName()));
        log.info("UserDetailsServiceImpl :: processLogin() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(userDto)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processCreateUser(UserDto request) throws Exception {
        log.info("UserDetailsServiceImpl :: processCreateUser() :: Init ");
        UserDetails user=repository.findByUserName(request.getUserName());
        if(!StringUtils.isEmpty(user))
            throw new IllegalArgumentException("This userName already exists");
        UserDetails userDetails= new UserDetails();
        BeanUtils.copyProperties(request,userDetails);
        repository.save(userDetails);
        request.setPassword(null);
        log.info("UserDetailsServiceImpl :: processCreateUser() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(request)
                .build();
    }
}
