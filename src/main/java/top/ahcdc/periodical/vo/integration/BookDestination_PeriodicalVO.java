package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDestination_PeriodicalVO {
    String userName;
    int volume;
    int year;
    int stage;
    String borrowDate;
    String dueDate;
}
