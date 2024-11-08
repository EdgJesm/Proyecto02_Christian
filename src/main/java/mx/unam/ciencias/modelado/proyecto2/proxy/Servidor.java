package mx.unam.ciencias.modelado.proyecto2.proxy;


import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;
import mx.unam.ciencias.modelado.proyecto2.factory.rutas.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.*;
import mx.unam.ciencias.modelado.proyecto2.common.ReaderWriter;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que se encarga de acceder al objeto real en proxy
 */
public class Servidor implements Serializable, PumabusService{
    
    /**Instancia de la clase. */
    private static Servidor instancia;
    /**Ruta compuesta del sistema compelto. */
    private RutaCompuesta sistemaCompleto;
    /**Lista de criterios de optimización. */
    private List<RutaOptima> criteriosOptimizacion;

    /**Constructor de la clase, inicializa los atributos. */
    private Servidor(){
        cargaRutas();
        cargaCriterios();
    }


    /**
     * Getter para generar una instancia de nuestra clase
     * @return Servidor clase instanciada
     */
      public static Servidor getInstancia(){
        if(instancia == null)
            instancia = new Servidor();
        return instancia;
      }

    /**
     * Método que carga el sistema de rutas.
     */
    private void cargaRutas(){
        sistemaCompleto = new RutaCompuesta();
        ReaderWriter rw = new ReaderWriter();

        sistemaCompleto.agrega(new Ruta1(rw.read("data/linea1.csv")));
        sistemaCompleto.agrega(new Ruta2(rw.read("data/linea2.csv")));
        sistemaCompleto.agrega(new Ruta3(rw.read("data/linea3.csv")));
        sistemaCompleto.agrega(new Ruta4(rw.read("data/linea4.csv")));
        sistemaCompleto.agrega(new Ruta5(rw.read("data/linea5.csv")));
        sistemaCompleto.agrega(new Ruta6(rw.read("data/linea6.csv")));
        sistemaCompleto.agrega(new Ruta7(rw.read("data/linea7.csv")));
        sistemaCompleto.agrega(new Ruta8(rw.read("data/linea8.csv")));
        sistemaCompleto.agrega(new Ruta9(rw.read("data/linea9.csv")));
        sistemaCompleto.agrega(new Ruta10(rw.read("data/linea10.csv")));
        sistemaCompleto.agrega(new Ruta11(rw.read("data/linea11.csv")));
        sistemaCompleto.agrega(new Ruta12(rw.read("data/linea12.csv")));
    }

    /**
     * Método que carga los criterios de optimización.
     */
    private void cargaCriterios(){
        criteriosOptimizacion = new ArrayList<>();
        criteriosOptimizacion.add(new MenorAfluencia());
        criteriosOptimizacion.add(new MenorNumeroDeParadas());
    }

    /**
     * Implementación del método para obtener el sistema completo.
     * @return la ruta compuesta con la que cuenta el servidor.
     */
    @Override public RutaCompuesta getSistemaCompleto(){
        return sistemaCompleto;
    }

    /**
     * Implementación del método para obtener los criterios de optimización.
     * @return una List{@code <RutaOptima>} con los criterios de optimización disponibles.
     */
    @Override public List<RutaOptima> getCriteriosOptimizacion(){
        return criteriosOptimizacion;
    }


}
