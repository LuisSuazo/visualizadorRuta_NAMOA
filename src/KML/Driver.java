package kml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kml.estructuras.Calle;
import kml.estructuras.Circulo;
import kml.estructuras.Nodo;
import kml.io.Escritura;
import kml.io.Lectura;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		if(args.length < 7) {
			log.error("Argumentos insuficientes");
			return;
		}
		String name = args[6];
		Lectura lectura=new Lectura(args[0],args[1],args[2],args[3],args[4],args[5]);
		log.info("Escribiendo ruta {}",lectura);
		Escritura escribir=new Escritura();
		List<Calle> calles=new ArrayList<>();
		Map<Integer,Nodo> puntos=new HashMap<>();
		Map<String,Calle> mapa=new HashMap<>();
		Map<Integer,List<Nodo>> ruta=new HashMap<>();
		Map<Integer,List<Calle>> arcos=new HashMap<>();
		Map<Integer,List<Calle>> arcosRepresentativos=new HashMap<>();
		Map<Integer,List<Nodo>> rutaRepresentativa=new HashMap<>();
		List<Circulo> radio800 = new ArrayList<>();
		List<Circulo> radio1000 = new ArrayList<>();
		List<Nodo> puntosRiesgo = new ArrayList<>();
		Map<Integer,List<String>> costos = new HashMap<>();
		Map<Integer,List<String>> costosRepresentativos = new HashMap<>();
		lectura.leerCalles(calles,puntos,mapa);
		lectura.leerRuta(puntos,ruta,true);
		lectura.leerRuta(puntos,rutaRepresentativa,false);
		lectura.leerPuntosRiesgo(puntosRiesgo,radio800,0.8);
		lectura.leerPuntosRiesgo(puntosRiesgo,radio1000,1.0);
		lectura.leerCostos(costos,true);
		lectura.leerCostos(costosRepresentativos,false);
		escribir.grafoKML(calles);
		escribir.nodosKML(puntos);
		escribir.rutaKML(ruta,mapa,true,name,arcos);
		escribir.rutaKML(rutaRepresentativa,mapa,false,name,arcosRepresentativos);
		escribir.escribirNodosCostos(costos, ruta, arcos, name,true);
		escribir.escribirNodosCostos(costosRepresentativos, rutaRepresentativa, arcosRepresentativos, name,false);
		escribir.nodosRiesgoKML(radio800,"800");
		escribir.nodosRiesgoKML(radio1000,"1000");
	}
}
