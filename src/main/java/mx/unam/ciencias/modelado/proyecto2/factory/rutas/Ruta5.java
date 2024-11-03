package mx.unam.ciencias.modelado.proyecto2.factory.rutas;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import java.util.List;

/*
* Clase para crear nuestra Ruta 5
*/
public class Ruta5 extends FabricaRuta {

    private final String NOMBRE = "Ruta 5";

    public Ruta5(List<String> lineas){
        super(lineas);
    }

    /**
     * Método para fabricar nuestra estación (Ruta5).
     * @param datos datos para poder crear nuestra Ruta 5.
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

        ColorHex colorVertice = ColorHex.RUTA5;

        // Retornar una nueva instancia de Estacion
        return new Estacion(coordX, coordY, afluencia, colorVertice, nombre);
    }

    /**
     * Getter para el nombre de nuestra Ruta.
     * @return String NOMBRE nombre de la ruta.
     */
    @Override
    public String getNombre(){return this.NOMBRE;}
}
