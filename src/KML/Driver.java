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
		if(args.length<5) {
			log.error("Argumentos insuficientes");
			return;
		}
		log.info("Escribiendo ruta {}",args[4]);
		Lectura lectura=new Lectura(args[0],args[1],args[2],args[3]);
		Escritura escribir=new Escritura();
		List<Calle> calles=new ArrayList<>();
		Map<Integer,Nodo> puntos=new HashMap<>();
		Map<String,Calle> mapa=new HashMap<>();
		Map<Integer,List<Nodo>> ruta=new HashMap<>();
		Map<Integer,List<Nodo>> rutaRepresentativa=new HashMap<>();
		List<Circulo> radio800 = new ArrayList<>();
		List<Circulo> radio1000 = new ArrayList<>();
		List<Nodo> puntosRiesgo = new ArrayList<>();
		lectura.leerCalles(calles,puntos,mapa);
		lectura.leerRuta(puntos,ruta,true);
		lectura.leerRuta(puntos,rutaRepresentativa,false);
		lectura.leerPuntosRiesgo(puntosRiesgo,radio800,0.8);
		lectura.leerPuntosRiesgo(puntosRiesgo,radio1000,1.0);
		escribir.grafoKML(calles);
		escribir.nodosKML(puntos);
		escribir.rutaKML(ruta,mapa,false,args[4]);
		escribir.rutaKML(rutaRepresentativa,mapa,true,args[4]);
		escribir.nodosRiesgoKML(radio800,"800");
		escribir.nodosRiesgoKML(radio1000,"1000");
	}
}
