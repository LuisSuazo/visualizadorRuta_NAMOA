package kml.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.List;

import com.csvreader.CsvReader;

import kml.estructuras.Calle;
import kml.estructuras.Circulo;
import kml.estructuras.Nodo;
import kml.funciones.Funciones;

public class Lectura {
	
	Funciones funciones = new Funciones();
	
	private String redExpuesta;
	private String rutas;
	private String rutasRepresentativas;
	private String puntosRiesgo;
	
	public Lectura(String redExpuesta, String rutas, String rutasRepresentativas, String puntosRiesgo) {
		this.redExpuesta = redExpuesta;
		this.rutas = rutas;
		this.rutasRepresentativas = rutasRepresentativas;
		this.puntosRiesgo = puntosRiesgo;
	}
	
	public void leerCalles(List<Calle> calles,Map<Integer,Nodo> puntos,Map<String,Calle> mapa) throws FileNotFoundException, IOException{
		CsvReader archivo = new CsvReader(this.redExpuesta);
		archivo.setDelimiter( ';' );
		archivo.readHeaders( );
		while( archivo.readRecord( ) ){
			int idArco=Integer.parseInt(archivo.get(0));
			double longitud=Double.parseDouble(archivo.get(1));
			double velocidad=Double.parseDouble(archivo.get(2));
			double tiempo=Double.parseDouble(archivo.get(3));
			double probabilidad=Double.parseDouble(archivo.get(4));
			double pobExpuesta800=Double.parseDouble(archivo.get(5));
			double pobExpuesta1000=Double.parseDouble(archivo.get(6));
			double riesgo800=Double.parseDouble(archivo.get(7));
			double riesgo1000=Double.parseDouble(archivo.get(8));
			int nodoA=Integer.parseInt(archivo.get(9));	
			int nodoB=Integer.parseInt(archivo.get(10));
			String ab=archivo.get(11);
			double aX=Double.parseDouble(archivo.get(12));
			double aY=Double.parseDouble(archivo.get(13));
			double bX=Double.parseDouble(archivo.get(14));
			double bY=Double.parseDouble(archivo.get(15));
			double latLonA[] = funciones.conv_gdc(aX,aY);
			double latLonB[] = funciones.conv_gdc(bX,bY);
			Calle aux=new Calle(idArco,longitud,velocidad,tiempo,probabilidad,pobExpuesta800,
					pobExpuesta1000,riesgo800,riesgo1000,nodoA,nodoB,aX,aY,bX,bY,
					latLonA[0],latLonA[1],latLonB[0],latLonB[1]);
			calles.add(aux);
			mapa.put(ab,aux);
			puntos.put(nodoA,new Nodo(nodoA,latLonA[0],latLonA[1]));
			puntos.put(nodoB,new Nodo(nodoB,latLonB[0],latLonB[1]));
			
		}
		
	}

	public void leerRuta(Map<Integer,Nodo> puntos,Map<Integer,List<Nodo>> ruta,boolean rr) throws FileNotFoundException, IOException{
		CsvReader archivo = null;
		if(rr) {
			archivo = new CsvReader(this.rutas);
		}else {
			archivo = new CsvReader(this.rutasRepresentativas);
		}
		archivo.setDelimiter( ';' );
		int cont=0;
		while( archivo.readRecord( ) ){
			List<Nodo> aux=new ArrayList<>();
			for(String it: archivo.getValues()) {
				aux.add(puntos.get(Integer.parseInt(it)));
			}
			ruta.put(cont,aux);
			cont++;
		}
	}
	
	
	
	public void leerPuntosRiesgo(List<Nodo> puntosRiesgo,List<Circulo> circulos) throws IOException {
		CsvReader archivo = new CsvReader(this.puntosRiesgo);
		archivo.setDelimiter( ';' );
		archivo.readHeaders( );
		while( archivo.readRecord( ) ){
			double latLon[] = funciones.conv_gdc(Double.valueOf(archivo.get(3)),Double.valueOf(archivo.get(4)));
			Nodo aux = new Nodo(archivo.get(0),archivo.get(2),latLon[0],latLon[1]);
			puntosRiesgo.add(aux);
			Circulo auxCirculo = new Circulo(aux);
			circulos.add(auxCirculo);
		}
	}
}
