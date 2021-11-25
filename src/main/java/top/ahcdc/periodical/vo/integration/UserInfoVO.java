package top.ahcdc.periodical.vo.integration;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoVO {
    private String userNum;
    private String userProfile;
    private double balance;
    private String userEmail;
}
