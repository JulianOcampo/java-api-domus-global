package domus.global.challenge.domain.service;

import domus.global.challenge.application.dto.PagedResponse;
import domus.global.challenge.application.dto.ProductFilter;
import domus.global.challenge.domain.model.Product;
import domus.global.challenge.infrastructure.repository.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private InMemoryProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRepository();
        service = new ProductService(repository);

        repository.save(new Product(null, "Laptop Lenovo", 1500.0));
        repository.save(new Product(null, "Mouse Logitech", 25.0));
        repository.save(new Product(null, "Monitor Samsung", 300.0));
        repository.save(new Product(null, "Laptop HP", 1300.0));
        repository.save(new Product(null, "Teclado Mecánico", 90.0));
    }

    @Test
    void shouldReturnPagedProducts() {
        ProductFilter filter = new ProductFilter();
        filter.setPage(0);
        filter.setSize(3);

        PagedResponse<Product> result = service.getAllProducts(filter);

        assertThat(result.getData()).hasSize(3);
        assertThat(result.getMetadata().getTotal()).isEqualTo(5);
        assertThat(result.getMetadata().getPage()).isEqualTo(0);
        assertThat(result.getMetadata().getSize()).isEqualTo(3);
    }

    @Test
    void shouldFilterProductsByName() {
        ProductFilter filter = new ProductFilter();
        filter.setName("Laptop");
        filter.setPage(0);
        filter.setSize(10);

        PagedResponse<Product> result = service.getAllProducts(filter);

        assertThat(result.getData()).hasSize(2);
        assertThat(result.getData().get(0).getName().toLowerCase()).contains("laptop");
        assertThat(result.getMetadata().getTotal()).isEqualTo(2);
    }

    @Test
    void shouldPaginateProducts() {
        ProductFilter filter = new ProductFilter();
        filter.setPage(1);
        filter.setSize(2);

        PagedResponse<Product> result = service.getAllProducts(filter);

        assertThat(result.getData()).hasSize(2);
        assertThat(result.getMetadata().getPage()).isEqualTo(1);
        assertThat(result.getMetadata().getSize()).isEqualTo(2);
        assertThat(result.getMetadata().getTotal()).isEqualTo(5);
    }

    @Test
    void shouldCreateNewProduct() {
        Product newProduct = new Product(null, "Tablet", 500.0);
        Product saved = service.createProduct(newProduct);

        assertThat(saved.getId()).isNotNull();
        assertThat(repository.findAll()).hasSize(6);
        assertThat(repository.findAll())
                .anyMatch(p -> p.getName().equals("Tablet"));
    }
}
