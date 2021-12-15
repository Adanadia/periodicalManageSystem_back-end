package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.PeriodicalContentEntity;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.ReservePageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalBorrowVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Service

public interface ReserveService {
    ReservePageVO getReservePageInfo(String userNum);
    List<PeriodicalBorrowVO> getBorrow();//借出的期刊
    List<PeriodicalBorrowVO> ReserveSearch(int type,String search_content);
    List<PeriodicalContentEntity> detailDisp(String pName, int year, int volume, int stage);
    void ReserveBooks(String pNname, String userNum, int year, int stage, int volume);
}
