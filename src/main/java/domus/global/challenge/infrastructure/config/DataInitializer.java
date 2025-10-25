package domus.global.challenge.infrastructure.config;

import domus.global.challenge.domain.model.Product;
import domus.global.challenge.domain.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataInitializer {

    private final ProductService productService;

    public DataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicializando productos de ejemplo...");

        List<String> productNames = List.of(
                "Laptop Lenovo ThinkPad", "Laptop HP Envy", "Laptop Dell XPS",
                "Monitor Samsung 24\"", "Monitor LG 27\"", "Teclado Logitech K380",
                "Mouse Razer DeathAdder", "Mouse Logitech MX Master 3",
                "Audífonos Sony WH-1000XM5", "Audífonos Bose QC45",
                "Silla Gamer Cougar", "Silla de Oficina Ergonómica",
                "Smartphone iPhone 15", "Smartphone Samsung S24", "Smartphone Google Pixel 8",
                "Tablet iPad Air", "Tablet Samsung Galaxy Tab", "Smartwatch Apple Watch 9",
                "Smartwatch Garmin Venu", "Smartwatch Samsung Watch 6",
                "Impresora HP LaserJet", "Impresora Epson EcoTank",
                "Disco Duro Seagate 1TB", "SSD Samsung 1TB", "Cámara Canon EOS M50"
        );

        IntStream.range(0, productNames.size()).forEach(i -> {
            double price = 50 + (Math.random() * 950); // entre 50 y 1000
            Product p = new Product(null, productNames.get(i), Math.round(price * 100.0) / 100.0);
            productService.createProduct(p);
        });

        System.out.println("productos creados exitosamente.");
    }
}
