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
    @JoinColumn(name = "race_id", referencedColumnName = "idR")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "IdD")
    private Driver driver;

    @NotNull
    @Min(1)
    @Column(name = "position")
    private int position;

    public RaceResult(Race race, Driver driver, int position) {
        setRace(race);
        setDriver(driver);
        setPosition(position);
    }
}
