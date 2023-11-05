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
    private static ShowTasks instance;

    @FXML
    void exitViewClicked(ActionEvent event) {
        Stage currentStage = (Stage) exitView.getScene().getWindow();
        currentStage.close();
    }
    public void setSerialPort(SerialPort serialPort) {
        this.serialPort1 = serialPort;
    }

    public static ShowTasks getInstance() {
        if (instance == null) {
            instance = new ShowTasks();
        }
        return instance;
    }

    @FXML
    void initialize() {

        this.serialPort1 = SerialPort.getCommPort("COM3");
        System.out.println(this.serialPort1.openPort());
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


        int hashCode = System.identityHashCode(taskManager);
        System.out.println("Dirección de memoria de taskmanager en el timer: " + Integer.toHexString(hashCode));

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
                    //System.out.println(task.isCompleted());

                    // Limpiar y agregar nuevos datos al ObservableList (simulando cambios en tiempo real)
                    data.clear();
                    data.addAll(TaskManager.getInstance().getTasks());
                    System.out.println("HOLAAA" + TaskManager.getInstance().getTasks().getFirst().isCompleted());
                });
            }
        }).start();
    }

    private void sendDataToArduino(String data) {
        if (!this.serialPort1.isOpen()) {
            byte[] dataBytes = data.getBytes();
            System.out.println(data);
            this.serialPort1.writeBytes(dataBytes, dataBytes.length);
        }
    }

}





