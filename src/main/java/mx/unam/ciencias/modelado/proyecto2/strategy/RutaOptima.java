package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.util.List;

/**
 * Interfaz del patrón Strategy para las posibles rutas óptimas que podría tener el programa.
 * Esta interfaz define el contrato que debe cumplir cualquier estrategia que calcule una ruta óptima 
 * entre dos estaciones en un grafo dirigido.
 * 
 * <p>El objetivo de esta interfaz es permitir que diferentes estrategias para encontrar rutas 
 * entre estaciones sean intercambiables sin necesidad de cambiar el código que las utiliza.</p>
 */
public interface RutaOptima {

    /**
     * Método principal de la interfaz. Calcula una trayectoria de estaciones dado un grafo dirigido 
     * y los vértices origen y destino. Cada implementación de esta interfaz deberá definir su propia 
     * lógica para calcular la ruta óptima según el criterio de optimización.
     * 
     * @param grafo el grafo dirigido que contiene las estaciones.
     * @param origen la estación de origen desde donde comienza la ruta.
     * @param destino la estación de destino a la que se desea llegar.
     * @return una lista de estaciones que representa la trayectoria del origen al destino, según la estrategia implementada.
     */
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino);
}
