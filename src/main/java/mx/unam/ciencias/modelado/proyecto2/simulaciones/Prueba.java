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

        FabricaRuta r1 = new Ruta1(rw.read("data/linea1.csv"));
        FabricaRuta r2 = new Ruta2(rw.read("data/linea2.csv"));
        FabricaRuta r3 = new Ruta3(rw.read("data/linea3.csv"));
        FabricaRuta r4 = new Ruta4(rw.read("data/linea4.csv"));
        FabricaRuta r5 = new Ruta5(rw.read("data/linea5.csv"));
        FabricaRuta r6 = new Ruta6(rw.read("data/linea6.csv"));
        FabricaRuta r7 = new Ruta7(rw.read("data/linea7.csv"));
        FabricaRuta r8 = new Ruta8(rw.read("data/linea8.csv"));
        FabricaRuta r9 = new Ruta9(rw.read("data/linea9.csv"));
        FabricaRuta r10 = new Ruta10(rw.read("data/linea10.csv"));
        FabricaRuta r11 = new Ruta11(rw.read("data/linea11.csv"));
        FabricaRuta r12 = new Ruta12(rw.read("data/linea12.csv"));

        System.err.println("Rutas cargadas");

        sistema.agrega(r1.getGrafica());
        sistema.agrega(r2.getGrafica());
        sistema.agrega(r3.getGrafica());
        sistema.agrega(r4.getGrafica());
        sistema.agrega(r5.getGrafica());
        sistema.agrega(r6.getGrafica());
        sistema.agrega(r7.getGrafica());
        sistema.agrega(r8.getGrafica());
        sistema.agrega(r9.getGrafica());
        sistema.agrega(r10.getGrafica());
        sistema.agrega(r11.getGrafica());
        sistema.agrega(r12.getGrafica());
        System.err.println("Rutas agregadas.");


        Estacion origen = new Estacion(7.7, 5.9, 24.0, ColorHex.NEGRO, "BaseMetroCU");
        Estacion origen2 = new Estacion(7.3, 7.8, 17.0, ColorHex.NEGRO, "TVUNAM");
        Estacion origen3 = new Estacion(5.7, 9.2, 17.0, ColorHex.NEGRO, "Universum");
        Estacion destino = new Estacion(7.3, 6.9, 17.0, ColorHex.NEGRO, "TiendaUNAM1");
        Estacion destino2 = new Estacion(5.3, 6.1, 17.0, ColorHex.NEGRO, "DGTIC");
        Estacion destino3 = new Estacion(6.2, 3.0, 17.0, ColorHex.NEGRO, "FacultadMedicina");
        Estacion destino4 = new Estacion(2.4, 1.8, 17.0, ColorHex.NEGRO, "AAPAUNAM");

        System.err.println("Trayectoria establecida.");
        
        RutaOptima criterio = new MenorNumeroDeParadas();
        System.err.println("Criterio establecido.");

        List<Estacion> trayectoria = sistema.buscaRuta(origen3, destino4, criterio);
        System.err.println("Trayectoria encontrada.");

        GraficadorBuilderSVG<Estacion> graficador = new GraficadorBuilderSVG<>(sistema.getGrafica());
        graficador.setTrayectoria(trayectoria);
        System.err.println("Graficos logrados");


        System.out.println(graficador.graficar());

        System.err.println("lol??");


    }
}



