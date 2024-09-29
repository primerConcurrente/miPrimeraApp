package io.primerconcurrente.mi_primeraapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SensorMovimientoDTO {

    private Long id;

    @Size(max = 255)
    @SensorMovimientoNombreUnique
    private String nombre;

    @NotNull
    private Boolean hayMovimiento;

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

    public Boolean getHayMovimiento() {
        return hayMovimiento;
    }

    public void setHayMovimiento(final Boolean hayMovimiento) {
        this.hayMovimiento = hayMovimiento;
    }

}
