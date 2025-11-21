package com.mycompany.businessmanagement;

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

public class PrimaryController {

    @FXML
    private Button botonNuevo;

    @FXML
    private Button botonAbrir;

    @FXML
    private AnchorPane panelContenido;

    @FXML
    private ImageView centerImage;

    @FXML
    private Label centerLabel;

    // Aquí puedes usar tu DAO real (EmpresaDAO). Dejo comentario para que lo sustituyas.
    // private final EmpresaDAO empresaDAO = new EmpresaDAO();

    @FXML
    private void initialize() {
        // Inicialmente ocultamos la imagen/label si existen (protección null)
        if (centerImage != null) {
            centerImage.setVisible(false);
            centerImage.setManaged(false);
        }
        if (centerLabel != null) {
            centerLabel.setVisible(false);
            centerLabel.setManaged(false);
        }

        // Cargar imagen desde resources/imagenes/imagencrearempresa.png
        try {
            // ruta basada en tu estructura: resources/imagenes/imagencrearempresa.png
            Image img = new Image(getClass().getResourceAsStream("/imagenes/imagencrearempresa.png"));
            if (centerImage != null) centerImage.setImage(img);
        } catch (Exception ex) {
            System.err.println("No se pudo cargar la imagen desde /imagenes/imagencrearempresa.png : " + ex.getMessage());
        }

        // cursor/efecto al pasar ratón (si image existe)
        if (centerImage != null) {
            centerImage.setOnMouseEntered(ev -> centerImage.setStyle("-fx-opacity: 0.9; -fx-cursor: hand;"));
            centerImage.setOnMouseExited(ev -> centerImage.setStyle("-fx-opacity: 1.0;"));
        }

        // conectar botones con acciones (si no lo haces en FXML)
        if (botonNuevo != null) botonNuevo.setOnAction(e -> botonNuevoAction());
        if (botonAbrir != null) botonAbrir.setOnAction(e -> botonAbrirAction());
    }

    @FXML
    private void botonNuevoAction() {
        // Mostrar la imagen y label en el panel central para que el usuario clickee
        if (centerImage != null) {
            centerImage.setVisible(true);
            centerImage.setManaged(true);
        }
        if (centerLabel != null) {
            centerLabel.setVisible(true);
            centerLabel.setManaged(true);
        }

        // Al hacer clic en la imagen abrimos la ventana de creación (secondary.fxml)
        if (centerImage != null) {
            centerImage.setOnMouseClicked((MouseEvent me) -> openCreateCompanyWindow());
        }
    }

    @FXML
    private void botonAbrirAction() {
        // Ejemplo: mostrar listado. Sustituye por tu llamada a EmpresaDAO.findAll()
        ObservableList<String> items = FXCollections.observableArrayList(
                "Empresa A", "Empresa B" // ej. reemplazar por datos reales
        );

        ListView<String> listView = new ListView<>(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setPrefSize(420, 300);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 12;");
        root.getChildren().addAll(listView);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Empresas existentes");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void openCreateCompanyWindow() {
        try {
            // Ruta absoluta en classpath a tus fxml (tu carpeta resources/com.mycompany.businessmanagement)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/businessmanagement/secondary.fxml"));

            // Usa Parent para evitar ClassCastException si la raíz del FXML no es AnchorPane
            Parent root = loader.load();

            // Si vas a pasar DAO real, obtén el controlador y pásalo:
            // SecondaryController controller = loader.getController();
            // controller.setEmpresaDAO(empresaDAO);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Crear nueva empresa");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error cargando secondary.fxml. Asegúrate de que el fichero existe en resources/com.mycompany.businessmanagement/secondary.fxml");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.err.println("getResource devolvió null: ruta incorrecta o FXML no está en resources.");
        }
    }
}
