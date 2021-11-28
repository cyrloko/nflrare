package fr.nflrae.assemblers;

import fr.nflrae.dtos.PlayerDto;
import fr.nflrae.games.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerAssembler {


    public PlayerDto bo2dto(Player player){
        return new ModelMapper().map(player, PlayerDto.class);
    }

    public Player dto2bo(PlayerDto playerDto){
        return new ModelMapper().map(playerDto, Player.class);
    }




}
