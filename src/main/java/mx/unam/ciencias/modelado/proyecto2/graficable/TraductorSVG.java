package mx.unam.ciencias.modelado.proyecto2.graficable;

import java.util.Locale;

/**
 * Clase Traductor SVG.
 * Se encarga de almacenar todas las posibles figuras que podamos utilizar a lo largo
 * del programa, además de almacenarlas en un stringBuilder local.
 */
public class TraductorSVG implements TraductorLenguaje {

    /** Constructor vacío */
    public TraductorSVG() {}

    @Override public String terminacion() {
        return ".svg";
    }

    /**
     * Empieza el archivo dados sus medidas de ancho y alto.
     * @param ancho el ancho del lienzo "width".
     * @param alto el alto del lienzo "height".
     * @return una cadena que inicia el archivo SVG.
     */
    @Override public String start(double ancho, double alto) {
        return String.format(Locale.US, "<?xml version='1.0' encoding='UTF-8' ?>\n<svg xmlns='http://www.w3.org/2000/svg' width='%.2fpx' height='%.2fpx'>\n <g>\n", ancho, alto);
    }



    /** Cierra el archivo, termina de hacer cambios.
     * @return una cadena que cierra el archivo SVG.
     */
    @Override public String end() {
        return " </g>\n</svg>\n";
    }

    /**
     * Método que dibuja un círculo con un texto como su contenido.
     * @param cx la coordenada x del centro.
     * @param cy la coordenada y del centro.
     * @param radio el radio del círculo.
     * @param colorBorde el color del borde del círculo.
     * @param gruesoBorde el grosor del borde del círculo.
     * @param colorFigura el color del círculo.
     * @return una cadena SVG que representa el círculo.
     */
    @Override public String dibujaCirculo(double cx, double cy, int radio, ColorHex colorBorde, int gruesoBorde, ColorHex colorFigura) {
        return String.format(Locale.US, "    <circle cx='%.2f' cy='%.2f' r='%d' stroke='%s' stroke-width='%d' fill='%s' />\n", 
                              cx, cy, radio, colorBorde.getCodigoColor(), gruesoBorde, colorFigura.getCodigoColor());
    }

    /**
     * Método que dibuja una línea en el lienzo.
     * @param x1 coordenada horizontal del primer extremo.
     * @param y1 coordenada vertical del primer extremo.
     * @param x2 coordenada horizontal del segundo extremo.
     * @param y2 coordenada vertical del segundo extremo.
     * @param colorPoste el color de la línea.
     * @param anchoPoste el ancho de la línea.
     * @return una cadena SVG que representa la línea.
     */
    @Override public String dibujaLinea(double x1, double y1, double x2, double y2, ColorHex colorPoste, int anchoPoste) {
        return String.format(Locale.US, "    <line x1='%.2f' y1='%.2f' x2='%.2f' y2='%.2f' stroke='%s' stroke-width='%d' />\n", 
                              x1, y1, x2, y2, colorPoste.getCodigoColor(), anchoPoste);
    }

    /**
     * Método encargado de generar un texto en el lienzo.
     * @param x la coordenada x.
     * @param y la coordenada y.
     * @param texto la cadena a escribir.
     * @param tamFuente el tamaño de la fuente.
     * @param colorTexto el texto a escribir en el lienzo.
     * @return una cadena SVG que representa el texto.
     */
    @Override public String dibujaTexto(double x, double y, String texto, int tamFuente, ColorHex colorTexto) {
        return String.format(Locale.US, "    <text fill='%s' font-family='sans-serif' font-size='%d' font-weight='bold' x='%.2f' y='%.2f' text-anchor='middle'>%s</text>\n",
                             colorTexto.getCodigoColor(), tamFuente, x, y, texto);
    }

    /**
     * Dibuja un texto en el lienzo en la posición especificada [alineado a la izquierda].
     *
     * @param x la coordenada x del punto de inicio del texto.
     * @param y la coordenada y del punto de inicio del texto.
     * @param texto el contenido del texto que se mostrará en el lienzo.
     * @param tamFuente el tamaño de la fuente del texto.
     * @param colorTexto el color del texto, especificado mediante una instancia de ColorHex.
     */
    @Override public String dibujaTextoIzquierda(double x, double y, String texto, int tamFuente, ColorHex colorTexto){
        return String.format(Locale.US, "    <text fill='%s' font-family='sans-serif' font-size='%d' font-weight='bold' x='%.2f' y='%.2f' text-anchor='start'>%s</text>\n",
                             colorTexto.getCodigoColor(), tamFuente, x, y, texto);
    }

    /**
     * Dibuja un texto en el lienzo en la posición especificada [alineado a la derecha].
     *
     * @param x la coordenada x del punto de inicio del texto.
     * @param y la coordenada y del punto de inicio del texto.
     * @param texto el contenido del texto que se mostrará en el lienzo.
     * @param tamFuente el tamaño de la fuente del texto.
     * @param colorTexto el color del texto, especificado mediante una instancia de ColorHex.
     */
    @Override public String dibujaTextoDerecha(double x, double y, String texto, int tamFuente, ColorHex colorTexto){
        return String.format(Locale.US, "    <text fill='%s' font-family='sans-serif' font-size='%d' font-weight='bold' x='%.2f' y='%.2f' text-anchor='end'>%s</text>\n",
                             colorTexto.getCodigoColor(), tamFuente, x, y, texto);
    }

    /**
     * Método que colorea el lienzo de un color.
     * @param color el color del lienzo.
     * @return una cadena SVG que representa el rectángulo de fondo.
     */
    @Override public String coloreaLienzo(ColorHex color) {
        return String.format(Locale.US, "    <rect width='100%%' height='100%%' fill='%s' />\n", color.getCodigoColor());
    }
}
