package com.mycompany.businessmanagement.modelos;


public abstract class Entidad{
protected int id;
protected int codigo;
protected String nombre;
protected int fk_id_informacion;
protected int fk_id_direccion;

public Entidad( int id, int codigo, String nombre, int fk_id_informacion, int fk_id_direccion )  {
this.id = id;
this.codigo = codigo;
this.nombre = nombre;
this.fk_id_informacion = fk_id_informacion;
this.fk_id_direccion = fk_id_direccion;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
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
 
public int getFk_id_informacion() {
    return fk_id_informacion;
}
 
public void setFk_id_informacion(int fk_id_informacion) {
    this.fk_id_informacion = fk_id_informacion;
}
 
public int getFk_id_direccion() {
    return fk_id_direccion;
}
 
public void setFk_id_direccion(int fk_id_direccion) {
    this.fk_id_direccion = fk_id_direccion;
}
 


}