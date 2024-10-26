package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
/*
* Clase para crear nuestra Ruta 12
*/
public class Ruta1 extends FabricaRuta {

  private final String NOMBRE = "Ruta 12";
  /**
   * Método para fabricar nuestra estación (Ruta12).
   * @param datos datos para poder crear nuestra ruta 12
   * @return Estacion NOMBRE nombre de la ruta 12.
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
  public String getNombre(String[] datos){return this.NOMBRE;}
}
