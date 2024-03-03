package org.dharce.springcloud.msvcempleados.repositories;

import org.dharce.springcloud.msvcempleados.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado,Long> {
    Optional<Empleado> findByDni(String dni);
}
