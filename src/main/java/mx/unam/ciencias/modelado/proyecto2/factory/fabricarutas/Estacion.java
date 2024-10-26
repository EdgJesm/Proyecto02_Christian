package mx.unam.ciencias.modelado.proyecto1.factory.fabricaclientes;

import java.util.HashMap;
import java.util.Map;
/**
 * Clase Estacion para crear un objeto de tipo Estacion //para nuestras rutas.
 */
public class Estacion{

  private double coordX;
  private double coordY;
  private ColorHex colorVertice;
  private Strign descripcion;

  /**
   * Constructor de la clase Estacion para crear nuestras estaciones.
   * @param coordX coordenada x de nuestra estacion en el plano.
   * @param coordY coordenada y de nuestra estacion en el plano.
   * @param ColorHex colorVertice color para el vertice que representa nuestra estación.
   * @param String descripcion breve descripción para la estación.
   */
  public void Estacion(double coordX, double coordY, ColorHex colorVertice, String descripcion){
      this.coordX = coordX;
      this.coordY = coordY;
      this.colorVertice = colorVertice;
      this.descripcion = descripcion;
  }
  /**
   * Getter para coordenada x de nuestra Estacion.
   * @return double coordX.
   */
  public double getCoordX(){return this.coordX;}
  /**
   * Getter para coordenada y de nuestra Estacion.
   * @return double coordY.
   */
  public double getCoordY(){return this.coordY;}
  /**
   * Getter para el color del vértice de nuestra estación.
   * @return ColorHex colorVertice.
   */
  public ColorHex getColorVertice(){return this.colorVertice;}
  /**
   * Getter para la descripcion en nuestra estación.
   * @return String descripcion.
   */
  public String getDescripcion(){return this.descripcion;}
  /**
   * Método que verifica que dos objetos de tipo ... Estacion ... sean iguales.
   * @return boolean igualdad entre 2 Objetos.
   */
  public boolean equals(Object obj){
    return false;
  }
  /**
   * Método que te crea un hashCode para tu estación.
   * @return int el hashCode generado para tu estación.
   */
  public int hashCode(){
    // agregar hashCode para el método
    return 0;
  }
}
