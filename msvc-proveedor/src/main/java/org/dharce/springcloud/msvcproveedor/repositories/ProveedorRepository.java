package org.dharce.springcloud.msvcproveedor.repositories;

import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<Proveedor,Long> {
    Optional<Proveedor> findByRuc(String ruc);
    @Modifying
    @Query("delete from ProveedorArticulo cu where cu.articuloId=?1")
    void eliminarProveedorArticuloPorId(Long id);

    @Modifying
    @Query("delete from ProveedorInsumo cu where cu.insumoId=?1")
    void eliminarProveedorInsumoPorId(Long id);
}
