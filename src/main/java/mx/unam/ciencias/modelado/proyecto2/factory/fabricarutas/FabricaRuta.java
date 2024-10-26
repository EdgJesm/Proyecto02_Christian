package mx.unam.ciencias.modelado.proyecto1.factory.fabricarutas;

//import mx.unam.ciencias.modelado.proyecto2 ...;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Fabrica del patrón factory. Se encarga principalmente de generar un diccionario
 * de objetos de tipo Ruta a partir de líneas de datos.
 */
public abstract class FabricaRuta {

    private GraficaDirigida<Estacion> ruta;
    /**
     * Método que genera una graficaDirigida que contiene las GraficasDirigidas fabricadas.
     * @param lineas una lista de cadenas, se asume que está separada por ",".
     * @return una GraficaDirigida<Estacion>
     */
    public GraficaDirigida<Estacion> generaGrafica(List<String> lineas) {
        //GraficaDirigida<Estacion> graficaDirigida = new GraficaDirigida();

        for (String linea : lineas) {
            String[] datos = linea.split(",");
            // Cliente cliente = fabricaCliente(datos);
            // clientes.agregar(cliente);
        }
        return null;
    }
    /**
     * Método que aplica el algoritmo Dijkstra sobre una gráfica
     * @param Estacion inicial(raiz para arborescencia con dijkstra).
     * @param Estacion final(vertice al que queremos llegar con ruta más corta).
     * @return una instancia List<VerticeGrafica<T>> con lista con la ruta más corta de vertices
     */
    public List<VerticeGrafica<T>> dijkstra(Estacion inicio, Estacion final){
      // implementar...
      return null;
    }
    /**
     * Método que verifica si existe una estación en la gráfica
     * @param Estacion elemento estacion a verificar existencia
     * @return boolean existencia de la estación
     */
    public boolean contiene(Estacion elemento){
      return false;
    }
    /**
     * Método abstracto de tipo getter para obtener el nombre de nuestra estación.
     * @return String NOMBRE nombre de la ruta.
     */
    public abstract String getNombre();
    /**
     * Método abstracto que creará una Estacion a partir de un arreglo de cadenas.
     * @param datos un arreglo de cadenas que pueda ser empleado para crear un objeto.
     * @return una instancia de Estacion con los datos dados.
     */
    public abstract Estacion fabricaEstacion(String[] datos);

}
