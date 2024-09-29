package io.primerconcurrente.mi_primeraapp.repos;

import io.primerconcurrente.mi_primeraapp.domain.SensorTempertaura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SensorTempertauraRepository extends JpaRepository<SensorTempertaura, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

}
