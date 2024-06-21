package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "DriverStandings")
@Entity
public class DriverStandings {
    @Setter(AccessLevel.NONE)
    @Column(name = "IdDS")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDS;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idD")
    private Driver driver;

    @Min(0)
    @Column(name = "PointsPerRace")
    private int pointsPerRace;

    @Min(0)
    @Column(name = "Wins")
    private int wins;

    @Column(name = "FastestLap")
    private boolean fastestLap;

    @OneToOne
    private RaceResult raceResult;

    @Min(1)
    @Max(24)
    private int numberOfTheRace;


    public DriverStandings(Driver driver, RaceResult raceResult , int numberOfTheRace) {
        setDriver(driver);
        setNumberOfTheRace(numberOfTheRace);
        setRaceResult(raceResult);
        calculatePoints();
    }

    public void calculatePoints() {
        int[] pointsPerPosition = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        int position = raceResult.getPosition();
        int points = 0;
        
        if(position > 0 && position <= pointsPerPosition.length) {
        	points = pointsPerPosition[position - 1];
        	if(position == 1) wins++;
        }
        if(isFastestLap()) points++;
        
        setPointsPerRace(points);
        driver.setTotalPoints(driver.getTotalPoints() + points);
    }
}
