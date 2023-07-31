package com.yflash.tech.SampleAPI.service.impl;

import com.yflash.tech.SampleAPI.common.CommonConstants;
import com.yflash.tech.SampleAPI.entity.UserEntity;
import com.yflash.tech.SampleAPI.exception.DetailsNotFoundException;
import com.yflash.tech.SampleAPI.exception.ProcessingException;
import com.yflash.tech.SampleAPI.model.in.PostUserRequest;
import com.yflash.tech.SampleAPI.model.in.PutUserRequest;
import com.yflash.tech.SampleAPI.model.out.User;
import com.yflash.tech.SampleAPI.repository.UserRepository;
import com.yflash.tech.SampleAPI.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isEmpty()) {
            throw new DetailsNotFoundException(CommonConstants.GENERIC_EXCEPTION_MESSAGE, CommonConstants.DETAILS_NOT_FOUND_ERROR_CODE, Collections.singletonList(CommonConstants.HTTP_STATUS_MESSAGE_DETAILS_NOT_FOUND));
        }
        return modelMapper.map(userEntity,User.class);
    }
    @Override
    public User addUserDetails(PostUserRequest userRequest) {
        UserEntity userEntity = modelMapper.map(userRequest,UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, User.class);
    }
    @Override
    public User updateUserDetails(PutUserRequest userRequest) {
        if(!userRepository.existsById(userRequest.getId())) {
            throw new ProcessingException(CommonConstants.GENERIC_EXCEPTION_MESSAGE, CommonConstants.INTERNAL_SERVER_ERROR_CODE, Collections.singletonList(CommonConstants.USER_NOT_FOUND));
        }
        UserEntity userEntity = modelMapper.map(userRequest,UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, User.class);
    }
    @Override
    public String deleteUserDetails(Integer id) {
        if(!userRepository.existsById(id)) {
            throw new DetailsNotFoundException(CommonConstants.GENERIC_EXCEPTION_MESSAGE,CommonConstants.DETAILS_NOT_FOUND_ERROR_CODE, Collections.singletonList(CommonConstants.HTTP_STATUS_MESSAGE_DETAILS_NOT_FOUND));
        }
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return id.toString();
        }
        return null;
    }
}


