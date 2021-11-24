package top.ahcdc.periodical.service;

import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.UserEntity;

@Service
public interface UserService {
    public UserEntity getPasswordByUserNum(String userNum);
}
