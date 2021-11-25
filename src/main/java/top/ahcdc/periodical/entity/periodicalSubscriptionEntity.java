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
public class periodicalSubscriptionEntity {
    @TableField(value = "mail_code")
    private String mailCode;
    @TableField(value = "periodical_name")
    private String periodicalName;
    @TableField(value = "subscription_year")
    private String subscriptionYear;
}
