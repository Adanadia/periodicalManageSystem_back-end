package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.form.RegisterForm;
import top.ahcdc.periodical.service.UserLoginService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.utils.Authorization;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//RequestBody获取的是json字符串
@RestController
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @CrossOrigin
    @PostMapping("/userlogin")
    public CommonResponse<Authorization> userLogin(@RequestParam("user_num") String userNum,
                                            @RequestParam("password") String password,
                                            HttpServletResponse response){
        UserEntity user = userLoginService.getPasswordByUserNum(userNum);
        if(user==null){
            return CommonResponse.createForError("输入账号有误");
        }
        Map<String,String> userInfoMap = new HashMap<>();
        if(user.getPassword().equals(password)){
            userInfoMap.put("userNum",user.getUserNum());
            userInfoMap.put("userName",user.getUserName());
            userInfoMap.put("userEmail",user.getUserEmail());
            String token = JWTUtils.getToken(userInfoMap);
            response.setHeader("Authorization",token);
            return CommonResponse.createForSuccess("登录成功",new Authorization(token));
        }
        return CommonResponse.createForError("密码有误");
    }
    @CrossOrigin
    @PostMapping("/userlogin/register")
    public CommonResponse<String> register(@Valid RegisterForm registerForm, BindingResult result){
        if (result.hasErrors()){
            return CommonResponse.createForError(result.getFieldError().getDefaultMessage());
        }
        if(userLoginService.getPasswordByUserNum(registerForm.getUser_num()) != null){
            return CommonResponse.createForError("用户名已存在");
        }
        userLoginService.insertUser(registerForm);
        return CommonResponse.createForSuccess("注册成功");
    }
}
