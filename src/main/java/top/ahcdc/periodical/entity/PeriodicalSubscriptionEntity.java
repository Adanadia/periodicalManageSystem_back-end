package top.ahcdc.periodical.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "periodical_subscription")
public class PeriodicalSubscriptionEntity {
    @TableField(value = "mailing_code")
    private String mailingCode;
    @TableField(value = "periodical_name")
    private String periodicalName;
    @TableField(value = "subscription_year")
    private int subscriptionYear;
}
