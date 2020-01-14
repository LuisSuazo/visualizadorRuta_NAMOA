package KML;

public class Nodo {
	private final Integer id;
	private final Double x;
	private final Double y;
	private final Integer Demanda;
	
	public Nodo(Integer id,Double x, Double y) {
		this.id=id;
		this.x = x;
		this.y = y;
		this.Demanda=0;
	}
	
	public Nodo(Double x, Double y) {
		this.id = 0;
		this.x = x;
		this.y = y;
		this.Demanda = 0;
	}
	
	public Nodo(String id,String Demanda,Double x, Double y) {
		this.id = Integer.valueOf(id);
		this.x = x;
		this.y = y;
		this.Demanda = Integer.valueOf(Demanda);
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getDemanda() {
		return Demanda;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
