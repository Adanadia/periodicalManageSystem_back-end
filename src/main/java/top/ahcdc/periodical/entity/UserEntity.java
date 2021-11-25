package top.ahcdc.periodical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "user_table")
public class UserEntity {
    @TableId(value = "user_num",type = IdType.INPUT)
    private String userNum;
    @TableField(value = "user_name")
    private String userName;
    @TableField(value = "user_email")
    private String userEmail;
    @TableField(value = "password")
    private String password;
    @TableField(value = "balance")
    private double balance;
    @TableField(value = "user_profile")
    private String userProfiles;
}
