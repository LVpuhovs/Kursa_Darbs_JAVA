package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "TeamTable")
@Entity
public class Team {

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

    @ManyToOne
    @JoinColumn(name = "idD")
    private Driver drivers;

    public Team(String teamName, Driver drivers) {
        setTeamName(teamName);
        setDrivers(drivers);
    }
}
