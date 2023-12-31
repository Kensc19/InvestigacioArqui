package com.example.investig_arqui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTasks.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Administrador De Tareas");
        stage.setResizable(false);
        stage.setScene(scene);
        AddTasks tareas = new AddTasks();
        tareas = fxmlLoader.getController();
        tareas.setTextArea();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}