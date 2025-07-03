package com.acme.keeplo.platform.wishlist.interfaces.rest;

import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllWishesByCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetWishById;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishCommandService;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishQueryService;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.*;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.WishResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishes")
@Tag(name = "Wishes", description = "Operaciones sobre deseos")
public class WishController {

    private final WishCommandService wishCommandService;
    private final WishQueryService wishQueryService;

    public WishController(WishCommandService wishCommandService, WishQueryService wishQueryService) {
        this.wishCommandService = wishCommandService;
        this.wishQueryService = wishQueryService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo deseo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Deseo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<WishResource> createWish(@RequestBody CreateWishResource resource) {
        var command = CreateWishCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = wishCommandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var wish = result.get();
        var response = WishResourceFromEntityAssembler.toResourceFromEntity(wish);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{wishId}")
    @Operation(summary = "Obtener un deseo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deseo encontrado"),
            @ApiResponse(responseCode = "404", description = "Deseo no encontrado")
    })
    public ResponseEntity<WishResource> getWishById(@PathVariable Long wishId) {
        var query = new GetWishById(wishId);
        var wish = wishQueryService.handle(query);
        return wish
                .map(w -> ResponseEntity.ok(WishResourceFromEntityAssembler.toResourceFromEntity(w)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/collection/{collectionId}")
    @Operation(summary = "Listar deseos por colección")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de deseos")
    })
    public ResponseEntity<List<WishResource>> getWishesByCollection(@PathVariable Long collectionId) {
        var query = new GetAllWishesByCollectionId(collectionId);
        var wishes = wishQueryService.handle(query);
        var resources = wishes.stream()
                .map(WishResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
    @DeleteMapping("/wishes/{wishId}")
    @Operation(summary = "Eliminar un deseo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deseo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Deseo no encontrado")
    })
    public ResponseEntity<Void> deleteWish(@PathVariable Long wishId) {
        var result = wishCommandService.deleteById(wishId);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}