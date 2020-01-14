package KML;

public class Calle {
	
	private final int idArco;
	private final double longitud;
	private final double velocidad;
	private final double tiempo;
	private final double probabilidad;
	private final double pobExpuesta800;
	private final double pobExpuesta1000;
	private final double riesgo800;
	private final double riesgo1000;
	private final int nodoA;
	private final int nodoB;
	private final double aX;
	private final double aY;
	private final double bX;
	private final double bY;
	private final double aLat;
	private final double aLon;
	private final double bLat;
	private final double bLon;
	
	public Calle(int idArco, double longitud, double velocidad, double tiempo, double probabilidad,
			double pobExpuesta800, double pobExpuesta1000, double riesgo800, double riesgo1000, int nodoA, int nodoB,
			double aX, double aY, double bX, double bY,double aLat,double aLon,double bLat,double bLon) {
		super();
		this.idArco = idArco;
		this.longitud = longitud;
		this.velocidad = velocidad;
		this.tiempo = tiempo;
		this.probabilidad = probabilidad;
		this.pobExpuesta800 = pobExpuesta800;
		this.pobExpuesta1000 = pobExpuesta1000;
		this.riesgo800 = riesgo800;
		this.riesgo1000 = riesgo1000;
		this.nodoA = nodoA;
		this.nodoB = nodoB;
		this.aX = aX;
		this.aY = aY;
		this.bX = bX;
		this.bY = bY;
		this.aLat=aLat;
		this.aLon=aLon;
		this.bLat=bLat;
		this.bLon=bLon;
		
	}

	public int getIdArco() {
		return idArco;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public double getTiempo() {
		return tiempo;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public double getPobExpuesta800() {
		return pobExpuesta800;
	}

	public double getPobExpuesta1000() {
		return pobExpuesta1000;
	}

	public double getRiesgo800() {
		return riesgo800;
	}

	public double getRiesgo1000() {
		return riesgo1000;
	}

	public int getNodoA() {
		return nodoA;
	}

	public int getNodoB() {
		return nodoB;
	}

	public double getaX() {
		return aX;
	}

	public double getaY() {
		return aY;
	}

	public double getbX() {
		return bX;
	}

	public double getbY() {
		return bY;
	}

	public double getaLat() {
		return aLat;
	}

	public double getaLon() {
		return aLon;
	}

	public double getbLat() {
		return bLat;
	}

	public double getbLon() {
		return bLon;
	}
	
}
