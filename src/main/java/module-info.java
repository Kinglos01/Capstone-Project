module com.example.csc311_capstone_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires org.checkerframework.checker.qual;
    requires itextpdf;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires com.azure.storage.blob;


    opens com.example.csc311_capstone_project to javafx.fxml;
    exports com.example.csc311_capstone_project.model;
    opens com.example.csc311_capstone_project.model to javafx.fxml;
    exports com.example.csc311_capstone_project;
}
