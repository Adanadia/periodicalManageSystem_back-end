package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.AdministratorEntity;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.mapper.AdministratorMapper;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{
    @Autowired
    AdministratorMapper administratorMapper;
    public AdministratorEntity getPasswordByAdminNum(String adminNum) {
        QueryWrapper<AdministratorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",adminNum);
        AdministratorEntity admin = administratorMapper.selectOne(queryWrapper);
        return admin;
    }
}
