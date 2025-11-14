module com.mycompany.businessmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.businessmanagement to javafx.fxml;
    exports com.mycompany.businessmanagement;
}
