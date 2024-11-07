package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.menus.Menu;
import java.io.Serializable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Clase que maneja el flujo del programa como Cliente-Servidor.
 */
public class ClienteRemoto {

    private static final int PORT = 12345; // Puerto para la conexión
    private static final String HOST = "localhost"; // Host del servidor

    /**
     * Método que inicia el servidor, configurando el sistema de rutas y criterios de optimización.
     */
    public static void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            Socket clientSocket = serverSocket.accept(); // Acepta conexiones de cliente
            System.out.println("Cliente conectado.");

            RemoteMessagePassing<Serializable> remote = new RemoteMessagePassing<>(clientSocket);
            PumabusService servidor = Servidor.getInstancia(); // Instancia del servidor
            Proxy proxy = new Proxy(servidor); // Crear el proxy del servidor

            // Enviar el sistema completo y los criterios de optimización al cliente
            remote.send(proxy.getSistemaCompleto());
            remote.send((Serializable) proxy.getCriteriosOptimizacion());

            remote.close(); // Cierra la conexión
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que inicia el cliente, conectándose al servidor para recibir los datos necesarios.
     */
    public static void iniciarCliente() {
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Conectado al servidor en " + HOST + ":" + PORT);

            RemoteMessagePassing<Serializable> remote = new RemoteMessagePassing<>(socket);

            // Recibe el sistema completo y los criterios de optimización del servidor
            RutaCompuesta sistemaCompleto = (RutaCompuesta) remote.receive();
            
            // Recibe el objeto que contiene los criterios de optimización
            Object receivedObject = remote.receive();  // Recibe el objeto como Object
            
            
            // Verificar si el objeto recibido es una lista
            if (receivedObject instanceof List<?>) {
                List<?> list = (List<?>) receivedObject;
                
                // Verificar que los elementos de la lista sean de tipo RutaOptima
                if (list.isEmpty() || list.get(0) instanceof RutaOptima) {
                    @SuppressWarnings("unchecked")
                    List<RutaOptima> criteriosOptimizacion = (List<RutaOptima>) list;  // Cast seguro
                    // Lanza el menú con los datos recibidos
                    Menu.launchMenu(sistemaCompleto, criteriosOptimizacion);
                } else {
                    System.out.println("El tipo recibido no es compatible con List<RutaOptima>");
                }
            } else {
                System.out.println("El objeto recibido no es una lista.");
            }

            remote.close(); // Cierra la conexión
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Método principal para ejecutar el programa en modo cliente o servidor.
     * @param args si es "servidor", ejecuta en modo servidor; si es "cliente", ejecuta en modo cliente.
     */
    public static void main(String[] args) {
        iniciarServidor();
    }
}
