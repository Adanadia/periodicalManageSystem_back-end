package top.ahcdc.periodical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.form.RegisterForm;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void insertUser(RegisterForm registerForm) {
        userMapper.insert(new UserEntity(
                registerForm.getUserNum(),
                registerForm.getUserName(),
                registerForm.getEmail(),
                registerForm.getPassword(),
                0.00,
                null
                )
        );
    }
}
