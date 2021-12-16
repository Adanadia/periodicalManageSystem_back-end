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
@TableName(value = "periodical_catalogue")
public class PeriodicalCatalogueEntity {
    @TableField(value = "periodical_name")
    private String periodicalName;
    @TableField(value = "ISSN")
    private String ISSN;
    @TableField(value = "CN")
    private String CN;
    @TableField(value = "mailing_code")
    private String mailingCode;
    @TableField(value = "public_cycle")
    private String publicCycle;
}
