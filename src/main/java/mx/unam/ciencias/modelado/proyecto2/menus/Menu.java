package mx.unam.ciencias.modelado.proyecto2.menus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.composite.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends Application {

    private RutaCompuesta rutaCompuesta;
    private List<RutaOptima> rutasOptimas;

    // Variables estáticas para inicialización inicial
    private static RutaCompuesta inicialRutaCompuesta;
    private static List<RutaOptima> inicialRutasOptimas;

    // Constructor vacío para JavaFX
    public Menu() {}

    public static void launchMenu(RutaCompuesta rutaCompuesta, List<RutaOptima> rutasOptimas) {
        // Guardar temporalmente los datos necesarios en variables estáticas
        inicialRutaCompuesta = rutaCompuesta;
        inicialRutasOptimas = rutasOptimas;

        // Iniciar la aplicación JavaFX
        Application.launch(Menu.class);
    }

    @Override
    public void start(Stage primaryStage) {
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
                    List<Estacion> ruta = rutaCompuesta.buscaRuta(origen, destino, rutaOptimaSeleccionada);
                    mostrarRuta(ruta);
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

    private void mostrarRuta(List<Estacion> ruta) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ruta Calculada");
        alert.setHeaderText("La ruta seleccionada es:");
        alert.setContentText(ruta.stream().map(Estacion::toString).collect(Collectors.joining(" -> ")));
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
