package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
/*
* Clase para crear nuestra Ruta 5
*/
public class Ruta5 extends FabricaRuta {

  private final String NOMBRE = "Ruta 5";
  /**
   * Método para fabricar nuestra estación (Ruta5).
   * @param datos datos para poder crear nuestra ruta 5
   * @return Estacion NOMBRE nombre de la ruta 5.
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
