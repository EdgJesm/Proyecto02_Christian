package mx.unam.ciencias.modelado.proyecto2.simulaciones;

import mx.unam.ciencias.modelado.proyecto2.common.ReaderWriter;
import mx.unam.ciencias.modelado.proyecto2.graficable.*;
import mx.unam.ciencias.modelado.proyecto2.strategy.*;
import mx.unam.ciencias.modelado.proyecto2.composite.*;
import mx.unam.ciencias.modelado.proyecto2.factory.fabricarutas.*;
import mx.unam.ciencias.modelado.proyecto2.factory.rutas.*;
//import mx.unam.ciencias.modelado.proyecto2.edd.GraficaDirigida;
import java.util.List;

public class Prueba{

    private static ReaderWriter rw = new ReaderWriter();

    public static void main(String[] args){

        RutaCompuesta sistema = new RutaCompuesta();

        Ruta1 r1 = new Ruta1(rw.read("data/linea1.csv"));
        Ruta2 r2 = new Ruta2(rw.read("data/linea2.csv"));
        Ruta3 r3 = new Ruta3(rw.read("data/linea3.csv"));
        System.err.println("Rutas cargadas");

        sistema.agrega(r1.getGrafica());
        sistema.agrega(r2.getGrafica());
        sistema.agrega(r3.getGrafica());
        System.err.println("Rutas agregadas.");


        Estacion origen = new Estacion(7.7, 5.9, 24.0, ColorHex.NEGRO, "BaseMetroCU");
        Estacion destino = new Estacion(7.3, 6.9, 17.0, ColorHex.NEGRO, "TiendaUNAM1");
        System.err.println("Trayectoria establecida.");
        
        RutaOptima criterio = new MenorDistancia();
        System.err.println("Criterio establecido.");

        List<Estacion> trayectoria = sistema.buscaRuta(origen, destino, criterio);
        System.err.println("Trayectoria encontrada.");

        GraficadorBuilderSVG<Estacion> graficador = new GraficadorBuilderSVG<>(sistema.getGrafica());
        graficador.setTrayectoria(trayectoria);
        System.err.println("Graficos logrados");


        System.out.println(graficador.graficar());

        System.err.println("lol??");


    }
}



