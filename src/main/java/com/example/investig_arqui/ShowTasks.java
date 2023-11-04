package com.example.investig_arqui;

import Domain.Task;
import Service.TaskManager;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<?, ?> statusColumn;

    private TaskManager taskManager;
    private SerialPort serialPort1;

    @FXML
    void exitViewClicked(ActionEvent event) {
        Stage currentStage = (Stage) exitView.getScene().getWindow();
        currentStage.close();
    }


    @FXML
    void initialize() {

        this.serialPort1 = SerialPort.getCommPort("COM3");
        this.serialPort1.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        Thread communicationThread = new Thread(() -> {

            while (true) {

                if (this.serialPort1.bytesAvailable() > 0) {
                    byte[] readBuffer = new byte[ this.serialPort1.bytesAvailable()];
                    int numRead =  this.serialPort1.readBytes(readBuffer, readBuffer.length);
                    String message = new String(readBuffer, 0, numRead);

                    if (message.contains("e")) {
                        Platform.runLater(() -> {
                            exitViewClicked(null);

                        });
                    }
                }
            }
        });

        communicationThread.start();

        tableTask.refresh();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));

        descColumn.setCellValueFactory(new PropertyValueFactory<>("timer"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));

        if (taskManager == null) {
            taskManager = TaskManager.getInstance();
        }

        ObservableList<Task> data = FXCollections.observableArrayList(taskManager.getTasks());
        tableTask.setItems(data);


        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Modificar los datos en el hilo de la interfaz de usuario para asegurar la sincronización
                Platform.runLater(() -> {

                    // Limpiar y agregar nuevos datos al ObservableList (simulando cambios en tiempo real)
                    data.clear();
                    data.addAll(TaskManager.getInstance().getTasks());
                });
            }
        }).start();
    }


}





