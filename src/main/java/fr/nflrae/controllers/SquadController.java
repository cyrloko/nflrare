package fr.nflrae.controllers;

import fr.nflrae.assemblers.SquadAssembler;
import fr.nflrae.dtos.SquadDto;
import fr.nflrae.games.Squad;
import fr.nflrae.repositories.SquadRepository;
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
@RequestMapping("/squad")
public class SquadController {

    @Autowired
    SquadRepository squadRepository;
    @Autowired
    SquadAssembler squadAssembler;

    @GetMapping("/all")
    public List<SquadDto> getAllSquads(){

        List<Squad> squads = squadRepository.findAll();

        return (List<SquadDto>) squads.stream().map(squad ->squadAssembler.bo2dto(squad)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SquadDto getSquadById (@PathVariable("id") Integer id) throws IllegalArgumentException{
        Squad squad = squadRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return squadAssembler.bo2dto(squad);
    }

    @GetMapping("/addTest")
    public boolean ajouterTest(){
        Squad test = new Squad();
        test.setName("test");
        test.setPalmares("test");

        try {
            squadRepository.save(test);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @PostMapping(path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SquadDto create(@RequestBody SquadDto squadDto) throws Exception {
        Squad squadSaved = squadRepository.save(squadAssembler.dto2bo(squadDto));
        if (squadSaved == null) {
            throw new Exception("L'équipe n'a pas pu être ajoutée");
        } else {
            return squadAssembler.bo2dto(squadSaved);
        }
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SquadDto update(@RequestBody SquadDto squadDto){
        Optional<Squad> optionalSquad = squadRepository.findById(squadDto.getId());
        Squad squad = optionalSquad.orElse(null);
        if(squad != null){
            squad.setPalmares(squadDto.getPalmares() == null ? squad.getPalmares() : squadDto.getPalmares());
            squad.setName(squadDto.getName() == null ? squad.getName() : squadDto.getName());

            return squadAssembler.bo2dto(squadRepository.save(squad));
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public SquadDto delete(@PathVariable("id") Integer id){
        Squad squad = squadRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        squadRepository.delete(squad);
        return squadAssembler.bo2dto(squad);
    }
}
