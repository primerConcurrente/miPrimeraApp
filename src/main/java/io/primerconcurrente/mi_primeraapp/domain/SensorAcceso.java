package io.primerconcurrente.mi_primeraapp.domain;

import io.primerconcurrente.mi_primeraapp.model.TipoDeAcceso;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;


@Entity
@Table(name = "SensorAccesoes")
public class SensorAcceso extends Sensor {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeAcceso tipoDeAcceso;

    public TipoDeAcceso getTipoDeAcceso() {
        return tipoDeAcceso;
    }

    public void setTipoDeAcceso(final TipoDeAcceso tipoDeAcceso) {
        this.tipoDeAcceso = tipoDeAcceso;
    }

    @Override
    public void activar() {
        System.out.println("Sensor de acceso activado en la sección" + tipoDeAcceso);
    }


}
