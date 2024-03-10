package org.dharce.springcloud.msvcinsumos.controllers;

import jakarta.validation.Valid;
import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;
import org.dharce.springcloud.msvcinsumos.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        Optional<Insumo> articuloOptional = service.porId(id);
        if (articuloOptional.isPresent()) {
            return ResponseEntity.ok(articuloOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Insumo insumo, BindingResult result) {
        if (service.porNombre(insumo.getName()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Ese Insumo ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(insumo));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Insumo insumo,BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Insumo> op = service.porId(id);
        if (op.isPresent()) {
            Insumo insumoDB = op.get();
            if(!insumo.getName().equalsIgnoreCase(insumoDB.getName()) && service.porNombre(insumo.getName()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Ups !!!", "Ya Existe ese Insumo"));
            }
            insumoDB.setName(insumo.getName());
            insumoDB.setCantidad(insumo.getCantidad());
            insumoDB.setUnidad(insumo.getUnidad());

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

    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error !!!" + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
