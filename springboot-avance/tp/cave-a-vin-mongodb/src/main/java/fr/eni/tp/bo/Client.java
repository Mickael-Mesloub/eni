package fr.eni.tp.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Client {
    @Field(name = "login")
    private String pseudo;

    @Field(name = "quantity_ordered")
    private Integer quantiteCommandee;
}
