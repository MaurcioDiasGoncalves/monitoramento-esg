package com.projeto_fiap.monitoramento_esg.repository.consumo;

import com.projeto_fiap.monitoramento_esg.models.entity.consumo.AlertaConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaConsumoRepository extends JpaRepository<AlertaConsumo, Long> {
}

