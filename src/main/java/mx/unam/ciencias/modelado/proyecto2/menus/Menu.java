package mx.unam.ciencias.modelado.proyecto2.menus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.composite.*;
import mx.unam.ciencias.modelado.proyecto2.graficable.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.common.ReaderWriter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase menu que contempla toda la logica en la que el usuario puede generar su ruta.
 */
public class Menu extends Application {

    /**Ruta compuesta, es necesario que acceda al sistema completo. */
    private RutaCompuesta rutaCompuesta;
    /**Lista de estrategias que son los criterios de optimización. */
    private List<RutaOptima> rutasOptimas;

    /**Variable estatica que será un valor momentaneo para la rutaCompuesta. */
    private static RutaCompuesta inicialRutaCompuesta;
        /**Variable estatica que será un valor momentaneo para la lista de rutas otpimas.. */
    private static List<RutaOptima> inicialRutasOptimas;

    // Constructor vacío para JavaFX
    public Menu() {}

    /**
     * Método launcher que inicializa la aplicación.
     * @param rutaCompuesta el sistema al que accede el usuario.
     * @param rutasOptimas la lista de criterios de optimización.
     */
    public static void launchMenu(RutaCompuesta rutaCompuesta, List<RutaOptima> rutasOptimas) {
        // Guardar temporalmente los datos necesarios en variables estáticas
        inicialRutaCompuesta = rutaCompuesta;
        inicialRutasOptimas = rutasOptimas;

        // Iniciar la aplicación JavaFX
        Application.launch(Menu.class);
    }

    /**
     * Implementación del método start()
     * @param primaryStage la ventana que será la interfaz de usuario.
     */
    @Override public void start(Stage primaryStage) {
        // Este método se ejecuta en el hilo de JavaFX
        Platform.runLater(() -> {
            // Inicializar los datos y liberar referencias estáticas
            rutaCompuesta = inicialRutaCompuesta;
            rutasOptimas = inicialRutasOptimas;
            inicialRutaCompuesta = null;
            inicialRutasOptimas = null;

            // Verificación de datos
            if (rutaCompuesta == null || rutasOptimas == null) {
                mostrarError("Los datos no pueden ser nulos. Asegúrate de inicializarlos correctamente.");
                return;
            }

            // Crear ComboBoxes
            ComboBox<Ruta> comboRutas = new ComboBox<>();
            comboRutas.getItems().addAll(rutaCompuesta.getRutas());

            ComboBox<Estacion> comboEstacionOrigen = new ComboBox<>();
            ComboBox<Estacion> comboEstacionDestino = new ComboBox<>();

            ComboBox<RutaOptima> comboRutasOptimas = new ComboBox<>();
            comboRutasOptimas.getItems().addAll(rutasOptimas);

            comboRutas.setOnAction(e -> {
                Ruta rutaSeleccionada = comboRutas.getValue();
                if (rutaSeleccionada != null) {
                    List<Estacion> estaciones = rutaSeleccionada.getEstaciones();
                    comboEstacionOrigen.getItems().setAll(estaciones);
                    comboEstacionDestino.getItems().setAll(estaciones);
                }
            });

            Button button = new Button("Buscar Ruta");
            button.setOnAction(e -> {
                Ruta rutaSeleccionada = comboRutas.getValue();
                Estacion origen = comboEstacionOrigen.getValue();
                Estacion destino = comboEstacionDestino.getValue();
                RutaOptima rutaOptimaSeleccionada = comboRutasOptimas.getValue();

                if (rutaSeleccionada != null && origen != null && destino != null && rutaOptimaSeleccionada != null) {
                    List<Estacion> trayectoria = rutaSeleccionada.buscaRuta(origen, destino, rutaOptimaSeleccionada);
                    generaArchivo(rutaSeleccionada, trayectoria);
                    mostrarRuta(trayectoria);
                } else {
                    mostrarError("Por favor, selecciona todos los campos necesarios.");
                }
            });

            VBox vbox = new VBox(10, comboRutas, comboEstacionOrigen, comboEstacionDestino, comboRutasOptimas, button);
            Scene scene = new Scene(vbox, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Seleccionar Ruta en RutaCompuesta");
            primaryStage.show();
        });
    }

    /**
     * Método que lanza una ventana donde se muestran las estaciones de la trayectoria
     * @param trayectoria la trayectoria que seguirá para llegar a su destino.
     */
    private void mostrarRuta(List<Estacion> trayectoria) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ruta Calculada");
        alert.setHeaderText("La ruta seleccionada es:");
        alert.setContentText(trayectoria.stream().map(Estacion::toString).collect(Collectors.joining(" -> ")));
        alert.showAndWait();
    }

    /**
     * Método que genera el archivo svg de la grafica que construimos.
     * @param ruta una instancia de ruta (que debe contener la grafica a graficar).
     * @param trayectoria la lista de estaciones que constituyen la trayectoria de una estacion a otra.
     */
    private void generaArchivo(Ruta ruta, List<Estacion> trayectoria){
        GraficadorBuilderSVG<Estacion> graficador = new GraficadorBuilderSVG<>(ruta.getGrafica());
        graficador.setTrayectoria(trayectoria);
        ReaderWriter.writeOverwrite(graficador.graficar(), graficador.getNombreArchivo());
    }

    /**
     * Método para mostrar un errror en pantalla.
     * @param mensaje el mensaje de error.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
