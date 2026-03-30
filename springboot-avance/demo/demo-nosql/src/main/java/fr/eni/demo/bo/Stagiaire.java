package fr.eni.demo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stagiaire {
    @Field(name = "student_registration")
    private String immatriculation;

    @Field(name = "class_id")
    private String promotion;

}
