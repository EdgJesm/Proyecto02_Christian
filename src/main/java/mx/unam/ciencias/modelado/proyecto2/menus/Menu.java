package mx.unam.ciencias.modelado.proyecto2.menus;

import mx.unam.ciencias.modelado.proyecto2.common.ReaderWriter;
import mx.unam.ciencias.modelado.proyecto2.graficable.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.*;
import mx.unam.ciencias.modelado.proyecto2.composite.*;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.factory.rutas.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class Menu extends Application {

    private static RutaCompuesta rutaCompuesta;
    private static List<RutaOptima> rutasOptimas;

    //Constructor vacío.
    public Menu(){}

    // Constructor que recibe RutaCompuesta y lista de RutaOptima
    public void inicializaDatos(RutaCompuesta rutaCompuesta, List<RutaOptima> rutasOptimas) {
        this.rutaCompuesta = rutaCompuesta;
        this.rutasOptimas = rutasOptimas;
    }

    @Override
    public void start(Stage primaryStage) {
        if (rutaCompuesta == null) {
          throw new IllegalStateException("rutaCompuesta no puede ser nulo.");
        }

        // Crear ComboBoxes
        ComboBox<Ruta> comboRutas = new ComboBox<>();
        comboRutas.getItems().addAll(rutaCompuesta.getRutas());

        ComboBox<Estacion> comboEstacionOrigen = new ComboBox<>();
        ComboBox<Estacion> comboEstacionDestino = new ComboBox<>();

        ComboBox<RutaOptima> comboRutasOptimas = new ComboBox<>();
        comboRutasOptimas.getItems().addAll(rutasOptimas);

        // Listener para actualizar estaciones según la gráfica seleccionada
        comboRutas.setOnAction(e -> {
            Ruta rutaSeleccionada = comboRutas.getValue();
            if (rutaSeleccionada != null) {
                List<Estacion> estaciones = rutaSeleccionada.getEstaciones();
                comboEstacionOrigen.getItems().setAll(estaciones);
                comboEstacionDestino.getItems().setAll(estaciones);
                comboEstacionOrigen.getSelectionModel().clearSelection();
                comboEstacionDestino.getSelectionModel().clearSelection();
            }
        });

        // Botón para procesar la selección del usuario
        Button button = new Button("Buscar Ruta");
        button.setOnAction(e -> {
            Ruta rutaSeleccionada = comboRutas.getValue();
            Estacion estacionOrigenSeleccionada = comboEstacionOrigen.getValue();
            Estacion estacionDestinoSeleccionada = comboEstacionDestino.getValue();
            RutaOptima rutaOptimaSeleccionada = comboRutasOptimas.getValue();

            // Validación de selección y llamada a buscaRuta
            if (rutaSeleccionada != null && estacionOrigenSeleccionada != null && estacionDestinoSeleccionada != null && rutaOptimaSeleccionada != null) {
                List<Estacion> ruta = rutaCompuesta.buscaRuta(estacionOrigenSeleccionada, estacionDestinoSeleccionada, rutaOptimaSeleccionada);
                mostrarRuta(ruta);
            } else {
                mostrarError("Por favor, selecciona todos los campos necesarios.");
            }
        });

        // Configurar layout
        VBox vbox = new VBox(10, comboRutas, comboEstacionOrigen, comboEstacionDestino, comboRutasOptimas, button);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Seleccionar Ruta en RutaCompuesta");
        primaryStage.show();
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

    public static void main(String[] args) {
        // Llama al método para inicializar datos
        Menu menu = new Menu();
        menu.inicializaDatos(rutaCompuesta, rutasOptimas);
        
        launch(args); // Lanza la aplicación JavaFX
    }
}
