package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.UserDto;
import com.kat.dmc.common.model.UserEntity;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.common.util.PasswordUtil;
import com.kat.dmc.repository.interfaces.UserRepo;
import com.kat.dmc.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(this::TsUser2UserDto).collect(Collectors.toList());
    }

    @Override
    public void save(UserDto userDto) {
        UserEntity savingObj = userRepo.findById(userDto.getUserId());
        if(savingObj == null){
            savingObj = new UserEntity();
            savingObj.setId(userDto.getUserId());
        }
        savingObj.setUsername(userDto.getUserName());
        savingObj.setPermission(userDto.getUserType());
        savingObj.setCode(userDto.getCode());
        savingObj.setStatus(userDto.getStatus());
        savingObj.setName(userDto.getFullName());
        if(!CommonUtil.isNull(userDto.getPassword())){
            savingObj.setPassword(PasswordUtil.hashMD5(userDto.getPassword()));
        }
        userRepo.save(savingObj);
    }

    @Override
    public void delete(Integer userId) {
        UserEntity deletingObj = userRepo.findById(userId);
        userRepo.delete(deletingObj);
    }

    private UserDto TsUser2UserDto(UserEntity input){
        UserDto map = modelMapper.map(input, UserDto.class);
        map.setStatus(input.getStatus());
        map.setUserName(input.getUsername());
        map.setUserId(input.getId());
        map.setFullName(input.getName());
        map.setUserType(input.getPermission());
        return map;
    }
}
