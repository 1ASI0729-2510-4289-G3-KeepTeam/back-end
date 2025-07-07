package com.acme.keeplo.platform.subscription.interfaces.rest;

import com.acme.keeplo.platform.subscription.domain.services.MembershipQueryService;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.MembershipResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
/**
 * REST controller that exposes endpoints to retrieve membership information.
 */
@RestController
@RequestMapping(value = "/api/v1/memberships", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Membership", description = "Endpoints for memberships")
public class MembershipController {

    private final MembershipQueryService membershipQueryService;
    /**
     * Constructs the controller with the required membership query service.
     *
     * @param membershipQueryService The service used to retrieve membership data.
     */
    public MembershipController(MembershipQueryService membershipQueryService) {
        this.membershipQueryService = membershipQueryService;
    }
    /**
     * Retrieves all available memberships.
     *
     * @return A list of {@link MembershipResource} objects representing available memberships.
     */

    @GetMapping
    public List<MembershipResource> getAllMemberships() {
        return membershipQueryService.handle(new GetAllMemberships()).stream()
                .map(MembershipResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }
}
