package com.example.investig_arqui;

import Domain.Task;
import Service.TaskManager;
import Service.TimerClass;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTasks {
    TimerClass taskTimer;
    boolean firstTime;

    private Stage currentStage;

    private Stage nuevoStage;

    public AddTasks() {
    }

    private int countTask;

    private int time;

    @FXML
    private Button exit_add;

    @FXML
    private Button restBut;
    private SerialPort serialPort;

    @FXML
    private TextArea textArea_task = new TextArea();
    public void initialize() throws IOException {
        serialPort = SerialPort.getCommPort("COM3");  // Reemplaza "COM3" con el puerto correcto
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

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
                                accept_but_clicked(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } else if (message.contains("s")) {
                        Platform.runLater(() -> {
                            try {
                                exit_add_clicked(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    else if (message.contains("n")) {
                        Platform.runLater(() -> {
                            try {
                                restBut_clicked(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    else if (message.contains("M")) {
                        Platform.runLater(() -> {
                            try {
                                sumBut_clicked(null);
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
    void accept_but_clicked(ActionEvent event) throws IOException {


        // Obtén el texto del TextArea
        String taskText = textArea_task.getText();

        // Divide el texto en líneas para obtener los datos
        String[] lines = taskText.split("\n");

        // Inicializa variables para los datos
        String description = "";
        int minutes = 0;

        // Analiza cada línea para obtener los datos
        for (String line : lines) {
            if (line.startsWith("ID: ")) {
                countTask = Integer.parseInt(line.substring(4).trim());
            } else if (line.startsWith("Tiempo: ")) {
                minutes = Integer.parseInt(line.substring(7, line.indexOf(" minutos")).trim());
            } else if (line.startsWith("Tarea")) {
                // Asume que cualquier línea que no comienza con "Tarea no." es la descripción
                description += line + "\n";
            }
        }

        // Crea una nueva instancia de Task con los datos
        Task newTask = new Task(countTask, description, minutes, false);

        if(minutes > 0) {
            // Agrega la tarea al TaskManager (asegúrate de que tengas una instancia)
            TaskManager.getInstance().addTask(newTask);
            sendDataToArduino("k");
            // crear el objeto timer
            taskTimer = new TimerClass();
            //long minToMili = minutes * 60 * 1000; // convertir minutos en milisegundos
            taskTimer.initTimer(60000, countTask);


            if (isActive(nuevoStage) == false || nuevoStage.isShowing() == false) {
                firstTime = true;
                //System.out.println(newTask.toString());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("showTasks.fxml"));
                Scene scene = new Scene(loader.load());
                nuevoStage = new Stage();
                nuevoStage.setScene(scene);
                nuevoStage.show();
            }

            countTask += 1;
            time = 0;
            setTextArea();
        }else
            textArea_task.setText("No se puede añadir la tarea");

    }



    @FXML
    void exit_add_clicked(ActionEvent event) throws IOException {
        currentStage = (Stage) exit_add.getScene().getWindow();
        Platform.exit();
    }

    @FXML
    void restBut_clicked(ActionEvent event) throws IOException {
        time -= 1;

        setTextArea();
    }

    @FXML
    void sumBut_clicked(ActionEvent event) throws IOException {
        time += 1;

        setTextArea();
    }

    public void setTextArea() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTasks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage nuevoStage = new Stage();
        nuevoStage.setScene(scene);

        if(isActive(nuevoStage) == true && time == 0)
            restBut.setDisable(true);
        else if(isActive(nuevoStage) == true && time > 0)
            restBut.setDisable(false);

        textArea_task.setText("ID: " + countTask +
                "\nTarea" +
                "\nTiempo: " + time + " minutos");
    }

    public boolean isActive(Stage stage){
        if (stage == null) {
            return false;
        } else {
            return true;
        }
    }
    private void sendDataToArduino(String data) {
        if (serialPort.isOpen()) {
            byte[] dataBytes = data.getBytes();
            serialPort.writeBytes(dataBytes, dataBytes.length);
        }
    }

}
