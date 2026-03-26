package fr.eni.cave.bo;

import fr.eni.cave.bo.client.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j

public class TestClient {
    @Test
    public void test_ClientAllAttributes() {
        Client client = Client.builder()
                .pseudo("Jeanjj")
                .password("azerty")
                .nom("Jeanj")
                .prenom("Jean")
                .build();

        log.info(client.toString());

        assertThat(client.getPseudo()).isEqualTo("Jeanjj");
        assertThat(client.getNom()).isEqualTo("Jeanj");
        assertThat(client.getPrenom()).isEqualTo("Jean");
    }
}
