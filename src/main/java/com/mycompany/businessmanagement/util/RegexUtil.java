package com.mycompany.businessmanagement.util;

import java.time.LocalDate;

public class RegexUtil {

    public static String regexNIF(String s) throws Exception {
        if (s.matches("^[0-9]{8}[A-Za-z]$")) {
            return s;
        }
        throw new Exception("El NIF no es correcto");
    }

    public static String regexNIE(String s) throws Exception {
        if (s.matches("^[XYZxyz][0-9]{7}[A-Za-z]$")) {
            return s;
        }
        throw new Exception("El NIE no es correcto");
    }

    public static String regexCIF(String s) throws Exception {
        if (s.matches("^[A-HJNPQRSUVW][0-9]{7}[0-9A-J]$")) {
            return s;
        }
        throw new Exception("El CIF no es correcto");
    }

    public static String telefonoEspanol(String s) throws Exception {
        if (s.matches("^(?:\\+34|0034)?\\s?(6|7|8|9)\\d{8}$")) {
            return s;
        }
        throw new Exception("El número de teléfono no es correcto");
    }

    public static String regexCIF_NIE_NIF(String s) throws Exception {
        if (s.matches("^([0-9]{8}[A-Za-z]|[XYZxyz][0-9]{7}[A-Za-z]|[A-HJNPQRSUVW][0-9]{7}[0-9A-J])$")) {
            return s;
        }
        throw new Exception("El NIF no es correcto");
    }

    public static String codCli_Pro(String s) throws Exception {
        if (s.matches("^[0-9]+$")) {
            return s;
        }
        throw new Exception("El código de cliente/proveedor no es correcto");
    }

    public static String codProducto(String s) throws Exception {
        if (s.matches("^[A-Za-z0-9_-]{1,13}$")) {
            return s;
        }
        throw new Exception("El código de producto no es correcto");
    }

    public static String desc_articulos(String s) throws Exception {
        if (s.matches("^[\\w\\s.,;:()\\-ºªáéíóúÁÉÍÓÚñÑ/]{1,200}$")) {
            return s;
        }
        throw new Exception("La descripción del artículo no es correcta");
    }

    public static String tipoIva(String s) throws Exception {
        if (s.matches("^(0|4|10|21)$")) {
            return s;
        }
        throw new Exception("El tipo de IVA no es correcto");
    }

    public static String codProvHabitual(String s) throws Exception {
        if (s.matches("^[0-9]+$")) {
            return s;
        }
        throw new Exception("El código de proveedor habitual no es correcto");
    }

    public static String refProv(String s) throws Exception {
        if (s.matches("^[A-Za-z0-9\\-_/]{1,30}$")) {
            return s;
        }
        throw new Exception("La referencia del proveedor no es correcta");
    }

    public static String fabNombre(String s) throws Exception {
        if (s.matches("^[A-Za-z0-9\\s.,\\-áéíóúÁÉÍÓÚñÑ]{1,60}$")) {
            return s;
        }
        throw new Exception("El nombre del fabricante no es correcto");
    }

    public static String precioCosto(String s) throws Exception {
        if (s.matches("^[0-9]+([.,][0-9]{1,2})?$")) {
            return s;
        }
        throw new Exception("El precio de costo no es correcto");
    }

    public static String fecha(String s) throws Exception {
        if (s.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            return s;
        }
        throw new Exception("La fecha no es correcta (formato dd/mm/yyyy)");
    }

    public static String numFactura(String s) throws Exception {
        if (s.matches("^[0-9]+$")) {
            return s;
        }
        throw new Exception("El número de factura no es correcto");
    }

    public static String cantidad(String s) throws Exception {
        if (s.matches("^[0-9]+([.,][0-9]+)?$")) {
            return s;
        }
        throw new Exception("La cantidad no es correcta");
    }

    public static String importe(String s) throws Exception {
        if (s.matches("^[0-9]+([.,][0-9]{1,2})?$")) {
            return s;
        }
        throw new Exception("El importe no es correcto");
    }

    public static String email(String s) throws Exception {
        if (s.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return s;
        }
        throw new Exception("El email no es correcto");
    }

    public static String codigoPostal(String s) throws Exception {
        if (s.matches("^[0-9]{5}$")) {
            return s;
        }
        throw new Exception("El código postal no es correcto (deben ser 5 dígitos).");
    }

    public static String ciudad(String s) throws Exception {
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s-]{2,40}$")) {
            return s;
        }
        throw new Exception("La ciudad no es válida.");
    }

    public static String provincia(String s) throws Exception {
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s-]{2,40}$")) {
            return s;
        }
        throw new Exception("La provincia no es válida.");
    }

    public static String pais(String s) throws Exception {
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s-]{2,40}$")) {
            return s;
        }
        throw new Exception("El país no es válido.");
    }

    public static String direccion(String s) throws Exception {
        if (s.matches("^[A-Za-z0-9ÁÉÍÓÚáéíóúÑñ\\s.,#ºª/-]{5,100}$")) {
            return s;
        }
        throw new Exception("La dirección no es válida.");
    }

    public static String etiqueta(String s) throws Exception {
        if (s == null || s.isBlank()) return s;
        if (s.matches("^[A-Za-z0-9ÁÉÍÓÚáéíóúÑñ\\s-]{1,30}$")) {
            return s;
        }
        throw new Exception("La etiqueta no es válida.");
    }
     

    public static String nombre(String s) throws Exception {
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s.,-]{2,60}$")) return s;
        throw new Exception("El nombre no es válido.");
    }

    public static String web(String s) throws Exception {
        if (s.matches("^(https?://)?[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) return s;
        throw new Exception("La web no es válida.");
    }

    public static String concepto(String s) throws Exception {
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s.,;:()/-]{3,200}$")) return s;
        throw new Exception("El concepto no es válido.");
    }

    public static double decimal(double n) throws Exception {
        if (n >= 0) return n;
        throw new Exception("El valor numérico no puede ser negativo.");
    }

    public static String estadoFactura(String s) throws Exception {
        if (s.matches("^(PENDIENTE|PAGADA|ANULADA)$")) return s;
        throw new Exception("El estado de la factura no es válido.");
    }

    public static String tipoFactura(String s) throws Exception {
        if (s.matches("^(SERVICIO|PRODUCTO)$")) return s;
        throw new Exception("El tipo de factura no es válido.");
    }

    public static String textoOpcional(String s) throws Exception {
        if (s == null || s.isBlank()) return s;
        if (s.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s.,;:()/-]{0,300}$")) return s;
        throw new Exception("Las observaciones no son válidas.");
    }

    public static java.time.LocalDate fecha(LocalDate f) throws Exception {
        if (f != null) return f;
        throw new Exception("La fecha no es válida.");
    }

}
