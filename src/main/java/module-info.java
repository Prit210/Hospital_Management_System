module com.example.hostiplemanagementsys {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    opens com.example.hostiplemanagementsys to javafx.fxml;
    exports com.example.hostiplemanagementsys;
    exports com.example.hostiplemanagementsys.user;
    opens com.example.hostiplemanagementsys.user to javafx.fxml;
    exports com.example.hostiplemanagementsys.managers;
    opens com.example.hostiplemanagementsys.managers to javafx.fxml;
}