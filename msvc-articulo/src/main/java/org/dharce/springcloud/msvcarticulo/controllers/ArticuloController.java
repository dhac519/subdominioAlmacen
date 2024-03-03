package org.dharce.springcloud.msvcarticulo.controllers;
import jakarta.validation.Valid;
import org.dharce.springcloud.msvcarticulo.models.entity.Articulo;
import org.dharce.springcloud.msvcarticulo.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("/api/articulo")
@RestController
public class ArticuloController {

    @Autowired
    private ArticuloService service;

    @GetMapping
    public List<Articulo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Articulo> articuloOptional = service.porId(id);
        if (articuloOptional.isPresent()) {
            return ResponseEntity.ok(articuloOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Articulo articulo, BindingResult result) {
        if (service.porNombre(articulo.getName()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Ese articulo ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(articulo));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Articulo articulo,BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Articulo> op = service.porId(id);
        if (op.isPresent()) {
            Articulo articuloDB = op.get();
            if(!articulo.getName().equalsIgnoreCase(articuloDB.getName()) && service.porNombre(articulo.getName()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Ups !!!", "Ya Existe ese articulo"));
            }
            articuloDB.setName(articulo.getName());
            articuloDB.setPrice(articulo.getPrice());
            articuloDB.setStock(articulo.getStock());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(articuloDB));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Articulo> op = service.porId(id);
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
