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
		this.iniTablero();
	}
	
	public Tab(int max) {
		this();
		if(compMax(max)) { 
			this.max = max;
			tablero = new char[max][max];
			this.iniTablero();
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
			if(b instanceof Barco2 != false) barcos[numBarco] = new Barco2(b);
			else {
				if(b instanceof Barco3 != false) barcos[numBarco] = new Barco3(b);
				else {
					if(b instanceof Barco4 != false) barcos[numBarco] = new Barco4(b);
					else barcos[numBarco] = new Barco5(b);
				}
			}
			numBarco++;
			return true;
		}
		return false;
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
	
	private void iniTablero() {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = 'A';
			}
		}
	}
}
