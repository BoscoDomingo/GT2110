package controllers;

import interfaces.ITimeController;

import java.util.Timer;
import java.util.TimerTask;

public class TimeController implements ITimeController {

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

    @Override
    public void contar() {
        this.segundos = 0;
        timer.schedule(new Contador(), 0, 1000);
    }

    @Override
    public void detener() {
        timer.cancel();
    }

    @Override
    public int getSegundos() {
        return this.segundos;
    }

    @Override
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

}
