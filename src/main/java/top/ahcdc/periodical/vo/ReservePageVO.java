package top.ahcdc.periodical.vo;

import top.ahcdc.periodical.vo.integration.PeriodicalBorrowVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.List;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ReservePageVO {
    UserInfoVO userInfoVO;
    List<PeriodicalBorrowVO> periodicalBorrowVOList;
}
