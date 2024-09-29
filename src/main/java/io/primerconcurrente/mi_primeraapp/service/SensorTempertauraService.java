package io.primerconcurrente.mi_primeraapp.service;

import io.primerconcurrente.mi_primeraapp.domain.SensorTempertaura;
import io.primerconcurrente.mi_primeraapp.model.SensorTempertauraDTO;
import io.primerconcurrente.mi_primeraapp.repos.SensorTempertauraRepository;
import io.primerconcurrente.mi_primeraapp.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SensorTempertauraService {

    private final SensorTempertauraRepository sensorTempertauraRepository;

    public SensorTempertauraService(final SensorTempertauraRepository sensorTempertauraRepository) {
        this.sensorTempertauraRepository = sensorTempertauraRepository;
    }

    public List<SensorTempertauraDTO> findAll() {
        final List<SensorTempertaura> sensorTempertauras = sensorTempertauraRepository.findAll(Sort.by("id"));
        return sensorTempertauras.stream()
                .map(sensorTempertaura -> mapToDTO(sensorTempertaura, new SensorTempertauraDTO()))
                .toList();
    }

    public SensorTempertauraDTO get(final Long id) {
        return sensorTempertauraRepository.findById(id)
                .map(sensorTempertaura -> mapToDTO(sensorTempertaura, new SensorTempertauraDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SensorTempertauraDTO sensorTempertauraDTO) {
        final SensorTempertaura sensorTempertaura = new SensorTempertaura();
        mapToEntity(sensorTempertauraDTO, sensorTempertaura);
        return sensorTempertauraRepository.save(sensorTempertaura).getId();
    }

    public void update(final Long id, final SensorTempertauraDTO sensorTempertauraDTO) {
        final SensorTempertaura sensorTempertaura = sensorTempertauraRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sensorTempertauraDTO, sensorTempertaura);
        sensorTempertauraRepository.save(sensorTempertaura);
    }

    public void delete(final Long id) {
        sensorTempertauraRepository.deleteById(id);
    }

    private SensorTempertauraDTO mapToDTO(final SensorTempertaura sensorTempertaura,
            final SensorTempertauraDTO sensorTempertauraDTO) {
        sensorTempertauraDTO.setId(sensorTempertaura.getId());
        sensorTempertauraDTO.setNombre(sensorTempertaura.getNombre());
        sensorTempertauraDTO.setTemperatura(sensorTempertaura.getTemperatura());
        return sensorTempertauraDTO;
    }

    private SensorTempertaura mapToEntity(final SensorTempertauraDTO sensorTempertauraDTO,
            final SensorTempertaura sensorTempertaura) {
        sensorTempertaura.setNombre(sensorTempertauraDTO.getNombre());
        sensorTempertaura.setTemperatura(sensorTempertauraDTO.getTemperatura());
        return sensorTempertaura;
    }

    public boolean nombreExists(final String nombre) {
        return sensorTempertauraRepository.existsByNombreIgnoreCase(nombre);
    }

}
