package flotav2;


public class Barco2 extends Barco {
	private int x2;
	private int y2;
	private boolean undido;
	private int direccion; // 0 = derecha, 1 = izquierda, 2 = arriba de 9 a 0, 3 a bajo de 0 a 9
	
	//Constructores
	public Barco2() {
		super();
		undido = false;
		direccion = -1; //comrpovar!!
	}
	
	public Barco2(int x1, int y1, int direccion, Tab taulell) {
		this();
		if(compPos(x1, taulell)) this.x1 = x1;
		if(compPos(y1, taulell)) this.y1  = y1;
		if(compDir(direccion)) this.direccion = direccion;
	}
	
	//Setters
	public void setX1(int x1, Tab taulell) {
		if(compPos(x1, taulell)) this.x1 = x1;
	}
	
	public void setY1(int y1, Tab taulell) {
		if(compPos(y1, taulell)) this.y1 = y1;
	}
	
	public void setDireccion(int dir) {
		if(compDir(dir)) direccion = dir;
	}
	
	public void setUndido(boolean estado) {
		undido = estado;
	}
	
	//getters
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	 
	public int getY2() {
		return y2;
	}
	
	public boolean getUndido() {
		return undido;
	}
	
	public int getDireccion() {
		return direccion;
	}
	
	//otros
	public boolean compDir(int value) {//privado
		if (value >= 0 && value <= 3) return true;
		else return false;
	}
	
	public boolean compPos(int value, Tab taulell) {//privado
		if(value >= 0 && value < taulell.getMax()) return true;
		else return false;
	}
	
	public void calPosiciones() {
		switch(direccion) {
		case 0:
			x2 = x1;
			y2 = y1 + 1;
			break;
		case 1:
			x2 = x1;
			y2 = y1 - 1;
			break;
		case 2:
			x2 = x1 - 1;
			y2 = y1;
			break;
		case 3:
			x2 = x1 + 1;
			y2 = y1;
		}
	}
	
	public String toString() {
		return "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2 + " direccion: " + direccion + " undit: " + undido;
	}
}
