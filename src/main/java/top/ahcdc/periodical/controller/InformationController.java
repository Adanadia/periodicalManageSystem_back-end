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
        DecodedJWT tokenInfo = null;
        String userNum = null;
        UserMainPageVO mainPageInfo = null;
        try{
            tokenInfo = JWTUtils.getTokenInfo(token);
            userNum = tokenInfo.getClaim("userNum").asString();
            mainPageInfo = informationService.getMainPageInfo(userNum);
        }catch (NullPointerException e){
            return CommonResponse.createForError("用户不存在");
        }
        return CommonResponse.createForSuccess(mainPageInfo);
    }
}
