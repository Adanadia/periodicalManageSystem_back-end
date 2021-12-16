package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.*;
import top.ahcdc.periodical.mapper.*;
import top.ahcdc.periodical.service.ReserveService;
import top.ahcdc.periodical.utils.CalendarString;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.ReservePageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalBorrowVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private BorrowTableMapper borrowTableMapper;
    @Autowired
    private PeriodicalRegisterMapper periodicalRegisterMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PeriodicalContentMapper periodicalContentMapper;
    @Autowired
    private PeriodicalReserveMapper periodicalReserveMapper;
    private CalendarString calendarString = new CalendarString();
    public void ReserveBooks(String pName, String userNum, int year, int stage, int volume){
        UserEntity userEntityForUpdate = new UserEntity();
        QueryWrapper<PeriodicalRegisterEntity> periodicalRegisterQueryWrapper = new QueryWrapper<>();
        QueryWrapper<UserEntity> userQueryWrapper = new QueryWrapper<>();
        periodicalRegisterQueryWrapper.eq("periodical_name", pName)
                .eq("year", year)
                .eq("stage", stage)
                .eq("volume", volume);
        PeriodicalRegisterEntity periodicalRegisterEntity = periodicalRegisterMapper.selectOne(periodicalRegisterQueryWrapper);
        userQueryWrapper.eq("user_num", userNum);
        UserEntity userEntity = userMapper.selectOne(userQueryWrapper);
        Calendar current = Calendar.getInstance();
        periodicalReserveMapper.insert(new PeriodicalReserveEntity(userNum,pName,volume,year,stage,calendarString.CToS(current)));
    }
    public ReservePageVO getReservePageInfo(String userNum){
        List<PeriodicalBorrowVO> ret = getBorrow();
        QueryWrapper<UserEntity> userInfoQuery = new QueryWrapper<>();
        userInfoQuery.eq("user_num", userNum);
        UserEntity user = userMapper.selectOne(userInfoQuery);
        UserInfoVO userInfo = new UserInfoVO(
                user.getUserNum(),
                user.getUserProfiles(),
                user.getBalance(),
                user.getUserEmail()
        );
        return new ReservePageVO(userInfo, ret);
    }
    public List<PeriodicalContentEntity> detailDisp(String pName, int year, int volume, int stage){
        QueryWrapper<PeriodicalContentEntity> detailQuerywrapper=new QueryWrapper<>();
        detailQuerywrapper.eq("periodical_name",pName)
                .eq("volume",volume)
                .eq("year",year)
                .eq("stage",stage);
        List<PeriodicalContentEntity> periodicalContentEntities= periodicalContentMapper.selectList(detailQuerywrapper);
        return periodicalContentEntities;
    }
    public List<PeriodicalBorrowVO> getBorrow() {
        List<BorrowTabelEntity> borrowTabelEntityList = borrowTableMapper.selectList(null);

        List<PeriodicalBorrowVO> borrowed = new LinkedList<>();
        for (BorrowTabelEntity borrowTabelEntity : borrowTabelEntityList) {
            QueryWrapper<PeriodicalRegisterEntity> periodicalRegisterQueryWrapper=new QueryWrapper<>();
            if (!(borrowTabelEntity.getReturnDate() == null || borrowTabelEntity.getReturnDate().isBlank())) continue;
            periodicalRegisterQueryWrapper.eq("periodical_name",borrowTabelEntity.getPeriodicalName())
                    .eq("volume",borrowTabelEntity.getVolume())
                            .eq("year",borrowTabelEntity.getYear())
                                    .eq("stage",borrowTabelEntity.getStage());
            PeriodicalRegisterEntity periodicalRegisterEntity=periodicalRegisterMapper.selectOne(periodicalRegisterQueryWrapper);
            borrowed.add(new PeriodicalBorrowVO(
                    borrowTabelEntity.getPeriodicalName(),
                    borrowTabelEntity.getVolume(),
                    borrowTabelEntity.getYear(),
                    borrowTabelEntity.getStage(),
                    periodicalRegisterEntity.getDeposit()
            ));
        }
        return borrowed;
    }
    public List<PeriodicalBorrowVO> ReserveSearch(int type,String search_content){
        List<PeriodicalContentEntity> periodicalContentEntities;//查询出来的期刊内容记录
        List<PeriodicalBorrowVO> ret=getBorrow();//借出的期刊信息
        List<PeriodicalBorrowVO> searchRes=new LinkedList<>();//返回的期刊查询记录
        QueryWrapper<PeriodicalContentEntity> periodicalContentQuery=new QueryWrapper<>();
        if(type==1){
            periodicalContentQuery.eq("paper_keyword_1",search_content).or().eq("paper_keyword_2",search_content)
                    .or().eq("paper_keyword_3",search_content).or().eq("paper_keyword_4",search_content)
                    .or().eq("paper_keyword_5",search_content);
            periodicalContentEntities=periodicalContentMapper.selectList(periodicalContentQuery);
            for(PeriodicalBorrowVO periodicalBorrowVO:ret){
                for(PeriodicalContentEntity periodicalContentEntity:periodicalContentEntities){
                    if(periodicalBorrowVO.getPeriodicalName().equals(periodicalContentEntity.getPeriodicalName())&&periodicalBorrowVO.getStage()==periodicalContentEntity.getStage()
                    &&periodicalBorrowVO.getVolume()==periodicalContentEntity.getVolume()&&periodicalBorrowVO.getYear()==periodicalContentEntity.getYear()){
                        searchRes.add(periodicalBorrowVO);
                        break;
                    }
                }
            }
        }
        else if(type==2){
            periodicalContentQuery.like("paper_author",search_content);
            periodicalContentEntities=periodicalContentMapper.selectList(periodicalContentQuery);
            for(PeriodicalBorrowVO periodicalBorrowVO:ret){
                for(PeriodicalContentEntity periodicalContentEntity:periodicalContentEntities){
                    if(periodicalBorrowVO.getPeriodicalName().equals(periodicalContentEntity.getPeriodicalName())&&
                            periodicalBorrowVO.getStage()==periodicalContentEntity.getStage()&&periodicalBorrowVO.getVolume()==periodicalContentEntity.getVolume()&&
                            periodicalBorrowVO.getYear()==periodicalContentEntity.getYear()){
                        searchRes.add(periodicalBorrowVO);
                        break;
                    }
                }
            }
        }
        return searchRes;
    }
    public void DeleteReserve(String pNname, String userNum, int year, int stage, int volume){
        QueryWrapper<PeriodicalReserveEntity> periodicalReserveQueryWrapper=new QueryWrapper<>();
        periodicalReserveQueryWrapper.eq("user_num",userNum)
                .eq("periodical_name",pNname)
                .eq("volume",volume)
                .eq("year",year)
                .eq("stage",stage);
        periodicalReserveMapper.delete(periodicalReserveQueryWrapper);
    }
    public boolean getByUserNum(String userNum){
        QueryWrapper<PeriodicalReserveEntity> periodicalReserveQueryWrapper= new QueryWrapper<>();
        periodicalReserveQueryWrapper.eq("user_num",userNum);
        PeriodicalReserveEntity periodicalReserveEntity=periodicalReserveMapper.selectOne(periodicalReserveQueryWrapper);
        if(periodicalReserveEntity!=null) return true;
        else return false;
    }
}
