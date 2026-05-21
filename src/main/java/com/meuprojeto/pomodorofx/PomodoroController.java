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

    private PomodoroModel model = new PomodoroModel();
    private Timeline pomodoroTimer;

    @FXML private Label timerLabel;
    @FXML private Button startButton;
    @FXML private Button resetButton;
    @FXML private Button btnPomodoro;
    @FXML private Button btnPausaCurta;
    @FXML private Button btnPausaLonga;

    @FXML
    public void initialize(){
        pomodoroTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> tick()));
        pomodoroTimer.setCycleCount(Timeline.INDEFINITE);
        resetButton.setDisable(true);
        atualizarLabel();
    }

    private void tick(){
        model.decrementar();
        if(model.terminou()) {
            pomodoroTimer.stop();
            timerLabel.setText("Tempo Finalizado");
            new AudioClip(getClass().getResource("notificacao.mp3").toExternalForm()).play();
            startButton.setText("Iniciar");
            resetButton.setDisable(true);
            model.resetarModoAtual();
        } else {
            atualizarLabel();
        }
    }

    @FXML
    private void handleStart() {
        if (pomodoroTimer.getStatus() == Animation.Status.RUNNING) {
            pomodoroTimer.stop();
            startButton.setText("Continuar");
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
        model.resetarModoAtual();
        atualizarLabel();
        startButton.setText("Iniciar");
        resetButton.setDisable(true);
    }

    @FXML
    private void handlePomodoro() {
        trocarModo(PomodoroModel.Modo.POMODORO);
        destacarBotaoModo(btnPomodoro);
    }

    @FXML
    private void handlePausaCurta() {
        trocarModo(PomodoroModel.Modo.PAUSA_CURTA);
        destacarBotaoModo(btnPausaCurta);
    }

    @FXML
    private void handlePausaLonga() {
        trocarModo(PomodoroModel.Modo.PAUSA_LONGA);
        destacarBotaoModo(btnPausaLonga);
    }

    private void trocarModo(PomodoroModel.Modo modo) {
        pomodoroTimer.stop();
        model.setModo(modo);
        atualizarLabel();
        startButton.setText("Iniciar");
        resetButton.setDisable(true);
    }

    private void atualizarLabel(){
        int min = model.getSegundosRestantes() / 60;
        int seg = model.getSegundosRestantes() % 60;
        timerLabel.setText(String.format("%02d:%02d", min, seg));
    }

    private void destacarBotaoModo(Button botaoAtivo) {
        btnPomodoro.getStyleClass().setAll("btn-modo");
        btnPausaCurta.getStyleClass().setAll("btn-modo");
        btnPausaLonga.getStyleClass().setAll("btn-modo");


        botaoAtivo.getStyleClass().setAll("btn-modo-ativo");
    }
}

