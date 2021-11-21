package fr.nflrae.game;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Entity
@Table(name = "player")
@SequenceGenerator(name="seq", initialValue=5)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    @Column(length = 200, name = "firstname")
    private String firstname;
    @Column(length = 200, name = "lastname")
    private String lastname;
    @Column(name = "number")
    private int number;
    @Column(length = 200, name = "squad")
    private String squad;

    public Player(){
    }
}
