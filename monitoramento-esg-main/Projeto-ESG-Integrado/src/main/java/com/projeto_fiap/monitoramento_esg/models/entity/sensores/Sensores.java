package com.projeto_fiap.monitoramento_esg.models.entity.sensores;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.projeto_fiap.monitoramento_esg.models.entity.consumo.Consumo;

import java.util.List;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Sensores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "LOCALIZACAO", length = 100, nullable = false)
    private String localizacao;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private List<Consumo> consumos;
}
