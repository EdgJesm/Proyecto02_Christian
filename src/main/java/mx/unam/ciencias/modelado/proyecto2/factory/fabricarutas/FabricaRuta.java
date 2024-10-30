package mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.graficable.VerticeCoordenado;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.composite.Ruta;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Fabrica del patrón factory. Se encarga principalmente de generar un diccionario
 * de objetos de tipo Ruta a partir de líneas de datos.
 */
public abstract class FabricaRuta implements Ruta{

    private GraficaDirigida<Estacion> ruta;

    /**
     * Constructor de la clase, asigna la ruta.
     * @param lineas una lista de cadenas que deberá contener la información de la ruta.
     */
    public FabricaRuta(List<String> lineas){
      ruta = generaGrafica(lineas);
    }

    /**
     * Método que genera una graficaDirigida que contiene las GraficasDirigidas fabricadas.
     * @param lineas una lista de cadenas, se asume que está separada por ",".
     * @return una GraficaDirigida<Estacion>
     */
    public GraficaDirigida<Estacion> generaGrafica(List<String> lineas) {
        GraficaDirigida<Estacion> graficaDirigida = new GraficaDirigida<>();
        //Se lleva un registro de las estaciones para hacer las conexiones.
        List<Estacion> estaciones = new ArrayList<>();
        
        // Fabricamos y agregamos las estaciones
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            Estacion estacion = fabricaEstacion(datos);
            graficaDirigida.agrega(estacion);
            estaciones.add(estacion);
        }
        
        // Conectamos las estaciones en un ciclo
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion actual = estaciones.get(i);
            Estacion siguiente = estaciones.get((i + 1) % estaciones.size()); // Ciclo que conecta la última con la primera
            graficaDirigida.conecta(actual, siguiente, actual.getAfluencia());
        }

        return graficaDirigida;
    }

    /**
     * Método que se encargará de buscar un camino entre dos estaciones.
     * @param origen estacion origen.
     * @param destino estacion destino.
     * @return una lista de estaciones que supone una trayectoria.
     */
    @Override public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima){
        return rutaOptima.calculaRuta(ruta, origen, destino);
    }

    /**
     * Método que compone todo en una sola grafica dirigida.
     */
    public GraficaDirigida<Estacion> getGrafica(){
        return ruta;
    } 

    /**
     * Método que verifica si existe una estación en la gráfica
     * @param Estacion elemento estacion a verificar existencia
     * @return boolean existencia de la estación
     */
    public boolean contiene(Estacion elemento){
        return ruta.contiene(elemento);
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
