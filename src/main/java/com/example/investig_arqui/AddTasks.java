package com.example.investig_arqui;

import Domain.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTasks {

    private int countTask;
    private int countSum;
    private int countRest;
    private int time;
    boolean state = false;

    @FXML
    private Button accept_but;

    @FXML
    private Button exit_add;

    @FXML
    private Button restBut;

    @FXML
    private Button sumBut;

    @FXML
    private TextArea textArea_task = new TextArea();

    @FXML
    void accept_but_clicked(ActionEvent event) throws IOException {
        textArea_task.setText("Hola");
    }

    @FXML
    void exit_add_clicked(ActionEvent event) throws IOException {

    }

    @FXML
    void restBut_clicked(ActionEvent event) {
        countRest-= 10;
        time+= countRest;
    }

    @FXML
    void sumBut_clicked(ActionEvent event) {
        countSum+= 10;
        time+= countSum;
    }

    public void setTextArea() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTasks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage nuevoStage = new Stage();
        nuevoStage.setScene(scene);
        isActive(nuevoStage);

    }

    public boolean isActive(Stage stage){
        if (stage == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setTextArea_task(TextArea textArea_task) {
        textArea_task.setText("Hola");
    }
}
