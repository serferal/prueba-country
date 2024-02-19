module com.ceica.restcountriesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens com.ceica.restcountriesfx to javafx.fxml;
    exports com.ceica.restcountriesfx;
}