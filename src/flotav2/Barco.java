package flotav2;

public abstract class Barco {
	private int x1;
	private int y1;
	private int direccion;  // 0 = derecha, 1 = izquierda, 2 = arriba de 9 a 0, 3 a bajo de 0 a 9
	private boolean undido;
	private int posiciones;
	
	//constructores
	public Barco() {
		this.x1 = -1;
		this.y1 = -1;
		this.direccion = -1;
		posiciones = 2;
		undido = false;
	}
	
	public Barco(int x1, int y1, int Direccion, int posiciones, Tab tablero) {
		this();
		if(compP(x1, tablero)) this.x1 = x1;
		if(compP(y1, tablero)) this.y1 = y1;
		if(posiciones > 0) this.posiciones = posiciones;
		if(Direccion >= 0 && Direccion < 5) this.direccion = Direccion;
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
	
	public void setUndido(boolean i) {
		undido = i;
	}
	
	public void setPosiciones(int pos) {
		if(pos > 0) posiciones = pos;
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
	
	public boolean getUndido() {
		return undido;
	}
	
	public int getPosiciones() {
		return posiciones;
	}
	//otros
	private boolean compP(int x, Tab tabl) {
		if(x >= 0 && x < tabl.getMax()) return true;
		return false;
	}
	
	public abstract void calPos();
	
	public abstract void clonar(Barco b, Tab tablero);
}
