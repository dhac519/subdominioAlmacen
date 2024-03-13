package org.dharce.springcloud.msvcarticulo.services;

import org.dharce.springcloud.msvcarticulo.models.entity.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> listar();
    Optional<Articulo> porId(Long id);
    Articulo guardar(Articulo articulo);
    void eliminar(Long id);

    Optional<Articulo> porNombre(String name);

    //Metodos remotos

    List<Articulo> listaPorIds(Iterable<Long> ids);
}
