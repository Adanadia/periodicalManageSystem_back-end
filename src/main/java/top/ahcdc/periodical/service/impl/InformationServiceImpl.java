package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.*;
import top.ahcdc.periodical.mapper.BorrowTableMapper;
import top.ahcdc.periodical.mapper.PeriodicalCatalogueMapper;
import top.ahcdc.periodical.mapper.PeriodicalRegisterMapper;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.InformationService;
import top.ahcdc.periodical.vo.UserMainPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;
import top.ahcdc.periodical.utils.CalendarString;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BorrowTableMapper borrowTableMapper;
    @Autowired
    private PeriodicalRegisterMapper registerMapper;
    @Autowired
    private PeriodicalCatalogueMapper catalogueMapper;
    private CalendarString calendarString= new CalendarString();
    @Override
    public UserMainPageVO getMainPageInfo(String userNum){
        QueryWrapper<BorrowTabelEntity> borrowQueryWrapper= new QueryWrapper<>();
        borrowQueryWrapper.eq("user_num",userNum);
        List<BorrowTabelEntity> userBorrowList = borrowTableMapper.selectList(borrowQueryWrapper);

        List<PeriodicalVO> periodicalVOList = new LinkedList<>();
        for(BorrowTabelEntity list:userBorrowList){
            if(!(list.getReturnDate() == null || list.getReturnDate().isBlank())) {
                continue;
            }
            QueryWrapper<PeriodicalCatalogueEntity> catalogueEntityQueryWrapper = new QueryWrapper<>();
            catalogueEntityQueryWrapper.eq("periodical_name",list.getPeriodicalName());
            QueryWrapper<PeriodicalRegisterEntity> registerEntityQueryWrapper = new QueryWrapper<>();
            registerEntityQueryWrapper.eq("periodical_name",list.getPeriodicalName())
                                      .eq("year",list.getYear())
                                      .eq("volume",list.getVolume())
                                      .eq("stage",list.getStage());
            periodicalVOList.add(new PeriodicalVO(
                    catalogueMapper.selectOne(catalogueEntityQueryWrapper).getISSN(),
                    list.getPeriodicalName(),
                    registerMapper.selectOne(registerEntityQueryWrapper).getPeriodicalCover(),
                    list.getDueDate(),
                    list.getVolume(),
                    list.getYear(),
                    list.getStage()
                    )
            );
        }
        QueryWrapper<UserEntity> userInfoQuery = new QueryWrapper<>();
        userInfoQuery.eq("user_num",userNum);
        UserEntity user = userMapper.selectOne(userInfoQuery);
        UserInfoVO userInfo = new UserInfoVO(
                user.getUserNum(),
                user.getUserProfiles(),
                user.getBalance(),
                user.getUserEmail()
        );
        return new UserMainPageVO(periodicalVOList,userInfo);
    }
    @Override
    public String returnPeriodical(String periodical_name,int stage,int volume,int year,String user_num){
        Calendar current=Calendar.getInstance();
        QueryWrapper<BorrowTabelEntity> borrowTabelQueryWrapper=new QueryWrapper<>();
        borrowTabelQueryWrapper.eq("periodical_name",periodical_name)
                                .eq("user_num",user_num)
                                .eq("stage",stage)
                                .eq("volume",volume)
                                .eq("year",year);
        BorrowTabelEntity borrowTabelEntity=borrowTableMapper.selectOne(borrowTabelQueryWrapper);
        borrowTabelEntity.setReturnDate(calendarString.CToS(current));
        borrowTableMapper.update(borrowTabelEntity,borrowTabelQueryWrapper);
        return borrowTabelEntity.getDueDate();
    }//返回期刊到期时间
    public void ReturnMoney(String periodical_name,int stage,int volume,int year,String user_num){
        QueryWrapper<PeriodicalRegisterEntity> periodicalRegisterQueryWrapper=new QueryWrapper<>();
        QueryWrapper<UserEntity> userQueryWrapper=new QueryWrapper<>();
        periodicalRegisterQueryWrapper.eq("periodical_name",periodical_name)
                .eq("stage",stage)
                .eq("volume",volume)
                .eq("year",year);
        userQueryWrapper.eq("user_num",user_num);
        UserEntity userEntity=userMapper.selectOne(userQueryWrapper);
        PeriodicalRegisterEntity periodicalRegisterEntity=registerMapper.selectOne(periodicalRegisterQueryWrapper);
        userEntity.setBalance(userEntity.getBalance()+periodicalRegisterEntity.getDeposit());
    }
}
