package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodicalNotBorrowVO {
    private String periodicalName;
    private String periodicalCover;
    private int volume;
    private int year;
    private int stage;
    private double deposit;
}
