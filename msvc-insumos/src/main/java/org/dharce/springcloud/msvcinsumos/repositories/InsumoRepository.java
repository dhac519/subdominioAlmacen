package org.dharce.springcloud.msvcinsumos.repositories;

import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;
import org.springframework.data.repository.CrudRepository;

public interface InsumoRepository extends CrudRepository<Insumo,Long> {
}
