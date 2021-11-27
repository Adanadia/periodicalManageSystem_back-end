package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

import java.util.List;

@Service
public interface BorrowService {
    BorrowPageVO getBorrowPageInfo(String userNum);
    String getCover(String name,int year,int volume,int stage);
}
