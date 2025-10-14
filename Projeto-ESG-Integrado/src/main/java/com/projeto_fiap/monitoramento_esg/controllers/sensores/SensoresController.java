package com.projeto_fiap.monitoramento_esg.controllers.sensores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.sensores.SensoresDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto_fiap.monitoramento_esg.services.sensores.SensoresService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensores")
@Tag(name = "Endpoints de sensores", description = "Endpoints para criar, listar e buscar informações dos sensores.")
public class SensoresController {
    private final SensoresService sensoresService;

    @Operation(summary = "Cadastrar sensor", description = "Cadastra um novo sensor com nome e localização.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sensor criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensoresDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<SensoresDTO> criarSensor(@RequestBody @Valid SensoresDTO dto) {
        SensoresDTO sensor = this.sensoresService.criarSensor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sensor);
    }

    @Operation(summary = "Buscar sensores", description = "Retorna sensores paginados e ordenados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sensores encontrados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensoresDTO.class))),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<Page<SensoresDTO>> listarTodosSensores(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<SensoresDTO> sensores = this.sensoresService.listarTodos(pageable);
        return ResponseEntity.ok(sensores);
    }

    @Operation(summary = "Buscar sensor por ID", description = "Busca os dados de um sensor pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sensor encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensoresDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SensoresDTO> buscarSensorPorId(@PathVariable Long id) {
        SensoresDTO sensor = this.sensoresService.buscarPorId(id);
        return ResponseEntity.ok(sensor);
    }

    @Operation(summary = "Atualizar sensor", description = "Atualiza os dados de um sensor existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sensor atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensoresDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SensoresDTO> atualizarSensor(@PathVariable Long id, @RequestBody @Valid SensoresDTO dto) {
        SensoresDTO sensorAtualizado = this.sensoresService.atualizarSensor(id, dto);
        return ResponseEntity.ok(sensorAtualizado);
    }

    @Operation(summary = "Deletar sensor", description = "Remove um sensor existente do sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sensor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSensor(@PathVariable Long id) {
        this.sensoresService.deletarSensor(id);
        return ResponseEntity.noContent().build();
    }
}
