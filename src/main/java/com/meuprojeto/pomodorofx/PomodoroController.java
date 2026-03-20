package com.meuprojeto.pomodorofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javafx.scene.media.AudioClip;

public class PomodoroController {

    private int segundosRestantes = 1500;
    private Timeline pomodoroTimer;

    @FXML
    private Label timerLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    public void initialize(){
        pomodoroTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (segundosRestantes > 0) {
                segundosRestantes --;

                int minutos = segundosRestantes / 60;
                int segundos = segundosRestantes % 60;
                timerLabel.setText(String.format("%02d:%02d", minutos, segundos));
            } else  {
                pomodoroTimer.stop();
                timerLabel.setText("Tempo finalizado");
                AudioClip som = new AudioClip(getClass().getResource("notificacao.mp3").toExternalForm());
                som.play();
                startButton.setText("Reiniciar");
                segundosRestantes = 1500;

                startButton.setDisable(false);
                resetButton.setDisable(true);
            }

        }));
        pomodoroTimer.setCycleCount(Timeline.INDEFINITE);
        resetButton.setDisable(true);
    }

    @FXML
    private void handleStart() {

        if (pomodoroTimer.getStatus() == Animation.Status.RUNNING) {

            pomodoroTimer.stop();
            startButton.setText("Iniciar");
            resetButton.setDisable(false);

        } else {
            pomodoroTimer.play();
            startButton.setText("Pausar");
            resetButton.setDisable(false);
        }
    }

    @FXML
    private void handleReset() {
        pomodoroTimer.stop();
        segundosRestantes = 1500;
        timerLabel.setText("25:00");
        startButton.setText("Iniciar");

        resetButton.setDisable(true);
        startButton.setDisable(false);
    }
}
