package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;

import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;

import java.util.List;
/*
* Interfaz para patrón de diseño proxy
*/
public interface PumabusService{

    /**
     * Método para obtener la ruta compuesta por el sistema.
     * @return una instancia de RutaCompuesta.
     */
    public RutaCompuesta getSistemaCompleto();

    /**
     * Método para obtener una lista de criterios de optimización.
     * @return List<RutaOptima>
     */
    public List<RutaOptima> getCriteriosOptimizacion();
}
