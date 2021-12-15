//package top.ahcdc.periodical.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import top.ahcdc.periodical.entity.BorrowTabelEntity;
//import top.ahcdc.periodical.entity.PeriodicalContentEntity;
//import top.ahcdc.periodical.entity.PeriodicalRegisterEntity;
//import top.ahcdc.periodical.mapper.BorrowTableMapper;
//import top.ahcdc.periodical.mapper.PeriodicalContentMapper;
//import top.ahcdc.periodical.mapper.PeriodicalRegisterMapper;
//import top.ahcdc.periodical.mapper.UserMapper;
//import top.ahcdc.periodical.service.ReserveService;
//import top.ahcdc.periodical.utils.CalendarString;
//import top.ahcdc.periodical.vo.integration.PeriodicalBorrowVO;
//import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@Service
//public class ReserveServiceImpl implements ReserveService {
//    @Autowired
//    private BorrowTableMapper borrowTableMapper;
//    @Autowired
//    private PeriodicalRegisterMapper periodicalRegisterMapper;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private PeriodicalContentMapper periodicalContentMapper;
//    private CalendarString calendarString = new CalendarString();
//    public List<PeriodicalContentEntity> detailDisp(String pName, int year, int volume, int stage){
//        QueryWrapper<PeriodicalContentEntity> detailQuerywrapper=new QueryWrapper<>();
//        detailQuerywrapper.eq("periodical_name",pName)
//                .eq("volume",volume)
//                .eq("year",year)
//                .eq("stage",stage);
//        List<PeriodicalContentEntity> periodicalContentEntities= periodicalContentMapper.selectList(detailQuerywrapper);
//        return periodicalContentEntities;
//    }
//    public List<PeriodicalBorrowVO> getBorrow() {
//        List<BorrowTabelEntity> borrowTabelEntityList = borrowTableMapper.selectList(null);
//        PeriodicalRegisterEntity periodicalRegisterEntity;
//        QueryWrapper<PeriodicalRegisterEntity> periodicalRegisterQueryWrapper=new QueryWrapper<>();
//        List<PeriodicalBorrowVO> borrowed = new LinkedList<>();
//        for (BorrowTabelEntity borrowTabelEntity : borrowTabelEntityList) {
//            if (!(borrowTabelEntity.getReturnDate() == null || borrowTabelEntity.getReturnDate().isBlank())) continue;
//            periodicalRegisterQueryWrapper.eq("periodical_name",borrowTabelEntity.getPeriodicalName())
//                    .eq("volume",borrowTabelEntity.getVolume())
//                            .eq("year",borrowTabelEntity.getYear())
//                                    .eq("stage",borrowTabelEntity.getStage());
//            periodicalRegisterEntity=periodicalRegisterMapper.selectOne(periodicalRegisterQueryWrapper);
//            borrowed.add(new PeriodicalBorrowVO(
//                    borrowTabelEntity.getPeriodicalName(),
//                    borrowTabelEntity.getVolume(),
//                    borrowTabelEntity.getYear(),
//                    borrowTabelEntity.getStage(),
//                    periodicalRegisterEntity.getDeposit()
//            ));
//        }
//        return borrowed;
//    }
//    public List<PeriodicalBorrowVO> ReserveSearch(int type,String search_content){
//        List<PeriodicalContentEntity> periodicalContentEntities;//查询出来的期刊内容记录
//        List<PeriodicalBorrowVO> ret=getBorrow();//未借出的期刊信息
//        List<PeriodicalBorrowVO> searchRes=new LinkedList<>();//返回的期刊查询记录
//        QueryWrapper<PeriodicalContentEntity> periodicalContentQuery=new QueryWrapper<>();
//        if(type==1){
//            periodicalContentQuery.eq("paper_keyword_1",search_content).or().eq("paper_keyword_2",search_content)
//                    .or().eq("paper_keyword_3",search_content).or().eq("paper_keyword_4",search_content)
//                    .or().eq("paper_keyword_5",search_content);
//            periodicalContentEntities=periodicalContentMapper.selectList(periodicalContentQuery);
//            for(PeriodicalBorrowVO periodicalBorrowVO:ret){
//                for(PeriodicalContentEntity periodicalContentEntity:periodicalContentEntities){
//                    if(periodicalBorrowVO.getPeriodicalName().equals(periodicalContentEntity.getPeriodicalName())){
//                        searchRes.add(periodicalBorrowVO);
//                        break;
//                    }
//                }
//            }
//        }
//        else if(type==2){
//            periodicalContentQuery.like("paper_author",search_content);
//            periodicalContentEntities=periodicalContentMapper.selectList(periodicalContentQuery);
//            for(PeriodicalBorrowVO periodicalBorrowVO:ret){
//                for(PeriodicalContentEntity periodicalContentEntity:periodicalContentEntities){
//                    if(periodicalBorrowVO.getPeriodicalName().equals(periodicalContentEntity.getPeriodicalName())&&
//                            periodicalBorrowVO.getStage()==periodicalContentEntity.getStage()&&periodicalBorrowVO.getVolume()==periodicalContentEntity.getVolume()&&
//                            periodicalBorrowVO.getYear()==periodicalContentEntity.getYear()){
//                        searchRes.add(periodicalBorrowVO);
//                        break;
//                    }
//                }
//            }
//        }
//        return searchRes;
//    }
//}
