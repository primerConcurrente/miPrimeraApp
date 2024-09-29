package io.primerconcurrente.mi_primeraapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SensorTempertauraDTO {

    private Long id;

    @Size(max = 255)
    @SensorTempertauraNombreUnique
    private String nombre;

    @NotNull
    private Integer temperatura;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(final Integer temperatura) {
        this.temperatura = temperatura;
    }

}
