package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

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

    @OneToMany(mappedBy = "race")
    private Collection<RaceResult> raceResults;

    public Race(String raceName) {
        setRaceName(raceName);
    }
}
