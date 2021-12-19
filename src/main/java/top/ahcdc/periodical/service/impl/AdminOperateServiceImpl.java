package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.PeriodicalCatalogueEntity;
import top.ahcdc.periodical.entity.PeriodicalRegisterEntity;
import top.ahcdc.periodical.entity.PeriodicalSubscriptionEntity;
import top.ahcdc.periodical.mapper.PeriodicalCatalogueMapper;
import top.ahcdc.periodical.mapper.PeriodicalRegisterMapper;
import top.ahcdc.periodical.mapper.PeriodicalSubscriptionMapper;
import top.ahcdc.periodical.service.AdminOperateService;

import javax.validation.constraints.Null;
import java.util.List;

@Service
public class AdminOperateServiceImpl implements AdminOperateService {
    @Autowired
    PeriodicalCatalogueMapper periodicalCatalogueMapper;
    @Autowired
    PeriodicalSubscriptionMapper periodicalSubscriptionMapper;
    @Autowired
    PeriodicalRegisterMapper periodicalRegisterMapper;


    public PeriodicalCatalogueEntity GetCatalogueByMailingCode(String mailing_code){
        QueryWrapper<PeriodicalCatalogueEntity> periodicalCatalogueQueryWrapper=new QueryWrapper<>();
        periodicalCatalogueQueryWrapper.eq("mailing_code",mailing_code);
        PeriodicalCatalogueEntity periodicalCatalogueEntity=periodicalCatalogueMapper.selectOne(periodicalCatalogueQueryWrapper);
        return periodicalCatalogueEntity;
    }
    public void PeriodicalSubscribe(String mailing_code,String ISSN,String CN,String name,int subscription_year,String public_cycle){
        PeriodicalCatalogueEntity periodicalCatalogueEntity=GetCatalogueByMailingCode(mailing_code);
        if(periodicalCatalogueEntity== null){
            periodicalCatalogueMapper.insert(new PeriodicalCatalogueEntity(name,ISSN,CN,mailing_code,public_cycle));
            periodicalSubscriptionMapper.insert(new PeriodicalSubscriptionEntity(mailing_code,name,subscription_year));
        }//征订没有查询到目录信息
        else{
            periodicalSubscriptionMapper.insert(new PeriodicalSubscriptionEntity(
                    mailing_code,periodicalCatalogueEntity.getPeriodicalName(),subscription_year
            ));

        }
    }
    public PeriodicalSubscriptionEntity GetSubscriptionByMailingCode(String mailing_code){
        QueryWrapper<PeriodicalSubscriptionEntity> periodicalSubscriptionQueryWrapper= new QueryWrapper<>();
        periodicalSubscriptionQueryWrapper.eq("mailing_code",mailing_code);
        PeriodicalSubscriptionEntity periodicalSubscriptionEntity=periodicalSubscriptionMapper.selectOne(periodicalSubscriptionQueryWrapper);
        return periodicalSubscriptionEntity;
    }
    public List<PeriodicalRegisterEntity> GetRegisterByPeriodicalName(String periodical_name){
        QueryWrapper<PeriodicalRegisterEntity> periodicalRegisterQueryWrapper=new QueryWrapper<>();
        periodicalRegisterQueryWrapper.eq("periodical_name",periodical_name)
                .eq("year",2021);
        List<PeriodicalRegisterEntity> periodicalRegisterEntities=periodicalRegisterMapper.selectList(periodicalRegisterQueryWrapper);
        return periodicalRegisterEntities;
    }
    public void PeriodicalComes(String mailing_code,int stage,double deposit){
        QueryWrapper<PeriodicalCatalogueEntity> periodicalCatalogueQueryWrapper=new QueryWrapper<>();
        periodicalCatalogueQueryWrapper.eq("mailing_code",mailing_code);
        PeriodicalCatalogueEntity periodicalCatalogueEntity=periodicalCatalogueMapper.selectOne(periodicalCatalogueQueryWrapper);
        List<PeriodicalRegisterEntity> periodicalRegisterEntity=GetRegisterByPeriodicalName(periodicalCatalogueEntity.getPeriodicalName());
        periodicalRegisterMapper.insert(new PeriodicalRegisterEntity(
                periodicalRegisterEntity.get(0).getPeriodicalName(),
                2021-periodicalRegisterEntity.get(0).getYear()+periodicalRegisterEntity.get(0).getVolume(),
                2021, stage, periodicalRegisterEntity.get(0).getPeriodicalCover(),deposit
        ));
    }
}
