package com.mycompany.businessmanagement.modelos;


public class Facturadetalle{
private int fk_id_factura;
private int fk_id_producto;
private double cantidad;
private double precio_unitario;
private double total_linea;

public Facturadetalle( int fk_id_factura, int fk_id_producto, double cantidad, double precio_unitario, double total_linea )  {
this.fk_id_factura = fk_id_factura;
this.fk_id_producto = fk_id_producto;
this.cantidad = cantidad;
this.precio_unitario = precio_unitario;
this.total_linea = total_linea;
}

public int getFk_id_factura() {
    return fk_id_factura;
}
 
public void setFk_id_factura(int fk_id_factura) {
    this.fk_id_factura = fk_id_factura;
}
 
public int getFk_id_producto() {
    return fk_id_producto;
}
 
public void setFk_id_producto(int fk_id_producto) {
    this.fk_id_producto = fk_id_producto;
}
 
public double getCantidad() {
    return cantidad;
}
 
public void setCantidad(double cantidad) {
    this.cantidad = cantidad;
}
 
public double getPrecio_unitario() {
    return precio_unitario;
}
 
public void setPrecio_unitario(double precio_unitario) {
    this.precio_unitario = precio_unitario;
}
 
public double getTotal_linea() {
    return total_linea;
}
 
public void setTotal_linea(double total_linea) {
    this.total_linea = total_linea;
}
 


}