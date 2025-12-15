package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.DireccionDAO;
import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.DAOS.InformacionDAO;
import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.controllers.DireccionController;
import com.mycompany.businessmanagement.controllers.EmpresaController;
import com.mycompany.businessmanagement.controllers.InformacionController;
import com.mycompany.businessmanagement.modelos.Direccion;
import com.mycompany.businessmanagement.modelos.Empresa;
import com.mycompany.businessmanagement.modelos.Informacion;
import com.mycompany.businessmanagement.services.DireccionService;
import com.mycompany.businessmanagement.services.EmpresaService;
import com.mycompany.businessmanagement.services.InformacionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PrimaryController {


    public TextField nifTextField;
    public TextField emailTextField;
    public TextField telefonoTextField;
    public TextField etiquetaTextField;
    public TextField paisTextField;
    public TextField provinciaTextField;
    public TextField ciudadTextField;
    public TextField codigoPostalTextField;
    public TextField direccionTextField;
    public TextField webTextField;
    public TextField nombreTextField;
    public TextField codigoTextField;
    public Label IdError;
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
    private ListView<Empresa> empresasListView;
    @FXML
    private Button cargarButton;
            
        
    
    private EmpresaDAO empresaDAO = new EmpresaDAO(); // inyectado desde PrimaryController



    @FXML
    private void initialize() {
        

        // conectar botones con acciones (si no lo haces en FXML)
        nuevoButton.setOnMouseClicked(e -> botonNuevoAction());
        abrirButton.setOnMouseClicked(e -> botonAbrirAction());
        guardarButton.setOnMouseClicked(e -> {
            try {
                guardarAction();
            } catch (SQLException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        empresasListView.setCellFactory(lv -> new ListCell<Empresa>() {
            @Override
            protected void updateItem(Empresa empresa, boolean empty) {
                super.updateItem(empresa, empty);
                if (empty || empresa == null) {
                    setText(null); // Si el item es vacío, no mostrar nada
                } else {
                    setText(empresa.getNombre()); // Mostrar el nombre de la empresa
                }
            }
        });
     cargarButton.setOnAction(e->cargarBoton());
        
//        if (guardarButton != null) guardarButton.setOnAction(e -> guardarEmpresa());
//        if (cancelarButton != null) cancelarButton.setOnAction(e -> cerrarVentana());
    }
        
        @FXML
        private void cargarBoton(){
         try {
             SecondaryController.empresaEnUso = empresasListView.getSelectionModel().getSelectedItem();
            App.setRoot("secondary");
        } catch (Exception e) {
        e.printStackTrace();
        }
     }
        
        
    private void guardarAction() throws SQLException {
        Connection conn = Conexion.getConnection();
        try{
            conn.setAutoCommit(false);
            Informacion informacion= new InformacionController().crearInformacion(new Informacion(0,nifTextField.getText(),emailTextField.getText(),telefonoTextField.getText()));
            Direccion direccion = new DireccionController().crearDireccion(new Direccion(0,direccionTextField.getText(),codigoPostalTextField.getText(),ciudadTextField.getText(),provinciaTextField.getText(),paisTextField.getText(),etiquetaTextField.getText()));
            new InformacionService().crearInformacion(informacion);
            new DireccionService().crearDireccion(direccion);
            Empresa empresa = new EmpresaController().crearEmpresa(new Empresa(0,Integer.parseInt(codigoTextField.getText()),nombreTextField.getText(),webTextField.getText(),direccion.getId(),informacion.getId()));
            new EmpresaService().crearEmpresa(empresa);
            conn.commit();
            IdError.setVisible(true);
            IdError.setText("Empresa creada con exito");
        } catch (Exception e){
            try{
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            IdError.setVisible(true);
            IdError.setText(e.getMessage());
        }
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
        
       
       
        List<Empresa> empresas = new EmpresaService().selectAll();
         ObservableList<Empresa> nombres = FXCollections.observableArrayList(empresas);
               


        empresasListView.setItems(nombres);

    }


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
