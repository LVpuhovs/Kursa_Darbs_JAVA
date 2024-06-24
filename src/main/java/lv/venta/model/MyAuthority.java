package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "AuthorityTable")

@Entity
public class MyAuthority{
    @Column(name =  "Ida")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ida;

    @NotNull
    @Pattern(regexp = "[A-Z]{4,5}", message = "Only letters and numbers")
    @Column(name = "Title")
    private String title;

    @ManyToMany
    @JoinTable(name = "UserAuthorityTable",
            joinColumns = @JoinColumn(name = "Ida"),
            inverseJoinColumns = @JoinColumn(name = "Idu"))
    @ToString.Exclude
    private Collection<MyUser> users = new ArrayList<>();


    public void addAuthority(MyUser user) {
        if(!users.contains(user))
            users.add(user);
    }

    public void remove (MyUser user) {
        if(users.contains(user))
            users.remove(user);
    }

    public MyAuthority(String title)
    {
        setTitle(title);
    }
}
