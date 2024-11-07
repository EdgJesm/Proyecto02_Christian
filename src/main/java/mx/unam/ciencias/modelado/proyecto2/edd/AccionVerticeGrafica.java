package mx.unam.ciencias.modelado.proyecto2.edd;

/**
 * Interfaz para realizar acciones sobre vértices de gráficas.
 * 
 *  @param <T> el tipo de los elementos almacenados en los vértices de la gráfica.
 */
@FunctionalInterface
public interface AccionVerticeGrafica<T> {

    /**
     * Realiza una acción sobre un vértice de gráfica.
     * @param vertice el vértice sobre el que se realizará la acción.
     */
    public void actua(VerticeGrafica<T> vertice);
}

