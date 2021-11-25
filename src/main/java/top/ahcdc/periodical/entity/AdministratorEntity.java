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
@TableName(value = "administrator")
public class AdministratorEntity {
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    @TableField(value = "password")
    private String password;
}
