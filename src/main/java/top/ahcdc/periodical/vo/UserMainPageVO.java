package top.ahcdc.periodical.vo;

import lombok.*;
import top.ahcdc.periodical.vo.integration.PeriodicalVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMainPageVO {
    private List<PeriodicalVO> periodical;
    private UserInfoVO user_table;
}
