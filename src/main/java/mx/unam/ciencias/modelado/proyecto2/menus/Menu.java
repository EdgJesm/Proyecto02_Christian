package mx.unam.ciencias.modelado.proyecto2.menus;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;
//import mx.unam.ciencias.modelado.proyecto2.;
// import mx.unam.ciencias.modelado.proyecto2 ... ;

import java.util.*;
/**
* Clase Menu que crea un menú orientado al cliente para navegar en la app
*/
public class Menu {

    // Atributos de la clase
    private Estacion origen;
    private Estacion destino;

    /**
     * Método que genera el menú de las estaciones.
     * @param estaciones todas las estaciones existentes.
     * @return String menu de las estaciones
     */
    private String menuEstaciones(List<Estacion> estaciones) {
      StringBuilder str = new StringBuilder();
      str.append("----- Menu de estaciones -----");
      str.append("______________________________");
      for(Estacion estacion: estaciones){
        // str.append(estacion.hashCode + "> " + estacion.toString());
      }
      return str.toString();
    }
    /**
     * Método para poder seleccionar una estación .
     * @return Estacion estación seleccionada.
     */
    public Estacion eligeEstacion(){
      return null;
    }
    /**
     * Método para crear un menú general.
     * @return void.
     */
    public void menuGeneral() {
      StringBuilder str = new StringBuilder();
      str.append("----- Menu general -----");
      str.append("1 > Opcion .");
      str.append("2 > Opcion .");
      System.out.println(str.toString());
      // while (true){}
    }
}
