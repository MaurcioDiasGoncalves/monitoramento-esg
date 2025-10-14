package com.projeto_fiap.monitoramento_esg.services.consumo.alerta;

import com.projeto_fiap.monitoramento_esg.models.dto.consumo.AlertaConsumoDTO;
import com.projeto_fiap.monitoramento_esg.models.entity.consumo.Consumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertaConsumoService {
    Page<AlertaConsumoDTO> listarAlertas(Pageable pageable);
    void verificarERegistrarAlertas(Consumo consumo);
}
