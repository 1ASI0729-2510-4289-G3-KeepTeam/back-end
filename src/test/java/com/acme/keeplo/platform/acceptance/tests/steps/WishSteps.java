package com.acme.keeplo.platform.acceptance.tests.steps;

import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateWishResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.WishResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishSteps {
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private ResponseEntity<WishResource> response;
    private Long collectionId;

    @Given("an existing collection with ID {long}")
    public void an_existing_collection_with_id(Long id) {
        // In real tests, you would POST to create it
        this.collectionId = id;
    }

    @When("I create a wish titled {string}")
    public void i_create_a_wish_titled(String title) {
        CreateWishResource resource = new CreateWishResource(
                title,
                "https://store.nintendo.com/switch",
                "Video game console",
                "https://images/nintendo.jpg",
                collectionId,
                false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateWishResource> request = new HttpEntity<>(resource, headers);

        response = restTemplate.postForEntity("http://localhost:8080/api/v1/wishes", request, WishResource.class);
    }

    @Then("the wish should be saved correctly with title {string}")
    public void the_wish_should_be_saved_correctly_with_title(String expectedTitle) {
        assert response.getBody() != null;
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo(expectedTitle);
    }
}