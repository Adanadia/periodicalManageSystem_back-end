package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.*;
import top.ahcdc.periodical.mapper.*;
import top.ahcdc.periodical.service.BorrowService;
import top.ahcdc.periodical.service.InformationService;
import top.ahcdc.periodical.vo.UserMainPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
import top.ahcdc.periodical.vo.integration.PeriodicalVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.LinkedList;
import java.util.List;

@Service
public class BorrowSearchServicempl implements BorrowSearchService {
    @Autowired
    private PeriodicalRegisterMapper periodicalRegisterMapper;
    @Autowired
    private BorrowTableMapper borrowTableMapper;
    @Autowired
    private PeriodicalContentMapper periodicalContentMapper;
    @Override
    public PeriodicalNotBorrowVO getBorrowSearchVO(int type, String search_content) {


        List<PeriodicalRegisterEntity> periodicalRegisterEntityList = periodicalRegisterMapper.selectList(null);
        List<BorrowTabelEntity> borrowTabelEntityList = borrowTableMapper.selectList(null);
        List<PeriodicalNotBorrowVO> all = new LinkedList<>();
        List<PeriodicalNotBorrowVO> borrowed = new LinkedList<>();
        for(PeriodicalRegisterEntity periodicalRegisterEntity:periodicalRegisterEntityList){
            all.add(new PeriodicalNotBorrowVO(
                    periodicalRegisterEntity.getPeriodicalName(),
                    periodicalRegisterEntity.getPeriodicalCover(),
                    periodicalRegisterEntity.getVolume(),
                    periodicalRegisterEntity.getYear(),
                    periodicalRegisterEntity.getStage()
            ));
        }
        for(BorrowTabelEntity borrowTabelEntity:borrowTabelEntityList){
            borrowed.add(new PeriodicalNotBorrowVO(
                    borrowTabelEntity.getPeriodicalName(),
                    getCover(borrowTabelEntity.getPeriodicalName(),
                            borrowTabelEntity.getYear(),
                            borrowTabelEntity.getVolume(),
                            borrowTabelEntity.getStage()),
                    borrowTabelEntity.getVolume(),
                    borrowTabelEntity.getYear(),
                    borrowTabelEntity.getStage()
            ));
        }
        List<PeriodicalNotBorrowVO> ret = new LinkedList<>();

        for(PeriodicalNotBorrowVO a:all){
            int flag = 0;
            for(PeriodicalNotBorrowVO b:borrowed){
                if(a.getStage()==b.getStage() && a.getPeriodicalName() == b.getPeriodicalName()
                        && a.getVolume() == b.getVolume() && a.getYear() == b.getYear()){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                ret.add(a);
            }
        };
        if(type==1){

        }
    }
    public String getCover(String name, int year, int volume, int stage) {
        QueryWrapper<PeriodicalRegisterEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("periodical_name",name);
        queryWrapper.eq("year",year);
        queryWrapper.eq("volume",volume);
        queryWrapper.eq("stage",stage);
        return periodicalRegisterMapper.selectOne(queryWrapper).getPeriodicalCover();
    }
}
