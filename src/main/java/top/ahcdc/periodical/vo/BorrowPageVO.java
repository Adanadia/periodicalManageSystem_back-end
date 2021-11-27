package top.ahcdc.periodical.vo;

import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;
import top.ahcdc.periodical.vo.integration.UserInfoVO;

import java.util.List;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowPageVO {
    UserInfoVO userInfo;
    List<PeriodicalNotBorrowVO> periodicalVOList;
}
