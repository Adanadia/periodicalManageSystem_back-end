package top.ahcdc.periodical.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "periodical_register")
public class PeriodicalRegisterEntity {
    @MppMultiId
    @TableField(value = "periodical_name")
    private String periodicalName;
    @MppMultiId
    @TableField(value = "volume")
    private int volume;
    @MppMultiId
    @TableField(value = "year")
    private int year;
    @MppMultiId
    @TableField(value = "stage")
    private int stage;
    @TableField(value = "periodical_cover")
    private String periodicalCover;
}
