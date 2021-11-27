package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.BorrowTabelEntity;
import top.ahcdc.periodical.entity.PeriodicalRegisterEntity;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.mapper.BorrowTableMapper;
import top.ahcdc.periodical.mapper.PeriodicalRegisterMapper;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.BorrowService;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowTableMapper borrowTableMapper;
    @Autowired
    private PeriodicalRegisterMapper periodicalRegisterMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public BorrowPageVO getBorrowPageInfo(String userNum) {
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
        QueryWrapper<UserEntity> userInfoQuery = new QueryWrapper<>();
        userInfoQuery.eq("user_num",userNum);
        UserEntity user = userMapper.selectOne(userInfoQuery);
        UserInfoVO userInfo = new UserInfoVO(
                user.getUserNum(),
                user.getUserProfiles(),
                user.getBalance(),
                user.getUserEmail()
        );
        return new BorrowPageVO(userInfo,ret);
    }

    @Override
    public String getCover(String name, int year, int volume, int stage) {
        QueryWrapper<PeriodicalRegisterEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("periodical_name",name);
        queryWrapper.eq("year",year);
        queryWrapper.eq("volume",volume);
        queryWrapper.eq("stage",stage);
        return periodicalRegisterMapper.selectOne(queryWrapper).getPeriodicalCover();
    }

}
