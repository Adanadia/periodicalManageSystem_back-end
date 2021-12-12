package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.PeriodicalCatalogueEntity;
import top.ahcdc.periodical.entity.PeriodicalRegisterEntity;
import top.ahcdc.periodical.entity.PeriodicalSubscriptionEntity;

@Service
public interface AdminOperateService {
    PeriodicalCatalogueEntity GetCatalogueByMailingCode(String mailing_code);
    void PeriodicalSubscribe(String mailing_code,String ISSN,String CN,String name,int subscription_year,String public_cycle);
    PeriodicalSubscriptionEntity GetSubscriptionByMailingCode(String mailing_code);
    void PeriodicalComes(String mailing_code,int stage,double deposit);
     PeriodicalRegisterEntity GetRegisterByPeriodicalName(String periodical_name);

}
