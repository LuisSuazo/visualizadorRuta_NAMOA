package kml.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;

import kml.estructuras.Calle;
import kml.estructuras.Circulo;
import kml.estructuras.Nodo;
import kml.funciones.Funciones;

public class Lectura {
	Funciones Funciones = new Funciones();
	public void leerCalles(ArrayList<Calle> Calles,HashMap<Integer,Nodo> Puntos,HashMap<String,Calle> Mapa) throws FileNotFoundException, IOException{
		CsvReader archivo = new CsvReader("input/RedExpuesta.csv");
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
			String AB=archivo.get(11);
			double aX=Double.parseDouble(archivo.get(12));
			double aY=Double.parseDouble(archivo.get(13));
			double bX=Double.parseDouble(archivo.get(14));
			double bY=Double.parseDouble(archivo.get(15));
			double lat_lonA[] = Funciones.conv_gdc(aX,aY);
			double lat_lonB[] = Funciones.conv_gdc(bX,bY);
			Calle aux=new Calle(idArco,longitud,velocidad,tiempo,probabilidad,pobExpuesta800,
					pobExpuesta1000,riesgo800,riesgo1000,nodoA,nodoB,aX,aY,bX,bY,
					lat_lonA[0],lat_lonA[1],lat_lonB[0],lat_lonB[1]);
			Calles.add(aux);
			Mapa.put(AB,aux);
			Puntos.put(nodoA,new Nodo(nodoA,lat_lonA[0],lat_lonA[1]));
			Puntos.put(nodoB,new Nodo(nodoB,lat_lonB[0],lat_lonB[1]));
			
		}
		
	}
	
	public void leerRuta(HashMap<Integer,Nodo> Puntos,HashMap<Integer,ArrayList<Nodo>> Ruta,HashMap<Integer,Integer> RutasMalas) throws FileNotFoundException, IOException{
		CsvReader archivo = new CsvReader("input/Rutas_Bronfman_NAMOA_3O_S_54602_G_23561_nSOL_2491_Rutas_Representativas.csv");
		archivo.setDelimiter( ';' );
		int cont=0;
		while( archivo.readRecord( ) ){
			ArrayList<Nodo> aux=new ArrayList<Nodo>();
			for(String it: archivo.getValues()) {
				aux.add(Puntos.get(Integer.parseInt(it)));
			}
			Ruta.put(cont,aux);
			cont++;
		}
	}
	
	public void leerPuntosRiesgo(List<Nodo> puntosRiesgo,List<Circulo> circulos) throws IOException {
		CsvReader archivo = new CsvReader("input/PuntosRiesgos.csv");
		archivo.setDelimiter( ';' );
		archivo.readHeaders( );
		while( archivo.readRecord( ) ){
			double lat_lon[] = Funciones.conv_gdc(Double.valueOf(archivo.get(3)),Double.valueOf(archivo.get(4)));
			Nodo aux = new Nodo(archivo.get(1),archivo.get(2),lat_lon[0],lat_lon[1]);
			puntosRiesgo.add(aux);
			Circulo auxCirculo = new Circulo(aux);
			circulos.add(auxCirculo);
		}
	}
}
