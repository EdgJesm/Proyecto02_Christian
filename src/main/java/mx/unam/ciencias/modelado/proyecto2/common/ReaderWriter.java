package mx.unam.ciencias.modelado.proyecto2.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

/**
 * Clase utilitaria para el manejo de archivos empleando java.io.
 * Se implementan metodos para lectura (read) y escritura (read) de archivos.
 */
public class ReaderWriter {

    /**Constructor vacío. */
    public ReaderWriter(){}

    /**
     * Método para leer los renglones de un archivo completo.
     * @param fileName el nombre del archivo a leer.
     * @return una lista con las lineas del archivo.
     */
    public static List<String> read(String fileName)  {
        String line;
        LinkedList<String> renglones = null;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(fileName));
            renglones = new LinkedList<>();
            while ((line = in.readLine()) != null) {
                renglones.add(line);
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return renglones;
    }

    /**
     * Método para escribir una linea al final de un archivo.
     * @param articulo el bloque de texto que se quiere escribir.
     * @param fileName el nombre del archivo en el cual se va a escribir.
     */
    public static void write(String articulo, String fileName) {
        FileWriter out = null;

        try {
            out = new FileWriter(fileName, true);
            out.write(articulo);
            out.write("\n");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Método para escribir una cadena en un archivo, sobrescribiendo cualquier contenido existente.
     * @param contenido el texto que se quiere escribir.
     * @param fileName el nombre del archivo en el cual se va a escribir.
     */
    public static void writeOverwrite(String contenido, String fileName) {
        FileWriter out = null;

        try {
            // Crear el FileWriter en modo de sobrescritura (append = false)
            out = new FileWriter(fileName, false);
            out.write(contenido);
            out.write("\n"); // Opcional: agrega un salto de línea al final
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    /**
     * Método para escribir una lista de cadenas en un archivo.
     * Sobrescribe cualquier contenido existente en el archivo.
     * @param lineas la lista de cadenas a escribir.
     * @param fileName el nombre del archivo en el cual se va a escribir.
     */
    public static void writeList(List<String> lineas, String fileName) {
        FileWriter out = null;

        try {
            // Sobrescribir el archivo existente
            out = new FileWriter(fileName, false);
            for (String linea : lineas) {
                out.write(linea);
                out.write("\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}
