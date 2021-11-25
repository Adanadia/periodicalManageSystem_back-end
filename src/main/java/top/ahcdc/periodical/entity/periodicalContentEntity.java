package top.ahcdc.periodical.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "periodical_content")
public class periodicalContentEntity {
    @TableField(value = "periodical_name")
    private String periodicalName;
    @TableField(value = "paper_title")
    private String paperTitle;
    @TableField(value = "volume")
    private int volume;
    @TableField(value = "year")
    private int year;
    @TableField(value = "stage")
    private int stage;
    @TableField(value = "paper_keyword_1")
    private String paperKeyword1;
    @TableField(value = "paper_keyword_2")
    private String paperKeyword2;
    @TableField(value = "paper_keyword_3")
    private String paperKeyword3;
    @TableField(value = "paper_keyword_4")
    private String paperKeyword4;
    @TableField(value = "paper_keyword_5")
    private String paperKeyword5;
    @TableField(value = "periodical_page")
    private String periodicalPage;
}
