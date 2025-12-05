package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.modelos.Empresa;
import com.mycompany.businessmanagement.modelos.Entidad;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfWeb;

    @FXML
    private TextField tfFkDireccion;

    @FXML
    private TextField tfFkInformacion;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private EmpresaDAO empresaDAO; // inyectado desde PrimaryController

    @FXML
    private void initialize() {
        if (btnGuardar != null) btnGuardar.setOnAction(e -> guardarEmpresa());
        if (btnCancelar != null) btnCancelar.setOnAction(e -> cerrarVentana());
    }
    // Método para que PrimaryController le pase el DAO
    public void setEmpresaDAO(EmpresaDAO dao) {
        this.empresaDAO = dao;
    }

    private void guardarEmpresa() {
        try {
            String nombre = tfNombre != null ? tfNombre.getText().trim() : "";
            String web = tfWeb != null ? tfWeb.getText().trim() : "";
            int codigo = 0;
            int fkDireccion = 0;
            int fkInformacion = 0;

            if (tfCodigo != null && !tfCodigo.getText().trim().isEmpty()) {
                codigo = Integer.parseInt(tfCodigo.getText().trim());
            }
            if (tfFkDireccion != null && !tfFkDireccion.getText().trim().isEmpty()) {
                fkDireccion = Integer.parseInt(tfFkDireccion.getText().trim());
            }
            if (tfFkInformacion != null && !tfFkInformacion.getText().trim().isEmpty()) {
                fkInformacion = Integer.parseInt(tfFkInformacion.getText().trim());
            }

            if (nombre.isEmpty()) {
                System.err.println("El nombre es obligatorio");
                return;
            }

            // Usamos el constructor que nos indicaste: Empresa(int id, int codigo, String nombre, String web, int fk_id_direccion, int fk_id_informacion)
            Empresa e = new Empresa(0, codigo, nombre, web, fkDireccion, fkInformacion);

            if (empresaDAO != null) {
                Empresa inserted = empresaDAO.insert(e); // el DAO debería asignar el id generado
                if (inserted != null) {
                    System.out.println("Empresa guardada con id = " + inserted.getId());
                } else {
                    System.err.println("Error al guardar la empresa (DAO devolvió null).");
                }
            } else {
                System.err.println("empresaDAO no inyectado: la empresa no se ha guardado en BD.");
            }

            // Cierra ventana después de guardar
            cerrarVentana();

        } catch (NumberFormatException nfe) {
            System.err.println("Los campos numéricos deben contener números válidos: " + nfe.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error guardando empresa: " + ex.getMessage());
        }
    }

    private void cerrarVentana() {
        if (btnCancelar != null && btnCancelar.getScene() != null) {
            Stage s = (Stage) btnCancelar.getScene().getWindow();
            s.close();
        } else {
            try {
                Stage stage = (Stage) tfNombre.getScene().getWindow();
                stage.close();
            } catch (Exception ignore) {}
        }
    }
}
