package com.projeto_fiap.monitoramento_esg.models.mapper;

import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.sensores.SensoresDTO;
import com.projeto_fiap.monitoramento_esg.models.entity.sensores.Sensores;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensoresMapper {

    public SensoresDTO toDTO(Sensores entity) {
        return SensoresDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .localizacao(entity.getLocalizacao())
                .build();
    }

    public Sensores toEntity(SensoresDTO input) {
        return Sensores
                .builder()
                .id(input.getId())
                .nome(input.getNome())
                .localizacao(input.getLocalizacao())
                .build();
    }
}
