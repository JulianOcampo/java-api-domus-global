package domus.global.challenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BasePaginationFilter {

    @Schema(description = "Número de página (por defecto 0)", example = "0")
    private Integer page = 0;

    @Schema(description = "Tamaño de página (por defecto 10)", example = "10")
    private Integer size = 10;

    public int getSafePage() {
        return (page == null || page < 0) ? 0 : page;
    }

    public int getSafeSize() {
        return (size == null || size <= 0) ? 10 : size;
    }
}
