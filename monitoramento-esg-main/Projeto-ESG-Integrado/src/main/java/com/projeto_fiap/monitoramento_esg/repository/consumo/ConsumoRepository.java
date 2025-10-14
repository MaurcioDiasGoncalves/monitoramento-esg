package com.projeto_fiap.monitoramento_esg.repository.consumo;

import com.projeto_fiap.monitoramento_esg.models.entity.consumo.Consumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
    Page<Consumo> findBySensorId(Long sensorId, Pageable pageable);
}
