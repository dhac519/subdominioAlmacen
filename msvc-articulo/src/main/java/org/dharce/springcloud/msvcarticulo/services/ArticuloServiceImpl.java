package org.dharce.springcloud.msvcarticulo.services;

import org.dharce.springcloud.msvcarticulo.models.entity.Articulo;
import org.dharce.springcloud.msvcarticulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private ArticuloRepository repository;
    @Override
    @Transactional
    public List<Articulo> listar() {
        return (List<Articulo>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Articulo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Articulo guardar(Articulo articulo) {
        return repository.save(articulo);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Articulo> porNombre(String name) {return repository.findByName(name);}
}
