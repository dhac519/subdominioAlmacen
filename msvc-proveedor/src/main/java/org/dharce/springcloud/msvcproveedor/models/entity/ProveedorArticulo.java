package org.dharce.springcloud.msvcproveedor.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor_articulo")
public class ProveedorArticulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "articulo_id") // Se elimino unique = true -> se debe crear un metodo para validar la asignacion de un usuario a un solo curso
    private Long articuloId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(!(obj instanceof ProveedorArticulo)){
            return false;
        }
        ProveedorArticulo o = (ProveedorArticulo) obj;
        return this.articuloId!=null && this.articuloId.equals(o.articuloId);
    }
}
