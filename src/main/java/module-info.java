module com.mycompany.cotizacion {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.cotizacion to javafx.fxml;
    exports com.mycompany.cotizacion;
}
