package org.dharce.springcloud.msvcproveedor.repositories;

import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<Proveedor,Long> {
    Optional<Proveedor> findByRuc(String ruc);
}
