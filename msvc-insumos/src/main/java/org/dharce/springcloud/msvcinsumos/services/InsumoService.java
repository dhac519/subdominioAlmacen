package org.dharce.springcloud.msvcinsumos.services;

import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;

import java.util.List;
import java.util.Optional;

public interface InsumoService {
    List<Insumo> listar();
    Optional<Insumo> porId(Long id);
    Insumo guardar(Insumo articulo);
    void eliminar(Long id);

    Optional<Insumo> porNombre(String name);

    //Metodos remotos

    List<Insumo> listaPorIds(Iterable<Long> ids);
}
