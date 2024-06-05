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
public class DriverStandings {
    @Setter(AccessLevel.NONE)
    @Column(name = "IdDS")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDS;

    @NotNull
    @ManyToOne
    private Driver driver;

    @Min(0)
    private int points;

    public DriverStandings(Driver driver, int points) {
        setDriver(driver);
        setPoints(points);
    }
}
