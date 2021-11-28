package fr.nflrae.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "squad")
@SequenceGenerator(name="seq_squad", initialValue=1)
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_squad")
    private int id;
    @Column(length = 200, name = "name")
    private String name;
    @Column(name = "palmares")
    private String palmares;
    @OneToMany
    @JoinColumn(name = "squad_id")
    private List<Player> players;

    public String toString(){return "name: " + this.getName() + " palmares: " + this.getPalmares();}

}
