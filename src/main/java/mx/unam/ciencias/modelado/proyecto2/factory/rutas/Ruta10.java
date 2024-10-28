package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
/*
* Clase para crear nuestra Ruta 10
*/
public class Ruta10 extends FabricaRuta {

  private final String NOMBRE = "Ruta 10";
  /**
   * Método para fabricar nuestra estación (Ruta10).
   * @param datos datos para poder crear nuestra ruta 10
   * @return Estacion NOMBRE nombre de la ruta 10.
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
