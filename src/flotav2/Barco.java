package flotav2;

import flota.Tab;

public abstract class Barco {
	private int x1;
	private int y1;
	private int direccion;
	
	//constructores
	public Barco() {
		this.x1 = -1;
		this.y1 = -1;
		this.direccion = -1;
	}
	
	public Barco(int x1, int y1, Tab tablero) {
		this();
		if(compP(x1, tablero)) this.x1 = x1;
		if(compP(y1, tablero)) this.y1 = y1;
	}
	//setters
	public boolean setX1(int x1, Tab tablero) {
		if(compP(x1, tablero)) {
			this.x1 = x1;
			return true;
		}
		return false;
	}
	
	public boolean setY1(int y1, Tab tablero) {
		if(compP(y1, tablero)) {
			this.y1 = y1;
			return true;
		}
		return false;
	}
	
	public boolean setDireccion(int direccion) {
		if(direccion >= 0 && direccion < 4) {
			this.direccion = direccion;
			return true;
		}
		return false;
	}
	
	//getters
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getDireccion() {
		return direccion;
	}
	
	//otros
	private boolean compP(int x, Tab tabl) {
		if(x >= 0 && x < tabl.getMax()) return true;
		return false;
	}
	
	public abstract void calPos();
}
