package flotav2;
//emmagatzema les tirades dels jugadors als taulells dels jugadors ribals.
public class History {
	private Player player;
	private int x1;
	private int y1;
	private boolean tocat;
	
	//constructores
	public History() {
		player = Player.MAQUINA;
		x1 = -1;
		y1 = -1;
		tocat = false;
	}
	
	public History(Player p, int x1, int y1, Tab tablero) {
		this();
		player = p;
		if(this.compPos(x1, tablero) == true) this.x1 = x1;
		if(this.compPos(y1, tablero) == true) this.y1 = y1;
	}
	
	public History(Player p, int x1, int y1, boolean toc, Tab tablero) {
		player = p;
		if(this.compPos(x1, tablero) == true) this.x1 = x1;
		if(this.compPos(y1, tablero) == true) this.y1 = y1;
		tocat = toc;
	}
	
	//setters
	public void setX1(int x1, Tab tablero) {
		if(this.compPos(x1, tablero)) this.x1 = x1;
	}
	
	public void setY1(int y1, Tab tablero) {
		if(this.compPos(y1, tablero)) this.y1 = y1;
	}
	
	public void setTocat(boolean inp) {
		this.tocat = inp;
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	//getters
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public boolean getTocat() {
		return tocat;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	//otros
	//comprova que la posicio del taulell és corecta 
	private boolean compPos(int x, Tab tablero) {
		if(x >= 0 && x < Tab.getMax()) return true;
		return false;
	}
	
	//Visualitza aquest registre o historial.
	public void visualizar() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "Tirada del jugador " + player + " a les posicions x: " + x1 + " i y: " + y1 + ", el resultat a estat " + tocat + ".";
	}
}
