package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "RaceTable")
@Entity
public class Race {
    @Setter(AccessLevel.NONE)
    @Column(name = "IdR")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idR;

    @NotNull
    @Column(name = "raceName")
    private String raceName;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceResult> raceResults;
    
    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TeamStandings> teamStandings;

    public Race(String raceName) {
        setRaceName(raceName);
    }
}
