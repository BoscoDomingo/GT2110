package controllers;

import java.util.Timer;
import java.util.TimerTask;

public class TimeController {

    private Timer timer;
    private String user;
    private int segundos;

    class Contador extends TimerTask {
        @Override
        public void run() {
            segundos++;
        }
    }

    public TimeController(String user) {
        timer = new Timer(user);
        this.user = user;
    }

    public void contar() {
        this.segundos = 0;
        timer.schedule(new Contador(), 0, 1000);
    }

    public void detener() {
        timer.cancel();
    }

    public int getSegundos() {
        return this.segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

}
