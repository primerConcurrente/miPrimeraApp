package io.primerconcurrente.mi_primeraapp.repos;

import io.primerconcurrente.mi_primeraapp.domain.SensorAcceso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SensorAccesoRepository extends JpaRepository<SensorAcceso, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

}
