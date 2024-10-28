package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
/*
* Clase para crear nuestra Ruta 6
*/
public class Ruta6 extends FabricaRuta {

  private final String NOMBRE = "Ruta 6";
  /**
   * Método para fabricar nuestra estación (Ruta6).
   * @param datos datos para poder crear nuestra ruta 6
   * @return Estacion NOMBRE nombre de la ruta 6.
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
