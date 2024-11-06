package mx.unam.ciencias.modelado.proyecto2;

import mx.unam.ciencias.modelado.proyecto2.simulaciones.Prueba;
import mx.unam.ciencias.modelado.proyecto2.proxy.ClienteRemoto;


//import mx.unam.ciencias.modelado.proyecto1.proxy.ClienteRemoto;

/**
 * Clase Principal que alberga el cliente y el servidor de nuestra app para PumaBus
 * Permite conocer las distancias óptimas con distintos costos entre 2 estaciones
 */
public class Main {

  /**
   * Metodo main que ejecuta el cliente o el servidor dependiendo de la entrada de argumentos
   * server como argumento inicia el servidor
   * client como argumento inicia el cliente
   */
    public static void main(String[] args) {
        ClienteRemoto.iniciarCliente();
    }
}
