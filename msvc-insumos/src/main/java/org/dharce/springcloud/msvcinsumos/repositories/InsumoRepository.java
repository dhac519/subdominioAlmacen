package org.dharce.springcloud.msvcinsumos.repositories;

import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InsumoRepository extends CrudRepository<Insumo,Long> {
    Optional<Insumo> findByName(String name);
}
