package org.dharce.springcloud.msvcproveedor.services;

import org.dharce.springcloud.msvcproveedor.models.Articulo;
import org.dharce.springcloud.msvcproveedor.models.Insumo;
import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<Proveedor> listar(); //lista  user
    Optional<Proveedor> porId(Long id); //te devuelve un solo valor
    Proveedor guardar(Proveedor proveedor); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Proveedor> porRuc(String ruc);


    //Articulo
    //Metodos remotos relacionados al cliente Http
    //(Al ApiRest que cominicar con el otro msvc)

    Optional<Articulo> asignarArticulo(Articulo articulo, Long proveedorId);
    Optional<Articulo> crearArticulo(Articulo articulo, Long proveedorId);
    Optional<Articulo> eliminarArticulo(Articulo articulo, Long proveedorId);

    Optional<Proveedor> porIdConArticulo(Long id);

    void eliminarProveedorArticuloPorId(Long id);

    //Insumo

    //Metodos remotos relacionados al cliente Http
    //(Al ApiRest que cominicar con el otro msvc)

    Optional<Insumo> asignarInsumo(Insumo insumo, Long proveedorId);
    Optional<Insumo> crearInsumo(Insumo insumo, Long proveedorId);
    Optional<Insumo> eliminarInsumo(Insumo insumo, Long proveedorId);

    Optional<Proveedor> porIdConInsumo(Long id);

    void eliminarProveedorInsumoPorId(Long id);
}
