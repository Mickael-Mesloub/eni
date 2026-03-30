package fr.eni.demo.bo.clecomposite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoursId implements Serializable {
    @Field
    private String reference;

    @Field(name = "computer_science_course")
    private String filiere;

}
