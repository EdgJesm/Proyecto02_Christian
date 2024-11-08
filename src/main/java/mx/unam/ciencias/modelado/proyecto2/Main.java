package mx.unam.ciencias.modelado.proyecto2;

import mx.unam.ciencias.modelado.proyecto2.proxy.ClienteRemoto;


//import mx.unam.ciencias.modelado.proyecto1.proxy.ClienteRemoto;

/**
 * Clase Principal que alberga el cliente y el servidor de nuestra app para PumaBus
 * Permite conocer las distancias óptimas con distintos costos entre 2 estaciones
 */
public class Main {

    /**Constructor de la clase. */
    public Main(){}

    /**
     * Método main para usar el programa como usuario.
     * @param args un arreglo de argumentos.
     */
    public static void main(String[] args) {
        ClienteRemoto.iniciarCliente();
    }
}
