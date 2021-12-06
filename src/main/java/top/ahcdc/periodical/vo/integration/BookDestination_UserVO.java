package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDestination_UserVO {
    String periodicalName;
    int volume;
    int stage;
    int year;
    String borrowDate;
    String dueDate;
}
