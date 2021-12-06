package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.vo.BookDestination_PeriodicalPageVO;
import top.ahcdc.periodical.vo.integration.BookDestination_PeriodicalVO;
import top.ahcdc.periodical.vo.BookDestination_UserPageVO;

@Service
public interface BookDestinationService {
    BookDestination_UserPageVO getBookDestinationPage_User(String search_content);
    BookDestination_PeriodicalPageVO getBookDestination_Periodical(String search_content);
}
