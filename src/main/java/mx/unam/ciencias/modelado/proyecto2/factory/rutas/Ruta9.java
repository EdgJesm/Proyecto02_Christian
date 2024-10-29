package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import java.util.List;

/*
* Clase para crear nuestra Ruta 9
*/
public class Ruta9 extends FabricaRuta {

  private final String NOMBRE = "Ruta 9";

  public Ruta9(List<String> lineas){
    super(lineas);
  }

  /**
   * Método para fabricar nuestra estación (Ruta9).
   * @param datos datos para poder crear nuestra ruta 9
   * @return Estacion NOMBRE nombre de la ruta 9.
   */
  @Override
  public Estacion fabricaEstacion(String[] datos){
    // inserte código aquí

    // return new Estacion();
    return null;
  }
  /**
   * Getter para el nombre de nuestra Ruta.
   * @return String NOMBRE nombre de la ruta.
   */
  @Override
  public String getNombre(){return this.NOMBRE;}
}
