package com.mycompany.businessmanagement.modelos;


import java.time.LocalDate;

public class Movimientostock{
private int id;
private int fk_id_producto;
private LocalDate fecha;
private double cantidad;
private String motivo;
private String tipo;

public Movimientostock( int id, int fk_id_producto, LocalDate fecha, double cantidad, String motivo, String tipo )  {
this.id = id;
this.fk_id_producto = fk_id_producto;
this.fecha = fecha;
this.cantidad = cantidad;
this.motivo = motivo;
this.tipo = tipo;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public int getFk_id_producto() {
    return fk_id_producto;
}
 
public void setFk_id_producto(int fk_id_producto) {
    this.fk_id_producto = fk_id_producto;
}
 
public LocalDate getFecha() {
    return fecha;
}
 
public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
}
 
public double getCantidad() {
    return cantidad;
}
 
public void setCantidad(double cantidad) {
    this.cantidad = cantidad;
}
 
public String getMotivo() {
    return motivo;
}
 
public void setMotivo(String motivo) {
    this.motivo = motivo;
}
 
public String getTipo() {
    return tipo;
}
 
public void setTipo(String tipo) {
    this.tipo = tipo;
}
 


}