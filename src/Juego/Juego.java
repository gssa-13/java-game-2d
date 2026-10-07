package Juego;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Juego extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static volatile boolean enFuncionamiento = false;

    private static final String NOMBRE = "Juego";

    private static JFrame ventana;
    private static Thread thread;

    private Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO)); // define la dimension de la ventana

        ventana = new JFrame(NOMBRE); // define el titulo o nombre de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // agrega el evento cerrar ventana y detiene el proceso
        ventana.setResizable(false); // evita que el usuario modifique las dimensiones de la ventana
        ventana.setLayout(new BorderLayout()); // agrega un disenno u organizacion interna
        ventana.add(this, BorderLayout.CENTER); //
        ventana.pack(); //bloquea la venta a las medidas establecidas
        ventana.setLocationRelativeTo(null); // posicionamos la ventana en el centro del escritorio
        ventana.setVisible(true); // la ventana sera visible al ejecutar el juego
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar() {
        enFuncionamiento = true;
        // se crea el hilo encargado de generar los graficos
        thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener() {
        enFuncionamiento = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (enFuncionamiento) {

        }
    }
}
