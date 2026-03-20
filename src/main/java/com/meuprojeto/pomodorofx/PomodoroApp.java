package com.meuprojeto.pomodorofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PomodoroApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PomodoroApp.class.getResource("pomodoro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        scene.getStylesheets().add(PomodoroApp.class.getResource("style.css").toExternalForm());
        stage.setTitle("Pomodoro");
        stage.setScene(scene);
        stage.getIcons().add(new Image(PomodoroApp.class.getResourceAsStream("icone.png")));
        stage.show();

    }
}
