package com.mycompany.businessmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfCif;

    @FXML
    private TextField tfDireccion;

    @FXML
    private TextField tfTelefono;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    // Aquí puedes inyectar tu EmpresaDAO si lo tienes
    // private EmpresaDAO empresaDAO;

    @FXML
    private void initialize() {
        if (btnGuardar != null) btnGuardar.setOnAction(e -> guardarEmpresa());
        if (btnCancelar != null) btnCancelar.setOnAction(e -> cerrarVentana());
    }

    // Si vas a pasar DAO desde el PrimaryController, añade este método:
    // public void setEmpresaDAO(EmpresaDAO dao) { this.empresaDAO = dao; }

    private void guardarEmpresa() {
        String nombre = tfNombre != null ? tfNombre.getText().trim() : "";
        String cif = tfCif != null ? tfCif.getText().trim() : "";
        String direccion = tfDireccion != null ? tfDireccion.getText().trim() : "";
        String telefono = tfTelefono != null ? tfTelefono.getText().trim() : "";

        if (nombre.isEmpty()) {
            System.err.println("El nombre es obligatorio");
            return;
        }

        // Crear entidad y guardarla con el DAO (si tienes empresaDAO)
        // Empresa e = new Empresa(...);
        // empresaDAO.insert(e);

        // Cierra ventana después de guardar
        cerrarVentana();
    }

    private void cerrarVentana() {
        // cerrar ventana donde esté el botón cancelar
        if (btnCancelar != null && btnCancelar.getScene() != null) {
            Stage s = (Stage) btnCancelar.getScene().getWindow();
            s.close();
        } else {
            // fallback: intentar cerrar a través de cualquier control
            try {
                Stage stage = (Stage) tfNombre.getScene().getWindow();
                stage.close();
            } catch (Exception ignore) {}
        }
    }
}
