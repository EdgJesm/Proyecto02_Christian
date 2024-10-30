package mx.unam.ciencias.modelado.proyecto2;

import java.io.*;
import java.net.*;

import mx.unam.ciencias.modelado.proyecto2.proxy.Proxy;
import mx.unam.ciencias.modelado.proyecto2.proxy.Servidor;
import mx.unam.ciencias.modelado.proyecto2.proxy.RemoteMessagePassing;
import mx.unam.ciencias.modelado.proyecto2.menus.Menu;
import mx.unam.ciencias.modelado.proyecto2.common.Colors;

// para otras importaciones...
//import mx.unam.ciencias.modelado.proyecto1.proxy.ClienteRemoto;

/**
 * Clase Principal que alberga el cliente y el servidor de nuestra app para PumaBus
 * Permite conocer las distancias óptimas con distintos costos entre 2 estaciones
 */
public class Main {

  /**
   * Metodo main que ejecuta el cliente o el servidor dependiendo de la entrada de argumentos
   * server como argumento inicia el servidor
   * client como argumento inicia el cliente
   */
    public static void main(String[] args) {

      if("server".equals(args[0])) {
          runServer();
      }else if("client".equals(args[0])) {
          runClient();
      }else{
          System.out.println("Por favor ingresa algo valido" +
                  "\nPruba escribiendo:" +
                  "   \"server\" o \"client\"" +
                  "\n E intentalo de nuevo...");
      }
    }

    /**
     * Metodo dedicado al del servidor en PumabusService
     */
    public static void runServer(){
      System.out.println("Servidor PumaBusApp");
      System.out.println("-------------------");
      try{
        ServerSocket server = new ServerSocket(9090);
        System.out.println("Iniciando servidor PumaBusApp");
        // objeto real
        Servidor servidor = Servidor.getInstancia();
        Proxy proxy = new Proxy(servidor);
        while(true){
          Socket s = server.accept();
          System.out.println("Conexión aceptada desde: " + s.getInetAddress());
          RemoteMessagePassing rmp = new RemoteMessagePassing(s);
          rmp.send(proxy);
          System.out.println("Servidor PumaBusApp corriendo");
        }
      }catch (IOException e){
        e.printStackTrace();
      }
    }
    /**
     * Metodo dedicado a la interfaz del cliente en PumabusService
     */
    public static void runClient(){
      System.out.println("=======PumaBusApp=======");

      try{
        Socket s = new Socket("localhost", 9090);
        RemoteMessagePassing rmp = new RemoteMessagePassing(s);
        Proxy proxy = (Proxy)rmp.receive();
        System.out.println("BIENVENIDO!");

        // insertar menu, etc...

        s.close();
       }catch (UnknownHostException e){
          e.printStackTrace();
       }catch (IOException e){
          e.printStackTrace();
       }
     }

    /**
     * Metodo para validar la entrada de un numero entero dentro de un rango.
     *
     * @param mensaje Mensaje mostrado al usuario para ingresar el numero.
     * @param error   Mensaje mostrado al usuario en caso de un error.
     * @param min     Valor minimo aceptado.
     * @param max     Valor maximo aceptado.
     * @return Numero entero validado.
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
}
