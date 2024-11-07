package mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas;

import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.graficable.ColorHex;
import mx.unam.ciencias.modelado.proyecto2.composite.Ruta;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase abstracta que implementa el patrón de diseño Factory. Esta clase es responsable de
 * generar un diccionario de objetos de tipo {@link Ruta} a partir de líneas de datos
 * representando estaciones y rutas en una gráfica dirigida.
 * 
 * <p>La clase maneja la creación de una {@link GraficaDirigida} de estaciones, las cuales
 * están conectadas entre sí, y permite buscar rutas óptimas entre estaciones dadas
 * utilizando el algoritmo correspondiente.</p>
 */
public abstract class FabricaRuta implements Ruta, Serializable {

    /** Serial version UID para la serialización del objeto. */
    private static final long serialVersionUID = 1L;

    /** La gráfica dirigida que corresponde a la ruta completa. */
    private GraficaDirigida<Estacion> ruta;

    /**
     * Constructor de la clase {@link FabricaRuta}.
     * 
     * @param lineas Una lista de cadenas que contiene la información de las estaciones y sus conexiones.
     *               Se asume que cada línea contiene los datos de una estación, separados por comas.
     */
    public FabricaRuta(List<String> lineas) {
        ruta = generaGrafica(lineas);
    }

    /**
     * Genera una gráfica dirigida que contiene las estaciones fabricadas a partir de las líneas de datos.
     * 
     * @param lineas Una lista de cadenas representando las estaciones.
     * @return Una {@link GraficaDirigida} que contiene las estaciones conectadas.
     */
    public GraficaDirigida<Estacion> generaGrafica(List<String> lineas) {
        GraficaDirigida<Estacion> graficaDirigida = new GraficaDirigida<>();
        List<Estacion> estaciones = new ArrayList<>();

        // Se fabrica y agrega cada estación
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            Estacion estacion = fabricaEstacion(datos);

            if (estacion.getColorVertice() == null) {
                System.err.println("No tiene color: " + getNombre() + " - " + estacion);
            }

            if (!graficaDirigida.contiene(estacion)) {
                graficaDirigida.agrega(estacion);
            }
            estaciones.add(estacion);
        }

        // Se conectan las estaciones formando un ciclo
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion actual = estaciones.get(i);
            Estacion siguiente = estaciones.get((i + 1) % estaciones.size()); // Ciclo que conecta la última con la primera
            graficaDirigida.conecta(actual, siguiente, actual.getAfluencia());
        }

        return graficaDirigida;
    }

    /**
     * Busca una ruta entre dos estaciones dadas utilizando el algoritmo proporcionado por {@link RutaOptima}.
     * 
     * @param origen Estación de origen.
     * @param destino Estación de destino.
     * @param rutaOptima Estrategia de cálculo de la ruta óptima.
     * @return Una lista de estaciones que representa el camino encontrado entre el origen y el destino.
     */
    @Override
    public List<Estacion> buscaRuta(Estacion origen, Estacion destino, RutaOptima rutaOptima) {
        return rutaOptima.calculaRuta(ruta, origen, destino);
    }

    /**
     * Devuelve la gráfica dirigida que representa la ruta.
     * 
     * @return La {@link GraficaDirigida} que representa la ruta completa.
     */
    @Override
    public GraficaDirigida<Estacion> getGrafica() {
        return ruta;
    }

    /**
     * Obtiene la lista de estaciones que componen la ruta.
     * 
     * @return Una lista de estaciones en la ruta.
     */
    @Override
    public List<Estacion> getEstaciones() {
        return ruta.obtenerElementos();
    }

    /**
     * Devuelve un diccionario de cadenas asociadas a un color, utilizado para la simbología de la ruta.
     * Este mapa es útil para la representación gráfica de la ruta.
     * 
     * @return Un {@link Map} que contiene cadenas como claves y colores como valores asociados.
     */
    @Override
    public Map<String, ColorHex> getDatosColoracion() {
        Map<String, ColorHex> datosColores = new HashMap<>();
        datosColores.put(getNombre(), getColoracion());
        return datosColores;
    }

    /**
     * Devuelve el nombre de la ruta.
     * 
     * @return El nombre de la ruta.
     */
    @Override
    public String toString() {
        return getNombre();
    }

    /**
     * Compara si dos rutas son iguales basándose en el nombre de la ruta.
     * 
     * @param obj El objeto a comparar.
     * @return {@code true} si las rutas son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Ruta that = (Ruta) obj;

        return this.getNombre().equals(that.getNombre());
    }

    /**
     * Verifica si una estación existe dentro de la gráfica de la ruta.
     * 
     * @param elemento Estación a verificar.
     * @return {@code true} si la estación existe, {@code false} si no.
     */
    public boolean contiene(Estacion elemento) {
        return ruta.contiene(elemento);
    }

    /**
     * Método abstracto que debe ser implementado por las clases concretas para obtener el nombre de la ruta.
     * 
     * @return El nombre de la ruta.
     */
    public abstract String getNombre();

    /**
     * Método abstracto que debe ser implementado por las clases concretas para obtener el color de la ruta.
     * 
     * @return El color de la ruta como una instancia de {@link ColorHex}.
     */
    @Override
    public abstract ColorHex getColoracion();

    /**
     * Método abstracto que debe ser implementado por las clases concretas para crear una estación a partir
     * de un arreglo de cadenas.
     * 
     * @param datos Arreglo de cadenas con los datos necesarios para crear la estación.
     * @return Una instancia de {@link Estacion} con los datos proporcionados.
     */
    public abstract Estacion fabricaEstacion(String[] datos);

}
