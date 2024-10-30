package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.util.List;

/**
 * Implementacion de una de las estrategias para las rutas optimas.
 * En este caso se trata de la ruta con la minima afluencia en estaciones.
 */
public class MenorAfluencia implements RutaOptima{

    /**
     * Método principal de la interfaz, calculará una trayectoria de estaciones dado un grafo dirigido y los vértices origen y destino.
     * El criterio de optimización será minimizar la afluencia en las estaciones.
     * @param origen la estacion origen.
     * @param destino la estacion destino.
     * @return una lista que supone una trayectoria del origen al destino.
     */
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino){
        return reseteaPesos(grafo).dijkstraElementos(origen, destino);

    }

    /**
     * Método que resetea los pesos del grafo a partir de la afluencia de las estaciones.
     * @param grafo un grafo dirigido de estaciones.
     * @return una grafica con los vertices del grafo pero cuyas aristas tienen el peso de la afluencia.
     */
    private GraficaDirigida<Estacion> reseteaPesos(GraficaDirigida<Estacion> grafo){
        GraficaDirigida<Estacion> grafoAfluencia = new GraficaDirigida<Estacion>();

        for(Estacion estacion : grafo.obtenerElementos()){
            grafoAfluencia.agrega(estacion);
        }

        for(Estacion estacion : grafo.obtenerElementos()){
            for(Estacion vecino : grafo.obtenerVecinos(estacion)){
                if(grafo.getPeso(estacion, vecino) == 1){
                    grafoAfluencia.conecta(estacion, vecino, estacion.getAfluencia());
                }
            }
        }

        return grafoAfluencia;
    }


}