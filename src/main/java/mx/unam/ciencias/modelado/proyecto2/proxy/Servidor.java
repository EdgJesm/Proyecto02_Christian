package mx.unam.ciencias.modelado.proyecto2.menus;


import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;

import java.io.Serializable;
import java.util.List;
/**
 * Clase que se encarga de acceder al objeto real en proxy
 */
public class Servidor implements Serializable, PumabusService{
  // atributos
  private Servidor instancia;
  private RutaCompuesta sistemaCompleto
  public Servidor(){
    // implementar
  }
  /**
   * Getter para generar una instancia de nuestra clase
   * @return Servidor clase instanciada
   */
    public static Servidor getInstancia(){
      if(instancia == null)
          instancia = new RealChemsMart();
      return instancia;
    }
  /**
   * Método para calcular la ruta más corta entre dos estaciones eligiendo entre tipo de costos
   * @param int opcion opción para el tipo de costos
   * @param Estacion origen estación en la que nos encontramos
   * @param Estacion destion estación a la que queremos llegar
   * @return List<Estacion> la ruta óptima a seguir
   */
  @Override
  public List<Estacion> buscaRutaCorta(int opcion, Estacion origen, Estacion destino){
    // implementar algoritmo...
    return null;
  }

// implementar descripciones...

  public void generaSistemaCompleto(){
    return null;
  }
  public double getEstaciones(List<Estaciones>){
    return null;
  }
  public List<RutaOptima> getTiposDeRutas(){
    return null;
  }
}
