package fr.nflrae.assemblers;

import fr.nflrae.dtos.SquadDto;
import fr.nflrae.games.Squad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SquadAssembler {
    public SquadDto bo2dto(Squad squad){
        return new ModelMapper().map(squad,SquadDto.class);
    }

    public Squad dto2bo(SquadDto squadDto){
        return new ModelMapper().map(squadDto,Squad.class);
    }
}
