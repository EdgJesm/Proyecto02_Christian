package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.io.Serializable;
import java.util.List;

/**
 * Implementación de una estrategia para las rutas óptimas.
 * Esta estrategia calcula la ruta con la mínima afluencia en las estaciones.
 */
public class MenorAfluencia implements RutaOptima, Serializable {

    /** Para objetos serializables. */
    private static final long serialVersionUID = 1L;

    /**Constructor de la clase. */
    public MenorAfluencia() {}

    /**
     * Método principal de la interfaz {@code RutaOptima}. Calcula una trayectoria de estaciones
     * desde una estación de origen hasta una estación de destino, minimizando la afluencia en las estaciones.
     * 
     * <p>Este método utiliza el algoritmo de Dijkstra para encontrar la ruta óptima en un grafo dirigido
     * representado por las estaciones.</p>
     * 
     * @param grafo el grafo dirigido que contiene las estaciones y sus conexiones.
     * @param origen la estación de origen desde donde comienza la ruta.
     * @param destino la estación de destino a la que se desea llegar.
     * @return una lista de estaciones que representa la trayectoria del origen al destino.
     */
    @Override
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino) {
        return grafo.dijkstraElementos(origen, destino);
    }

    /**
     * Método {@code toString} que devuelve una representación en cadena de la estrategia.
     * 
     * @return la cadena "Menor afluencia" que representa la estrategia utilizada.
     */
    @Override
    public String toString() {
        return "Menor afluencia";
    }
}
