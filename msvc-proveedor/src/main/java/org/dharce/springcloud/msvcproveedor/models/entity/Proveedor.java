package org.dharce.springcloud.msvcproveedor.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.dharce.springcloud.msvcproveedor.models.Articulo;
import org.dharce.springcloud.msvcproveedor.models.Insumo;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "\\d{11}")
    private String ruc;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;


    ///Articulo
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "proveedor_id")
    private List<ProveedorArticulo> proveedorArticulos;
    @Transient
    private List<Articulo> articulos;




    //Insumos
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "proveedor_id")
    private List<ProveedorInsumo> proveedorInsumos;
    @Transient
    private List<Insumo> insumo;

    public Proveedor(){
        proveedorArticulos = new LinkedList<>();
        proveedorInsumos = new LinkedList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    //Articulo

    public List<ProveedorArticulo> getProveedorArticulos() {
        return proveedorArticulos;
    }

    public void setProveedorArticulos(List<ProveedorArticulo> proveedorArticulos) {
        this.proveedorArticulos = proveedorArticulos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void addProveedorArticulo(ProveedorArticulo proveedorArticulo){
        proveedorArticulos.add(proveedorArticulo);
    }

    public void removeProveedorArticulo(ProveedorArticulo proveedorArticulo){
        proveedorArticulos.remove(proveedorArticulo);
    }


    //Insumos


    public List<ProveedorInsumo> getProveedorInsumos() {
        return proveedorInsumos;
    }

    public void setProveedorInsumos(List<ProveedorInsumo> proveedorInsumos) {
        this.proveedorInsumos = proveedorInsumos;
    }

    public List<Insumo> getInsumo() {
        return insumo;
    }

    public void setInsumo(List<Insumo> insumo) {
        this.insumo = insumo;
    }

    public void addProveedorInsumo(ProveedorInsumo proveedorInsumo){
        proveedorInsumos.add(proveedorInsumo);
    }

    public void removeProveedorInsumo(ProveedorInsumo proveedorInsumo){
        proveedorInsumos.remove(proveedorInsumo);
    }


}
