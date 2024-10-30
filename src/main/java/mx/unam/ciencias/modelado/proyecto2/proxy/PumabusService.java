package mx.unam.ciencias.modelado.proyecto2.menus;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.Estacion;

import java.util.List;
/*
* Interfaz para patrón de diseño proxy
*/
public interface PumabusService{
  /*
  * Método abstracto para calcular la ruta más corta entre dos estaciones eligiendo entre tipo de costos
  * @param int opcion opción para el tipo de costos
  * @param Estacion origen estación en la que nos encontramos
  * @param Estacion destion estación a la que queremos llegar
  * @return List<Estacion> la ruta óptima a seguir
  */
  List<Estacion> buscaRutaCorta(int opcion, Estacion origen, Estacion destino);

}
