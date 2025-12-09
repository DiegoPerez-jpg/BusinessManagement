package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.modelos.Empresa;
import com.mycompany.businessmanagement.modelos.Entidad;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SecondaryController {

    public Button btnNuevoRibbon;
    public Button btnAbrirRibbon;
    public Button btnMenuEntidad;
    public Button btnMenuProductos;
    public Button btnMenuFacturas;
    public Button btnMenuFabricante;
    public Button btnMenuProveedor;
    public Button btnMenuCliente;

    public HBox entidadesTab;
    public HBox productoTab;
    public HBox facturasTab;




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

    private ArrayList<HBox> tabs = new ArrayList<>();
    private ArrayList<VBox> paneles = new ArrayList<>();

    private EmpresaDAO empresaDAO; // inyectado desde PrimaryController


    public VBox crearProductoPanel;
    public VBox modificarProductoPanel;
    public VBox eliminarProductoPanel;
    public VBox listarProductosPanel;
    public VBox crearFacturaPanel;
    public VBox listarFacturasPanel;

    public Button btnEntidadCrudCrear;
    public Button btnEntidadCrudModificar;
    public Button btnEntidadCrudEliminar;
    public Button btnEntidadCrudListar;


    public Button btnFacturaCrear;
    public Button btnFacturaGenerarPdf;
    public Button btnFacturaListar;
    @FXML
    private void initialize() {
        if (btnGuardar != null) btnGuardar.setOnAction(e -> guardarEmpresa());
        if (btnCancelar != null) btnCancelar.setOnAction(e -> cerrarVentana());



    }


    private void setupTabs(){
        //submenus

        tabs.add(entidadesTab);
        btnMenuEntidad.setOnAction(e->activarTab(entidadesTab));
        tabs.add(productoTab);
        btnMenuProductos.setOnAction(e->activarTab(productoTab));
        tabs.add(facturasTab);
        btnMenuFacturas.setOnAction(e->activarTab(facturasTab));


        //frames

        //productos
        paneles.add(crearProductoPanel);
        btnEntidadCrudCrear.setOnMouseClicked(e->activarPaneles(crearProductoPanel));
        paneles.add(modificarProductoPanel);
        btnEntidadCrudModificar.setOnMouseClicked(e->activarPaneles(modificarProductoPanel));
        paneles.add(eliminarProductoPanel);
        btnEntidadCrudEliminar.setOnMouseClicked(e->activarPaneles(eliminarProductoPanel));
        paneles.add(listarProductosPanel);
        btnEntidadCrudListar.setOnMouseClicked(e->activarPaneles(listarProductosPanel));
        //facturas
        paneles.add(crearFacturaPanel);
        btnFacturaCrear.setOnMouseClicked(e->activarPaneles(crearFacturaPanel));
        paneles.add(listarFacturasPanel);
        btnFacturaListar.setOnMouseClicked(e->activarPaneles(listarFacturasPanel));
    }

    private void desactivarPaneles(VBox h){
        h.setVisible(false);
        h.setDisable(true);
    }

    private void activarPaneles(VBox h){
        paneles.forEach(this::desactivarPaneles);
        h.setDisable(false);
        h.setVisible(true);
    }


    private void desactivarTabs(HBox h){
        h.setVisible(false);
        h.setDisable(true);
    }

    private void activarTab(HBox h){
        tabs.forEach(this::desactivarTabs);
        h.setDisable(false);
        h.setVisible(true);
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
