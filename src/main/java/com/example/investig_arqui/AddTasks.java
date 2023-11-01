package com.example.investig_arqui;

import Domain.Task;
import Service.TaskManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTasks {
    private Stage currentStage;

    public AddTasks() {
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    private int countTask;

    private int time;
    boolean state = false;

    @FXML
    private Button accept_but;

    @FXML
    private Button exit_add;

    @FXML
    private Button restBut;


    @FXML
    private TextArea textArea_task = new TextArea();

    @FXML
    void accept_but_clicked(ActionEvent event) throws IOException {


        // Obtén el texto del TextArea
        String taskText = textArea_task.getText();

        // Divide el texto en líneas para obtener los datos
        String[] lines = taskText.split("\\n");

        // Inicializa variables para los datos
        String description = "";
        int timing = 0;

        // Analiza cada línea para obtener los datos
        for (String line : lines) {
            if (line.startsWith("ID: ")) {
                countTask = Integer.parseInt(line.substring(4).trim());
            } else if (line.startsWith("Tiempo: ")) {
                timing = Integer.parseInt(line.substring(7, line.indexOf(" minutos")).trim());
            } else if (line.startsWith("Tarea")) {
                // Asume que cualquier línea que no comienza con "Tarea no." es la descripción
                description += line + "\n";
            }
        }

        // Crea una nueva instancia de Task con los datos
        Task newTask = new Task(countTask, description, timing, false);

        // Agrega la tarea al TaskManager (asegúrate de que tengas una instancia)
        TaskManager taskManager = TaskManager.getInstance(); // O como obtengas la instancia
        taskManager.addTask(newTask);


        // Cierra la ventana actual si es necesario
        Stage currentStage = (Stage) accept_but.getScene().getWindow();
        currentStage.hide();

        //System.out.println(newTask.toString());

        countTask += 1;
        time = 0;
        setTextArea();

    }



    @FXML
    void exit_add_clicked(ActionEvent event) throws IOException {
        currentStage = (Stage) exit_add.getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    void restBut_clicked(ActionEvent event) throws IOException {
        time -= 10;


        setTextArea();
    }

    @FXML
    void sumBut_clicked(ActionEvent event) throws IOException {
        time += 10;

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

}
