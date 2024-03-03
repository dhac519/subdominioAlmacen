package org.dharce.springcloud.msvcproveedor.services;

import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<Proveedor> listar(); //lista  user
    Optional<Proveedor> porId(Long id); //te devuelve un solo valor
    Proveedor guardar(Proveedor proveedor); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Proveedor> porRuc(String ruc);
}
