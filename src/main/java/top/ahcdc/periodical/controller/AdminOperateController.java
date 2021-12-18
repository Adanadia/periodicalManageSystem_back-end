package top.ahcdc.periodical.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.PeriodicalSubscriptionEntity;
import top.ahcdc.periodical.mapper.PeriodicalSubscriptionMapper;
import top.ahcdc.periodical.service.AdminOperateService;
import top.ahcdc.periodical.utils.JWTUtils;

import java.util.Calendar;

@RestController
public class AdminOperateController {
    @Autowired
    AdminOperateService adminOperateService;
    @CrossOrigin
    @PostMapping("/admin/subscribe")
    public CommonResponse<Object> Subscribe(
            @RequestParam("mailing_code") String mailing_code, @RequestParam("ISSN") String ISSN, @RequestParam("CN") String CN,
            @RequestParam("periodical_name") String periodical_name, @RequestParam("subscription_year") int subscription_year,
            @RequestParam("public_cycle") String public_cycle,@RequestHeader("Authorization") String token){
        DecodedJWT tokenInfo= JWTUtils.getTokenInfo(token);
        String KEY=tokenInfo.getClaim("key").asString();
        if(!KEY.equals("adminIdentity")) return CommonResponse.createForError(10,"您不是管理员,请重新登录");
        if(subscription_year< Calendar.getInstance().get(Calendar.YEAR)){
           return CommonResponse.createForError("不能征订以前的期刊");
        }
        adminOperateService.PeriodicalSubscribe(mailing_code,ISSN,CN,periodical_name,subscription_year,public_cycle);
        return CommonResponse.createForSuccessMessage("征订成功！");
    }
    @PostMapping("/admin/bookcomes")
    public CommonResponse<Object> BookComes(@RequestParam("mailing_code") String mailing_code,@RequestParam("stage") int stage,@RequestParam("deposit") double deposit,@RequestHeader("Authorization") String token){
        DecodedJWT tokenInfo= JWTUtils.getTokenInfo(token);
        String KEY=tokenInfo.getClaim("key").asString();
        if(!KEY.equals("adminIdentity")) return CommonResponse.createForError(10,"您不是管理员,请重新登录");
        PeriodicalSubscriptionEntity periodicalSubscriptionEntity= adminOperateService.GetSubscriptionByMailingCode(mailing_code);
        if(periodicalSubscriptionEntity==null||periodicalSubscriptionEntity.getSubscriptionYear()!=2021){
            return CommonResponse.createForError("入库数据出错！不存在本年度的征订数据");
        }//入库的期刊不是订的2021年的或者没有订阅记录
        else {
            adminOperateService.PeriodicalComes(mailing_code,stage,deposit);
            return CommonResponse.createForSuccess("期刊入库成功！");
        }
    }
}
