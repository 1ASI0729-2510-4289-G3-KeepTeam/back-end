package com.acme.keeplo.platform.integration.tests;

import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateWishResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.WishResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/wishes";
    }

    @Test
    public void createWish_ShouldReturnCreatedWish() {
        // Arrange
        CreateWishResource request = new CreateWishResource(
                "PS5",
                "playstation.com",
                "Consola PS5",
                "https://ps5.png",
                1L,
                false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateWishResource> entity = new HttpEntity<>(request, headers);

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl(), entity, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).contains("PS5");
        assertThat(response.getBody()).contains("playstation.com");
    }

    public void getWishById_ShouldReturnWish() {
        // Arrange
        CreateWishResource request = new CreateWishResource(
                "Nintendo Switch 2",
                "https://nintendo.com/switch",
                "Consola de Nintendo",
                "https://nintendoimg.png",
                1L,
                false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateWishResource> entity = new HttpEntity<>(request, headers);

        ResponseEntity<WishResource> createResponse =
                restTemplate.postForEntity(getBaseUrl(), entity, WishResource.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        Long wishId = createResponse.getBody().id();

        // Act
        ResponseEntity<WishResource> getResponse =
                restTemplate.getForEntity(getBaseUrl() + "/" + wishId, WishResource.class);

        // Assert
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().title()).isEqualTo("Nintendo Switch 2");
    }
}