package org.dharce.springcloud.msvcinsumos.controllers;

import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;
import org.dharce.springcloud.msvcinsumos.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/api/insumo")
@RestController
public class InsumoController {
    @Autowired
    private InsumoService service;

    @GetMapping
    public List<Insumo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Insumo> insumoOptional = service.porId(id);
        if (insumoOptional.isPresent()) {
            return ResponseEntity.ok(insumoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Insumo insumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(insumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Insumo insumo, @PathVariable Long id) {
        Optional<Insumo> op = service.porId(id);
        if (op.isPresent()) {
            Insumo insumoDB = op.get();
            insumoDB.setName(insumo.getName());
            insumoDB.setPrice(insumo.getPrice());
            insumoDB.setStock(insumo.getStock());


            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(insumoDB));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Insumo> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
