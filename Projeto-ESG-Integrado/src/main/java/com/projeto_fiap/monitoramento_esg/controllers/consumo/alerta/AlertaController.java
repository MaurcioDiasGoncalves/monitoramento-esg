package com.projeto_fiap.monitoramento_esg.controllers.consumo.alerta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.consumo.AlertaConsumoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto_fiap.monitoramento_esg.services.consumo.alerta.AlertaConsumoService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/alertas")
@Tag(name = "Endpoint do alerta de consumo", description = "Endpoint buscar os alertas de consumo elevado.")
public class AlertaController {
    private final AlertaConsumoService alertaConsumoService;

    @Operation(summary = "Buscar alertas", description = "Buscar alertas de alto consumo paginados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlertaConsumoDTO.class)
                    )),
            @ApiResponse(responseCode = "401", description = "Houve um erro interno",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<Page<AlertaConsumoDTO>> buscarAlertarPorPagina(
            @PageableDefault(sort = "dataHora", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(this.alertaConsumoService.listarAlertas(pageable));
    }
}
