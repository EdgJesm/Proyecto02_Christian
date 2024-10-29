package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.util.List;

/**
 * Implementacion de una de las estrategias para las rutas optimas.
 * En este caso se trata de la ruta con la menor distancia entre estaciones.
 */
public class MenorDistancia implements RutaOptima{

    /**
     * Método principal de la interfaz, calculará una trayectoria de estaciones dado un grafo dirigido y los vértices origen y destino.
     * El criterio de optimización será minimizar la distancia entre estaciones.
     * @param origen la estacion origen.
     * @param destino la estacion destino.
     * @return una lista que supone una trayectoria del origen al destino.
     */
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino){
        GraficaDirigida<Estacion> grafoReseteado = reseteaPesos(grafo);
        return grafoReseteado.dijkstraElementos(origen, destino);

    }

    /**
     * Método que resetea los pesos del grafo en base a la distancia euclidiana entre estaciones.
     * @param grafo un grafo dirigido de estaciones.
     * @return una copia del grafo recibido pero cuyas aritas pesan la distancia entre estaciones.
     */
    private GraficaDirigida<Estacion> reseteaPesos(GraficaDirigida<Estacion> grafo){
        GraficaDirigida<Estacion> grafoDistancia = grafo;

        for(Estacion estacion : grafo.obtenerElementos()){
            for(Estacion vecino : grafo.obtenerVecinos(estacion)){
                grafoDistancia.setPeso(estacion, vecino, distancia(estacion, vecino));
            }
        }

        return grafoDistancia;
    }


    /**
     * Calcula la distancia entre dos estaciones.
     * @param estacionA primera Estacion.
     * @param estacionB segunda Estacion.
     * @return double distancia entre estacionA y estacionB.
     */
    private static double distancia(Estacion estacionA, Estacion estacionB) {
        double distanciaX = estacionA.getCoordX() - estacionB.getCoordX();
        double distanciaY = estacionA.getCoordY() - estacionB.getCoordY();
        return Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }

}