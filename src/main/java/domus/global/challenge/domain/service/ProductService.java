package domus.global.challenge.domain.service;

import domus.global.challenge.application.dto.PagedResponse;
import domus.global.challenge.application.dto.ProductFilter;
import domus.global.challenge.domain.model.Product;
import domus.global.challenge.infrastructure.repository.InMemoryProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final InMemoryProductRepository repository;

    public ProductService(InMemoryProductRepository repository) {
        this.repository = repository;
    }


    public PagedResponse<Product> getAllProducts(ProductFilter filter) {
        List<Product> all = repository.findAll();

        if (filter.getName() != null && !filter.getName().isBlank()) {
            all = all.stream()
                    .filter(p -> p.getName().toLowerCase().contains(filter.getName().toLowerCase()))
                    .toList();
        }

        int total = all.size();
        int page = filter.getSafePage();
        int size = filter.getSafeSize();

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);
        List<Product> paged = (fromIndex >= total) ? List.of() : all.subList(fromIndex, toIndex);

        Map<String, Object> filters = new HashMap<>();
        if (filter.getName() != null) filters.put("name", filter.getName());

        var metadata = PagedResponse.Metadata.builder()
                .page(page)
                .size(size)
                .total(total)
                .build();

        return PagedResponse.<Product>builder()
                .data(paged)
                .metadata(metadata)
                .build();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero");
        }

        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existing = repository.findById(id);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        Product product = existing.get();
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
