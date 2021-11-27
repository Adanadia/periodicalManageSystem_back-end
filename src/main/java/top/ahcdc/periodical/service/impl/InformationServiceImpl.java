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

import java.util.LinkedList;
import java.util.List;

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
    @Override
    public UserMainPageVO getMainPageInfo(String userNum){
        QueryWrapper<BorrowTabelEntity> borrowQueryWrapper= new QueryWrapper<>();
        borrowQueryWrapper.eq("user_num",userNum);
        List<BorrowTabelEntity> userBorrowList = borrowTableMapper.selectList(borrowQueryWrapper);
        List<PeriodicalVO> periodicalVOList = new LinkedList<>();
        for(BorrowTabelEntity list:userBorrowList){
            QueryWrapper<PeriodicalCatalogueEntity> catalogueEntityQueryWrapper = new QueryWrapper<>();
            catalogueEntityQueryWrapper.eq("periodical_name",list.getPeriodicalName());
            QueryWrapper<PeriodicalRegisterEntity> registerEntityQueryWrapper = new QueryWrapper<>();
            registerEntityQueryWrapper.eq("periodical_name",list.getPeriodicalName());
            registerEntityQueryWrapper.eq("year",list.getYear());
            registerEntityQueryWrapper.eq("volume",list.getVolume());
            registerEntityQueryWrapper.eq("stage",list.getStage());
            periodicalVOList.add(new PeriodicalVO(
                    catalogueMapper.selectOne(catalogueEntityQueryWrapper).getISSN(),
                    list.getPeriodicalName(),
                    registerMapper.selectOne(registerEntityQueryWrapper).getPeriodicalCover(),
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
}
