package com.projeto_fiap.monitoramento_esg.models.dto.sensores;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SensoresDTO {
    private Long id;
    private String nome;
    private String localizacao;
}
