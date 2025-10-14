package com.projeto_fiap.monitoramento_esg.models.entity.consumo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.projeto_fiap.monitoramento_esg.models.entity.sensores.Sensores;

import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(schema = "RM555486", name = "ALERTAS_CONSUMO")
public class AlertaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENSOR_ID", nullable = false)
    private Sensores sensor;

    @NotNull
    @Column(name = "CONSUMO_KW", nullable = false)
    private Double consumoKw;

    @NotNull
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;
}
