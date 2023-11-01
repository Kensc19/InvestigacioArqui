package com.example.investig_arqui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    Stage existingStage;

    @FXML
    private Button addButton;

    @FXML
    private Button exitButtonAll;

    @FXML
    private Button showButton;
    private AddTasks addTasks;


    @FXML
    void addButton_clicked(ActionEvent event) throws IOException {
        if(addTasks == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTasks.fxml"));
            Scene scene = new Scene(loader.load());
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.show();
            addTasks = loader.getController(); // referenciar a la clase addTask que se crea
            addTasks.setTextArea(); // setea el text area con la nueva tarea
            addTasks.setCurrentStage(nuevoStage);
            //addTasks.setTextArea(); // setea el text area con la nueva tarea
            //existingStage = addTasks.getCurrentStage();

        }else
            existingStage = addTasks.getCurrentStage();
            existingStage.show();

    }


    @FXML
    void showButton_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showTasks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage nuevoStage = new Stage();
        nuevoStage.setScene(scene);
        nuevoStage.show();
    }


    @FXML
    void exitButtonAll_clicked(ActionEvent event) {
        Platform.exit();
    }

}
