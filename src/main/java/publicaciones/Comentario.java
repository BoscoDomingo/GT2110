package publicaciones;

import pkg.CuentaUsuario;

import java.util.ArrayList;
import java.util.Date;

public class Comentario {
    private final String id;
    private Date fecha;
    private String texto;
    private Comentario respondeA; //si respondeA != null, significa que este Comentario ya es respuesta, ergo
    // this.respuestas = null y cualquier respuesta irá a la padre con el alias del que es respondido en el mensaje
    private Publicacion perteneceA;
    private CuentaUsuario escritoPor;
    private ArrayList<Comentario> respuestas; //Si es un comentario sin mas


    public Comentario(String id, Date fecha, String texto, Publicacion perteneceA, CuentaUsuario escritoPor, ArrayList<Comentario> respuestas) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.respondeA = null;
        this.perteneceA = perteneceA;
        this.escritoPor = escritoPor;
        this.respuestas = respuestas;
    }

    public Comentario(String id, Date fecha, String texto, Comentario respondeA, Publicacion perteneceA, CuentaUsuario escritoPor, ArrayList<Comentario> respuestas) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.respondeA = respondeA;
        this.perteneceA = perteneceA;
        this.escritoPor = escritoPor;
        this.respuestas = respuestas;
    }

    public Publicacion getPerteneceA() {
        return perteneceA;
    }

    public void addRespuesta(Comentario respuesta){
        this.respuestas.add(respuesta);
    }

    public Comentario getRespondeA() {
        return respondeA;
    }

    public ArrayList<Comentario> getRespuestas() {
        return respuestas;
    }

    public String getTexto() {
        return texto;
    }

    public CuentaUsuario getEscritoPor() {
        return escritoPor;
    }
}

//EJEMPLO

/*
Comentario 1 //respuestas = ArrayList con (Respuesta1, Respuesta2, Respuesta3), respondeA = null
* |--Respuesta1 de a0000: Mis dieses //respuestas = null, respondeA = Comentario1
* |--Respuesta2 de a0001 a Respuesta1: @aliasDea0000 yo tambien //respuestas = null, respondeA = Comentario1
* |--Respuesta3 de a0002: Click aqui para entrar a un sorteo gratis de iPhone 80!!! //respuestas = null, respondeA =
* Comentario1
* */