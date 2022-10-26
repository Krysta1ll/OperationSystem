module com.example.operationsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens OS.views to javafx.fxml;
    opens OS to javafx.fxml;
    exports OS;
    exports OS.views;


}