package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;

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
    @Column(name = "Points")
    private int points;

    @Min(0)
    @Column(name = "Wins")
    private int wins;

    @Min(0)
    @Column(name = "FastestLaps")
    private int fastestLaps;

    @Min(0)
    @ElementCollection
    private ArrayList<Integer> raceResults = new ArrayList<>();

    @Min(10)
    @Max(24)
    private int numberOfRaces;


    public DriverStandings(Driver driver, int numberOfRaces, int points) {
        setDriver(driver);
        setNumberOfRaces(numberOfRaces);
        setPoints(calculatePoints());
    }

    public int calculatePoints() {
        int[] pointsPerPosition = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        int totalPoints = 0;
        int currentWinPosition = 0;
        for (int position : raceResults) {
            if (position <= pointsPerPosition.length) {
                totalPoints += pointsPerPosition[position - 1];
                if (position == 1) {
                    currentWinPosition++;
                }
            }
        }
        setPoints(totalPoints);
        setWins(currentWinPosition);
        return totalPoints;
    }
}
