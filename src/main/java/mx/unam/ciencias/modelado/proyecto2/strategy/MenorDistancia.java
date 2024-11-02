package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.util.List;
import java.util.Arrays;

/**
 * Implementacion de una de las estrategias para las rutas optimas.
 * En este caso se trata de la ruta con la menor distancia entre estaciones.
 */
public class MenorDistancia implements RutaOptima {

    /**
     * Método principal de la interfaz, calculará una trayectoria de estaciones dado un grafo dirigido y los vértices origen y destino.
     * El criterio de optimización será minimizar la distancia entre estaciones.
     * @param origen la estacion origen.
     * @param destino la estacion destino.
     * @return una lista que supone una trayectoria del origen al destino.
     */
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino) {
        // Modificamos directamente los pesos en el grafo original
        GraficaDirigida<Estacion> grafoDistancia = reseteaPesos(grafo);
        return grafoDistancia.dijkstraElementos(origen, destino);
        
        //return grafoDistancia.dijkstraElementos(origen, destino);
    }

    /**
     * Método que actualiza los pesos del grafo en base a la distancia euclidiana entre estaciones.
     * @param grafo un grafo dirigido de estaciones.
     */
    private GraficaDirigida<Estacion> reseteaPesos(GraficaDirigida<Estacion> grafo) {
        GraficaDirigida<Estacion> grafoDistancia = new GraficaDirigida<>();
        grafoDistancia = grafoDistancia.combinarGraficas(Arrays.asList(grafo));

        for(Estacion estacion : grafoDistancia.obtenerElementos()){
            for(Estacion vecino :  grafoDistancia.obtenerVecinos(estacion)){
                //if(grafoDistancia.getPeso(estacion, vecino) == 1.0){
                    grafoDistancia.setPeso(estacion, vecino, distancia(estacion, vecino));
                //}
            }
        }

        return grafoDistancia;
    }

    //private void reseteaPesosUnitarios(GraficaDirigida<Estacion> grafo){}

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

    @Override public double operacion(Estacion a, Estacion b){
        return distancia(a, b);
    }
}
