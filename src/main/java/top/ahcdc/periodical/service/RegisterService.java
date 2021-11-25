package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.form.RegisterForm;

@Service
public interface RegisterService {
    void insertUser(RegisterForm registerForm);
}
