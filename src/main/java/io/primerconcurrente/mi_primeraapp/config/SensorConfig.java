package io.primerconcurrente.mi_primeraapp.config;

import io.primerconcurrente.mi_primeraapp.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensorConfig {

    @Bean
    public SensorMovimiento sensorMovimiento() {
        return new SensorMovimiento();
    }

    @Bean
    public SensorTempertaura sensorTemperatura() {
        return new SensorTempertaura();
    }

    @Bean
    public SensorAcceso sensorLuz() {
        return new SensorAcceso();
    }

}
