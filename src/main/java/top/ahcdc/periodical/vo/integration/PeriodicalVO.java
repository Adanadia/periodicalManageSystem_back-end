package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodicalVO {
    private String ISSN;
    private String periodicalName;
    private String periodicalCover;
    private int volume;
    private int year;
    private int stage;
}
