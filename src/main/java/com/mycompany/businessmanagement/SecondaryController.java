package com.mycompany.businessmanagement;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.DTO.ClienteCompletoDTO;
import com.mycompany.businessmanagement.DTO.FacturaDetalleDTO;
import com.mycompany.businessmanagement.controllers.*;
import com.mycompany.businessmanagement.modelos.*;

import com.mycompany.businessmanagement.services.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SecondaryController {

    public static Empresa empresaEnUso;

    public Button btnNuevoRibbon;
    public Button btnAbrirRibbon;
    public Button btnMenuEntidad;
    public Button btnMenuProductos;
    public Button btnMenuFacturas;

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

    public Button btnGuardarFacturaCrear;
    @FXML
    private void initialize() {
        if (btnGuardar != null) btnGuardar.setOnAction(e -> guardarEmpresa());
        if (btnCancelar != null) btnCancelar.setOnAction(e -> cerrarVentana());

        setupColumnsListarProducto();
        setupColumnsModificarProducto();
        setupFacturas();
        setupColumnsClientes();
        setupTabs();
        btnGuardarClienteCrear.setOnAction(e ->setupCrearClientes());
        btnGuardarCrearProducto.setOnAction(e->guardarProducto());
        btnCancelarCrearProducto.setOnAction(e->cancelarCrearProducto());
        btnEliminarSeleccionadoProducto.setOnAction(e -> {
            if(tvProductosEliminar.getSelectionModel().getSelectedIndex()==-1)return;
            new ProductoService().eliminarProducto(tvProductosEliminar.getSelectionModel().getSelectedItem().getId());});
        btnGuardarCambiosProducto.setOnAction(e->modificarProducto());
        btnGuardarFacturaCrear.setOnAction(e->crearFactura());
        initializeEntidades();
    }


    public VBox panelEntidadCrear;
    public VBox panelEntidadBorrar;
    public VBox panelEntidadListar;

    public Button btnentidadesCrear;
    public Button btnentidadeseliminar;
    public Button btnentidadesListar;


    public Label listadoproveedoreslabel;
    public Label listadofabricanteslabel;
    public Label listadoclienteslabel;


    public Label listadoproveedoreseliminarlabel;
    public Label listadofabricanteseliminarlabel;
    public Label listadoclienteseliminarlabel;

    public Label crearproveedoreslabel;
    public Label crearfabricanteslabel;
    public Label crearclienteslabel;

    private void setupTabs(){
        //submenus

        tabs.add(entidadesTab);
        btnMenuEntidad.setOnAction(e->activarTab(entidadesTab));
        tabs.add(productoTab);
        btnMenuProductos.setOnAction(e->activarTab(productoTab));
        tabs.add(facturasTab);
        btnMenuFacturas.setOnAction(e->activarTab(facturasTab));
        tabs.add(entidadeshbox);

        //entidades
        paneles.add(panelEntidadCrear);
        btnentidadesCrear.setOnAction(e->{
            activarPaneles(panelEntidadCrear);
            List<Label> labels = new ArrayList<>();
            labels.add(crearproveedoreslabel);
            labels.add(crearfabricanteslabel);
            labels.add(crearclienteslabel);
            switchLabel(labels);
        });

        paneles.add(panelEntidadBorrar);
        btnentidadeseliminar.setOnAction(e->{
            activarPaneles(panelEntidadBorrar);
            List<Label> labels = new ArrayList<>();
            labels.add(listadoproveedoreseliminarlabel);
            labels.add(listadofabricanteseliminarlabel);
            labels.add(listadoclienteseliminarlabel);

            switchLabel(labels);
        });
        paneles.add(panelEntidadListar);
        btnentidadesListar.setOnAction(e->{
            activarPaneles(panelEntidadListar);
            List<Label> labels = new ArrayList<>();
            labels.add(listadoproveedoreslabel);
            labels.add(listadofabricanteslabel);
            labels.add(listadoclienteslabel);
            switch(modo){
                case("cliente"):
                    tvClientesListadoCompleto.setItems(FXCollections.observableArrayList(new EntidadService().getAllClientsDto(Cliente.class)));
                case("proveedor"):
                    tvClientesListadoCompleto.setItems(FXCollections.observableArrayList(new EntidadService().getAllClientsDto(Proveedor.class)));
            }
            switchLabel(labels);
        });



        //productos
        paneles.add(crearProductoPanel);
        btnEntidadCrudCrear.setOnMouseClicked(e-> {
            activarPaneles(crearProductoPanel);
            cbTipoIvaCrearProducto.getItems().setAll(new TipoivaService().selectAll().stream().map(Tipoiva::getPorcentaje).collect(Collectors.toList()));
        });
        paneles.add(modificarProductoPanel);
        btnEntidadCrudModificar.setOnMouseClicked(e->activarPaneles(modificarProductoPanel));
        paneles.add(eliminarProductoPanel);
        btnModificarProducto1.setOnAction(e-> {
            activarPaneles(modificarProductoPanel);
            if(tvProductosListar.getSelectionModel().getSelectedIndex()!=-1){
                cargarProductoEnModificar(tvProductosListar.getSelectionModel().getSelectedItem().getId());
            }
        });
        btnEntidadCrudEliminar.setOnMouseClicked(e-> {
            activarPaneles(eliminarProductoPanel);

            ObservableList<Producto> obs = FXCollections.observableArrayList(new ProductoService().selectAll());

            tvProductosEliminar.getItems().setAll(obs);
        });
        paneles.add(listarProductosPanel);
        btnEntidadCrudListar.setOnMouseClicked(e-> {

            ObservableList<Producto> obs = FXCollections.observableArrayList(new ProductoService().selectAll());
            tvProductosListar.setItems(obs);
            activarPaneles(listarProductosPanel);
        });
        //facturas
        paneles.add(crearFacturaPanel);
        btnFacturaCrear.setOnAction(e-> {
            activarPaneles(crearFacturaPanel);
            productos = new HashMap<>();

        });
        paneles.add(listarFacturasPanel);
        btnFacturaListar.setOnMouseClicked(e-> {
            tvFacturasListar.setItems(FXCollections.observableArrayList(new FacturaService().getFacturasCompletasDto()));
            activarPaneles(listarFacturasPanel);
        });
    }

    private void cargarProductoEnModificar(int id) {
        ProductoService productoService = new ProductoService();
        Producto pr = productoService.findById(id);
        tfCodigoModificarProducto.setText(pr.getCodigo());
        tfDescripcionCrearProducto.setText(pr.getDescripcion());
        tfDescripcionAuxCrearProducto.setText(pr.getDescripcion_aux());
        tfPrecioCosteCrearProducto.setText(pr.getPrecio_coste()+"");
        tfPrecioVentaCrearProducto.setText(pr.getPrecio_venta()+"");
        taObservacionesCrearProducto.setText(pr.getReferencia_proveedor());
        tfStockActualCrearProducto.setText(pr.getStock()+"");
        tfProveedorCrearProducto.setText(pr.getFk_id_proveedor()+"");
        tfFabricanteCrearProducto.setText(pr.getFk_id_fabricante()+"");
        cbTipoIvaCrearProducto.getSelectionModel().select(pr.getFk_id_tipoiva());
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

    public Label errorModificarProducto;
    private void modificarProducto(){
        ProductoService pr = new ProductoService();
        try{
            pr.eliminarProducto(pr.selectAll().stream().filter(p->p.getCodigo().equals(tfCodigoModificarProducto.getText())).findFirst().orElse(null).getId());
            if(new FabricanteService().existeFabricante(Integer.parseInt(tfFabricanteModificarProducto.getText())))throw new Exception("No existe el fabricante");
            if(new ProveedorService().existeProveedor(Integer.parseInt(tfProveedorModificarProducto.getText())))throw new Exception("No existe el proveedor");


            Producto producto = new ProductoController().crearProducto(new Producto(0,tfCodigoModificarProducto.getText(),tfDescripcionModificarProducto.getText(),tfDescripcionAuxModificarProducto.getText(),Integer.parseInt(tfPrecioCosteModificarProducto.getText()),Integer.parseInt(tfPrecioVentaModificarProducto.getText()),taObservacionesModificarProducto.getText(),Integer.parseInt(tfStockActualModificarProducto.getText()),Integer.parseInt(tfProveedorModificarProducto.getText()),Integer.parseInt(tfFabricanteModificarProducto.getText()),cbTipoIvaModificarProducto.getSelectionModel().getSelectedIndex()));
            new ProductoService().crearProducto(producto);
        } catch (Exception e) {
            errorModificarProducto.setText(e.getMessage());
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

    @FXML private TableView<FacturaDetalleDTO> tvFacturasListar;

    @FXML private TableColumn<FacturaDetalleDTO, Integer> colIdFacturaListar;
    @FXML private TableColumn<FacturaDetalleDTO, Integer> colIdEmpresaListar;
    @FXML private TableColumn<FacturaDetalleDTO, Integer> colIdClienteListar;
    @FXML private TableColumn<FacturaDetalleDTO, String> colNumeroFacturaListar;
    @FXML private TableColumn<FacturaDetalleDTO, LocalDate> colFechaFacturaListar;
    @FXML private TableColumn<FacturaDetalleDTO, LocalDate> colFechaServicioListar;
    @FXML private TableColumn<FacturaDetalleDTO, String> colConceptoListar;
    @FXML private TableColumn<FacturaDetalleDTO, Double> colBaseImponibleListar;
    @FXML private TableColumn<FacturaDetalleDTO, Double> colIvaTotalListar;
    @FXML private TableColumn<FacturaDetalleDTO, Double> colTotalFacturaListar;
    @FXML private TableColumn<FacturaDetalleDTO, String> colEstadoListar;
    @FXML private TableColumn<FacturaDetalleDTO, String> colTipoListar;
    @FXML private TableColumn<FacturaDetalleDTO, String> colObservacionListar;

    private void setupColumnsFacturas() {
        colIdFacturaListar.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdEmpresaListar.setCellValueFactory(new PropertyValueFactory<>("fk_id_empresa"));
        colIdClienteListar.setCellValueFactory(new PropertyValueFactory<>("fk_id_cliente"));
        colNumeroFacturaListar.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colFechaFacturaListar.setCellValueFactory(new PropertyValueFactory<>("fecha_emision"));
        colFechaServicioListar.setCellValueFactory(new PropertyValueFactory<>("fecha_servicio"));
        colConceptoListar.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        colBaseImponibleListar.setCellValueFactory(new PropertyValueFactory<>("base_imponible"));
        colIvaTotalListar.setCellValueFactory(new PropertyValueFactory<>("iva_total"));
        colTotalFacturaListar.setCellValueFactory(new PropertyValueFactory<>("total_factura"));
        colEstadoListar.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTipoListar.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colObservacionListar.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

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

    public TextField tfIdFacturaCrear;
    public TextField tfIdEmpresaCrear;
    public TextField tfIdClienteCrear;
    public TextField tfNumeroFacturaCrear;
    public TextField tfFechaFacturaCrear;
    public TextField tfFechaServicioCrear;

    public TextField tfBaseImponibleCrear;
    public TextField tfIvaTotalCrear;
    public TextField tfTotalFacturaCrear;

    public ComboBox<String> cbEstadoCrear;
    public ComboBox<String> cbTipoCrear;

    public TextArea taConceptoCrear;
    public TextArea taObservacionCrear;


    public void crearFactura(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Factura factura = new Factura(
                0,                 // id
                Integer.parseInt(tfIdEmpresaCrear.getText()),                 // fk_id_empresa
                Integer.parseInt(tfIdClienteCrear.getText()),                 // fk_id_cliente
                tfNumeroFacturaCrear.getText(),                               // numero
                LocalDate.parse(tfFechaFacturaCrear.getText(), fmt),          // fecha_emision
                LocalDate.parse(tfFechaServicioCrear.getText(), fmt),         // fecha_servicio
                taConceptoCrear.getText(),                                    // concepto
                Double.parseDouble(tfBaseImponibleCrear.getText()),           // base_imponible
                Double.parseDouble(tfIvaTotalCrear.getText()),                // iva_total
                Double.parseDouble(tfTotalFacturaCrear.getText()),            // total_factura
                cbEstadoCrear.getValue(),                                     // estado
                taObservacionCrear.getText(),                                 // observaciones
                cbTipoCrear.getValue()                                        // tipo
        );






        try{
            new FacturaService().crearFactura((new FacturaController().crearFactura(factura)));
            for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
                Producto producto = entry.getKey();   // el objeto Producto
                int cantidad = entry.getValue();      // la cantidad asociada

                double precioTotal = cantidad * producto.getPrecio_coste();

                Facturadetalle detalle = new Facturadetalle(
                        factura.getId(),    // fk_id_factura
                        producto.getId(),   // fk_id_producto
                        cantidad,   // cantidad
                        producto.getPrecio_coste(), // precio_unitario
                        precioTotal  // total_linea
                );
                new FacturadetalleService().crearFacturadetalle((new FacturaDetalleController().crearDetalle(detalle)));
                
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }





    //clientes y todo eso

    private String modo = "cliente";


    public Button btnMenuFabricante;
    public Button btnMenuProveedor;
    public Button btnMenuCliente;

    public HBox entidadeshbox;

    public void initializeEntidades(){


        changeModeAndABle(btnMenuFabricante,"fabricante");
        changeModeAndABle(btnMenuProveedor,"proveedor");
        changeModeAndABle(btnMenuCliente,"cliente");
    }

    private void changeModeAndABle(Button btn, String m){
        btn.setOnAction(e-> {
            modo = m;
            entidadeshbox.visibleProperty().setValue(true);
            entidadeshbox.setDisable(false);
        });
    }

    private void switchLabel(List<Label> labels){
        for (Label label : labels){
            label.setDisable(true);
            label.setVisible(false);
            if(label.getText().toLowerCase().contains(modo)){
                label.setVisible(true);
                label.setDisable(false);
            }
        }
    }
    @FXML private TableView<ClienteCompletoDTO> tvClientesListadoCompleto;

    @FXML private TableColumn<ClienteCompletoDTO, Integer> colClienteIdListarTbl;
    @FXML private TableColumn<ClienteCompletoDTO, Integer> colClienteCodigoListarTbl;
    @FXML private TableColumn<ClienteCompletoDTO, String> colClienteNombreListarTbl;

    @FXML private TableColumn<ClienteCompletoDTO, String> colClienteNifListarTbl;
    @FXML private TableColumn<ClienteCompletoDTO, String> colClienteEmailListarTbl;
    @FXML private TableColumn<ClienteCompletoDTO, String> colClienteTelefonoListarTbl;

    @FXML private TableColumn<ClienteCompletoDTO, String> colClienteDireccionListarTbl;
    @FXML private TableColumn<ClienteCompletoDTO, String> colClientePaisListarTbl;
    private void setupColumnsClientes() {

        colClienteIdListarTbl.setCellValueFactory(
                new PropertyValueFactory<>("idCliente"));

        colClienteCodigoListarTbl.setCellValueFactory(
                new PropertyValueFactory<>("codigo"));

        colClienteNombreListarTbl.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        // Campos de Informacion
        colClienteNifListarTbl.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInformacion().getNif()));

        colClienteEmailListarTbl.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInformacion().getEmail()));

        colClienteTelefonoListarTbl.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInformacion().getTelefono()));

        // Campos de Direccion
        colClienteDireccionListarTbl.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getDireccion()));

        colClientePaisListarTbl.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getPais()));
    }




    public ComboBox<Producto> cbProductoElegir;
    public TextField tfIdProductoCantidad;
    public Button btnAñadir;
    public Label errorCrearFactura;
    public Button btnEliminarProductoCrear;

    HashMap<Producto,Integer> productos;
    @FXML
    private TableView<Producto> tvAgregarProducto;

    // Columnas de la tabla
    @FXML
    private TableColumn<Producto, String> colProductoTblFactura; // para el nombre o descripción
    @FXML
    private TableColumn<Producto, Integer> colCantidadTblFactura; // para la cantidad
    @FXML
    private TableColumn<Producto, Double> colPrecioTblFactura; // para el precio unitario o total

    private void setupFacturasProductos(){
        cbProductoElegir.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Producto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getDescripcion());
            }
        });

        cbProductoElegir.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Producto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getDescripcion());
            }
        });
        colProductoTblFactura.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        colCantidadTblFactura.setCellValueFactory(cellData -> {
            Producto p = cellData.getValue();
            Integer cantidad = productos.get(p);
            return new SimpleIntegerProperty(cantidad).asObject();
        });

        colPrecioTblFactura.setCellValueFactory(cellData -> {
            Producto p = cellData.getValue();
            Integer cantidad = productos.get(p);
            double precioTotal = cantidad * p.getPrecio_coste();
            return new SimpleDoubleProperty(precioTotal).asObject();
        });

        btnAñadir.setOnAction(e -> {
            Producto p = cbProductoElegir.getSelectionModel().getSelectedItem();
            if (p == null) {
                errorCrearFactura.setText("Selecciona un producto");
                return;
            }

            String cantidadText = tfIdProductoCantidad.getText();
            if (cantidadText.isEmpty()) {
                errorCrearFactura.setText("Indique la cantidad");
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadText);
            } catch (NumberFormatException ex) {
                errorCrearFactura.setText("Cantidad inválida");
                return;
            }

            if (cantidad <= 0) {
                errorCrearFactura.setText("La cantidad debe ser mayor que 0");
                return;
            }

            // Añadir al HashMap
            productos.put(p, cantidad);

            // Limpiar campo y mensaje de error
            tfIdProductoCantidad.clear();
            errorCrearFactura.setText("");
            tvAgregarProducto.getItems().setAll(productos.keySet());
        });


        btnEliminarProductoCrear.setOnAction(e -> {
            Producto p = (Producto) tvAgregarProducto.getSelectionModel().getSelectedItem();
            if (p != null) {
                productos.remove(p);
                // También podrías actualizar la tabla
                tvAgregarProducto.getItems().remove(p);
                tvAgregarProducto.getItems().setAll(productos.keySet());
            } else {
                errorCrearFactura.setText("Selecciona un producto de la tabla para eliminar");
            }
        });
    }


    private void setupFacturas(){
        setupFacturasProductos();

    }





    // Información
    @FXML
    public TextField tfClienteNifCrear;
    @FXML
    public TextField tfClienteEmailCrear;
    @FXML
    public TextField tfClienteTelefonoCrear;

    // Dirección
    @FXML
    public TextField tfClienteDireccionCalleCrear;
    @FXML
    public TextField tfClienteCodigoPostalCrear;
    @FXML
    public TextField tfClienteCiudadCrear;
    @FXML
    public TextField tfClienteProvinciaCrear;
    @FXML
    public TextField tfClientePaisCrear;
    @FXML
    public TextField tfClienteEtiquetaCrear;

    // Entidad
    @FXML
    public TextField tfClienteCodigoCrear;
    @FXML
    public TextField tfClienteNombreCrear;

    // Botones y label
    @FXML
    public Button btnGuardarClienteCrear;
    @FXML
    public Button btnCancelarClienteCrear;
    @FXML
    public Label lblErrorCrearCliente;

    private void setupCrearClientes() {
        // Leer datos de los TextField
        String nif = tfClienteNifCrear.getText();
        String email = tfClienteEmailCrear.getText();
        String telefono = tfClienteTelefonoCrear.getText();

        String direccionCalle = tfClienteDireccionCalleCrear.getText();
        String codigoPostal = tfClienteCodigoPostalCrear.getText();
        String ciudad = tfClienteCiudadCrear.getText();
        String provincia = tfClienteProvinciaCrear.getText();
        String pais = tfClientePaisCrear.getText();
        String etiqueta = tfClienteEtiquetaCrear.getText();

        int codigoEntidad = 0;
        try {
            codigoEntidad = Integer.parseInt(tfClienteCodigoCrear.getText());
        } catch (NumberFormatException e) {
            lblErrorCrearCliente.setText("Código inválido");
            return;
        }
        String nombreEntidad = tfClienteNombreCrear.getText();

        Informacion informacion = new Informacion(0, nif, email, telefono);
        Direccion direccion = new Direccion(0, direccionCalle, codigoPostal, ciudad, provincia, pais, etiqueta);

        try{

            new InformacionService().crearInformacion(new InformacionController().crearInformacion(informacion));
            new DireccionService().crearDireccion(new DireccionController().crearDireccion(direccion));


            if (Objects.equals(modo, "cliente")){
                Cliente entidad = new Cliente(0, codigoEntidad, nombreEntidad, informacion.getId(), direccion.getId());
                new ClienteService().crearCliente(new ClienteController().crearCliente(entidad));
                new EntidadService().crearEntidad(entidad);

            } else if(Objects.equals(modo, "fabricante")){
                Fabricante entidad = new Fabricante(0, nombreEntidad);
                new FabricanteService().crearFabricante(entidad);

            } else if(Objects.equals(modo, "proveedor")){
                Proveedor entidad = new Proveedor(0, codigoEntidad, nombreEntidad, informacion.getId(), direccion.getId());
                new ProveedorService().crearProveedor(new ProveedorController().crearProveedor(entidad));
                new EntidadService().crearEntidad(entidad);
            }
        } catch (Exception e) {
            lblErrorCrearCliente.setText(e.getMessage());
        }



    }

}
