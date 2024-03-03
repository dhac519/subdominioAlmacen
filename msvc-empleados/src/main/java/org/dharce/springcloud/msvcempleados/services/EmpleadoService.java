package org.dharce.springcloud.msvcempleados.services;

import org.dharce.springcloud.msvcempleados.models.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    List<Empleado> listar(); //lista  user
    Optional<Empleado> porId(Long id); //te devuelve un solo valor
    Empleado guardar(Empleado empleado); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Empleado> porDni(String dni);
}
