package org.dharce.springcloud.msvcproveedor.services;

import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;
import org.dharce.springcloud.msvcproveedor.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ProveedorServiceImpl implements  ProveedorService{
    @Autowired //para inyectar la dependencia de una clase con metodos
    private ProveedorRepository repository;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Proveedor> listar() {
        return (List<Proveedor>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Proveedor> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Proveedor> porRuc(String ruc) {return repository.findByRuc(ruc);}
}
