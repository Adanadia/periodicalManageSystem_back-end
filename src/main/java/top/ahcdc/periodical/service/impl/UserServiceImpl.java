package top.ahcdc.periodical.service.impl;

import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.UserService;


public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    @Override
    public UserEntity getPasswordByUserNum(String userNum) {
        UserEntity user = userMapper.selectById(userNum);
        return user;
    }
}
