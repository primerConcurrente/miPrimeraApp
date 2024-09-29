package io.primerconcurrente.mi_primeraapp.service;

import io.primerconcurrente.mi_primeraapp.domain.SensorMovimiento;
import io.primerconcurrente.mi_primeraapp.model.SensorMovimientoDTO;
import io.primerconcurrente.mi_primeraapp.repos.SensorMovimientoRepository;
import io.primerconcurrente.mi_primeraapp.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SensorMovimientoService {

    private final SensorMovimientoRepository sensorMovimientoRepository;

    public SensorMovimientoService(final SensorMovimientoRepository sensorMovimientoRepository) {
        this.sensorMovimientoRepository = sensorMovimientoRepository;
    }

    public List<SensorMovimientoDTO> findAll() {
        final List<SensorMovimiento> sensorMovimientoes = sensorMovimientoRepository.findAll(Sort.by("id"));
        return sensorMovimientoes.stream()
                .map(sensorMovimiento -> mapToDTO(sensorMovimiento, new SensorMovimientoDTO()))
                .toList();
    }

    public SensorMovimientoDTO get(final Long id) {
        return sensorMovimientoRepository.findById(id)
                .map(sensorMovimiento -> mapToDTO(sensorMovimiento, new SensorMovimientoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SensorMovimientoDTO sensorMovimientoDTO) {
        final SensorMovimiento sensorMovimiento = new SensorMovimiento();
        mapToEntity(sensorMovimientoDTO, sensorMovimiento);
        return sensorMovimientoRepository.save(sensorMovimiento).getId();
    }

    public void update(final Long id, final SensorMovimientoDTO sensorMovimientoDTO) {
        final SensorMovimiento sensorMovimiento = sensorMovimientoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sensorMovimientoDTO, sensorMovimiento);
        sensorMovimientoRepository.save(sensorMovimiento);
    }

    public void delete(final Long id) {
        sensorMovimientoRepository.deleteById(id);
    }

    private SensorMovimientoDTO mapToDTO(final SensorMovimiento sensorMovimiento,
            final SensorMovimientoDTO sensorMovimientoDTO) {
        sensorMovimientoDTO.setId(sensorMovimiento.getId());
        sensorMovimientoDTO.setNombre(sensorMovimiento.getNombre());
        sensorMovimientoDTO.setHayMovimiento(sensorMovimiento.getHayMovimiento());
        return sensorMovimientoDTO;
    }

    private SensorMovimiento mapToEntity(final SensorMovimientoDTO sensorMovimientoDTO,
            final SensorMovimiento sensorMovimiento) {
        sensorMovimiento.setNombre(sensorMovimientoDTO.getNombre());
        sensorMovimiento.setHayMovimiento(sensorMovimientoDTO.getHayMovimiento());
        return sensorMovimiento;
    }

    public boolean nombreExists(final String nombre) {
        return sensorMovimientoRepository.existsByNombreIgnoreCase(nombre);
    }

}
