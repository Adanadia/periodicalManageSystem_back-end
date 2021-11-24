package top.ahcdc.periodical.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "borrow_table")
public class BorrowTabelEntity {
    @MppMultiId
    @TableField("volume")
    private int volume;
    @MppMultiId
    @TableField("year")
    private int year;
    @MppMultiId
    @TableField("stage")
    private int stage;
    @MppMultiId
    @TableField("periodical_name")
    private String periodicalName;
    @TableField("user_num")
    private String userNum;
    @TableField("borrow_date")
    private String borrowDate;
    @TableField("due_date")
    private String dueDate;
    @TableField("return_date")
    private String returnDate;
}
