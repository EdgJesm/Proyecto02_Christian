package mx.unam.ciencias.modelado.proyecto1.common;

import java.util.Scanner;

/**Clase de getters para las entradas del usuario. */
public class MetodosGet{

    /**
     * Método "getInt" para recibir un entero por el usuario y manejar sus excepciones
     * @param mensaje la cadena para solicitar la entrada
     * @param error un aviso emergente en caso de que la entrada no sea tratable
     * @param min límite inferior del intervalo en el que las entradas estarán acotadas
     * @param max límite superior del intervalo en el que las entradas estarán acotadas
     * @return val, el entero introducido y válido para su uso en el programa
     */
    public static int getInt(String mensaje, String error, int min, int max) {
        int val = 0;
        String s;
        Scanner scn = new Scanner(System.in);

        boolean continuar;

        do {
            try {
                Colors.println(mensaje, Colors.HIGH_INTENSITY);
                s = scn.next();
                val = Integer.parseInt(s);
                // (-infinito, min) || (max, infinito)
                if (val < min || max < val) {
                    throw new NumberFormatException();
                } else {
                    continuar = false;
                }
            } catch (NumberFormatException e) {
                continuar = true;
                scn.reset();
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            }
        } while (continuar);

        return val;
    }

    /**
     * Método "getShort" para recibir un entero por el usuario y manejar sus excepciones
     * @param mensaje la cadena para solicitar la entrada
     * @param error un aviso emergente en caso de que la entrada no sea tratable
     * @param min límite inferior del intervalo en el que las entradas estarán acotadas
     * @param max límite superior del intervalo en el que las entradas estarán acotadas
     * @return val, el entero introducido y válido para su uso en el programa
     */
    public static short getShort(String mensaje, String error, int min, int max) {
        short val = 0;
        String s;
        Scanner scn = new Scanner(System.in);

        boolean continuar;

        do {
            try {
                Colors.println(mensaje, Colors.HIGH_INTENSITY);
                s = scn.next();
                val = Short.parseShort(s);
                // (-infinito, min) || (max, infinito)
                if (val < min || max < val) {
                    throw new NumberFormatException();
                } else {
                    continuar = false;
                }
            } catch (NumberFormatException e) {
                continuar = true;
                scn.reset();
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            }
        } while (continuar);

        return val;
    }

    /**
     * Método "getString" para recibir una cadena por el usuario y manejar sus excepciones
     * @param mensaje la cadena para solicitar la entrada
     * @param error un aviso emergente en caso de que la entrada no sea tratable
     * @return val, la cadena introducida y válida para su uso en el programa
     */
    public static String getString(String mensaje, String error) {
        String val = null;
        Scanner scn = new Scanner(System.in);
        boolean continuar;

        do {
            try {
                Colors.println(mensaje, Colors.HIGH_INTENSITY);
                val = scn.nextLine();

                // Excepción, en caso de que la longitud de que el usuario no haya ingresado ningún caracter
                if (val.length() == 0) {
                    throw new IllegalArgumentException();
                } else {
                    continuar = false;
                }
            } catch (IllegalArgumentException e) {
                continuar = true;
                scn.reset();
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            }
        } while (continuar);

        return val;
    }

}