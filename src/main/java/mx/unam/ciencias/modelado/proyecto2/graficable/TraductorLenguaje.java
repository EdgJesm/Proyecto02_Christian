package mx.unam.ciencias.modelado.proyecto2.graficable;

/**
 * Interfaz que define los métodos necesarios para traducir y generar
 * representaciones gráficas de diversas figuras, como círculos, líneas,
 * textos, y para aplicar color al lienzo en diferentes formatos de salida.
 * Implementaciones de esta interfaz permiten adaptar la generación de
 * figuras en distintos lenguajes o formatos gráficos (como SVG, Canvas, etc.).
 */
public interface TraductorLenguaje {

    /**
     * Retorna la terminacion de los archivos del lenguaje.
     * 
     * @return el formato ".lenguaje" de los archivos.
     */
    public String terminacion();

    /**
     * Inicia el lienzo o contenedor gráfico con las medidas especificadas.
     *
     * @param ancho el ancho del lienzo.
     * @param alto el alto del lienzo.
     * @return una cadena que representa la apertura (firma) del archivo o contenedor gráfico.
     */
    public String start(double ancho, double alto);

    /**
     * Finaliza el lienzo o contenedor gráfico.
     *
     * @return una cadena que representa el cierre del archivo o contenedor gráfico.
     */
    public String end();

    /**
     * Dibuja un círculo en el lienzo con las especificaciones dadas.
     *
     * @param cx la coordenada x del centro del círculo.
     * @param cy la coordenada y del centro del círculo.
     * @param radio el radio del círculo.
     * @param colorBorde el color del borde del círculo, especificado mediante una instancia de ColorHex.
     * @param gruesoBorde el grosor del borde del círculo.
     * @param colorFigura el color de relleno del círculo, especificado mediante una instancia de ColorHex.
     * @return una cadena que representa el codigo para un circulo.
     */
    public String dibujaCirculo(double cx, double cy, int radio, ColorHex colorBorde, int gruesoBorde, ColorHex colorFigura);

    /**
     * Dibuja una línea en el lienzo entre dos puntos especificados.
     *
     * @param x1 la coordenada x del primer extremo de la línea.
     * @param y1 la coordenada y del primer extremo de la línea.
     * @param x2 la coordenada x del segundo extremo de la línea.
     * @param y2 la coordenada y del segundo extremo de la línea.
     * @param colorLinea el color de la línea, especificado mediante una instancia de ColorHex.
     * @param anchoLinea el grosor de la línea.
     * @return una cadena que representa el codigo para una linea recta.
     */
    public String dibujaLinea(double x1, double y1, double x2, double y2, ColorHex colorLinea, int anchoLinea);

    /**
     * Dibuja un texto en el lienzo en la posición especificada.
     *
     * @param x la coordenada x del punto de inicio del texto.
     * @param y la coordenada y del punto de inicio del texto.
     * @param texto el contenido del texto que se mostrará en el lienzo.
     * @param tamFuente el tamaño de la fuente del texto.
     * @param colorTexto el color del texto, especificado mediante una instancia de ColorHex.
     * @return una cadena que representa el codigo para un texto.
     */
    public String dibujaTexto(double x, double y, String texto, int tamFuente, ColorHex colorTexto);

    /**
     * Dibuja un texto en el lienzo en la posición especificada [alineado a la izquierda].
     *
     * @param x la coordenada x del punto de inicio del texto.
     * @param y la coordenada y del punto de inicio del texto.
     * @param texto el contenido del texto que se mostrará en el lienzo.
     * @param tamFuente el tamaño de la fuente del texto.
     * @param colorTexto el color del texto, especificado mediante una instancia de ColorHex.
     * @return una cadena que representa el codigo para un texto alineado a la izquierda.
     */
    public String dibujaTextoIzquierda(double x, double y, String texto, int tamFuente, ColorHex colorTexto);

    /**
     * Dibuja un texto en el lienzo en la posición especificada [alineado a la derecha].
     *
     * @param x la coordenada x del punto de inicio del texto.
     * @param y la coordenada y del punto de inicio del texto.
     * @param texto el contenido del texto que se mostrará en el lienzo.
     * @param tamFuente el tamaño de la fuente del texto.
     * @param colorTexto el color del texto, especificado mediante una instancia de ColorHex.
     * @return una cadena que representa el codigo para un texto alineado a la derecha.
     */
    public String dibujaTextoDerecha(double x, double y, String texto, int tamFuente, ColorHex colorTexto);

    /**
     * Aplica un color de fondo al lienzo.
     *
     * @param color el color de fondo del lienzo, especificado mediante una instancia de ColorHex.
     * @return una cadena pensada para colorear el lienzo de lo que será graficado.
     */
    public String coloreaLienzo(ColorHex color);
}
