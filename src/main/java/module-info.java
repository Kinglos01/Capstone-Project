module com.example.csc311_capstone_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311_capstone_project to javafx.fxml;
    exports com.example.csc311_capstone_project;
    exports com.example.csc311_capstone_project.LinkedListImplementation;
    opens com.example.csc311_capstone_project.LinkedListImplementation to javafx.fxml;
}