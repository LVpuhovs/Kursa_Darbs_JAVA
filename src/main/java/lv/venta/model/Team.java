package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "TeamTable")
@Entity
public class Team{

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idT")
    private int idT;

    @NonNull
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Only letters and space")
    @Size(min = 3, max = 20)
    @Column(name = "TeamName")
    private String teamName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver1_id", referencedColumnName = "IdD")
    private Driver driver1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver2_id", referencedColumnName = "IdD")
    private Driver driver2;

    public Team(String teamName, Driver driver1, Driver driver2) {
        setTeamName(teamName);
        setDriver1(driver1);
        setDriver2(driver2);
    }
}
