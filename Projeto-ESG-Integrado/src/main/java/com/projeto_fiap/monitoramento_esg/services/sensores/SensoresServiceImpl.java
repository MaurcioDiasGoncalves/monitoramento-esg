package com.projeto_fiap.monitoramento_esg.services.sensores;

import com.projeto_fiap.monitoramento_esg.constants.MensagensConstantes;
import com.projeto_fiap.monitoramento_esg.exceptions.SensorNotFoundException;
import lombok.RequiredArgsConstructor;
import com.projeto_fiap.monitoramento_esg.models.dto.sensores.SensoresDTO;
import com.projeto_fiap.monitoramento_esg.models.entity.sensores.Sensores;
import com.projeto_fiap.monitoramento_esg.models.mapper.SensoresMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.projeto_fiap.monitoramento_esg.repository.sensores.SensoresRepository;

@Service
@RequiredArgsConstructor
public class SensoresServiceImpl implements SensoresService {
    private final SensoresRepository sensoresRepository;
    private final SensoresMapper sensoresMapper;

    @Override
    public SensoresDTO criarSensor(SensoresDTO dto) {
        Sensores entity = sensoresMapper.toEntity(dto);
        return this.sensoresMapper.toDTO(this.sensoresRepository.save(entity));
    }

    @Override
    public SensoresDTO buscarPorId(Long id) {
        Sensores entity = this.sensoresRepository.findById(id)
                .orElseThrow(() -> new SensorNotFoundException(MensagensConstantes.SENSOR_NOT_FOUND_COM_ID + id));
        return this.sensoresMapper.toDTO(entity);
    }

    @Override
    public Page<SensoresDTO> listarTodos(Pageable pageable) {
        return this.sensoresRepository.findAll(pageable)
                .map(this.sensoresMapper::toDTO);
    }

    @Override
    public SensoresDTO atualizarSensor(Long id, SensoresDTO dto) {
        Sensores entity = this.sensoresRepository.findById(id)
                .orElseThrow(() -> new SensorNotFoundException(MensagensConstantes.SENSOR_NOT_FOUND_COM_ID + id));

        entity.setNome(dto.getNome());
        entity.setLocalizacao(dto.getLocalizacao());

        return this.sensoresMapper.toDTO(this.sensoresRepository.save(entity));
    }

    @Override
    public void deletarSensor(Long id) {
        if (!this.sensoresRepository.existsById(id)) {
            throw new SensorNotFoundException(MensagensConstantes.SENSOR_NOT_FOUND_COM_ID + id);
        }
        this.sensoresRepository.deleteById(id);
    }
}
