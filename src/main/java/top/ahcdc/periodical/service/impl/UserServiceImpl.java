package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserEntity getPasswordByUserNum(String userNum) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_num",userNum);
        UserEntity user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
