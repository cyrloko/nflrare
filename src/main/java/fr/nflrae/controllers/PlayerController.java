package fr.nflrae.controllers;

import fr.nflrae.assemblers.PlayerAssembler;
import fr.nflrae.assemblers.SquadAssembler;
import fr.nflrae.dtos.PlayerDto;
import fr.nflrae.dtos.SquadDto;
import fr.nflrae.games.Player;
import fr.nflrae.games.Squad;
import fr.nflrae.repositories.PlayerRepository;
import fr.nflrae.repositories.SquadRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    SquadRepository squadRepository;
    @Autowired
    PlayerAssembler playerAssembler;
    @Autowired
    SquadAssembler squadAssembler;


    @GetMapping("/all")
    public List<PlayerDto> getAllPlayers(){
        List<Player> players = playerRepository.findAll();

        return (List<PlayerDto>) players.stream().map(player -> playerAssembler.bo2dto(player)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlayerDto getPlayerById(@PathVariable("id") Integer id) throws IllegalArgumentException{
        Player player = playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return playerAssembler.bo2dto(player);
    }

    @GetMapping("/addTest")
    public boolean ajouterTest(){
        Squad squadTest = new Squad();
        squadTest.setName("test");
        squadTest.setPalmares("test");

        try {
            squadRepository.save(squadTest);
        }catch (Exception e){
            return false;
        }

        Player test = new Player();
        test.setFirstname("test");
        test.setLastname("test");
        test.setNumber(0);
        test.setSquad(squadTest);

        try {
            playerRepository.save(test);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @PostMapping(path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerDto create(@RequestBody PlayerDto playerDto) throws Exception {
        Player playerSaved = playerRepository.save(playerAssembler.dto2bo(playerDto));
        if (playerSaved == null) {
            throw new Exception("Le joueur n'a pas pu être ajouté");
        } else {
            return playerAssembler.bo2dto(playerSaved);
        }
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerDto update(@RequestBody PlayerDto playerDto) throws Exception {
        Optional<Player> optionalPlayer = playerRepository.findById(playerDto.getId());
        Player player = optionalPlayer.orElse(null);
        if(player != null){
            player.setFirstname(playerDto.getFirstname() == null ? player.getFirstname() : playerDto.getFirstname());
            player.setLastname(playerDto.getLastname() == null ? player.getLastname() : playerDto.getLastname());
            player.setNumber(playerDto.getNumber() == null ? player.getNumber() : playerDto.getNumber());
            try {
                SquadDto squadDto = playerDto.getSquad();
                Squad squad = squadRepository.findById(squadDto.getId()).orElseThrow(IllegalArgumentException::new);
                player.setSquad(squad);
            }catch (Exception e){
                throw new Exception(e);
            }

            return playerAssembler.bo2dto(playerRepository.save(player));
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public PlayerDto delete(@PathVariable Integer id){
        Player player = playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        playerRepository.delete(player);
        return playerAssembler.bo2dto(player);
    }

}
