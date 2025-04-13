module com.example.csc311_capstone_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires proto.google.cloud.document.ai.v1;
    requires google.cloud.document.ai;
    requires com.google.protobuf;
    requires java.sql;


    opens com.example.csc311_capstone_project to javafx.fxml;
    exports com.example.csc311_capstone_project;
}