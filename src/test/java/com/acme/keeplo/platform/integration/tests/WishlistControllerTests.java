package com.acme.keeplo.platform.integration.tests;

import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishlistControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/collections";
    }

    @Test
    public void createCollection_thenGetById_ShouldReturnSameCollection() {
        // Arrange: crear una nueva colección
        CreateCollectionResource createRequest = new CreateCollectionResource(
                "Gaming Wishlist",
                false,
                null,
                1L
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCollectionResource> entity = new HttpEntity<>(createRequest, headers);

        // Act
        ResponseEntity<CollectionResource> postResponse =
                restTemplate.postForEntity(getBaseUrl(), entity, CollectionResource.class);

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody()).isNotNull();
        Assertions.assertNotNull(postResponse.getBody());
        Long collectionId = postResponse.getBody().id();

        ResponseEntity<CollectionResource> getResponse =
                restTemplate.getForEntity(getBaseUrl() + "/" + collectionId, CollectionResource.class);

        // Assert
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        Assertions.assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().title()).isEqualTo("Gaming Wishlist");
    }
}
