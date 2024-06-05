package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "DriverStandings")
@Entity
public class TeamStandings {
    @Setter(AccessLevel.NONE)
    @Column(name = "IdDT")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDT;

    @NotNull
    @ManyToOne
    private Team team;

    @Min(0)
    private int points;

    public TeamStandings(Team team, int points) {
        setTeam(team);
        setPoints(points);
    }
}
