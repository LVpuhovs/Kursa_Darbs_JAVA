package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "DriversTable")
@Entity
public class Driver {

    @Setter(AccessLevel.NONE)
    @Column(name = "IdD")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idD;

    @NonNull
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Only letters")
    @Size(min = 3, max = 20)
    @Column(name = "Name")
    private String name;

    @NonNull
    @Pattern(regexp = "^[A-Za-z]+(?:-[A-Za-z]+)?$", message = "Only letters and dash")
    @Size(min = 3, max = 20)
    @Column(name = "Surname")
    private String surname;

    @Min(1)
    @Max(99)
    @Column(name = "DriverNumber")
    private int number;

    @ManyToOne
    @JoinColumn(name = "idT")
    @ToString.Exclude
    private Team team;
    
    @Min(0)
    @Column(name = "TotalPoints")
    private int totalPoints;

    @Min(0)
    @Column(name = "Wins")
    private int wins;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<DriverStandings> driverStandings;

    @Min(0)
    @Column(name = "DriverTotalPosition")
    private int driverTotalPosition;

    @Min(0)
    @Column(name = "TotalWins")
    private int totalWins;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<RaceResult> raceResults;

    public Driver(String name, String surname, int number){
        setName(name);
        setSurname(surname);
        setNumber(number);
    }
}
