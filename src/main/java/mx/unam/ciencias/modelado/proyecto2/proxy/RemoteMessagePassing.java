package mx.unam.ciencias.modelado.proyecto2.proxy;

import java.net.*;
import java.io.*;

import java.io.Serializable;

/**
 * Clase que modela el comportamiento de paso de mensajes remotos utilizando sockets.
 * Permite enviar y recibir objetos de forma remota a través de un {@link Socket}.
 * 
 * @param <T> El tipo de objeto que se va a enviar o recibir, debe ser serializable.
 */
public class RemoteMessagePassing<T extends Serializable> {

    /** El socket usado para la comunicación. */
    public Socket socket;
    
    /** El objeto de salida utilizado para enviar objetos a través del socket. */
    public ObjectOutputStream out;
    
    /** El objeto de entrada utilizado para recibir objetos desde el socket. */
    public ObjectInputStream in;
    
    //public Object sending = new Object(); // Objeto para la sincronización durante el envío.
    //public Object receiving = new Object(); // Objeto para la sincronización durante la recepción.

    /**
     * Constructor de la clase {@code RemoteMessagePassing}.
     * 
     * Establece la conexión utilizando un {@link Socket} y configura los flujos de entrada y salida
     * para la transmisión de objetos serializables.
     * 
     * @param socket El socket a través del cual se establecerá la comunicación.
     *               No debe ser {@code null}.
     */
    public RemoteMessagePassing(Socket socket) {
        if(socket == null){
            System.out.println("El socket está vacío.");
            return;
        }
        this.socket = socket;
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Método para enviar un objeto de tipo {@code T} a través del socket.
     * Utiliza el {@link ObjectOutputStream} para escribir el objeto al flujo de salida.
     * 
     * @param obj El objeto que se va a enviar. Este debe ser de un tipo que implemente {@link Serializable}.
     */
    public void send(T obj) {
        //synchronized(sending){
        try{
            out.writeObject(obj);
        } catch (IOException e){
            e.printStackTrace();
        }
        //}
    }

    /**
     * Método para recibir un objeto de tipo {@code T} a través del socket.
     * Utiliza el {@link ObjectInputStream} para leer el objeto desde el flujo de entrada.
     * 
     * @return El objeto recibido, o {@code null} si ocurre un error.
     */
    @SuppressWarnings("unchecked") 
    public T receive() {
        T value = null;
        //synchronized(receiving){
        try{
            Object obj = in.readObject();
            if(obj != null || obj.getClass().isAssignableFrom(value.getClass())){
                value = (T) obj;
            }else {
                System.out.println("Objeto con tipo distinto");
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (EOFException e) {
            System.out.println("Conexion interrumpida.");
        } catch (IOException e){
            e.printStackTrace();
        }
        //}
        return value;
    }

    /**
     * Método para cerrar la comunicación. Cierra el {@link Socket}, el {@link ObjectOutputStream}
     * y el {@link ObjectInputStream} asociados a esta instancia.
     * 
     * @throws IOException Si ocurre un error al cerrar los flujos o el socket.
     */
    public void close() throws IOException {
        try{
            if(socket != null)
                socket.close();
            if(out != null)
                out.close();
            if(in != null)
                in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
