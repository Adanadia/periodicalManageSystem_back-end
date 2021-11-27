package top.ahcdc.periodical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ahcdc.periodical.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{

}
