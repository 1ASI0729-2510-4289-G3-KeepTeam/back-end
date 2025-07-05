package com.acme.keeplo.platform.subscription.interfaces.rest;

import com.acme.keeplo.platform.subscription.domain.services.MembershipQueryService;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.MembershipResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/memberships", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Membership", description = "Endpoints for memberships")
public class MembershipController {

    private final MembershipQueryService membershipQueryService;

    public MembershipController(MembershipQueryService membershipQueryService) {
        this.membershipQueryService = membershipQueryService;
    }

    @GetMapping
    public List<MembershipResource> getAllMemberships() {
        return membershipQueryService.handle(new GetAllMemberships()).stream()
                .map(MembershipResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }
}
