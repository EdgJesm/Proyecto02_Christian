package mx.unam.ciencias.modelado.proyecto2.composite;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import java.util.List;
import java.util.Map;

/**
 * Interfaz que define los métodos que deben implementar los componentes del patrón Composite
 * para representar una ruta. Proporciona métodos para la búsqueda de caminos, 
 * verificación de estaciones, y obtener información
 * sobre las estaciones y la ruta en sí.
 */
public interface Ruta {

    /**
     * Método encargado de buscar un camino entre dos estaciones dentro de la ruta.
     * 
     * @param origen la estación de inicio del camino.
     * @param destino la estación de destino del camino.
     * @param rutaOptima el objeto que contiene la estrategia para calcular la ruta óptima.
     * @return una lista de estaciones que representan la trayectoria desde el origen hasta el destino.
     */
    public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima);

    /**
     * Método que verifica si la ruta contiene una estación específica.
     * 
     * @param elemento la estación que se busca en la ruta.
     * @return true si la estación está contenida en la ruta, false en caso contrario.
     */
    public boolean contiene(Estacion elemento);

    /**
     * Método que devuelve la gráfica dirigida correspondiente a la ruta.
     * 
     * @return una instancia de {@code GraficaDirigida<Estacion>} que representa la ruta.
     */
    public GraficaDirigida<Estacion> getGrafica();

    /**
     * Método que devuelve una lista de todas las estaciones que forman parte de la ruta.
     * 
     * @return una lista de estaciones que conforman la ruta.
     */
    public List<Estacion> getEstaciones();

    /**
     * Método que regresa el nombre de la ruta.
     * 
     * @return una cadena que representa el nombre de la ruta.
     */
    public String getNombre();

    /**
     * Método que devuelve un diccionario de cadenas asociadas a un color para la simbología
     * de la ruta, útil para representar la ruta de manera visual.
     * 
     * @return un diccionario donde la clave es el nombre de la ruta y el valor es su color asociado.
     */
    public Map<String, ColorHex> getDatosColoracion();

    /**
     * Método para obtener la coloración asociada a la ruta.
     * 
     * @return una instancia de ColorHex que representa el color de la ruta.
     */
    public ColorHex getColoracion();

}
