package mx.unam.ciencias.modelado.proyecto2.composite;

import mx.unam.ciencias.modelado.proyecto2.edd.Grafica;
import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import java.util.List;

/**
 * Clase para la ruta compuesta por otras rutas.
 * Consiste de un grafo compuesto por grafos dirigidos.
 */
public class RutaCompuesta implements Ruta{
    /**La grafica de graficas dirigidas. */
    private Grafica<GraficaDirigida<Estacion>> grafica;
    /**Nombre de la ruta compuesta. */
    private final String NOMBRE = "Pumabus sistema completo.";

    /**Constructor de la clase, inicializa la grafica. */
    public RutaCompuesta(){
        grafica = new Grafica<>();
    }

    /**
     * Método para agregar una ruta a la grafica compuesta.
     * @param elemento el nuevo elemento de la grafica.
     */
    public void agrega(GraficaDirigida<Estacion> elemento){
        grafica.agrega(elemento);
        /*
        //Hace las conexiones entre las gráficas.
        for(GraficaDirigida<Estacion> subGrafica : grafica.obtenerElementos()){
            for(Estacion estacion: elemento.obtenerElementos()){
                if(subGrafica.contiene(estacion)){
                    grafica.conecta(subGrafica, elemento);
                    break;
                }
            }
        }*/
    }

    /**
     * Método para eliminar una ruta de la grafica compuesta.
     * @param elemento el nuevo elemento de la grafica.
     */
    public void elimina(GraficaDirigida<Estacion> elemento){
        grafica.elimina(elemento);
    }

    /**
     * Implementación del método para buscar una ruta.
     * @param origen la estacion origen.
     * @param destino la estación destino.
     * @return una lista que supone una trayectoria.
     */
    @Override public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima){
        GraficaDirigida<Estacion> combinada = combinaRutaCompuesta(rutaOptima);

        return combinada.dijkstraElementos(origen, destino);

    }

    private GraficaDirigida<Estacion> combinaRutaCompuesta(RutaOptima rutaOptima){
        GraficaDirigida<Estacion> combinada = new GraficaDirigida<>(); 

        for (GraficaDirigida<Estacion> grafica : grafica.obtenerElementos()) {
            agregarVertices(grafica, combinada);
        }

        for (GraficaDirigida<Estacion> grafica : grafica.obtenerElementos()) {
            agregarConexiones(grafica, combinada, rutaOptima);
        }

        return combinada;

    }

    private void agregarVertices(GraficaDirigida<Estacion> grafica, GraficaDirigida<Estacion> graficaCombinada){
        for(Estacion estacion : grafica.obtenerElementos()){
            if (!graficaCombinada.contiene(estacion)) {
                // Agregar el nuevo vértice a la gráfica combinada
                graficaCombinada.agrega(estacion);
            }
        }
    }

    private void agregarConexiones(GraficaDirigida<Estacion> grafica, GraficaDirigida<Estacion> graficaCombinada, RutaOptima rutaOptima){
        for (Estacion estacion : grafica.obtenerElementos()) {
            // Agregar conexiones de los vecinos
            for (Estacion vecino : grafica.obtenerVecinos(estacion)) {
                if (!graficaCombinada.sonVecinos(estacion, vecino)) {
                    graficaCombinada.conecta(estacion, vecino, rutaOptima.operacion(estacion, vecino)); // Conectar desde el elemento actual al vecino
                }
            }

        }
    }

    /**
     * Implementacion del método contiene, busca entre todas las rutas un elemento
     * @param elemento la estacion buscada.
     * @return si la estación está contenida en alguna de las rutas.
     */
    @Override public boolean contiene(Estacion elemento){
        for(GraficaDirigida<Estacion> ruta : grafica.obtenerElementos()){
            if(ruta.contiene(elemento)){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter del nombre de la ruta
     * @return String NOMBRE.
     */
    @Override public String getNombre(){
        return NOMBRE;
    }

    /**
     * Método que compone todo en una sola grafica dirigida.
     */
    public GraficaDirigida<Estacion> getGrafica(){
        GraficaDirigida<Estacion> combinada = new GraficaDirigida<>();
        return combinada.combinarGraficas(grafica.obtenerElementos());
    }

}