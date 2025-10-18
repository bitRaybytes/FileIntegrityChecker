module com.example.fileintegritychecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;

    opens com.example.fileintegritychecker to javafx.fxml;
    opens com.example.fileintegritychecker.controller to javafx.fxml;
    opens com.example.fileintegritychecker.service to javafx.fxml;
    opens com.example.fileintegritychecker.icons to javafx.fxml;

    exports com.example.fileintegritychecker;
}