/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.businessmanagement;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import com.mycompany.businessmanagement.conexion.Conexion;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author alexd
 */
public class BotonGenerarPdf {
    
    public void generarPDF(int idFactura) {

    try {
        // Cargar el .jrxml como recurso
        InputStream reporteStream = getClass().getResourceAsStream("/reportes/factura.jrxml");

        // Compilar
        JasperReport reporte = JasperCompileManager.compileReport(reporteStream);

        // Parámetros
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID_FACTURA", idFactura);

        // Conexión a BD
       Connection conn = Conexion.getConnection();

        // Rellenar informe
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, conn);

        // Guardar PDF
        String outPath = "factura_" + idFactura + ".pdf";
        JasperExportManager.exportReportToPdfFile(print, outPath);

        System.out.println("PDF generado correctamente: " + outPath);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
