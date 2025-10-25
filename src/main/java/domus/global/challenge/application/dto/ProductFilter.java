package domus.global.challenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductFilter extends BasePaginationFilter {

    @Schema(description = "Nombre del producto para filtrar (opcional)", example = "Laptop")
    private String name;
}
