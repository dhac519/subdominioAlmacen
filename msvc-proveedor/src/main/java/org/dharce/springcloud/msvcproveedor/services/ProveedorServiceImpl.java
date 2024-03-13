package org.dharce.springcloud.msvcproveedor.services;

import org.dharce.springcloud.msvcproveedor.clients.ArticuloClientRest;
import org.dharce.springcloud.msvcproveedor.clients.InsumoClientRest;
import org.dharce.springcloud.msvcproveedor.models.Articulo;
import org.dharce.springcloud.msvcproveedor.models.Insumo;
import org.dharce.springcloud.msvcproveedor.models.entity.Proveedor;
import org.dharce.springcloud.msvcproveedor.models.entity.ProveedorArticulo;
import org.dharce.springcloud.msvcproveedor.models.entity.ProveedorInsumo;
import org.dharce.springcloud.msvcproveedor.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements  ProveedorService{
    @Autowired //para inyectar la dependencia de una clase con metodos
    private ProveedorRepository repository;
    @Autowired
    private ArticuloClientRest clientRest;
    @Autowired
    private InsumoClientRest insumoClientRest;

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

        // Articulo

    //Implementación de metodos remotos relacionados al cliente Http
    // (a ApiRest, comunicacion con el otro mscv)

    @Override
    @Transactional
    public Optional<Articulo> asignarArticulo(Articulo articulo, Long proveedorId) {
        Optional<Proveedor> o = repository.findById(proveedorId);

        if(o.isPresent()){
            Articulo usuarioMsvc = clientRest.detalle(articulo.getId());
            Proveedor proveedor = o.get();
            //aca va la validacion
            ProveedorArticulo proveedorArticulo = new ProveedorArticulo();
            proveedorArticulo.setArticuloId(usuarioMsvc.getId());
            for (ProveedorArticulo cu : proveedor.getProveedorArticulos()) {
                if(proveedorArticulo.getId() == cu.getId()){
                    return Optional.empty();
                }
            }
            proveedor.addProveedorArticulo(proveedorArticulo);

            repository.save(proveedor);
            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Articulo> crearArticulo(Articulo articulo, Long proveedorId) {
        Optional<Proveedor> o = repository.findById(proveedorId);
        if(o.isPresent()){
            Articulo usuarioNewMsvc = clientRest.crear(articulo);
            Proveedor proveedor = o.get();

            ProveedorArticulo cursoUsuario = new ProveedorArticulo();
            cursoUsuario.setArticuloId(usuarioNewMsvc.getId());


            proveedor.addProveedorArticulo(cursoUsuario);
            repository.save(proveedor);
            return Optional.of(usuarioNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Articulo> eliminarArticulo(Articulo articulo, Long cursoId) {
        Optional<Proveedor> o = repository.findById(cursoId);
        if(o.isPresent()){
            Articulo articuloMsvc = clientRest.detalle(articulo.getId());
            Proveedor proveedor = o.get();
            ProveedorArticulo proveedorArticulo = new ProveedorArticulo();
            proveedorArticulo.setArticuloId(articuloMsvc.getId());

            proveedor.removeProveedorArticulo(proveedorArticulo);

            repository.save(proveedor);
            return Optional.of(articuloMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> porIdConArticulo(Long id) {
        Optional<Proveedor> o = repository.findById(id);
        if(o.isPresent()){
            Proveedor proveedor = o.get();
            if(!proveedor.getProveedorArticulos().isEmpty()){
                List<Long> ids = proveedor.getProveedorArticulos().stream().map(
                        cu ->cu.getArticuloId()).collect(Collectors.toList());
                List<Articulo> articulos = clientRest.obtenerArticuloPorProveedor(ids);
                proveedor.setArticulos(articulos);
            }
            return Optional.of(proveedor);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarProveedorArticuloPorId(Long id) {
        repository.eliminarProveedorArticuloPorId(id);
    }


    // Insumo

    //Implementación de metodos remotos relacionados al cliente Http
    // (a ApiRest, comunicacion con el otro mscv)

    @Override
    @Transactional
    public Optional<Insumo> asignarInsumo(Insumo insumo, Long proveedorId) {
        Optional<Proveedor> o = repository.findById(proveedorId);

        if(o.isPresent()){
            Insumo insumoMsvc = insumoClientRest.detalle(insumo.getId());
            Proveedor proveedor = o.get();

            ProveedorInsumo proveedorInsumo = new ProveedorInsumo();
            proveedorInsumo.setInsumoId(insumoMsvc.getId());
            proveedor.addProveedorInsumo(proveedorInsumo);

            repository.save(proveedor);
            return Optional.of(insumoMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Insumo> crearInsumo(Insumo insumo, Long proveedorId) {
        Optional<Proveedor> o = repository.findById(proveedorId);
        if(o.isPresent()){
            Insumo insumoNewMsvc = insumoClientRest.crear(insumo);
            Proveedor proveedor = o.get();

            ProveedorInsumo proveedorInsumo = new ProveedorInsumo();
            proveedorInsumo.setInsumoId(insumoNewMsvc.getId());


            proveedor.addProveedorInsumo(proveedorInsumo);
            repository.save(proveedor);
            return Optional.of(insumoNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Insumo> eliminarInsumo(Insumo insumo, Long cursoId) {
        Optional<Proveedor> o = repository.findById(cursoId);
        if(o.isPresent()){
            Insumo insumoMsvc = insumoClientRest.detalle(insumo.getId());
            Proveedor curso = o.get();
            ProveedorInsumo proveedorInsumo = new ProveedorInsumo();
            proveedorInsumo.setInsumoId(insumoMsvc.getId());

            curso.removeProveedorInsumo(proveedorInsumo);

            repository.save(curso);
            return Optional.of(insumoMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> porIdConInsumo(Long id) {
        Optional<Proveedor> o = repository.findById(id);
        if(o.isPresent()){
            Proveedor proveedor = o.get();
            if(!proveedor.getProveedorInsumos().isEmpty()){
                List<Long> ids = proveedor.getProveedorInsumos().stream().map(
                        cu ->cu.getInsumoId()).collect(Collectors.toList());
                List<Insumo> insumos = insumoClientRest.obtenerInsumoPorProveedor(ids);
                proveedor.setInsumo(insumos);
            }
            return Optional.of(proveedor);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarProveedorInsumoPorId(Long id) {
        repository.eliminarProveedorInsumoPorId(id);
    }
}
