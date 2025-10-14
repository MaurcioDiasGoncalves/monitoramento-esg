package com.projeto_fiap.monitoramento_esg.controllers.consumo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.consumo.ConsumoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto_fiap.monitoramento_esg.services.consumo.ConsumoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumo")
@Tag(name = "Endpoints do consumo", description = "Endpoints para registrar e listar consumos e seu histórico.")
public class ConsumoController {
    private final ConsumoService consumoService;

    @Operation(summary = "Registrar consumo", description = "Registra um novo consumo de energia para um sensor.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consumo registrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConsumoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<ConsumoDTO> registrar(@RequestBody @Valid ConsumoDTO dto) {
        ConsumoDTO consumo = this.consumoService.registrarConsumo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumo);
    }

    @Operation(summary = "Listar todos os consumos", description = "Retorna todos os registros de consumo de energia.")
    @ApiResponse(responseCode = "200", description = "Consumos listados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ConsumoDTO.class)))
    @GetMapping("/buscar")
    public ResponseEntity<Page<ConsumoDTO>> listarTodos(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(this.consumoService.listarConsumos(pageable));
    }

    @Operation(summary = "Listar consumo por sensor", description = "Retorna histórico de consumo de um sensor específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConsumoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/historico/{sensorId}")
    public ResponseEntity<Page<ConsumoDTO>> historicoPorSensor(
            @PathVariable Long sensorId,
            @PageableDefault(sort = "dataHora", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(this.consumoService.listarHistoricoPorSensor(sensorId, pageable));
    }
}
