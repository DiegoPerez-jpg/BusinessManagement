package com.mycompany.businessmanagement.modelos;

public class Proveedor extends Entidad {

    public Proveedor(int id, int codigo, String nombre, int fk_id_informacion, int fk_id_direccion) {
        super(id, codigo, nombre, fk_id_informacion, fk_id_direccion);
    }

    // Aquí puedes añadir atributos propios de Cliente

}
