package com.acme.keeplo.platform.wishlist.interfaces.rest;

import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionQueryService;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CollectionResourceFromEntityAssembler;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CreateCollectionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/collections")
@Tag(name = "Wishlist", description = "Operaciones sobre colecciones de deseos")
public class WishlistController {

    private final CollectionCommandService collectionCommandService;
    private final CollectionQueryService collectionQueryService;

    public WishlistController(CollectionCommandService collectionCommandService, CollectionQueryService collectionQueryService) {
        this.collectionCommandService = collectionCommandService;
        this.collectionQueryService = collectionQueryService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva colección")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Colección creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<CollectionResource> createCollection(@RequestBody CreateCollectionResource resource) {
        var command = CreateCollectionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = collectionCommandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var collection = result.get();
        var response = CollectionResourceFromEntityAssembler.toResourceFromEntity(collection);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{collectionId}")
    @Operation(summary = "Obtener una colección por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colección encontrada"),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada")
    })
    public ResponseEntity<CollectionResource> getCollectionById(@PathVariable Long collectionId) {
        var query = new GetCollectionByIdQuery(collectionId);
        var collection = collectionQueryService.handle(query);
        return collection
                .map(c -> ResponseEntity.ok(CollectionResourceFromEntityAssembler.toResourceFromEntity(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Obtener todas las colecciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de colecciones")
    })
    public ResponseEntity<List<CollectionResource>> getAllCollections() {
        var query = new GetAllCollectionsQuery();
        var collections = collectionQueryService.handle(query);
        var resources = collections.stream()
                .map(CollectionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}


