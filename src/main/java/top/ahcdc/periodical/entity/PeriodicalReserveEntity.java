package top.ahcdc.periodical.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value="periodical_reserve")
public class PeriodicalReserveEntity {
    @TableField(value="user_num")
    private String userNum;
    @TableField(value = "periodical_name")
    private String periodicalName;
    @TableField(value="volume")
    private int volume;
    @TableField(value="year")
    private int year;
    @TableField(value="stage")
    private int stage;
    @TableField(value="reserve_date")
    private String reserveDate;
}
