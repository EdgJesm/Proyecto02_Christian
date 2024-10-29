package mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas;

import mx.unam.ciencias.modelado.proyecto2.graficable.VerticeCoordenado;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import java.util.Objects;
/**
 * Clase Estacion para crear un objeto de tipo Estacion //para nuestras rutas.
 */
public class Estacion implements VerticeCoordenado{

  private double coordX;
  private double coordY;
  private double afluencia;
  private ColorHex colorVertice;
  private String descripcion;

  /**
   * Constructor de la clase Estacion para crear nuestras estaciones.
   * @param coordX coordenada x de nuestra estacion en el plano.
   * @param coordY coordenada y de nuestra estacion en el plano.
   * @param afluencia un valor flotante que representa el peso de la estación.
   * @param ColorHex colorVertice color para el vertice que representa nuestra estación.
   * @param String descripcion breve descripción para la estación.
   */
  public void Estacion(double coordX, double coordY, double afluencia,ColorHex colorVertice, String descripcion){
      this.coordX = coordX;
      this.coordY = coordY;
      this.afluencia = afluencia;
      this.colorVertice = colorVertice;
      this.descripcion = descripcion;
  }
  /**
   * Getter para coordenada x de nuestra Estacion.
   * @return double coordX.
   */
  @Override public double getCoordX(){return this.coordX;}
  /**
   * Getter para coordenada y de nuestra Estacion.
   * @return double coordY.
   */
  @Override public double getCoordY(){return this.coordY;}
   /**
   * Getter para la fluencia de la estación.
   * @return double afluencia.
   */
  public double getAfluencia(){return this.afluencia;}
  /**
   * Getter para el color del vértice de nuestra estación.
   * @return ColorHex colorVertice.
   */
  @Override public ColorHex getColorVertice(){return this.colorVertice;}
  /**
   * Getter para la descripcion en nuestra estación.
   * @return String descripcion.
   */
  @Override public String getDescripcion(){return this.descripcion;}

  @Override public boolean equals(Object obj) {
        if (this == obj) return true; // Comparar referencia
        if (!(obj instanceof Estacion)) return false; // Verificar si es del mismo tipo
        Estacion other = (Estacion) obj; // Hacer el casting

        // Comparar todos los atributos relevantes
        return Double.compare(coordX, other.getCoordX()) == 0 &&
               Double.compare(coordY, other.getCoordY()) == 0 &&
               descripcion.equals(other.getDescripcion());
    }

    @Override public int hashCode() {
        return Objects.hash(coordX, coordY, descripcion);
    }
    
}
