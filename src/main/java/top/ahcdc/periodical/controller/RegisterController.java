package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.form.RegisterForm;
import top.ahcdc.periodical.service.RegisterService;

import javax.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @CrossOrigin
    @PostMapping("/userlogin/register")
    public CommonResponse<String> register(@Valid @RequestBody RegisterForm registerForm, BindingResult result){
        if (result.hasErrors()){
            return CommonResponse.createForError(result.getFieldError().getDefaultMessage());
        }
        registerService.insertUser(registerForm);
        return CommonResponse.createForSuccess("注册成功");
    }
}
