package fr.eni.demo.bo.formation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "COMPUTER_COURSE")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COMPUTER_COURSE_SCIENCE", length = 255)
    private String filiere;

    @Column(name = "COMPUTER_COURSE_REFERENCE", unique = true, length = 255)
    private String reference;

    @Column(name = "COMPUTER_COURSE_TITLE", length = 255)
    private String titre;
}