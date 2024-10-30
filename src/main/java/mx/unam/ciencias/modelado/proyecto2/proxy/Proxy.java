package mx.unam.ciencias.modelado.proyecto2.menus;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;

import java.io.Serializable;
import java.util.List;
/**
 * Clase que se encarga de acceder al objeto real en proxy
 */
public class Proxy implements Serializable, PumabusService{
  private Servidor servidor;
  /**
   * Método constructor de nuestra clase Proxy para proxy
   * @param servidor el objeto real en proxy
   */
  public Proxy(Servidor servidor){
    this.servidor = servidor;
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
    return servidor.buscaRutaCorta();
  }
}
