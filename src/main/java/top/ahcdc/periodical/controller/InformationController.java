package top.ahcdc.periodical.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.service.InformationService;
import top.ahcdc.periodical.utils.CalendarString;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.UserMainPageVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Calendar;
import java.util.ConcurrentModificationException;

@RestController
public class InformationController {
    @Autowired
    private InformationService informationService;
    private CalendarString calendarString=new CalendarString();
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
    @PutMapping("/information")
    public CommonResponse<Object> returnbooks(@RequestParam("periodical_name") String periodical_name,
                                              @RequestParam("volume") int volume,
                                              @RequestParam("year") int year,
                                              @RequestParam("stage") int stage,
                                              @RequestHeader("Authorization") String token) throws ParseException {
            DecodedJWT tokenInfo=JWTUtils.getTokenInfo(token);
            String userNum=tokenInfo.getClaim("userNum").asString();
            String dateString=informationService.returnPeriodical(periodical_name,stage,volume,year,userNum);
            Calendar current=Calendar.getInstance();
            if(calendarString.SToC(dateString).compareTo(current)==1){
                informationService.ReturnMoney(periodical_name,stage,volume,year,userNum);
                return CommonResponse.createForSuccessMessage("归还成功！已退还押金");
            }
            else return CommonResponse.createForError("逾期归还，押金不予退还");

        }
}
