package domus.global.challenge.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {

    @Schema(description = "Listado de resultados")
    private List<T> data;

    @Schema(description = "Información de la paginación y filtros aplicados")
    private Metadata metadata;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Metadata {
        private int page;
        private int size;
        private long total;
    }
}
