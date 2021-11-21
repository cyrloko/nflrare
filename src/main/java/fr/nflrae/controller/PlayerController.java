package fr.nflrae.controller;

import fr.nflrae.game.Player;
import fr.nflrae.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/all")
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @GetMapping("/addTest")
    public boolean ajouterTest(){
        Player test = new Player();
        test.setFirstname("test");
        test.setLastname("test");
        test.setNumber(0);
        test.setSquad("test");

        try {
            playerRepository.save(test);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
