package KML;

import java.util.ArrayList;
import java.util.List;

public class Circulo {
	
	private List<Nodo> puntos = new ArrayList<>();
	private Nodo Centro;
	
	public Circulo(Nodo Centro) {
		int[] a = new int[10];
		a[1]=5;
		double centerLat = (Centro.getX()* Math.PI) / 180.0;  //rad
		double centerLng = (Centro.getY()* Math.PI) / 180.0; //rad        
		double diameter = 0.8; // diameter of circle in km
		double dist = diameter / 6371.0; 
		double lan;
		for (int x = 0; x <= 360; x += 1){
			double brng = x * Math.PI / 180.0;         //rad
			double latitude = Math.asin(Math.sin(centerLat) * Math.cos(dist) +    Math.cos(centerLat) * Math.sin(dist) * Math.cos(brng));
			double longitude = ((centerLng + Math.atan2(Math.sin(brng) *   Math.sin(dist)* Math.cos(centerLat), Math.cos(dist) - Math.sin(centerLat) 
					* Math.sin(latitude))) * 180.0) / Math.PI;
			lan=(latitude * 180.0) / Math.PI; //, longitude));
			Nodo aux = new Nodo(lan,longitude);
			puntos.add(aux);
		}
		this.Centro=Centro;
	}

	public Nodo getCentro() {
		return Centro;
	}

	public void setCentro(Nodo centro) {
		Centro = centro;
	}
	
	public List<Nodo> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<Nodo> puntos) {
		this.puntos = puntos;
	}
}
