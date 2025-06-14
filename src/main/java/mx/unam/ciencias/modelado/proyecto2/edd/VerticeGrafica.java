package mx.unam.ciencias.modelado.proyecto2.edd;

/**
 * Interfaz para vértices de gráfica. Un vértice de gráfica puede darnos el
 * elemento en el vértice, un iterable con sus vecinos, decirnos el grado del
 * vértice y obtener o cambiar su color.
 * 
 * @param <T> el tipo del elemento almacenado en el vértice.
 * 
 */
public interface VerticeGrafica<T> {

    /**
     * Regresa el elemento del vértice.
     * @return el elemento del vértice.
     */
    public T get();

    /**
     * Regresa el grado del vértice.
     * @return el grado del vértice.
     */
    public int getGrado();

    /**
     * Regresa un iterable con los vecinos del vértice.
     * @return un iterable con los vecinos del vértice.
     */
    public Iterable<? extends VerticeGrafica<T>> vecinos();
}
