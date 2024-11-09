package mx.unam.ciencias.modelado.proyecto2.menus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.composite.*;
import mx.unam.ciencias.modelado.proyecto2.graficable.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.RutaOptima;
import mx.unam.ciencias.modelado.proyecto2.common.ReaderWriter;

import java.util.List;
import java.util.Comparator;

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

    /**Constructor de la clase. */
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
            comboRutas.setPromptText("Seleccione ruta.");

            ComboBox<Estacion> comboEstacionOrigen = new ComboBox<>();
            comboEstacionOrigen.setPromptText("Seleccione origen.");
            ComboBox<Estacion> comboEstacionDestino = new ComboBox<>();
            comboEstacionDestino.setPromptText("Seleccione destino.");

            ComboBox<RutaOptima> comboRutasOptimas = new ComboBox<>();
            comboRutasOptimas.setPromptText("Seleccione tipo de trayectoria.");

            comboRutasOptimas.getItems().addAll(rutasOptimas);
            // los deshabilitamos de inicio
            comboEstacionOrigen.setDisable(true);
            comboEstacionDestino.setDisable(true);
            comboRutasOptimas.setDisable(true);

            comboRutas.setOnAction(e -> {
                Ruta rutaSeleccionada = comboRutas.getValue();
                if (rutaSeleccionada != null) {
                    List<Estacion> estaciones = rutaSeleccionada.getEstaciones();
                    estaciones.sort(Comparator.comparing(Object::toString));//Los ordena.
                    comboEstacionOrigen.getItems().setAll(estaciones);
                    comboEstacionDestino.getItems().setAll(estaciones);
                    comboEstacionOrigen.setDisable(false) ;
                    comboEstacionDestino.setDisable(true);
                    comboRutasOptimas.setDisable(true);
                }
            });
            comboEstacionOrigen.setOnAction(e -> {
              if(comboEstacionOrigen.getValue() != null)
                  comboEstacionDestino.setDisable(false);
            });
            comboEstacionDestino.setOnAction(e -> {
              if(comboEstacionDestino.getValue() != null)
                  comboRutasOptimas.setDisable(false);
            });

            Button button = new Button("Buscar Ruta");
            button.setOnAction(e -> {
                Ruta rutaSeleccionada = comboRutas.getValue();
                Estacion origen = comboEstacionOrigen.getValue();
                Estacion destino = comboEstacionDestino.getValue();
                RutaOptima rutaOptimaSeleccionada = comboRutasOptimas.getValue();

                if(rutaSeleccionada == null || origen == null || destino == null || rutaOptimaSeleccionada == null){
                    mostrarError("Por favor, selecciona todos los campos necesarios.");
                }else{
                    List<Estacion> trayectoria = rutaSeleccionada.buscaRuta(origen, destino, rutaOptimaSeleccionada);
                    String archivo = generaArchivo(rutaSeleccionada, trayectoria);
                    mostrarRuta(archivo);
                }
            });

            VBox vbox = new VBox(10, comboRutas, comboEstacionOrigen, comboEstacionDestino, comboRutasOptimas, button);
            Scene scene = new Scene(vbox, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sistema de rutas para el Pumabus.");
            primaryStage.show();
        });
    }

    /**
     * Método que muestra el archivo de imagen en une nueva ventana.
     * @param pngFilePath la ruta del archivo png a mostrar.
     */
    private void mostrarRuta(String pngFilePath) {
        try (InputStream imageStream = new FileInputStream(pngFilePath)) {
            // Cargar la imagen PNG en JavaFX
            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(600); // Ajustar según sea necesario

            imageView.setOnScroll(event -> {
                double delta = event.getDeltaY(); // Detecta el movimiento de la rueda del mouse o el gesto del mousepad.

                // Si el gesto es hacia arriba (hacia afuera), hacemos zoom in, si es hacia abajo (hacia adentro), zoom out
                if (delta > 0) {
                    imageView.setFitWidth(imageView.getFitWidth() * 1.1); // Aumentar tamaño
                    imageView.setFitHeight(imageView.getFitHeight() * 1.1);
                } else if (delta < 0) {
                    imageView.setFitWidth(imageView.getFitWidth() * 0.9); // Reducir tamaño
                    imageView.setFitHeight(imageView.getFitHeight() * 0.9);
                }
            });

            // Habilitar el desplazamiento con el mouse (arrastrando la imagen)
            imageView.setOnMousePressed(event -> {
                // Calcular la diferencia entre la posición donde se hace clic y la posición de la imagen
                double offsetX = event.getSceneX() - imageView.getTranslateX();
                double offsetY = event.getSceneY() - imageView.getTranslateY();
                imageView.setUserData(new double[]{offsetX, offsetY});
            });

            imageView.setOnMouseDragged(event -> {
                // Recuperar el desplazamiento registrado
                double[] offset = (double[]) imageView.getUserData();
                if (offset != null) {
                    // Mover la imagen según el desplazamiento
                    imageView.setTranslateX(event.getSceneX() - offset[0]);
                    imageView.setTranslateY(event.getSceneY() - offset[1]);
                }
            });

            // Crear una ScrollPane para hacer la imagen desplazable
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(imageView);
            scrollPane.setFitToWidth(true); // Ajustar a la pantalla
            scrollPane.setFitToHeight(true);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            // Crear una nueva ventana para mostrar la imagen
            Stage stage = new Stage();
            VBox vbox = new VBox(imageView);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.setTitle("Ruta Calculada");
            stage.show();

        } catch (Exception e) {
            mostrarError("Error al mostrar la imagen de la ruta: " + e.getMessage());
        }
    }

    /**
     * Método que genera el archivo .svg y despues el .png de la grafica que construimos.
     * @param ruta una instancia de ruta (que debe contener la grafica a graficar).
     * @param trayectoria la lista de estaciones que constituyen la trayectoria de una estacion a otra.
     * @return el nombre del archivo para poder acceder a él desde otro lado.
     */
    private String generaArchivo(Ruta ruta, List<Estacion> trayectoria){
        GraficadorBuilderSVG<Estacion> graficador = new GraficadorBuilderSVG<>(ruta.getGrafica());
        graficador.setTrayectoria(trayectoria);
        graficador.setDatosColores(ruta.getDatosColoracion());

        // Guardamos el archivo SVG temporalmente
        String svgFilePath = graficador.getNombreArchivo() + ".svg";
        ReaderWriter.writeOverwrite(graficador.graficar(), svgFilePath);

        // Convertimos el SVG a PNG
        String pngFilePath = graficador.getNombreArchivo() + ".png";
        convertirSVGaPNG(svgFilePath, pngFilePath);

        // Devuelve la ruta del archivo PNG generado
        return pngFilePath;
    }

    /**
     * Método que, dado un archivo svg, lo convierte a png.
     * @param svgFilePath la ruta al svg
     * @param pngFilePath la ruta al png
     */
    private void convertirSVGaPNG(String svgFilePath, String pngFilePath) {
        try (FileInputStream svgInputStream = new FileInputStream(svgFilePath);
            FileOutputStream pngOutputStream = new FileOutputStream(pngFilePath)) {

            // Transcodificar SVG a PNG usando Batik
            PNGTranscoder transcoder = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(svgInputStream);
            TranscoderOutput output = new TranscoderOutput(pngOutputStream);
            transcoder.transcode(input, output);

        } catch (Exception e) {
            mostrarError("Error al convertir SVG a PNG: " + e.getMessage());
        }
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
