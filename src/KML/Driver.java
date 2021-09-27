package kml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kml.estructuras.Calle;
import kml.estructuras.Circulo;
import kml.estructuras.Nodo;
import kml.io.Escritura;
import kml.io.Lectura;

public class Driver {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		if(args.length<5) {
			System.out.println("Argumentos insuficientes");
			return;
		}
		Lectura lectura=new Lectura(args[0],args[1],args[2],args[3]);
		Escritura escribir=new Escritura();
		ArrayList<Calle> Calles=new ArrayList<Calle>();
		HashMap<Integer,Nodo> Puntos=new HashMap<Integer,Nodo>();
		HashMap<String,Calle> Mapa=new HashMap<String,Calle>();
		HashMap<Integer,ArrayList<Nodo>> Ruta=new HashMap<Integer,ArrayList<Nodo>>();
		HashMap<Integer,ArrayList<Nodo>> RutaRepresentativa=new HashMap<Integer,ArrayList<Nodo>>();
		List<Circulo> circulos = new ArrayList<>();
		List<Nodo> puntosRiesgo = new ArrayList<>();
		lectura.leerCalles(Calles,Puntos,Mapa);
		lectura.leerRuta(Puntos,Ruta,true);
		lectura.leerRuta(Puntos,RutaRepresentativa,false);
		lectura.leerPuntosRiesgo(puntosRiesgo,circulos);
		escribir.GrafoKML(Calles);
		escribir.NodosKML(Puntos);
		//escribir.RutaKML(Ruta,Mapa,false,args[4]);
		escribir.RutaKML(RutaRepresentativa,Mapa,true,args[4]);
		escribir.NodosRiesgoKML(circulos);
	}
}
