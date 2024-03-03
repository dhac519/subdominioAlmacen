package org.dharce.springcloud.msvcinsumos.services;

import org.dharce.springcloud.msvcinsumos.models.entity.Insumo;
import org.dharce.springcloud.msvcinsumos.repositories.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class InsumoSrviceImpl implements InsumoService{
    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private InsumoRepository repository;
    @Override
    @Transactional
    public List<Insumo> listar() {
        return (List<Insumo>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Insumo> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Insumo guardar(Insumo articulo) {
        return repository.save(articulo);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Insumo> porNombre(String name) {return repository.findByName(name);}
}
