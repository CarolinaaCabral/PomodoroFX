package com.meuprojeto.pomodorofx;

public class PomodoroModel {

    public enum Modo { POMODORO, PAUSA_CURTA, PAUSA_LONGA }

    private static final int DURACAO_POMODORO = 1500; // 25 min
    private static final int DURACAO_PAUSA_CURTA = 300; // 5 min
    private static final int DURACAO_PAUSA_LONGA = 900 ; // 15 min

    private Modo modoAtual = Modo.POMODORO;
    private int segundosRestantes = DURACAO_POMODORO;

    public void setModo(Modo modo) {
        this.modoAtual = modo;
        switch (modo) {
            case POMODORO:
                segundosRestantes = DURACAO_POMODORO;
                break;
            case PAUSA_CURTA:
                segundosRestantes = DURACAO_PAUSA_CURTA;
                break;
            case PAUSA_LONGA:
                segundosRestantes = DURACAO_PAUSA_LONGA;
                break;
        }
    }

    public Modo getModoAtual() {return modoAtual;}
    public int getSegundosRestantes(){return segundosRestantes;}
    public void decrementar() {segundosRestantes--;}
    public boolean terminou() {return segundosRestantes==0;}

    public void resetarModoAtual() {setModo(modoAtual);}
}
