package com.mycompany.businessmanagement.modelos;


import java.time.LocalDate;

public class Factura{
private int id;
private int fk_id_empresa;
private int fk_id_cliente;
private String numero;
private LocalDate fecha_emision;
private LocalDate fecha_servicio;
private String concepto;
private double base_imponible;
private double iva_total;
private double total_factura;
private String estado;
private String observaciones;
private String tipo;

public Factura( int id, int fk_id_empresa, int fk_id_cliente, String numero, LocalDate fecha_emision, LocalDate fecha_servicio, String concepto, double base_imponible, double iva_total, double total_factura, String estado, String observaciones, String tipo )  {
this.id = id;
this.fk_id_empresa = fk_id_empresa;
this.fk_id_cliente = fk_id_cliente;
this.numero = numero;
this.fecha_emision = fecha_emision;
this.fecha_servicio = fecha_servicio;
this.concepto = concepto;
this.base_imponible = base_imponible;
this.iva_total = iva_total;
this.total_factura = total_factura;
this.estado = estado;
this.observaciones = observaciones;
this.tipo = tipo;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public int getFk_id_empresa() {
    return fk_id_empresa;
}
 
public void setFk_id_empresa(int fk_id_empresa) {
    this.fk_id_empresa = fk_id_empresa;
}
 
public int getFk_id_cliente() {
    return fk_id_cliente;
}
 
public void setFk_id_cliente(int fk_id_cliente) {
    this.fk_id_cliente = fk_id_cliente;
}
 
public String getNumero() {
    return numero;
}
 
public void setNumero(String numero) {
    this.numero = numero;
}
 
public LocalDate getFecha_emision() {
    return fecha_emision;
}
 
public void setFecha_emision(LocalDate fecha_emision) {
    this.fecha_emision = fecha_emision;
}
 
public LocalDate getFecha_servicio() {
    return fecha_servicio;
}
 
public void setFecha_servicio(LocalDate fecha_servicio) {
    this.fecha_servicio = fecha_servicio;
}
 
public String getConcepto() {
    return concepto;
}
 
public void setConcepto(String concepto) {
    this.concepto = concepto;
}
 
public double getBase_imponible() {
    return base_imponible;
}
 
public void setBase_imponible(double base_imponible) {
    this.base_imponible = base_imponible;
}
 
public double getIva_total() {
    return iva_total;
}
 
public void setIva_total(double iva_total) {
    this.iva_total = iva_total;
}
 
public double getTotal_factura() {
    return total_factura;
}
 
public void setTotal_factura(double total_factura) {
    this.total_factura = total_factura;
}
 
public String getEstado() {
    return estado;
}
 
public void setEstado(String estado) {
    this.estado = estado;
}
 
public String getObservaciones() {
    return observaciones;
}
 
public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
}
 
public String getTipo() {
    return tipo;
}
 
public void setTipo(String tipo) {
    this.tipo = tipo;
}
 


}