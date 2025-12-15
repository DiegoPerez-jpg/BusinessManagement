package com.mycompany.businessmanagement.DTO;

import com.mycompany.businessmanagement.modelos.Direccion;
import com.mycompany.businessmanagement.modelos.Informacion;

public class ClienteCompletoDTO {

    private int idCliente;
    private int codigo;
    private String nombre;

    private Informacion informacion;
    private Direccion direccion;

    public ClienteCompletoDTO() {}

    public ClienteCompletoDTO(int idCliente, int codigo, String nombre,
                              Informacion informacion, Direccion direccion) {
        this.idCliente = idCliente;
        this.codigo = codigo;
        this.nombre = nombre;
        this.informacion = informacion;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Informacion getInformacion() {
        return informacion;
    }

    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString(){
        return "Nombre:" + getNombre();
    }
}