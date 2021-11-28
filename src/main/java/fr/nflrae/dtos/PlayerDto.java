package fr.nflrae.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerDto {
    private int id;
    private String firstname;
    private String lastname;
    private Integer number;
    private SquadDto squad;
}
