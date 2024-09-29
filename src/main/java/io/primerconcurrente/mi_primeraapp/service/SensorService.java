package io.primerconcurrente.mi_primeraapp.service;

import io.primerconcurrente.mi_primeraapp.domain.Sensor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class SensorService {

    @Async("sensorTaskExecutor")
    public CompletableFuture<Void> procesarDatos(Sensor sensor) {
        // Lógica de procesamiento
        return CompletableFuture.completedFuture(null);
    }
}
