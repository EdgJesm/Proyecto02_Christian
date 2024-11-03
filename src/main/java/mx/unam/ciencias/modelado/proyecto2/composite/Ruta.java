package mx.unam.ciencias.modelado.proyecto2.composite;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import java.util.List;

/**
 * Interfaz para las implementaciones de los componentes del patrón composite.
 * El objetivo es hacer una ruta compuesta por otras rutas.
 */
public interface Ruta{

    /**
     * Método que se encargará de buscar un camino entre dos estaciones.
     * @param origen estacion origen.
     * @param destino estacion destino.
     * @return una lista de estaciones que supone una trayectoria.
     */
    public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima);

    /**
     * Método que verifica contencion de la estacion.
     * @param elemento la estacion buscada.
     * @return si el elemento está contenido.
     */
    public boolean contiene(Estacion elemento);

    /**
     * Método que regresa el nombre de la ruta.
     * @return una cadena con el nombr especifico de la ruta.
     */
    public String getNombre();
}
