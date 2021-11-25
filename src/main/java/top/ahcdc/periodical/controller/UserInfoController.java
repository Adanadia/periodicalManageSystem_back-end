package top.ahcdc.periodical.controller;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.service.UserMainPageService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.UserMainPageVO;

import java.util.Map;

@RestController
public class UserInfoController {
    @Autowired
    private UserMainPageService userMainPageService;
    @CrossOrigin
    @GetMapping("/information")
    public CommonResponse<UserMainPageVO> MainPage(@RequestHeader("Authorization") String token){
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        UserMainPageVO mainPageInfo = userMainPageService.getMainPageInfo(userNum);
        return CommonResponse.createForSuccess(mainPageInfo);
    }
}
