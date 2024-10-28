package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
/*
* Clase para crear nuestra Ruta 4
*/
public class Ruta4 extends FabricaRuta {

  private final String NOMBRE = "Ruta 4";
  /**
   * Método para fabricar nuestra estación (Ruta4).
   * @param datos datos para poder crear nuestra ruta 4
   * @return Estacion NOMBRE nombre de la ruta 4.
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
