package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.form.RegisterForm;
import top.ahcdc.periodical.service.RegisterService;
import top.ahcdc.periodical.service.UserService;

import javax.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping("/userlogin/register")
    public CommonResponse<String> register(@Valid RegisterForm registerForm, BindingResult result){
        if (result.hasErrors()){
            return CommonResponse.createForError(result.getFieldError().getDefaultMessage());
        }
        if(userService.getPasswordByUserNum(registerForm.getUser_num()) != null){
            return CommonResponse.createForError("用户名已存在");
        }
        registerService.insertUser(registerForm);
        return CommonResponse.createForSuccess("注册成功");
    }
}
