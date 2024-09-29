package io.primerconcurrente.mi_primeraapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SensorAccesoDTO {

    private Long id;

    @Size(max = 255)
    @SensorAccesoNombreUnique
    private String nombre;

    @NotNull
    private TipoDeAcceso tipoDeAcceso;

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

    public TipoDeAcceso getTipoDeAcceso() {
        return tipoDeAcceso;
    }

    public void setTipoDeAcceso(final TipoDeAcceso tipoDeAcceso) {
        this.tipoDeAcceso = tipoDeAcceso;
    }

}
