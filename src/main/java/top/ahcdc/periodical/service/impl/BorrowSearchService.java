package top.ahcdc.periodical.service.impl;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
@Service
public interface BorrowSearchService {
     PeriodicalNotBorrowVO getBorrowSearchVO(int type,String search_content);

}
