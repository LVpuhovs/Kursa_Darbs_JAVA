package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "RaceResultTable")
@Entity
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRR")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idR", referencedColumnName = "idR")
    private Race race;
    
    @ManyToOne
    @JoinColumn(name = "IdD", referencedColumnName = "IdD")
    private Driver driver;

    @Min(1)
    @Column(name = "position")
    private int position;
    
    @OneToOne(mappedBy = "raceResult")
    @PrimaryKeyJoinColumn
    private DriverStandings driverStandings;

    public RaceResult(Race race, Driver driver, int position) {
        setRace(race);
        setDriver(driver);
        setPosition(position);
    }
}
