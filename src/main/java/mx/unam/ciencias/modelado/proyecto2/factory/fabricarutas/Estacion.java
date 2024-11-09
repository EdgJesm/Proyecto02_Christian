package mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas;

import mx.unam.ciencias.modelado.proyecto2.graficable.VerticeCoordenado;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase Estacion que representa una estación en un sistema de rutas.
 * Cada estación tiene coordenadas en un plano, una afluencia, un color
 * representativo y una descripción breve.
 */
public class Estacion implements VerticeCoordenado, Serializable {

    /**Para objetos serializables. */
    private static final long serialVersionUID = 1L;

    /**La coordenadaX. */
    private double coordX;
    /**La coodenada Y. */
    private double coordY;
    /**La afluencia de la estación. */
    private double afluencia;
    /**El color del vértice. */
    private ColorHex colorVertice;
    /**Una descripción de la estación.  */
    private String descripcion;

    /**
     * Constructor de la clase Estacion.
     *
     * @param coordX coordenada X de la estación en el plano.
     * @param coordY coordenada Y de la estación en el plano.
     * @param afluencia un valor que representa la afluencia de la estación.
     * @param colorVertice color para el vértice que representa la estación.
     * @param descripcion breve descripción para la estación.
     */
    public Estacion(double coordX, double coordY, double afluencia, ColorHex colorVertice, String descripcion) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.afluencia = afluencia;
        this.colorVertice = colorVertice;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la coordenada X de la estación.
     *
     * @return double que representa la coordenada X de la estación.
     */
    @Override
    public double getCoordX() {
        return this.coordX;
    }

    /**
     * Obtiene la coordenada Y de la estación.
     *
     * @return double que representa la coordenada Y de la estación.
     */
    @Override
    public double getCoordY() {
        return this.coordY;
    }

    /**
     * Obtiene la afluencia de la estación.
     *
     * @return double que representa la afluencia de la estación.
     */
    public double getAfluencia() {
        return this.afluencia;
    }

    /**
     * Obtiene el color del vértice de la estación.
     *
     * @return ColorHex que representa el color del vértice de la estación.
     */
    @Override
    public ColorHex getColorVertice() {
        return this.colorVertice;
    }

    /**
     * Obtiene la descripción de la estación.
     *
     * @return String que contiene la descripción de la estación.
     */
    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Compara esta estación con otro objeto.
     *
     * @param obj el objeto con el que se comparará.
     * @return true si el objeto es igual a esta estación; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Comparar referencia
        if (!(obj instanceof Estacion)) return false; // Verificar si es del mismo tipo
        Estacion other = (Estacion) obj; // Hacer el casting

        // Comparar todos los atributos relevantes
        return Double.compare(coordX, other.getCoordX()) == 0 &&
               Double.compare(coordY, other.getCoordY()) == 0 &&
               descripcion.equals(other.getDescripcion());
    }

    /**
     * Genera un código hash para esta estación.
     *
     * @return int que representa el código hash de la estación.
     */
    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY, descripcion);
    }

    /**
     * Implementación del método toString, importante para practicidad de javaFX.
     * 
     * @return la cadena devuelta por el método getDescripcion()
     */
    @Override public String toString(){
        return getDescripcion();
    }
}
