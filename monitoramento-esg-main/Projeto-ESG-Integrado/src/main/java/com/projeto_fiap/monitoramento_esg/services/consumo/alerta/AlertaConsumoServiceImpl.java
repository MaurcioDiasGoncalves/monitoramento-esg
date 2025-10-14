package com.projeto_fiap.monitoramento_esg.services.consumo.alerta;

import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.consumo.AlertaConsumoDTO;
import com.projeto_fiap.monitoramento_esg.models.entity.consumo.AlertaConsumo;
import com.projeto_fiap.monitoramento_esg.models.entity.consumo.Consumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.projeto_fiap.monitoramento_esg.repository.consumo.AlertaConsumoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertaConsumoServiceImpl implements AlertaConsumoService {
    private static final double LIMITE_EXCESSO_KW = 20.0;
    private final AlertaConsumoRepository alertaConsumoRepository;

    @Override
    public Page<AlertaConsumoDTO> listarAlertas(Pageable pageable) {
        return this.alertaConsumoRepository.findAll(pageable)
                .map(a -> AlertaConsumoDTO.builder()
                        .sensorId(a.getSensor().getId())
                        .nomeSensor(a.getSensor().getNome())
                        .localizacao(a.getSensor().getLocalizacao())
                        .consumoKw(a.getConsumoKw())
                        .dataHora(a.getDataHora())
                        .build());
    }

    @Override
    public void verificarERegistrarAlertas(Consumo consumo) {
        if (consumo.getConsumoKw() != null && consumo.getConsumoKw() > LIMITE_EXCESSO_KW) {
            AlertaConsumo alerta = AlertaConsumo.builder()
                    .sensor(consumo.getSensor())
                    .consumoKw(consumo.getConsumoKw())
                    .dataHora(consumo.getDataHora() != null ? consumo.getDataHora() : LocalDateTime.now())
                    .build();

            this.alertaConsumoRepository.save(alerta);
        }
    }
}
