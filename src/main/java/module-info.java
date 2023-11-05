module com.example.investig_arqui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;
    requires rxtxcomm;


    opens com.example.investig_arqui to javafx.fxml;
    opens Domain;
    exports com.example.investig_arqui;
}