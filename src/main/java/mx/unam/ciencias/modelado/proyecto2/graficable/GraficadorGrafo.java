package mx.unam.ciencias.modelado.proyecto2.graficable;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import java.util.*;

public class GraficadorGrafo<T extends VerticeCoordenado> {

    /** La grafica dirigida que vamos a graficar. */
    private GraficaDirigida<T> grafo; 
    /** Ancho del lienzo. */
    private double anchoLienzo = 200;
    /** Alto del lienzo. */
    private double altoLienzo = 20;
    /**Maxima coordenada X. */
    private double maxCoordX = 0;
    /**Máxima coordenada Y. */
    private double maxCoordY = 0;
    /** Traductor intercambiable. */
    private TraductorLenguaje traductor;
    /** StringBuilder que almacena los datos. */
    private StringBuilder sb;

    /**Radio de los vértices */
    private static final int RADIO_VERTICE = 10; 
    /**Color de los bordes de las figuras. */
    private static final ColorHex COLOR_BORDE = ColorHex.NEGRO;
    /**Grueso de los vordes de las figuras. */ 
    private static final int GRUESO_BORDE = 2; 
    /**Color de las figuras. */
    private static final ColorHex COLOR_FIGURA = ColorHex.BLANCO; 
    /**Tamaño de la fuente para el texto */
    private static final int TAM_FUENTE = 12;
    /**Color de los textos. */
    private static final ColorHex COLOR_TEXTO = ColorHex.NEGRO;
    /**Ancho de las lineas a dibujar. */
    private static final int ANCHO_LINEA = 4; 

    /**Constante de escala, permite que se modifique el tamaño de lo que se va a graficar */
    private static final double ESCALA = 200;

    /**
     * Constructor de la clase, asigna atributos.
     * 
     * @param grafo un grafo dirigido.
     */
    public GraficadorGrafo(GraficaDirigida<T> grafo, TraductorLenguaje traductor){
        this.grafo = grafo;
        this.traductor = traductor;
        this.sb = new StringBuilder(); // Inicializa StringBuilder
        calculaDimensiones();
        sb.append(traductor.start(anchoLienzo, altoLienzo));
        sb.append(traductor.coloreaLienzo(ColorHex.BLANCO));
    }

    /**
     * Método que calcula las dimensiones del grafo.
     */
    private void calculaDimensiones(){
        if (grafo == null || grafo.obtenerElementos().isEmpty()) {
            throw new IllegalArgumentException("El grafo está vacío o no inicializado.");
        }
        
        for (T vertice : grafo.obtenerElementos()) {
            double coordX = ESCALA * vertice.getCoordX();
            double coordY = ESCALA * vertice.getCoordY();

            if (coordX > maxCoordX) {
                maxCoordX = coordX;
            }
            if (coordY > maxCoordY) {
                maxCoordY = coordY;
            }
        }

        anchoLienzo += maxCoordX;
        altoLienzo += maxCoordY;
    }

    /**
     * Método que agrega los vértices al sb.
     */
    public void graficaVertices(){
        for (T vertice : grafo.obtenerElementos()) {
            double cx = ESCALA * vertice.getCoordX();
            double cy = ESCALA * vertice.getCoordY();
            
            // Dibuja el círculo
            sb.append(traductor.dibujaCirculo(cx, cy, RADIO_VERTICE, COLOR_BORDE, GRUESO_BORDE, COLOR_FIGURA));
            sb.append(traductor.dibujaTexto(cx, cy - 15, vertice.getDescripcion(), TAM_FUENTE, COLOR_TEXTO)); // Ajustar 'cy' para que el texto esté arriba
        }
    }

    /**
     * Método para graficar las aristas.
     */
    public void graficaAristas() {

        for (T vertice : grafo.obtenerElementos()) {
            for (T vecino : grafo.obtenerVecinos(vertice)) {
                double x1 = ESCALA * vertice.getCoordX();
                double y1 = ESCALA * vertice.getCoordY();
                double x2 = ESCALA * vecino.getCoordX();
                double y2 = ESCALA * vecino.getCoordY();

                sb.append(traductor.dibujaLinea(x1, y1, x2, y2, vecino.getColorVertice(), ANCHO_LINEA));

            }
        }
    }

    /**
     * Método para graficar un camino.
     * 
     * @param camino una lista de vertices.
     */
    public void graficaCamino(List<T> camino){

        ColorHex colorCamino = ColorHex.ROJO; // Puedes cambiar el color del camino

        for (int i = 0; i < camino.size() - 1; i++) {
            T verticeActual = camino.get(i);
            T siguienteVertice = camino.get(i + 1);
            double x1 = ESCALA * verticeActual.getCoordX();
            double y1 = ESCALA * verticeActual.getCoordY();
            double x2 = ESCALA * siguienteVertice.getCoordX();
            double y2 = ESCALA * siguienteVertice.getCoordY();

            sb.append(traductor.dibujaLinea(x1, y1, x2, y2, colorCamino, ANCHO_LINEA*2));
        }

    }

    /**
     * Método para graficar una lista con información de los vértices de un camin
     * a la derecha del lienzo
     */
    public void graficaDescripciones(List<T> camino) {
        double posicionX = maxCoordX + 30; // Margen a la derecha del lienzo
        double posicionY = 10; // Inicia en la parte superior

        for (T vertice : camino) {
            posicionY += TAM_FUENTE + 5; // Espaciado vertical entre descripciones
            sb.append(traductor.dibujaTexto(posicionX, posicionY, vertice.getDescripcion(), TAM_FUENTE, vertice.getColorVertice()));
        }
    }

    /**
     * Método toString, regresa el sb acumulado.
     * @return método toString del stringBuilder.
     */
    @Override public String toString(){
        sb.append(traductor.end());
        return sb.toString();
    }
}
