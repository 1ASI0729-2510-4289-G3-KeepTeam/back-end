package com.acme.keeplo.platform.acceptance.tests.steps;


import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CollectionSteps {

    private ResponseEntity<CollectionResource> response;

    @When("create a collection with the name {string}")
    public void create_a_collection_with_title(String nombre) {
        var resource = new CreateCollectionResource(nombre, false, null, 1L);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var entity = new HttpEntity<>(resource, headers);
        response = new TestRestTemplate().postForEntity("http://localhost:8080/api/v1/collections", entity, CollectionResource.class);
    }

    @Then("the collection should exist with the title {string}")
    public void collection_should_exist(String expectedTitle) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assert response.getBody() != null;
        assertThat(response.getBody().title()).isEqualTo(expectedTitle);
    }
}
