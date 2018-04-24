package flotav2;

public class Tab {
	private char[][] tablero;
	private int max;
	private Barco[] barcos;
	private int numBarco = 0;
	private static int maxBarco = 5;
	
	//Constructores
	public Tab() {
		max = 10;
		tablero = new char[max][max];
		barcos = new Barco[maxBarco];
		this.iniTablero('A');
	}
	
	public Tab(int max) {
		this();
		if(compMax(max)) { 
			this.max = max;
			tablero = new char[max][max];
			this.iniTablero('A');
		}
	}
	
	//Setters
	
	//getters
	public char[][] getTablero(){
		return tablero;
	}
	
	public int getMax() {
		return max;
	}
	
	public Barco[] getBarcos(){
		return barcos;
	}
	
	//Otros
	public boolean insertarBarco(Barco b) {
		if(numBarco < maxBarco) {
			if(b instanceof Barco2 != false) barcos[numBarco] = new Barco2( b, this);
			else {
				if(b instanceof Barco3 != false) barcos[numBarco] = new Barco3( b, this);
				else {
					if(b instanceof Barco4 != false) barcos[numBarco] = new Barco4( b, this);
					else {
						if(b instanceof Barco5 != false) barcos[numBarco] = new Barco5( b, this);
					}
				}
			}
			numBarco++;
			return true;//añadido
		}
		return false; //no añadido
	}
	
	private boolean compMax(int value) {
		if(value > 10) return true;
		else return false;
	}
	
	public void viewTab() {
		System.out.print("   ");
		for(int p = 0; p < max; p++) {
			System.out.print(p + " ");
		}
		for(int i = 0; i < max; i++) {
			System.out.print("\n"+i + " |");
			for(int p = 0; p < max; p++) {
				System.out.print(tablero[i][p] + "|");
			}
		}
	}
	
	private void iniTablero(char car) {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = car; // ?
			}
		}
	}
}
