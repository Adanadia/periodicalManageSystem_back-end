package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.PeriodicalContentEntity;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Service
public interface BorrowService {
    BorrowPageVO getBorrowPageInfo(String userNum);
    String getCover(String name,int year,int volume,int stage);
    List<PeriodicalNotBorrowVO> getNotBorrow();//未借出的期刊
    List<PeriodicalNotBorrowVO> BorrowSearch(int type,String search_content);
    PeriodicalContentEntity detailDisp(String pName, int year, int volume, int stage);
    boolean borrowBooks(String pNname,String userNum,int year,int stage,int volume);
    String CToS(Calendar c);

}
