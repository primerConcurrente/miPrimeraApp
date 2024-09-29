package io.primerconcurrente.mi_primeraapp.service;

import io.primerconcurrente.mi_primeraapp.domain.SensorAcceso;
import io.primerconcurrente.mi_primeraapp.model.SensorAccesoDTO;
import io.primerconcurrente.mi_primeraapp.repos.SensorAccesoRepository;
import io.primerconcurrente.mi_primeraapp.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SensorAccesoService {

    private final SensorAccesoRepository sensorAccesoRepository;

    public SensorAccesoService(final SensorAccesoRepository sensorAccesoRepository) {
        this.sensorAccesoRepository = sensorAccesoRepository;
    }

    public List<SensorAccesoDTO> findAll() {
        final List<SensorAcceso> sensorAccesoes = sensorAccesoRepository.findAll(Sort.by("id"));
        return sensorAccesoes.stream()
                .map(sensorAcceso -> mapToDTO(sensorAcceso, new SensorAccesoDTO()))
                .toList();
    }

    public SensorAccesoDTO get(final Long id) {
        return sensorAccesoRepository.findById(id)
                .map(sensorAcceso -> mapToDTO(sensorAcceso, new SensorAccesoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SensorAccesoDTO sensorAccesoDTO) {
        final SensorAcceso sensorAcceso = new SensorAcceso();
        mapToEntity(sensorAccesoDTO, sensorAcceso);
        return sensorAccesoRepository.save(sensorAcceso).getId();
    }

    public void update(final Long id, final SensorAccesoDTO sensorAccesoDTO) {
        final SensorAcceso sensorAcceso = sensorAccesoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sensorAccesoDTO, sensorAcceso);
        sensorAccesoRepository.save(sensorAcceso);
    }

    public void delete(final Long id) {
        sensorAccesoRepository.deleteById(id);
    }

    private SensorAccesoDTO mapToDTO(final SensorAcceso sensorAcceso,
            final SensorAccesoDTO sensorAccesoDTO) {
        sensorAccesoDTO.setId(sensorAcceso.getId());
        sensorAccesoDTO.setNombre(sensorAcceso.getNombre());
        sensorAccesoDTO.setTipoDeAcceso(sensorAcceso.getTipoDeAcceso());
        return sensorAccesoDTO;
    }

    private SensorAcceso mapToEntity(final SensorAccesoDTO sensorAccesoDTO,
            final SensorAcceso sensorAcceso) {
        sensorAcceso.setNombre(sensorAccesoDTO.getNombre());
        sensorAcceso.setTipoDeAcceso(sensorAccesoDTO.getTipoDeAcceso());
        return sensorAcceso;
    }

    public boolean nombreExists(final String nombre) {
        return sensorAccesoRepository.existsByNombreIgnoreCase(nombre);
    }

}
