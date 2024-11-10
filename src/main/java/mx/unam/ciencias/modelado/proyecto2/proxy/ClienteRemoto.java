package mx.unam.ciencias.modelado.proyecto2.proxy;

import mx.unam.ciencias.modelado.proyecto2.composite.RutaCompuesta;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.menus.Menu;
import java.io.Serializable;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que maneja el flujo del programa como Cliente-Servidor.
 */
public class ClienteRemoto {

    /** Puerto para la conexión */
    private static final int PORT = 12345;
    /** Host del servidor. */
    private static final String HOST = "localhost";
    /** Contador de conexiones activas atomicInteger es p<ara un uso concurrente de estas cuentas. */
    private static final AtomicInteger conexionesActivas = new AtomicInteger(0);

    /** Constructor de la clase. */
    public ClienteRemoto() {}

    /**
     * Método que inicia el servidor, configurando el sistema de rutas y criterios de optimización.
     * El servidor se mantendrá escuchando y aceptando conexiones mientras haya clientes.
     */
    public static void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);

            // Bucle para aceptar conexiones de clientes mientras el servidor tenga clientes conectados
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Acepta conexión de cliente
                System.out.println("Cliente conectado.");

                // Incrementa el contador de conexiones activas
                conexionesActivas.incrementAndGet();

                // Maneja el cliente en un hilo.
                new Thread(() -> manejarCliente(clientSocket)).start();
            }
        } catch (EOFException e) {
            System.out.println("Un cliente cerró la conexión inesperadamente.");
        } catch (IOException e) {
            System.err.println("Error inesperado: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * Método que maneja la comunicación con un cliente específico.
     * @param clientSocket El socket de la conexión con el cliente.
     */
    private static void manejarCliente(Socket clientSocket) {
        try {
            RemoteMessagePassing<Serializable> remote = new RemoteMessagePassing<>(clientSocket);
            // Instancia del servidor y creación del proxy
            PumabusService servidor = Servidor.getInstancia();
            Proxy proxy = new Proxy(servidor);

            // Enviar el sistema completo y los criterios de optimización al cliente
            remote.send(proxy.getSistemaCompleto());
            remote.send((Serializable) proxy.getCriteriosOptimizacion());

            // Recibe mensaje del cliente (por ejemplo, para indicar si el cliente se desconectó)
            System.out.println(remote.receive());

            // Cuando se recibe el mensaje, decrementamos las conexiones activas
            if (conexionesActivas.decrementAndGet() == 0) {
                System.out.println("No hay más clientes conectados. Cerrando el servidor.");
                System.exit(0);  // Cierra el servidor cuando no haya más clientes conectados
            }

            // Cierra la conexión con el cliente
            remote.close();
        }catch (EOFException e) {
            System.out.println("Un cliente cerró la conexión inesperadamente.");
        } catch (IOException e) {
            System.err.println("Error inesperado: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * Método que inicia el cliente, conectándose al servidor para recibir los datos necesarios.
     */
    public static void iniciarCliente() {
        try{
            Socket socket = new Socket(HOST, PORT);
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

            // Enviar mensaje para notificar al servidor que el cliente se desconectó
            remote.send("Cliente desconectado.");

            remote.close(); // Cierra la conexión
        } catch (EOFException e) {
            System.out.println("Un cliente cerró la conexión inesperadamente.");
        } catch (IOException e) {
            System.err.println("Error inesperado: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * Método principal para ejecutar el programa en modo cliente o servidor.
     * @param args si es "servidor", ejecuta en modo servidor; si es "cliente", ejecuta en modo cliente.
     */
    public static void main(String[] args) {
        // Inicia el servidor
        iniciarServidor();
    }
}
