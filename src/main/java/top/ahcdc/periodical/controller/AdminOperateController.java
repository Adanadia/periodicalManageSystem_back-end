package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.PeriodicalSubscriptionEntity;
import top.ahcdc.periodical.mapper.PeriodicalSubscriptionMapper;
import top.ahcdc.periodical.service.AdminOperateService;

@RestController
public class AdminOperateController {
    @Autowired
    AdminOperateService adminOperateService;
    @CrossOrigin
    @PostMapping("/admin/subscribe")
    public CommonResponse<Object> Subscribe(@RequestParam("mailing_code") String mailing_code,@RequestParam("ISSN") String ISSN,@RequestParam("CN") String CN,
                                            @RequestParam("periodical_name") String periodical_name,@RequestParam("subscription_year") int subscription_year,
                                            @RequestParam("public_cycle") String public_cycle){
        if(subscription_year<2021){
           return CommonResponse.createForError("不能征订以前的期刊");
        }
        adminOperateService.PeriodicalSubscribe(mailing_code,ISSN,CN,periodical_name,subscription_year,public_cycle);
        return CommonResponse.createForSuccessMessage("征订成功！");
    }
    @PostMapping("/admin/bookcomes")
    public CommonResponse<Object> BookComes(@RequestParam("mailing_code") String mailing_code,@RequestParam("stage") int stage,@RequestParam("deposit") double deposit){
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
