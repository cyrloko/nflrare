package fr.nflrae.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
@SequenceGenerator(name="seq", initialValue=5)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    @Column(length = 200)
    private String firstname;
    @Column(length = 200)
    private String lastname;
    @Column
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "squad_id", nullable = true)
    private Squad squad;

}
