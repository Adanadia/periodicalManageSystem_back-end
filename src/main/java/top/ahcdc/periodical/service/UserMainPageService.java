package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.vo.UserMainPageVO;

@Service
public interface UserMainPageService {
    UserMainPageVO getMainPageInfo(String userNum);
}
