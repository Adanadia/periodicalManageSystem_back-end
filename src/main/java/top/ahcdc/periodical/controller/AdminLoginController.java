package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.AdministratorEntity;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.service.AdminLoginService;
import top.ahcdc.periodical.service.UserLoginService;
import top.ahcdc.periodical.utils.Authorization;
import top.ahcdc.periodical.utils.JWTUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @CrossOrigin
    @PostMapping("/admin")
    public CommonResponse<Authorization> userLogin(@RequestParam("id") String Id,
                                                   @RequestParam("password") String password,
                                                   HttpServletResponse response){
        AdministratorEntity admin=adminLoginService.getPasswordByAdminNum(Id);
        if(admin==null){
            return CommonResponse.createForError("输入账号有误");
        }
        Map<String,String> adminInfoMap = new HashMap<>();
        if(admin.getPassword().equals(password)){
            adminInfoMap.put("Id",admin.getId());
            adminInfoMap.put("key","adminIdentity");
            String token = JWTUtils.getToken(adminInfoMap);
            response.setHeader("Authorization",token);
            return CommonResponse.createForSuccess("登录成功",new Authorization(token));
        }
        return CommonResponse.createForError("密码有误");
    }
}
