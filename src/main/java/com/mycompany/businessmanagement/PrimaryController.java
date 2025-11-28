package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.modelos.Empresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    
    @FXML
    private AnchorPane nuevoPanel;
    @FXML
    private AnchorPane cargarPanel;

    
    @FXML 
    private Button nuevoButton;
    @FXML 
    private Button abrirButton;
    @FXML 
    private Button guardarButton;
    @FXML 
    private Button cancelarButton;
    
    @FXML
    private ListView empresasListView;
    @FXML
    private Button cargarButton;
            
        
    
    private EmpresaDAO empresaDAO = new EmpresaDAO(); // inyectado desde PrimaryController



    @FXML
    private void initialize() {
        

        // conectar botones con acciones (si no lo haces en FXML)
        nuevoButton.setOnMouseClicked(e -> botonNuevoAction());
        abrirButton.setOnMouseClicked(e -> botonAbrirAction());
        
//        if (guardarButton != null) guardarButton.setOnAction(e -> guardarEmpresa());
//        if (cancelarButton != null) cancelarButton.setOnAction(e -> cerrarVentana());
    }

    private void botonNuevoAction() {
        nuevoPanel.setVisible(true);        
        nuevoPanel.setDisable(false);
        
        cargarPanel.setVisible(false);        
        cargarPanel.setDisable(true);

    }

    private void botonAbrirAction() {
        
        cargarPanel.setVisible(true);        
        cargarPanel.setDisable(false);
        
        nuevoPanel.setVisible(false);        
        nuevoPanel.setDisable(true);
        
        final ObservableList<String> items;
        try {
            // obtenemos la lista de entidades Empresa y la convertimos a strings sencillos (nombre)
            List<Empresa> empresas = empresaDAO.findAll();
             items = FXCollections.observableArrayList();
            empresas.forEach(e -> items.add(e.getNombre() + " (id:" + e.getId() + ")"));
        } catch (Exception ex) {
            // fallback si algo falla en la BD
//            items = FXCollections.observableArrayList("No se pudieron cargar empresas (error BD)", "Comprueba conexión");
        }

    }


//    private void guardarEmpresa() {
//        try {
//            String nombre = tfNombre != null ? tfNombre.getText().trim() : "";
//            String web = tfWeb != null ? tfWeb.getText().trim() : "";
//            int codigo = 0;
//            int fkDireccion = 0;
//            int fkInformacion = 0;
//
//            if (tfCodigo != null && !tfCodigo.getText().trim().isEmpty()) {
//                codigo = Integer.parseInt(tfCodigo.getText().trim());
//            }
//            if (tfFkDireccion != null && !tfFkDireccion.getText().trim().isEmpty()) {
//                fkDireccion = Integer.parseInt(tfFkDireccion.getText().trim());
//            }
//            if (tfFkInformacion != null && !tfFkInformacion.getText().trim().isEmpty()) {
//                fkInformacion = Integer.parseInt(tfFkInformacion.getText().trim());
//            }
//
//            if (nombre.isEmpty()) {
//                System.err.println("El nombre es obligatorio");
//                return;
//            }
//
//            // Usamos el constructor que nos indicaste: Empresa(int id, int codigo, String nombre, String web, int fk_id_direccion, int fk_id_informacion)
//            Empresa e = new Empresa(0, codigo, nombre, web, fkDireccion, fkInformacion);
//
//            if (empresaDAO != null) {
//                Empresa inserted = empresaDAO.insert(e); // el DAO debería asignar el id generado
//                if (inserted != null) {
//                    System.out.println("Empresa guardada con id = " + inserted.getId());
//                } else {
//                    System.err.println("Error al guardar la empresa (DAO devolvió null).");
//                }
//            } else {
//                System.err.println("empresaDAO no inyectado: la empresa no se ha guardado en BD.");
//            }
//
//            // Cierra ventana después de guardar
//            cerrarVentana();
//
//        } catch (NumberFormatException nfe) {
//            System.err.println("Los campos numéricos deben contener números válidos: " + nfe.getMessage());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.err.println("Error guardando empresa: " + ex.getMessage());
//        }
//    }
//
//    private void cerrarVentana() {
//        if (btnCancelar != null && btnCancelar.getScene() != null) {
//            Stage s = (Stage) btnCancelar.getScene().getWindow();
//            s.close();
//        } else {
//            try {
//                Stage stage = (Stage) tfNombre.getScene().getWindow();
//                stage.close();
//            } catch (Exception ignore) {}
//        }
//    }
}
