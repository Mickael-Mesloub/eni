package fr.eni.demo.bo.formation;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TRAINER")
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LAST_NAME", length = 50)
    private String nom;

    @Column(name = "FIRST_NAME", length = 50)
    private String prenom;

    @Column(name = "EMAIL", unique = true, length = 255)
    private String email;

    @Column(name = "REGISTRATION", unique = true, length = 50)
    private String immatriculation;

    @ManyToMany
    @JoinTable(name = "COMPUTER_COURSES_PROVIDED",
            joinColumns = {@JoinColumn(name = "TRAINER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")})
    private @Builder.Default List<Cours> listeCours = new ArrayList<>();
}