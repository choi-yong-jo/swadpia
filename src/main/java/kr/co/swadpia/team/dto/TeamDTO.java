package kr.co.swadpia.team.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TeamDTO {

    @Column(length = 10)
    private String teamId;

    @Column(length = 20)
    private String teamNm;

    private String description;

}
