package io.primerconcurrente.mi_primeraapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "SensorTempertauras")
public class SensorTempertaura extends Sensor {

    @Column(nullable = false)
    private Integer temperatura;

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(final Integer temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public void activar() {

    }
}
