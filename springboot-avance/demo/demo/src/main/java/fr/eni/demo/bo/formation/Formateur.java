package fr.eni.demo.bo.formation;


import fr.eni.demo.bo.Employe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "TRAINER")
public class Formateur extends Employe {
    @ManyToMany
    @JoinTable(name = "COMPUTER_COURSES_PROVIDED",
            joinColumns = {@JoinColumn(name = "TRAINER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")})
    private @Builder.Default List<Cours> listeCours = new ArrayList<>();

}