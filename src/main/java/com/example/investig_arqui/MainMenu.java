package com.example.investig_arqui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.fazecast.jSerialComm.SerialPort;



import java.io.IOException;

public class  MainMenu {
    Stage existingStage;

    @FXML
    private Button addButton;

    @FXML
    private Button exitButtonAll;

    @FXML
    private Button showButton;
    private AddTasks addTasks;

    private SerialPort serialPort;
    private ShowTasks showTasks;
    public void initialize() {
        serialPort = SerialPort.getCommPort("COM3");  // Reemplaza "COM3" con el puerto correcto
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        System.out.println(serialPort.openPort());
        sendDataToArduino("k");
        // Iniciar un hilo para la comunicación con Arduino
        Thread communicationThread = new Thread(() -> {
            while (true) {
                if (serialPort.bytesAvailable() > 0) {
                    byte[] readBuffer = new byte[serialPort.bytesAvailable()];
                    int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
                    String message = new String(readBuffer, 0, numRead);

                    if (message.contains("a")) {
                        Platform.runLater(() -> {
                            try {
                                addButton_clicked(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } else if (message.contains("s")) {
                        Platform.runLater(() -> {
                            exitButtonAll_clicked(null);
                        });
                    } else if (message.contains("m")) {
                        Platform.runLater(() -> {
                            try {
                                showButton_clicked(null);
                                sendDataToArduino("l");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            }
        });
        communicationThread.start();
    }
    @FXML
    void addButton_clicked(ActionEvent event) throws IOException {
        if(addTasks == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTasks.fxml"));
            Scene scene = new Scene(loader.load());
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.show();
            addTasks = new AddTasks();
            addTasks = loader.getController(); // referenciar a la clase addTask que se crea
            addTasks.setTextArea(); // setea el text area con la nueva tarea
            addTasks.setCurrentStage(nuevoStage);

        }else {
            existingStage = addTasks.getCurrentStage();
            existingStage.show();
        }
    }


    @FXML
    void showButton_clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showTasks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage nuevoStage = new Stage();
        nuevoStage.setScene(scene);
        nuevoStage.show();

        ShowTasks showTasks = loader.getController();
        showTasks.setSerialPort(serialPort);// Configura el objeto SerialPort en ShowTasks
    }


    @FXML
    void exitButtonAll_clicked(ActionEvent event) {
        Platform.exit();
    }


    private void sendDataToArduino(String data) {
        if (serialPort.isOpen()) {
            byte[] dataBytes = data.getBytes();
            System.out.println(data);
            serialPort.writeBytes(dataBytes, dataBytes.length);
        }
    }

}
