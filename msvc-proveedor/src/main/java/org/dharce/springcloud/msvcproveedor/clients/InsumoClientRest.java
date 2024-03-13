package org.dharce.springcloud.msvcproveedor.clients;

import org.dharce.springcloud.msvcproveedor.models.Articulo;
import org.dharce.springcloud.msvcproveedor.models.Insumo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name="msvc-insumo", url="localhost:8002/api/insumo")
public interface InsumoClientRest {
    @GetMapping("/{id}")
    Insumo detalle(@PathVariable Long id);
    @PutMapping()
    Insumo crear(@RequestBody Insumo insumo);

    @GetMapping("/insumo-por-proveedor")
    List<Insumo> obtenerInsumoPorProveedor(@RequestParam Iterable<Long> ids);

}
