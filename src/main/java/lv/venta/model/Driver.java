package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;



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
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Only letters and space")
    @Size(min = 3, max = 20)
    @Column(name = "Name")
    private String name;

    @NonNull
    @Pattern(regexp = "^[A-Za-z]+(?:-[A-Za-z]+)?$", message = "Only letters and dash")
    @Size(min = 3, max = 20)
    @Column(name = "Surname")
    private String surname;

    @NotNull
    @Min(1)
    @Max(99)
    @Column(name = "DriverNumber")
    private int number;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "idT")
    private Team team;


    public Driver(String name, String surname, int number){
        setName(name);
        setSurname(surname);
        setNumber(number);

    }
}
