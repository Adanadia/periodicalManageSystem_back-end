package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PeriodicalBorrowVO  {
    private String periodicalName;
    private int volume;
    private int year;
    private int stage;
    private double deposit;
}
