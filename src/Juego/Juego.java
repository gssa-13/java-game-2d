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
    private static int aps = 0;
    private static int fps = 0;

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

    /**
     * metodo encargado de actualizar las variables del juego
     * posicion del jugador, equipo, vida entre otros
     */
    private void actualizar() {
        aps++;

    }

    private void mostrar() {
        fps++;
    }

    public void run() {
        System.nanoTime(); //mide el tiempo segun los ciclos de reloj del procesador
        final int NANO_SEGUNDO_POR_SEGUNDO = 1000000000; //equivalencia de nano segundo en 1s
        final byte ACTUALIZACIONES_POR_SEGUNDO_OBJETIVO = 60;
        final double NANO_SEGUNDO_POR_ACTUALIZACION = NANO_SEGUNDO_POR_SEGUNDO / ACTUALIZACIONES_POR_SEGUNDO_OBJETIVO;
        long referenciaActualizacion = System.nanoTime(); // se le atribuye una cantidad de tiempo en nanosegundos
        long referenciaContadorFramePerSecond =  System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0; //cantidad de tiempo transcurrido hasta que hay una actualizacion

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime(); // inicia el cronometro
            tiempoTranscurrido = inicioBucle - referenciaActualizacion; // medimos tiempo transcurrido
            referenciaActualizacion = inicioBucle; // se setea para comparar desde que inicio el bucle
            delta += tiempoTranscurrido / NANO_SEGUNDO_POR_ACTUALIZACION;

            while (delta >= 1) {
                actualizar();
                delta--;
            }

            mostrar();

            if (System.nanoTime() - referenciaContadorFramePerSecond > NANO_SEGUNDO_POR_SEGUNDO) {
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                // reiniciamos el contador
                aps = 0;
                fps = 0;
                referenciaContadorFramePerSecond = System.nanoTime();
            }
        }
    }
}
