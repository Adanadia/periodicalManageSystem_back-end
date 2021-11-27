package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.form.RegisterForm;

@Service
public interface UserLoginService {
    UserEntity getPasswordByUserNum(String userNum);
    void insertUser(RegisterForm registerForm);
}
