package com.mycompany.businessmanagement.modelos;


public class Entidad{
private int id;
private int codigo;
private String nombre;
private int fk_id_informacion;
private int fk_id_direccion;

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