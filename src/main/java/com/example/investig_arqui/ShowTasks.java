package com.example.investig_arqui;

import Domain.Task;
import Service.TaskManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShowTasks {

    TaskManager taskManager;

    @FXML
    private Button exitView;

    @FXML
    private TableView<?> tableTask;

    @FXML
    void exitViewClicked(ActionEvent event) {
        Stage currentStage = (Stage) exitView.getScene().getWindow();
        currentStage.close();
    }

    ObservableList<Task> data = FXCollections.observableArrayList(
           //new Task(1, )


    );

}
