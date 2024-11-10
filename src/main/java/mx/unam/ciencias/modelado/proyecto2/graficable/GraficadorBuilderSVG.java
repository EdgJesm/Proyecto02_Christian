package mx.unam.ciencias.modelado.proyecto2.graficable;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de construir la representación gráfica en formato SVG de un grafo dirigido.
 * Permite establecer la trayectoria y un diccionario de colores para la visualización del grafo.
 * Utiliza el patrón Builder para crear una instancia de graficado SVG.
 * 
 * @param <T> Tipo de los vértices del grafo, que debe implementar la interfaz VerticeCoordenado.
 */
public class GraficadorBuilderSVG<T extends VerticeCoordenado> {
    /** Trayectoria a resaltar en el grafo. */
    private List<T> trayectoria;
    /** Diccionario de cadenas asociadas a colores. */
    private Map<String, ColorHex> datosColores;
    /** Graficador de gráficos */
    private GraficadorGrafo<T> graficador;
    /** Nombre predeterminado de los archivos */
    private String nombreArchivo = "Grafica.svg";

    /**
     * Constructor de BuildGraficador.
     * 
     * @param grafo Grafo dirigido de vértices que implementan VerticeCoordenado.
     */
    public GraficadorBuilderSVG(GraficaDirigida<T> grafo) {
        this.graficador = new GraficadorGrafo<>(grafo, new TraductorSVG());
    }

    /**
     * Configura la trayectoria en el grafo.
     * 
     * @param trayectoria La lista de vértices que representan el camino o trayectoria.
     * @return La instancia actual de BuildGraficador para encadenamiento.
     */
    public GraficadorBuilderSVG<T> setTrayectoria(List<T> trayectoria) {
        this.trayectoria = trayectoria;
        T primero = trayectoria.get(0);
        T ultimo = trayectoria.get(trayectoria.size() - 1);
        nombreArchivo = primero.toString() + "-" + ultimo.toString();

        return this;
    }

    /**
     * Configura el diccionario de datosColores para cadenas asociadas a colores.
     * @param datosColores el diccionario de cadenas asociadas a colores.
     * @return La instancia actual de BuildGraficador para encadenamiento.
     */
    public GraficadorBuilderSVG<T> setDatosColores(Map<String, ColorHex> datosColores) {
        this.datosColores = datosColores;
        return this;
    }


    /**
     * Genera la representación gráfica en formato SVG.
     * 
     * @return La cadena SVG que representa el grafo con la trayectoria, si se configuró.
     */
    public String graficar() {
        
        // Graficar las aristas del grafo
        graficador.graficaAristas();

        // Si se ha establecido una trayectoria, graficarla
        if (trayectoria != null && !trayectoria.isEmpty()) {
            graficador.graficaCamino(trayectoria);
            graficador.graficaDescripciones(trayectoria);
        }

        if(datosColores != null && !datosColores.isEmpty()){
            graficador.graficaTextoColores(datosColores);
        }

        //Al ultimo los vértices
        graficador.graficaVertices();

        // Retornar el resultado SVG generado por GraficadorGrafo
        return graficador.toString();
    }

    /**
     * Getter del nombre del archivo graficable.
     * @return el atributo nombreArchivo.
     */
    public String getNombreArchivo(){
        return nombreArchivo;
    }
}
