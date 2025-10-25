package domus.global.challenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductUpsertRequest {

    @Schema(description = "Nombre del producto", example = "Laptop Lenovo ThinkPad", required = true)
    private String name;

    @Schema(description = "Precio del producto", example = "999.99", required = true)
    private Double price;
}
