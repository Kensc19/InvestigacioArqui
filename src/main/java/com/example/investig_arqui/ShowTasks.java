package com.example.investig_arqui;

import Domain.Task;
import Service.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShowTasks {
    @FXML
    private TableColumn<?, ?> descColumn;

    @FXML
    private Button exitView;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableView<Task> tableTask;

    @FXML
    private TableColumn<?, ?> timeColumn;

    private TaskManager taskManager;
    private Task task;
    private boolean booleanToChange = false;

    @FXML
    void exitViewClicked(ActionEvent event) {
        Stage currentStage = (Stage) exitView.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void initialize() {
        tableTask.refresh();
        if(task == null) {
            task = Task.getInstance();
            booleanToChange = task.isCompleted();
            task.setIdTask(4);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));

        descColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionTask"));

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));


        if (taskManager == null) {
            taskManager = TaskManager.getInstance();
        }






        ObservableList<Task> data = FXCollections.observableArrayList(taskManager.getTasks());
        tableTask.setItems(data);
    }
}



