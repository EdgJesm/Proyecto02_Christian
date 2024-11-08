package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;

import java.util.List;

/**
 * Interfaz para el patrón de diseño Proxy, que define los métodos
 * necesarios para interactuar con el sistema que gestiona rutas
 * y criterios de optimización para el servicio Pumabus.
 * 
 * Esta interfaz proporciona métodos para obtener una representación
 * completa del sistema de rutas y para obtener los criterios de
 * optimización disponibles que pueden ser utilizados para modificar
 * el comportamiento del sistema.
 */
public interface PumabusService {

    /**
     * Obtiene la ruta compuesta que representa el sistema completo.
     * Este método proporciona una representación estructurada de todas
     * las rutas gestionadas por el sistema, facilitando el acceso y la
     * manipulación de las rutas disponibles.
     * 
     * @return Una instancia de {@link RutaCompuesta} que representa el
     *         sistema completo de rutas.
     */
    public RutaCompuesta getSistemaCompleto();

    /**
     * Obtiene una lista de los criterios de optimización disponibles.
     * Los criterios de optimización permiten ajustar las rutas y
     * los recorridos según diferentes métricas, mejorando la eficiencia
     * y calidad del sistema.
     * 
     * @return Una lista de objetos {@link RutaOptima} que representan
     *         los criterios de optimización disponibles para el sistema.
     */
    public List<RutaOptima> getCriteriosOptimizacion();
}
