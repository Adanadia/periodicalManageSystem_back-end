package top.ahcdc.periodical.vo.integration;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveVO {
    private String periodicalName;
    private  String reserveDate;
    private int volume;
    private int year;
    private int stage;
}
