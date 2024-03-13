package org.dharce.springcloud.msvcproveedor.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor_insumo")
public class ProveedorInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "insumo_id") // Se elimino unique = true -> se debe crear un metodo para validar la asignacion de un usuario a un solo curso
    private Long insumoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Long insumoId) {
        this.insumoId = insumoId;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(!(obj instanceof ProveedorInsumo)){
            return false;
        }
        ProveedorInsumo o = (ProveedorInsumo) obj;
        return this.insumoId!=null && this.insumoId.equals(o.insumoId);
    }
}
