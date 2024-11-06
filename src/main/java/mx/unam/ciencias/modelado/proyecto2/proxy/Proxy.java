package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;
import mx.unam.ciencias.modelado.proyecto2.strategy.*;

import java.io.Serializable;
import java.util.List;
/**
 * Clase que se encarga de acceder al objeto real en proxy
 */
public class Proxy implements Serializable, PumabusService{
    private PumabusService servidor;

    /**
     * Método constructor de nuestra clase Proxy para proxy
     * @param servidor el objeto real en proxy
     */
    public Proxy(PumabusService servidor){
      this.servidor = servidor;
    }

    /**
     * Implementación del método para obtener el sistema completo.
     * @return la ruta compuesta con la que cuenta el servidor.
     */
    @Override public RutaCompuesta getSistemaCompleto(){
        return servidor.getSistemaCompleto();
    }

    /**
     * Implementación del método para obtener los criterios de optimización.
     * @return una List<RutaOptima> con los criterios de optimización disponibles.
     */
    @Override public List<RutaOptima> getCriteriosOptimizacion(){
        return servidor.getCriteriosOptimizacion();
    }    
}
