package KML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Driver {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		Lectura lectura=new Lectura();
		Escritura escribir=new Escritura();
		ArrayList<Calle> Calles=new ArrayList<Calle>();
		HashMap<Integer,Nodo> Puntos=new HashMap<Integer,Nodo>();
		HashMap<String,Calle> Mapa=new HashMap<String,Calle>();
		HashMap<Integer,ArrayList<Nodo>> Ruta=new HashMap<Integer,ArrayList<Nodo>>();
		HashMap<Integer,Integer> RutasMalas=new HashMap<Integer,Integer>();
		List<Circulo> circulos = new ArrayList<>();
		List<Nodo> puntosRiesgo = new ArrayList<>();
		lectura.leerCalles(Calles,Puntos,Mapa);
		lectura.leerRuta(Puntos,Ruta,RutasMalas);
		lectura.leerPuntosRiesgo(puntosRiesgo,circulos);
		escribir.GrafoKML(Calles);
		escribir.NodosKML(Puntos);
		escribir.RutaKML(Ruta,Mapa,RutasMalas);
		escribir.NodosRiesgoKML(circulos);
	}
}
