package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "TeamStandings")
@Entity
public class TeamStandings {
    @Setter(AccessLevel.NONE)
    @Column(name = "IdTT")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTT;

    @NotNull
    @ManyToOne
    private Team team;

    @Min(0)
    private int points;

    @ManyToOne
    @ToString.Exclude
    private Race race;

    public TeamStandings(Team team, Race race) {
        setTeam(team);
        setRace(race);
        calculateTeamPoints();
    }
    
    public void calculateTeamPoints() {
        int teamPoints = 0;

        if (team.getDriver1() != null) {
            for (DriverStandings standings : team.getDriver1().getDriverStandings()) {
                if (standings.getRaceResult().getRace().equals(race))
                    teamPoints += standings.getPointsPerRace();
            }
        }

        if (team.getDriver2() != null) {
            for (DriverStandings standings : team.getDriver2().getDriverStandings()) {
                if (standings.getRaceResult().getRace().equals(race))
                    teamPoints += standings.getPointsPerRace();
            }
        }
        setPoints(teamPoints);
        getTeam().setTotalTeamPoints(getTeam().getTotalTeamPoints() + teamPoints);
    }
}