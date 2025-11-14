package com.mycompany.businessmanagement.modelos;


public class Direccion{
private int id;
private String direccion;
private String codigopostal;
private String ciudad;
private String provincia;
private String pais;
private String etiqueta;

public Direccion( int id, String direccion, String codigopostal, String ciudad, String provincia, String pais, String etiqueta )  {
this.id = id;
this.direccion = direccion;
this.codigopostal = codigopostal;
this.ciudad = ciudad;
this.provincia = provincia;
this.pais = pais;
this.etiqueta = etiqueta;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public String getDireccion() {
    return direccion;
}
 
public void setDireccion(String direccion) {
    this.direccion = direccion;
}
 
public String getCodigopostal() {
    return codigopostal;
}
 
public void setCodigopostal(String codigopostal) {
    this.codigopostal = codigopostal;
}
 
public String getCiudad() {
    return ciudad;
}
 
public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
}
 
public String getProvincia() {
    return provincia;
}
 
public void setProvincia(String provincia) {
    this.provincia = provincia;
}
 
public String getPais() {
    return pais;
}
 
public void setPais(String pais) {
    this.pais = pais;
}
 
public String getEtiqueta() {
    return etiqueta;
}
 
public void setEtiqueta(String etiqueta) {
    this.etiqueta = etiqueta;
}
 


}