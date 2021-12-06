package top.ahcdc.periodical.vo;

import lombok.*;
import top.ahcdc.periodical.vo.integration.BookDestination_PeriodicalVO;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDestination_PeriodicalPageVO {
    List<BookDestination_PeriodicalVO> bookDestination_periodicalPageVOList;
}
