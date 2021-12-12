package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.vo.UserMainPageVO;

@Service
public interface InformationService {
    UserMainPageVO getMainPageInfo(String userNum);
    String returnPeriodical(String periodical_name,int stage,int volume,int year,String user_num);
    void ReturnMoney(String periodical_name,int stage,int volume,int year,String user_num);
}
