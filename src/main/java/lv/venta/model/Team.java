package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,19}$", message = "Only letters and space")
    @Size(min = 3, max = 20)
    @Column(name = "TeamName")
    private String teamName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver1_id", referencedColumnName = "IdD")
    private Driver driver1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver2_id", referencedColumnName = "IdD")
    private Driver driver2;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Collection<TeamStandings> teamStandings;

    @Min(0)
    @Column(name = "TotalTeamPoints")
    private int totalTeamPoints;

    @Min(0)
    @Column(name = "TeamTotalPosition")
    private int teamTotalPosition;

    public Team(String teamName, Driver driver1, Driver driver2) {
        setTeamName(teamName);
        setDriver1(driver1);
        setDriver2(driver2);
    }

    public void setDriver1(Driver driver1) {
        this.driver1 = driver1;
        if (driver1 != null) {
            driver1.setTeam(this);
        }
    }

    public void setDriver2(Driver driver2) {
        this.driver2 = driver2;
        if (driver2 != null) {
            driver2.setTeam(this);
        }
    }
}
