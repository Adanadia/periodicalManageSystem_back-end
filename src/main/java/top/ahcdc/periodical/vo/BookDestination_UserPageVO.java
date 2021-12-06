package top.ahcdc.periodical.vo;

import lombok.*;
import top.ahcdc.periodical.vo.integration.BookDestination_UserVO;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDestination_UserPageVO {
    List<BookDestination_UserVO> bookDestination_userList;
}
