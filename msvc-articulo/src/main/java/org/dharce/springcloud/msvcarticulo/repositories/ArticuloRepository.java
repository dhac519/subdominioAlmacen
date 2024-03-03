package org.dharce.springcloud.msvcarticulo.repositories;

import org.dharce.springcloud.msvcarticulo.models.entity.Articulo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticuloRepository extends CrudRepository<Articulo,Long> {
    Optional<Articulo> findByName(String name);
}
