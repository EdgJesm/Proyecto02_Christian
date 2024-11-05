package mx.unam.ciencias.modelado.proyecto2.composite;

import mx.unam.ciencias.modelado.proyecto2.edd.Grafica;
import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase para la ruta compuesta por otras rutas.
 * Consiste de un grafo compuesto por grafos dirigidos.
 */
public class RutaCompuesta implements Ruta{
    /**La grafica de graficas dirigidas. */
    private Grafica<GraficaDirigida<Estacion>> grafica;
    /**Lista de todas las rutas que se agreguen. */
    private List<Ruta> rutas;
    /**Nombre de la ruta compuesta. */
    private final String NOMBRE = "Pumabus sistema completo.";

    /**Constructor de la clase, inicializa la grafica. */
    public RutaCompuesta(){
        grafica = new Grafica<>();
        rutas = new ArrayList<>();
        rutas.add(this);
    }

    /**
     * Método para agregar una ruta a la grafica compuesta.
     * @param elemento el nuevo elemento de la grafica.
     */
    public void agrega(Ruta elemento){

        rutas.add(elemento);
        GraficaDirigida<Estacion> graficaElemento = elemento.getGrafica();

        grafica.agrega(graficaElemento);

        //Hace las conexiones entre las gráficas.
        for(GraficaDirigida<Estacion> subGrafica : grafica.obtenerElementos()){
            if (grafica.equals(subGrafica) || graficaElemento.equals(subGrafica)) {
                continue;
            }

            for(Estacion estacion: graficaElemento.obtenerElementos()){
                if(subGrafica.contiene(estacion) && !grafica.sonVecinos(graficaElemento, subGrafica)){
                    grafica.conecta(subGrafica, graficaElemento);
                    break;
                }
            }
        }
    }

    /**
     * Implementación del método para buscar una ruta.
     * @param origen la estacion origen.
     * @param destino la estación destino.
     * @return una lista que supone una trayectoria.
     */
    @Override public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima){
        return rutaOptima.calculaRuta(getGrafica(), origen, destino);
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
     * @return una grafica dirigida de las rutas que componen la grafica compuesta.
     */
    @Override public GraficaDirigida<Estacion> getGrafica(){
        GraficaDirigida<Estacion> combinada = new GraficaDirigida<>();
        return combinada.combinarGraficas(grafica.obtenerElementos());
    }

    /**
     * Método que devuelve la lista de estaciones con las que cuenta la ruta compuesta.
     * @return una List<Estacion>.
     */
    @Override public List<Estacion> getEstaciones(){
        return getGrafica().obtenerElementos();
    }

    /**
     * Método que devuelve una lista de instancias de Ruta de todo el sistema,
     * @return una List<Ruta>
     */
    public List<Ruta> getRutas(){
        return rutas;
    }

}