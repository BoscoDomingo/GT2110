package publicaciones;

import interfaces.IMenu;
import pkg.CuentaUsuario;
import pkg.Sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Comentario implements IMenu {
    private final String id;
    private Date fecha;
    private String texto;
    private Comentario respondeA; //si respondeA != null, significa que este Comentario ya es respuesta, ergo
    // this.respuestas = null y cualquier respuesta irá a la padre con el alias del que es respondido en el mensaje
    private Publicacion perteneceA;
    private CuentaUsuario escritoPor;
    private ArrayList<Comentario> respuestas; //Si es un comentario sin mas


    public Comentario(String id, Date fecha, String texto, Publicacion perteneceA, CuentaUsuario escritoPor,
                      ArrayList<Comentario> respuestas) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.respondeA = null;
        this.perteneceA = perteneceA;
        this.escritoPor = escritoPor;
        this.respuestas = respuestas;
    }

    public Comentario(String id, Date fecha, String texto, Comentario respondeA, Publicacion perteneceA,
                      CuentaUsuario escritoPor, ArrayList<Comentario> respuestas) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.respondeA = respondeA;
        this.perteneceA = perteneceA;
        this.escritoPor = escritoPor;
        this.respuestas = respuestas;
    }

    public void mostrarComentario() {
        System.out.println(this.escritoPor.getAlias() + "\t" + this.fecha);
        System.out.println(this.texto + "\n");
    }

    public void mostrarComentarioYRespuestas() {
        System.out.println("-------Comentario seleccionado--------");
        mostrarComentario();
        for (Comentario respuesta : this.respuestas) {
            System.out.println("\t" + respuesta.escritoPor.getAlias() + "\t" + respuesta.getFecha());
            System.out.println(
                    "\t Responde a: " + respuesta.getRespondeA().getEscritoPor().getAlias() + ". " + respuesta.texto);
        }
        System.out.println("-------------------");
    }

    public Comentario elegirComentario() {
        System.out.println("Elegir el comentario a responder introduciendo su posición: ");
        System.out.println("(0 para responder el comentario principal y 1 para la primera respuesta, etc.)");
        Scanner scan = new Scanner(System.in);
        int eleccion = scan.nextInt();
        if (eleccion == 0) {
            return this;
        } else if (eleccion <= this.respuestas.size()) {
            return this.respuestas.get(eleccion - 1);
        } else {
            System.out.println("Número introducido no válido");
            return this.elegirComentario();
        }
    }

    @Override
    public boolean menu() {
        boolean goBack = false;
        this.mostrarComentarioYRespuestas();
        if (escritoPor == Sistema.getCurrentUser()) {
            goBack = ownerMenu();
        } else {
            goBack = normalMenu();
        }
        return goBack;
    }

    private boolean normalMenu() {
        boolean valido = false;
        while (!valido) {
            System.out.println("\n1 - Responder comentario\n\n9 - Volver atrás\n");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 1:
                    valido = true;
                    System.out.print("Texto: ");
                    String texto = scan.nextLine();
                    Sistema.getCurrentUser().comentarComentario(this);
                    break;
                case 9:
                    valido = true;
                    break;
                default:
                    System.out.println("Numero no válido, por favor introduzca un número permitido");
            }
        }
        return true;
    }

    private boolean ownerMenu() {
        boolean valido = false;
        while (!valido) {
            System.out.println("\n1 - Responder comentario\n2 - Borrar comentario\n\n9 - Volver atrás\n");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch (selector) {
                case 1:
                    valido = true;
                    Sistema.getCurrentUser().comentarComentario(this);
                    break;
                case 2:
                    valido = true;
                    Sistema.getCurrentUser().borrarComentario(this);
                    break;
                case 9:
                    valido = true;
                    break;
                default:
                    System.out.println("Numero no válido, por favor introduzca un número permitido");
            }
        }
        return true;
    }

    //GETTERS & SETTERS

    public Publicacion getPerteneceA() {
        return perteneceA;
    }

    public void addRespuesta(Comentario respuesta) {
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

    public Date getFecha() {
        return fecha;
    }

    public String getID(){ return id; }

    public void setRespondeA(Comentario respondeA) {
        this.respondeA = respondeA;
    }

    public void setPerteneceA(Publicacion perteneceA) {
        this.perteneceA = perteneceA;
    }

    public void setEscritoPor(CuentaUsuario escritoPor) {
        this.escritoPor = escritoPor;
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