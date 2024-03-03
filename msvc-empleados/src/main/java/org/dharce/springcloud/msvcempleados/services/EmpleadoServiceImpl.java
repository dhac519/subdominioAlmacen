package org.dharce.springcloud.msvcempleados.services;

import org.dharce.springcloud.msvcempleados.models.entity.Empleado;
import org.dharce.springcloud.msvcempleados.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class EmpleadoServiceImpl implements  EmpleadoService{
    @Autowired //para inyectar la dependencia de una clase con metodos
    private EmpleadoRepository repository;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Empleado> listar() {
        return (List<Empleado>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Empleado> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Empleado> porDni(String dni) {return repository.findByDni(dni);}
}
