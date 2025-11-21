/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.businessmanagement.util;

/**
 *
 * @author alexd
 */
public class UtilFactuGest {
    public static String regexNIF(String s) throws Exception{
        if(s.matches("^[0-9]{8}[A-Za-z]$")){
                   return s; 
        }
        throw new Exception("El NIF no es correcto");
    } 
    public static String regexNIE(String s) throws Exception{
        if(s.matches("^[XYZxyz][0-9]{7}[A-Za-z]$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
    public static String regexCIF(String s) throws Exception{
        if(s.matches("^[A-HJNPQRSUVW][0-9]{7}[0-9A-J]$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
    public static String regexCIF_NIE_NIF(String s) throws Exception{
        if(s.matches("^([0-9]{8}[A-Za-z]|[XYZxyz][0-9]{7}[A-Za-z]|[A-HJNPQRSUVW][0-9]{7}[0-9A-J])$")){
                   return s; 
        }
        throw new Exception("El documento no es correcto");
    }
    public static String codCli_Pro(String s) throws Exception{
        if(s.matches("^[0-9]+$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
    public static String codProducto(String s) throws Exception{
        if(s.matches("^[A-Za-z0-9_-]{1,13}$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
     public static String desc_articulos(String s) throws Exception{
        if(s.matches("^[\\w\\s.,;:()\\-ºªáéíóúÁÉÍÓÚñÑ/]{1,200}$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
     public static String tipoIva(String s) throws Exception{
        if(s.matches("^(0|4|10|21)$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
     public static String codProvHabitual(String s) throws Exception{
        if(s.matches("^[0-9]+$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
     public static String refProv(String s) throws Exception{
        if(s.matches("^[A-Za-z0-9\\-_/]{1,30}$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
     
      public static String fabNombre(String s) throws Exception{
        if(s.matches("^[A-Za-z0-9\\s.,\\-áéíóúÁÉÍÓÚñÑ]{1,60}$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
       public static String precioCosto(String s) throws Exception{
        if(s.matches("^[0-9]+([.,][0-9]{1,2})?$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
       public static String fecha(String s) throws Exception{
        if(s.matches("^\\d{2}/\\d{2}/\\d{4}$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
       public static String numFactura(String s) throws Exception{
        if(s.matches("^[0-9]+$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
       public static String cantidad(String s) throws Exception{
        if(s.matches("^[0-9]+([.,][0-9]+)?$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
       public static String importe(String s) throws Exception{
        if(s.matches("^[0-9]+([.,][0-9]{1,2})?$")){
                   return s; 
        }
        throw new Exception("El NIE no es correcto");
    }
}
