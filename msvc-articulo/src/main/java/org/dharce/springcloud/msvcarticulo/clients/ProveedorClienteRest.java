package org.dharce.springcloud.msvcarticulo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-proveedor",url = "localhost:8001/api/proveedor")
public interface ProveedorClienteRest {
    @DeleteMapping("/eliminar-proart/{id}")
    void eliminarProveedorArticuloPorId(@PathVariable Long id);
}
