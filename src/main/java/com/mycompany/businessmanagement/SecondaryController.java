package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.controllers.ProductoController;
import com.mycompany.businessmanagement.modelos.Empresa;
import com.mycompany.businessmanagement.modelos.Entidad;

import com.mycompany.businessmanagement.modelos.Producto;
import com.mycompany.businessmanagement.modelos.Tipoiva;
import com.mycompany.businessmanagement.services.FabricanteService;
import com.mycompany.businessmanagement.services.ProductoService;
import com.mycompany.businessmanagement.services.ProveedorService;
import com.mycompany.businessmanagement.services.TipoivaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Button btnEliminarSeleccionadoProducto;
    public Button btnModificarProducto1;
    public Button btnGuardarCambiosProducto;

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

        setupColumnsListarProducto();
        setupColumnsModificarProducto();


        setupTabs();
        btnGuardarCrearProducto.setOnAction(e->guardarProducto());
        btnCancelarCrearProducto.setOnAction(e->cancelarCrearProducto());
        btnEliminarSeleccionadoProducto.setOnAction(e -> {new ProductoService().eliminarProducto(Integer.parseInt(colIdEliminar.getText()));});
        btnGuardarCambiosProducto.setOnAction(e->modificarProducto());

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
        btnEntidadCrudCrear.setOnMouseClicked(e-> {
            activarPaneles(crearProductoPanel);
            cbTipoIvaCrearProducto.getItems().setAll(new TipoivaService().selectAll().stream().map(Tipoiva::getPorcentaje).collect(Collectors.toList()));
        });
        paneles.add(modificarProductoPanel);
        btnEntidadCrudModificar.setOnMouseClicked(e->activarPaneles(modificarProductoPanel));
        paneles.add(eliminarProductoPanel);
        btnModificarProducto1.setOnAction(e->activarPaneles(modificarProductoPanel));
        btnEntidadCrudEliminar.setOnMouseClicked(e-> {
            activarPaneles(eliminarProductoPanel);

            ObservableList<Producto> obs = FXCollections.observableArrayList(new ProductoService().selectAll());

            tvProductosEliminar.getItems().setAll(obs);
        });
        paneles.add(listarProductosPanel);
        btnEntidadCrudListar.setOnMouseClicked(e-> {
            activarPaneles(listarProductosPanel);

            ObservableList<Producto> obs = FXCollections.observableArrayList(new ProductoService().selectAll());
            tvProductosListar.setItems(obs);
        });
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




    //Crear producto campos
    public TextField tfCodigoCrearProducto;
    public TextField tfDescripcionCrearProducto;
    public TextField tfDescripcionAuxCrearProducto;
    public TextField tfPrecioCosteCrearProducto;
    public TextField tfPrecioVentaCrearProducto;
    public TextField tfStockActualCrearProducto;
    public TextArea taObservacionesCrearProducto;
    public TextField tfProveedorCrearProducto;
    public Button btnBuscarProveedorCrearProducto;
    public TextField tfFabricanteCrearProducto;
    public Button btnBuscarFabricanteCrearProducto;
    public ComboBox cbTipoIvaCrearProducto;
    public Button btnGuardarCrearProducto;
    public Button btnCancelarCrearProducto;



    public TextField tfCodigoModificarProducto;
    public TextField tfDescripcionModificarProducto;
    public TextField tfDescripcionAuxModificarProducto;
    public TextField tfPrecioCosteModificarProducto;
    public TextField tfPrecioVentaModificarProducto;
    public TextField tfStockActualModificarProducto;
    public TextArea taObservacionesModificarProducto;
    public TextField tfProveedorModificarProducto;
    public TextField tfFabricanteModificarProducto;
    public ComboBox cbTipoIvaModificarProducto;


    private void modificarProducto(){
        ProductoService pr = new ProductoService();
        try{
            pr.eliminarProducto(pr.selectAll().stream().filter(p->p.getCodigo().equals(tfCodigoModificarProducto.getText())).findFirst().orElse(null).getId());
            if(new FabricanteService().existeFabricante(Integer.parseInt(tfFabricanteModificarProducto.getText())))throw new Exception("No existe el fabricante");
            if(new ProveedorService().existeProveedor(Integer.parseInt(tfProveedorModificarProducto.getText())))throw new Exception("No existe el proveedor");


            Producto producto = new ProductoController().crearProducto(new Producto(0,tfCodigoModificarProducto.getText(),tfDescripcionModificarProducto.getText(),tfDescripcionAuxModificarProducto.getText(),Integer.parseInt(tfPrecioCosteModificarProducto.getText()),Integer.parseInt(tfPrecioVentaModificarProducto.getText()),taObservacionesModificarProducto.getText(),Integer.parseInt(tfStockActualModificarProducto.getText()),Integer.parseInt(tfProveedorModificarProducto.getText()),Integer.parseInt(tfFabricanteModificarProducto.getText()),cbTipoIvaModificarProducto.getSelectionModel().getSelectedIndex()));
            new ProductoService().crearProducto(producto);
        } catch (Exception e) {
            System.out.println("Error guardando producto: " + e.getMessage());
        }
    }


    private void guardarProducto(){
        try{
            if(new FabricanteService().existeFabricante(Integer.parseInt(tfFabricanteCrearProducto.getText())))throw new Exception("No existe el fabricante");
            if(new ProveedorService().existeProveedor(Integer.parseInt(tfProveedorCrearProducto.getText())))throw new Exception("No existe el proveedor");


            Producto producto = new ProductoController().crearProducto(new Producto(0,tfCodigoCrearProducto.getText(),tfDescripcionCrearProducto.getText(),tfDescripcionAuxCrearProducto.getText(),Integer.parseInt(tfPrecioCosteCrearProducto.getText()),Integer.parseInt(tfPrecioVentaCrearProducto.getText()),taObservacionesCrearProducto.getText(),Integer.parseInt(tfStockActualCrearProducto.getText()),Integer.parseInt(tfProveedorCrearProducto.getText()),Integer.parseInt(tfFabricanteCrearProducto.getText()),cbTipoIvaCrearProducto.getSelectionModel().getSelectedIndex()));
            new ProductoService().crearProducto(producto);
        } catch (Exception e) {
            System.out.println("Error guardando producto: " + e.getMessage());
        }
    }


    private void cancelarCrearProducto(){
        tfCodigoCrearProducto.clear();
        tfDescripcionCrearProducto.clear();
        tfDescripcionAuxCrearProducto.clear();
        tfPrecioCosteCrearProducto.clear();
        tfPrecioVentaCrearProducto.clear();
        taObservacionesCrearProducto.clear();
        tfStockActualCrearProducto.clear();
        tfProveedorCrearProducto.clear();
        tfFabricanteCrearProducto.clear();
        cbTipoIvaCrearProducto.getSelectionModel().clearSelection();
    }



    @FXML private TableView<Producto> tvProductosListar;
    @FXML private TableColumn<Producto, String> colCodigoListar;
    @FXML private TableColumn<Producto, Integer> colPrecioCosteListar;
    @FXML private TableColumn<Producto, Integer> colPrecioVentaListar;
    @FXML private TableColumn<Producto, String> colRefProveedorListar;
    @FXML private TableColumn<Producto, Integer> colStockListar;


    private void setupColumnsListarProducto(){
        colCodigoListar.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colPrecioCosteListar.setCellValueFactory(new PropertyValueFactory<>("precio_coste"));
        colPrecioVentaListar.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        colRefProveedorListar.setCellValueFactory(new PropertyValueFactory<>("referencia_proveedor"));
        colStockListar.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }


    @FXML private TableView<Producto> tvProductosEliminar;

    @FXML private TableColumn<Producto, Integer> colIdEliminar;
    @FXML private TableColumn<Producto, String> colCodigoEliminar;
    @FXML private TableColumn<Producto, String> colDescripcionEliminar;
    @FXML private TableColumn<Producto, String> colDescripcionAuxEliminar;
    @FXML private TableColumn<Producto, Integer> colPrecioCosteEliminar;
    @FXML private TableColumn<Producto, Integer> colPrecioVentaEliminar;
    @FXML private TableColumn<Producto, String> colRefProveedorEliminar;
    @FXML private TableColumn<Producto, Integer> colStockEliminar;
    @FXML private TableColumn<Producto, Integer> colProveedorEliminar;
    @FXML private TableColumn<Producto, Integer> colFabricanteEliminar;
    @FXML private TableColumn<Producto, Integer> colTipoIvaEliminar;


    private void setupColumnsModificarProducto() {
        colIdEliminar.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCodigoEliminar.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDescripcionEliminar.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colDescripcionAuxEliminar.setCellValueFactory(new PropertyValueFactory<>("descripcionAux"));
        colPrecioCosteEliminar.setCellValueFactory(new PropertyValueFactory<>("precioCoste"));
        colPrecioVentaEliminar.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colRefProveedorEliminar.setCellValueFactory(new PropertyValueFactory<>("refProveedor"));
        colStockEliminar.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProveedorEliminar.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colFabricanteEliminar.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        colTipoIvaEliminar.setCellValueFactory(new PropertyValueFactory<>("tipoIva"));
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
