package com.projeto_fiap.monitoramento_esg.models.dto.consumo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AlertaConsumoDTO {
    private Long sensorId;
    private String nomeSensor;
    private String localizacao;
    private Double consumoKw;
    private LocalDateTime dataHora;
}
