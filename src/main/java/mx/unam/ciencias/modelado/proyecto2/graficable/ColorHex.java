package mx.unam.ciencias.modelado.proyecto2.graficable;

/**Enumeración para los colores en su valor hexadecimal. */
public enum ColorHex {
    /**Color: rojo. */
    ROJO("#FF0000"),
    /**Color: verde. */
    VERDE("#00FF00"),
    /**Color: azul. */
    AZUL("#0000FF"),
    /**Color: amarillo. */
    AMARILLO("#FFFF00"),
    /**Color: naranja. */
    NARANJA("#FFA500"),
    /**Color: negro. */
    NEGRO("#000000"),
    /**Color: blanco. */
    BLANCO("#FFFFFF"),
    /**Color: gris. */
    GRIS("#808080"),
    /** Color: magenta. */
    MAGENTA("#FF00FF"),
    /** Color: púrpura. */
    PURPURA("#800080"),
    /** Color: cian. */
    CYAN("#00FFFF"),
    /**Color de la ruta1 */
    RUTA1("#139546"),
    /**Color de la ruta2 */
    RUTA2("#E2D131"),
    /**Color de la ruta3 */
    RUTA3("#12663C"),
    /**Color de la ruta4 */
    RUTA4("#6F432F"),
    /**Color de la ruta5 */
    RUTA5("#0090C2"),
    /**Color de la ruta6 */
    RUTA6("#A2232D"),
    /**Color de la ruta7 */
    RUTA7("#E7A84C"),
    /**Color de la ruta8 */
    RUTA8("#30316F"),
    /**Color de la ruta9 */
    RUTA9("#A14980"),
    /**Color de la ruta10 */
    RUTA10("#241F1D"),
    /**Color de la ruta11 */
    RUTA11("#603491"),
    /**Color de la ruta12 */
    RUTA12("#B488B2");

    /**Constante codigoColor para los objetos. */
    private final String codigoColor;

    /**
     * Constructor de la enumeracion, asigna el atributo codigoColor.
     * @param codigoColor cadena correspondiente al codigo del color.
     */
    ColorHex(String codigoColor) {
        this.codigoColor = codigoColor;
    }

    /**
     * Getter del codigo de un objeto de la enumeración.
     * @return codigoColor.
     */
    public String getCodigoColor() {
        return codigoColor;
    }
}
