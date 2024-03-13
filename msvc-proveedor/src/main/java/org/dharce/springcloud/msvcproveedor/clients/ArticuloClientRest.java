package org.dharce.springcloud.msvcproveedor.clients;

import org.dharce.springcloud.msvcproveedor.models.Articulo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-articulo", url="localhost:8004/api/articulo")
public interface ArticuloClientRest {
    @GetMapping("/{id}")
    Articulo detalle(@PathVariable Long id);
    @PutMapping()
    Articulo crear(@RequestBody Articulo articulo);

    @GetMapping("/articulo-por-proveedor")
    List<Articulo> obtenerArticuloPorProveedor(@RequestParam Iterable<Long> ids);
}
