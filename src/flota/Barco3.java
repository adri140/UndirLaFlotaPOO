package flota;

public class Barco3 {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int x3;
	private int y3;
	private int direccion;
	private boolean undido;
	//boolean ok = new Barco2().compDir(direccion);
	
	//Constructores
		public Barco3() {
			undido = false;
			x1 = -1;
			y1 = -1;
			direccion = -1; //comrpovar!!
		}
		
		public Barco3(int x1, int y1, int direccion, Tab taulell) {
			this();
			if(new Barco2().compPos(x1, taulell)) this.x1 = x1;
			if(new Barco2().compPos(y1, taulell)) this.y1  = y1;
			if(new Barco2().compDir(direccion)) this.direccion = direccion;
		}
		
		//Setters
		public void setX1(int x1, Tab taulell) {
			if(new Barco2().compPos(x1, taulell)) this.x1 = x1;
		}
		
		public void setY1(int y1, Tab taulell) {
			if(new Barco2().compPos(y1, taulell)) this.y1 = y1;
		}
		
		public void setDireccion(int dir) {
			if(new Barco2().compDir(dir)) direccion = dir;
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
		
		public int getX3() {
			return x3;
		}
		 
		public int getY3() {
			return y3;
		}
		
		public boolean getUndido() {
			return undido;
		}
		
		public int getDireccion() {
			return direccion;
		}
		
		//otros
		public void calPosiciones() {
			switch(direccion) {
			case 0:
				y2 = y1 + 1;
				y3 = y2 + 1;
				break;
			case 1:
				y2 = y1 - 1;
				y3 = y2 - 1;
				break;
			case 2:
				x2 = x1 - 1;
				x3 = x2 - 1;
				break;
			case 3:
				x2 = x1 + 1;
				x3 = x2 + 1;
			}
		}
}
