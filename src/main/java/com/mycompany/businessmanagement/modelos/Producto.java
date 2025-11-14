package com.mycompany.businessmanagement.modelos;


public class Producto{
private int id;
private String codigo;
private String descripcion;
private String descripcion_aux;
private double precio_coste;
private double precio_venta;
private String referencia_proveedor;
private double stock;
private int fk_id_proveedor;
private int fk_id_fabricante;
private int fk_id_tipoiva;

public Producto( int id, String codigo, String descripcion, String descripcion_aux, double precio_coste, double precio_venta, String referencia_proveedor, double stock, int fk_id_proveedor, int fk_id_fabricante, int fk_id_tipoiva )  {
this.id = id;
this.codigo = codigo;
this.descripcion = descripcion;
this.descripcion_aux = descripcion_aux;
this.precio_coste = precio_coste;
this.precio_venta = precio_venta;
this.referencia_proveedor = referencia_proveedor;
this.stock = stock;
this.fk_id_proveedor = fk_id_proveedor;
this.fk_id_fabricante = fk_id_fabricante;
this.fk_id_tipoiva = fk_id_tipoiva;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public String getCodigo() {
    return codigo;
}
 
public void setCodigo(String codigo) {
    this.codigo = codigo;
}
 
public String getDescripcion() {
    return descripcion;
}
 
public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}
 
public String getDescripcion_aux() {
    return descripcion_aux;
}
 
public void setDescripcion_aux(String descripcion_aux) {
    this.descripcion_aux = descripcion_aux;
}
 
public double getPrecio_coste() {
    return precio_coste;
}
 
public void setPrecio_coste(double precio_coste) {
    this.precio_coste = precio_coste;
}
 
public double getPrecio_venta() {
    return precio_venta;
}
 
public void setPrecio_venta(double precio_venta) {
    this.precio_venta = precio_venta;
}
 
public String getReferencia_proveedor() {
    return referencia_proveedor;
}
 
public void setReferencia_proveedor(String referencia_proveedor) {
    this.referencia_proveedor = referencia_proveedor;
}
 
public double getStock() {
    return stock;
}
 
public void setStock(double stock) {
    this.stock = stock;
}
 
public int getFk_id_proveedor() {
    return fk_id_proveedor;
}
 
public void setFk_id_proveedor(int fk_id_proveedor) {
    this.fk_id_proveedor = fk_id_proveedor;
}
 
public int getFk_id_fabricante() {
    return fk_id_fabricante;
}
 
public void setFk_id_fabricante(int fk_id_fabricante) {
    this.fk_id_fabricante = fk_id_fabricante;
}
 
public int getFk_id_tipoiva() {
    return fk_id_tipoiva;
}
 
public void setFk_id_tipoiva(int fk_id_tipoiva) {
    this.fk_id_tipoiva = fk_id_tipoiva;
}
 


}