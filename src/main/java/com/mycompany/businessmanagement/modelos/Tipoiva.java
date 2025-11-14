package com.mycompany.businessmanagement.modelos;


public class Tipoiva{
private int id;
private String concepto;
private double porcentaje;

public Tipoiva( int id, String concepto, double porcentaje )  {
this.id = id;
this.concepto = concepto;
this.porcentaje = porcentaje;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public String getConcepto() {
    return concepto;
}
 
public void setConcepto(String concepto) {
    this.concepto = concepto;
}
 
public double getPorcentaje() {
    return porcentaje;
}
 
public void setPorcentaje(double porcentaje) {
    this.porcentaje = porcentaje;
}
 


}