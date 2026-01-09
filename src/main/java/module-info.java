module com.mycompany.businessmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
  // MySQL Connector
    requires mysql.connector.j;
    requires com.fasterxml.jackson.databind;
    opens com.mycompany.businessmanagement to javafx.fxml;
    exports com.mycompany.businessmanagement;
    opens com.mycompany.businessmanagement.modelos to javafx.base;

    exports com.mycompany.businessmanagement.DTO;
    opens  com.mycompany.businessmanagement.DTO to javafx.base;
}
