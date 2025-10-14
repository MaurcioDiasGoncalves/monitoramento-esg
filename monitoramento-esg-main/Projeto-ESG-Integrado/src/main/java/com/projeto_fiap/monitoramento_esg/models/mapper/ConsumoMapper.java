package com.projeto_fiap.monitoramento_esg.models.mapper;

import com.projeto_fiap.monitoramento_esg.constants.MensagensConstantes;
import com.projeto_fiap.monitoramento_esg.exceptions.SensorNotFoundException;
import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.consumo.ConsumoDTO;
import com.projeto_fiap.monitoramento_esg.models.entity.consumo.Consumo;
import com.projeto_fiap.monitoramento_esg.models.entity.sensores.Sensores;
import org.springframework.stereotype.Component;
import com.projeto_fiap.monitoramento_esg.repository.sensores.SensoresRepository;

@Component
@RequiredArgsConstructor
public class ConsumoMapper {
    private final SensoresRepository sensoresRepository;

    public ConsumoDTO toDTO(Consumo entity) {
        return ConsumoDTO.builder()
                .id(entity.getId())
                .sensorId(entity.getSensor().getId())
                .consumoKw(entity.getConsumoKw())
                .dataHora(entity.getDataHora())
                .build();
    }

    public Consumo toEntity(ConsumoDTO input) {
        Sensores sensor = sensoresRepository.findById(input.getSensorId())
                .orElseThrow(() -> new SensorNotFoundException(MensagensConstantes.SENSOR_NOT_FOUND_COM_ID + input.getSensorId()));

        return Consumo
                .builder()
                .id(input.getId())
                .sensor(sensor)
                .consumoKw(input.getConsumoKw())
                .build();
    }
}
