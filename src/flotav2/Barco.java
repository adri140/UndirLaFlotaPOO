package flotav2;


public abstract class Barco {
	private int player;
	private int x1;
	private int y1;
	private int direccion;
	
	//constructor
	public Barco(int Player) {
		if(Player == 0 || Player == 1) player = Player;
		else player = 0;
	}
	
	public Barco(int Player, int x1, int y1, int direccion, Tab tablero) {
		this(Player);
		if((comPos(x1, tablero)) && (comPos(y1, tablero))) {
			this.x1 = x1;
			this.y1 = y1;
		}
		else {
			this.x1 = -1;
			this.y1 = -1;
		}
		
		if(direccion >= 0 && direccion <= 3) this.direccion = direccion;
		else direccion = -1;
	}
	
	//setters
	public void setPlayer(int Player) {
		if(Player == 0 || Player == 1) player = Player;
		else player = 0;
	}
	
	public boolean setX1(int x1, Tab tablero) {
		if(comPos(x1, tablero)) {
			this.x1 = x1;
			return true;
		}
		else return false;
	}
	
	public boolean setY1(int y1, Tab tablero) {
		if(comPos(y1, tablero)) {
			this.y1 = y1;
			return true;
		}
		else return false;
	}
	
	public boolean setDireccion(int direccion) {
		if(direccion >= 0 && direccion < 4) {
			this.direccion = direccion;
			return true;
		}
		else return false;
	}
	
	//getters
	public int getPlayer() {
		return player;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getDireccion() {
		return direccion;
	}
	
	// Otros
	private boolean comPos(int i, Tab tablero) {
		if(i >= 0 && i < tablero.getMax()) return true;
		else return false;
	}
	
	public abstract void calPos();
}
