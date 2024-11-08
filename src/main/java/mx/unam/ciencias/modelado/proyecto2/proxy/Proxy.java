package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;
import mx.unam.ciencias.modelado.proyecto2.strategy.*;

import java.io.Serializable;
import java.util.List;

/**
 * Clase {@code Proxy} que implementa el patrón Proxy para acceder al objeto
 * real del servicio Pumabus. La clase se encarga de delegar las llamadas
 * a los métodos del servicio real, permitiendo un acceso indirecto.
 * 
 * <p>La clase {@code Proxy} implementa la interfaz {@link PumabusService}, lo que
 * le permite actuar como un representante del servicio real.</p>
 * 
 */
public class Proxy implements Serializable, PumabusService {

    /** Servidor asociado al proxy. */
    private PumabusService servidor;

    /**
     * Constructor de la clase {@code Proxy}.
     * 
     * Este constructor inicializa el proxy con el servidor real que se va a
     * delegar las solicitudes. El servidor es el objeto que contiene la lógica
     * real de obtención de rutas y criterios de optimización.
     * 
     * @param servidor El objeto real al que el proxy delegará las solicitudes.
     */
    public Proxy(PumabusService servidor) {
        this.servidor = servidor;
    }

    /**
     * Implementación del método {@link PumabusService#getSistemaCompleto()}.
     * Este método delega la llamada al servidor real para obtener el sistema
     * completo de rutas.
     * 
     * @return Una instancia de {@link RutaCompuesta} que representa el sistema
     *         completo de rutas gestionadas por el servidor real.
     */
    @Override
    public RutaCompuesta getSistemaCompleto() {
        return servidor.getSistemaCompleto();
    }

    /**
     * Implementación del método {@link PumabusService#getCriteriosOptimizacion()}.
     * Este método delega la llamada al servidor real para obtener los criterios
     * de optimización disponibles para el sistema.
     * 
     * @return Una lista de objetos {@link RutaOptima} que representan los criterios
     *         de optimización disponibles en el servidor real.
     */
    @Override
    public List<RutaOptima> getCriteriosOptimizacion() {
        return servidor.getCriteriosOptimizacion();
    }
}
