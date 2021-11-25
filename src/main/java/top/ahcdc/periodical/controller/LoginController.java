package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.service.UserService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.utils.Authorization;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
        if(user == null){
            return CommonResponse.createForError("没找到用户信息");
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
        return CommonResponse.createForError();
    }
}
