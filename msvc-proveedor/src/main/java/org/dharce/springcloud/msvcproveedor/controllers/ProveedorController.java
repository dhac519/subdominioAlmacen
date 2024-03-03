package org.dharce.springcloud.msvcproveedor.controllers;

import jakarta.validation.Valid;
import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;
import org.dharce.springcloud.msvcproveedor.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping ("/api/proveedor") //
@RestController
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public List<Proveedor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Proveedor> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> crear(@Valid @RequestBody Proveedor proveedor, BindingResult result) {
        if(service.porRuc(proveedor.getRuc()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Ups!!", "Este Proveedor ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Proveedor proveedor,BindingResult result,  @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<Proveedor> op = service.porId(id);
        if (op.isPresent()) {
            Proveedor proveedorDB = op.get();

            if(!proveedor.getRuc().equalsIgnoreCase(proveedorDB.getRuc()) && service.porRuc(proveedor.getRuc()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Upss!!! ", "Ya existe el Ruc en otro proveedor"));
            }
            proveedorDB.setName(proveedor.getName());
            proveedorDB.setRuc(proveedor.getRuc());
            proveedorDB.setAddress(proveedor.getAddress());
            proveedorDB.setPhone(proveedor.getPhone());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(proveedorDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Proveedor> op = service.porId(id);
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
            errores.put(err.getField(), "Error!!! " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
