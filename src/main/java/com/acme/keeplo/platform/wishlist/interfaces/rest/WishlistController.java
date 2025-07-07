package com.acme.keeplo.platform.wishlist.interfaces.rest;

import com.acme.keeplo.platform.wishlist.domain.exceptions.MaxCollectionsLimitReachedException;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByParentCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsByUserId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllCollectionsQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionQueryService;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.UpdateCollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CollectionResourceFromEntityAssembler;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CreateCollectionCommandFromResourceAssembler;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.UpdateCollectionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing collections (wishlists).
 * Provides endpoints for creating, retrieving, updating, and deleting collections.
 */
@RestController
@RequestMapping(value = "/api/v1/collections")
@Tag(name = "Wishlist", description = "Operaciones sobre colecciones de deseos")
public class WishlistController {

    private final CollectionCommandService collectionCommandService;
    private final CollectionQueryService collectionQueryService;
    /**
     * Constructs a new WishlistController with the required command and query services.
     *
     * @param collectionCommandService the service for handling collection commands
     * @param collectionQueryService   the service for handling collection queries
     */
    public WishlistController(CollectionCommandService collectionCommandService, CollectionQueryService collectionQueryService) {
        this.collectionCommandService = collectionCommandService;
        this.collectionQueryService = collectionQueryService;
    }
    /**
     * Creates a new collection.
     *
     * @param resource the request body containing collection details
     * @return the created collection resource
     */
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
    /**
     * Retrieves a collection by its ID.
     *
     * @param collectionId the ID of the collection
     * @return the collection resource, or 404 if not found
     */
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


    /**
     * Retrieves all collections associated with a specific user.
     *
     * @param userId the ID of the user
     * @return list of collections for the user
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener colecciones por User ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de colecciones por user id")
    })
    public ResponseEntity<List<CollectionResource>> getCollectionByUserId(@PathVariable Long userId) {
        var query = new GetAllCollectionsByUserId(userId);
        var collections = collectionQueryService.handle(query);
        var resources = collections.stream()
                .map(CollectionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
    /**
     * Retrieves all collections with a specific parent collection.
     *
     * @param parentCollectionId the ID of the parent collection
     * @return list of child collections
     */
    @GetMapping("/parentCollection/{parentCollectionId}")
    @Operation(summary = "Obtener colecciones por parent collection ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de colecciones por parent collection id")
    })
    public ResponseEntity<List<CollectionResource>> getCollectionByParentCollectionId(@PathVariable Long parentCollectionId) {
        var query = new GetAllCollectionsByParentCollectionId(parentCollectionId);
        var collections = collectionQueryService.handle(query);
        var resources = collections.stream()
                .map(CollectionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Retrieves all collections in the system.
     *
     * @return list of all collections
     */
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
    /**
     * Deletes a collection by its ID.
     *
     * @param collectionId the ID of the collection to delete
     * @return HTTP 204 if deleted, or 404 if not found
     */

    @DeleteMapping("/{collectionId}")
    @Operation(summary = "Eliminar una colección por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Colección eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada")
    })
    public ResponseEntity<Void> deleteCollection(@PathVariable Long collectionId) {
        var result = collectionCommandService.deleteById(collectionId);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    /**
     * Updates an existing collection.
     *
     * @param collectionId the ID of the collection to update
     * @param resource     the request body with updated collection data
     * @return the updated collection resource, or 404 if not found
     */

    @PutMapping("/{collectionId}")
    @Operation(summary = "Actualizar una colección existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colección actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada")
    })
    public ResponseEntity<CollectionResource> updateCollection(@PathVariable Long collectionId, @RequestBody UpdateCollectionResource resource) {
        var command = UpdateCollectionCommandFromResourceAssembler.toCommandFromResource(collectionId, resource);
        var result = collectionCommandService.handle(command);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var collection = result.get();
        var response = CollectionResourceFromEntityAssembler.toResourceFromEntity(collection);
        return ResponseEntity.ok(response);
    }


}


