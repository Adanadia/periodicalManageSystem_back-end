package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.service.UserService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.Authorization;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//RequestBody获取的是json字符串
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @CrossOrigin
    @PostMapping("/userLogin")
    public CommonResponse<Authorization> userLogin(@RequestParam("user_num") String userNum,
                                            @RequestParam("password") String password,
                                            HttpServletResponse response){
        UserEntity user = userService.getPasswordByUserNum(userNum);
        Map<String,String> userInfoMap = new HashMap<>();
        if(user.getPassword().equals(password)){
            userInfoMap.put("userNum",user.getUserNum());
            userInfoMap.put("userName",user.getUserName());
            userInfoMap.put("userEmail",user.getUserEmail());
            String token = JWTUtils.getToken(userInfoMap);
            response.setHeader("Authorization",token);
            return CommonResponse.createForSuccess(new Authorization(token));
        }
        return CommonResponse.createForError();
    }
}
