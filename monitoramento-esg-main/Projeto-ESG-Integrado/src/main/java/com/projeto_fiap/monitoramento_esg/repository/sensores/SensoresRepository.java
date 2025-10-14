package com.projeto_fiap.monitoramento_esg.repository.sensores;

import com.projeto_fiap.monitoramento_esg.models.entity.sensores.Sensores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensoresRepository extends JpaRepository<Sensores, Long> {
}
