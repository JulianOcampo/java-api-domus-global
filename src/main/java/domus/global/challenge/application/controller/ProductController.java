package domus.global.challenge.application.controller;

import domus.global.challenge.application.dto.*;
import domus.global.challenge.domain.model.Product;
import domus.global.challenge.domain.service.ProductService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Product>> getProducts(@ParameterObject @ModelAttribute ProductFilter filter) {
        return ResponseEntity.ok(service.getAllProducts(filter));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductUpsertRequest request) {
        Product product = new Product(null, request.getName(), request.getPrice());
        return ResponseEntity.ok(service.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpsertRequest request
    ) {
        Product product = new Product(id, request.getName(), request.getPrice());
        return ResponseEntity.ok(service.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
