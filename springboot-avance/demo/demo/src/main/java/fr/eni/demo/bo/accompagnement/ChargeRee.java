package fr.eni.demo.bo.accompagnement;


import fr.eni.demo.bo.Employe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "SCHOOL_BUSINESS_OFFICER")
public class ChargeRee extends Employe {

    @Column(name = "OFFICE_NUMBER", length = 50)
    private String numeroBureau;

}