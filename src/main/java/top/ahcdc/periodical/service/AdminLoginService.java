package top.ahcdc.periodical.service;

import top.ahcdc.periodical.entity.AdministratorEntity;
import top.ahcdc.periodical.entity.UserEntity;

public interface AdminLoginService {
    AdministratorEntity getPasswordByAdminNum(String adminNum);
}
