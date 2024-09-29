package io.primerconcurrente.mi_primeraapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "SensorMovimientoes")
public class SensorMovimiento extends Sensor {

    @Column(nullable = false, columnDefinition = "tinyint", length = 1)
    private Boolean hayMovimiento;

    public Boolean getHayMovimiento() {
        return hayMovimiento;
    }

    public void setHayMovimiento(final Boolean hayMovimiento) {
        this.hayMovimiento = hayMovimiento;
    }

    @Override
    public void activar() {

    }
}
