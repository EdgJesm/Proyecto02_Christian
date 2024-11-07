package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import java.util.List;

/*
* Clase para crear nuestra Ruta 11
*/
public class Ruta11 extends FabricaRuta {

    private static final String NOMBRE = "Ruta 11";
    private static final ColorHex COLOR = ColorHex.RUTA11;

    public Ruta11(List<String> lineas){
        super(lineas);
    }

    /**
     * Método para fabricar nuestra estación (Ruta11).
     * @param datos datos para poder crear nuestra Ruta 11.
     * @return Estacion nueva estación creada a partir de los datos.
     */
    @Override
    public Estacion fabricaEstacion(String[] datos) {
        // Verificar que el arreglo de datos tenga la longitud correcta
        if (datos.length < 4) {
            throw new IllegalArgumentException("Datos insuficientes para crear una estación.");
        }

        // Extraer los datos del arreglo
        String nombre = datos[0];
        double coordX;
        double coordY;
        double afluencia;

        // Manejo de excepciones para las conversiones a double
        try {
            coordX = Double.parseDouble(datos[1]); // coordenada X
            coordY = Double.parseDouble(datos[2]); // coordenada Y
            afluencia = Double.parseDouble(datos[3]); // afluencia
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número inválido: " + e.getMessage());
        }

        // Retornar una nueva instancia de Estacion
        return new Estacion(coordX, coordY, afluencia, this.getColoracion(), nombre);
    }

    /**
     * Getter para el nombre de nuestra Ruta.
     * @return String NOMBRE nombre de la ruta.
     */
    @Override
    public String getNombre(){return this.NOMBRE;}

    /**
     * Getter para el color de la ruta.
     * @return ColorHex de la ruta.
     */
    @Override 
    public ColorHex getColoracion(){return this.COLOR;}
}
