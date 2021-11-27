package top.ahcdc.periodical.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.service.InformationService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.UserMainPageVO;

@RestController
public class InformationController {
    @Autowired
    private InformationService informationService;
    @CrossOrigin
    @GetMapping("/information")
    public CommonResponse<UserMainPageVO> MainPage(@RequestHeader("Authorization") String token){
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        UserMainPageVO mainPageInfo = informationService.getMainPageInfo(userNum);
        return CommonResponse.createForSuccess(mainPageInfo);
    }
//    @CrossOrigin
//    @PutMapping('/information/return')
//    public CommonResponse<String> PeriodicalReturn(){
//
//    }
}
