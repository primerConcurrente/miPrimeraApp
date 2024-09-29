package io.primerconcurrente.mi_primeraapp.repos;

import io.primerconcurrente.mi_primeraapp.domain.SensorMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SensorMovimientoRepository extends JpaRepository<SensorMovimiento, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

}
