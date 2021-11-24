package top.ahcdc.periodical.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.service.UserService;
import top.ahcdc.periodical.service.impl.UserServiceImpl;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.Authorization;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    UserService userService = new UserServiceImpl();
    @CrossOrigin
    @PostMapping("/userlogin")
    public CommonResponse<String> userLogin(@RequestParam("user_num") String userNum,
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
            CommonResponse.createForSuccess(new Authorization(token));
        }
        return CommonResponse.createForError();
    }
}
