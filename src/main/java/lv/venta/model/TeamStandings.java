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
        calculateTeamPoints();
    }
    
    public void calculateTeamPoints() {
    	int totalPoints = 0;
    	if(team.getDriver1() != null) {
    		for(DriverStandings standings : team.getDriver1().getDriverStandings())
    			totalPoints += standings.getPointsPerRace();
    	}
    	if(team.getDriver2() != null) {
    		for(DriverStandings standings : team.getDriver2().getDriverStandings())
    			totalPoints += standings.getPointsPerRace();
    	}
    	setPoints(totalPoints);
    }
}
