package com.mycompany.businessmanagement.modelos;


public class Informacion{
private int id;
private String nif;
private String email;
private String telefono;

public Informacion( int id, String nif, String email, String telefono )  {
this.id = id;
this.nif = nif;
this.email = email;
this.telefono = telefono;
}

public int getId() {
    return id;
}
 
public void setId(int id) {
    this.id = id;
}
 
public String getNif() {
    return nif;
}
 
public void setNif(String nif) {
    this.nif = nif;
}
 
public String getEmail() {
    return email;
}
 
public void setEmail(String email) {
    this.email = email;
}
 
public String getTelefono() {
    return telefono;
}
 
public void setTelefono(String telefono) {
    this.telefono = telefono;
}
 


}