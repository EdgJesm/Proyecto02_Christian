package mx.unam.ciencias.modelado.proyecto2.strategy;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import java.util.List;

/**
 * Implementacion de una de las estrategias para las rutas optimas.
 * En este caso se trata de la ruta con el minimo numero de paradas entre estaciones.
 */
public class MenorAfluencia implements RutaOptima{

    /**
     * Método principal de la interfaz, calculará una trayectoria de estaciones dado un grafo dirigido y los vértices origen y destino.
     * El criterio de optimización será minimizar las paradas que hará el autobus.
     * @param origen la estacion origen.
     * @param destino la estacion destino.
     * @return una lista que supone una trayectoria del origen al destino.
     */
    public List<Estacion> calculaRuta(GraficaDirigida<Estacion> grafo, Estacion origen, Estacion destino){
        GraficaDirigida<Estacion> grafoReseteado = reseteaPesos(grafo);
        return grafoReseteado.dijkstraElementos(origen, destino);

    }

    private GraficaDirigida<Estacion> reseteaPesos(GraficaDirigida<Estacion> grafo){
        GraficaDirigida<Estacion> grafoAfluencia = grafo;

        for(Estacion estacion : grafo.obtenerElementos()){
            for(Estacion vecino : grafo.obtenerVecinos(estacion)){
                grafoAfluencia.setPeso(estacion, vecino, estacion.getAfluencia());
            }
        }

        return grafoAfluencia;
    }


}