package io.primerconcurrente.mi_primeraapp.rest;

import io.primerconcurrente.mi_primeraapp.model.SensorTempertauraDTO;
import io.primerconcurrente.mi_primeraapp.service.SensorTempertauraService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/sensorTempertauras", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorTempertauraResource {

    private final SensorTempertauraService sensorTempertauraService;

    public SensorTempertauraResource(final SensorTempertauraService sensorTempertauraService) {
        this.sensorTempertauraService = sensorTempertauraService;
    }

    @GetMapping
    public ResponseEntity<List<SensorTempertauraDTO>> getAllSensorTempertauras() {
        return ResponseEntity.ok(sensorTempertauraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorTempertauraDTO> getSensorTempertaura(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(sensorTempertauraService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSensorTempertaura(
            @RequestBody @Valid final SensorTempertauraDTO sensorTempertauraDTO) {
        final Long createdId = sensorTempertauraService.create(sensorTempertauraDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSensorTempertaura(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SensorTempertauraDTO sensorTempertauraDTO) {
        sensorTempertauraService.update(id, sensorTempertauraDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSensorTempertaura(@PathVariable(name = "id") final Long id) {
        sensorTempertauraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
