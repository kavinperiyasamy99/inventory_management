package com.inventory.service.impl;

import com.inventory.constants.MessageCodes;
import com.inventory.dto.UserDto;
import com.inventory.entity.UserDetails;
import com.inventory.enums.Status;
import com.inventory.io.BaseResponse;
import com.inventory.io.StatusMessage;
import com.inventory.repository.UserDetailsRepository;
import com.inventory.service.UserDetailsService;
import com.inventory.util.CommonUtils;
import com.inventory.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

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
        UserDetails userDetails=repository.findByUserName(request.getUserName(),Status.Active);
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
    public BaseResponse processRetrieveUser(Long userId) throws Exception {
        log.info("UserDetailsServiceImpl :: processRetrieveUser() :: Init ");
        UserDetails user=new UserDetails();
        UserDetails users=repository.findByUserId(userId);
        log.info("UserDetailsServiceImpl :: processRetrieveUser() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(users)
                .build();
    }

    @Override
    public BaseResponse processRetrieveUsers() throws Exception {
        log.info("UserDetailsServiceImpl :: processRetrieveUsers() :: Init ");
        UserDetails user=new UserDetails();
        List<UserDetails> users=repository.findAllActiveUsers(Status.Active);
        users.stream().forEach(f-> f.setPassword(null));
        log.info("UserDetailsServiceImpl :: processRetrieveUsers() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .data(users)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processCreateUser(UserDto request) throws Exception {
        log.info("UserDetailsServiceImpl :: processCreateUser() :: Init ");
        UserDetails user=repository.findByUserName(request.getUserName(),Status.Active);
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

    @Override
    @Transactional
    public BaseResponse processUpdateUser(UserDto request, Long userId) throws Exception {
        log.info("UserDetailsServiceImpl :: processUpdateUser() :: Init ");
        UserDetails user=repository.findByUserId(request.getUserId());
        if(StringUtils.isEmpty(user))
            throw new IllegalArgumentException("Please chek your Input");
        BeanUtils.copyProperties(request, user, CommonUtils.getNullPropertyNames(request));
        repository.save(user);
        UserDto userDto= new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setPassword(null);
        log.info("UserDetailsServiceImpl :: processUpdateUser() :: Ends ");
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
    public BaseResponse processDeleteUser(Long userId) throws Exception {
        log.info("UserDetailsServiceImpl :: processDeleteUser() :: Init ");
        UserDetails user=repository.findByUserId(userId);
        user.setStatus(Status.InActive);
        repository.save(user);
        log.info("UserDetailsServiceImpl :: processDeleteUser() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.SUCCESS_DESC)
                        .build())
                .build();
    }
}
