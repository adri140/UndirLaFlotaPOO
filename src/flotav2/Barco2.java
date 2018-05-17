package flotav2;

public class Barco2 extends Barco implements java.io.Serializable {
	private int x2;
	private int y2;
	
	//constructores
	public Barco2() {
		super();
		x2 = -1;
		y2 = -1;
	}
	
	public Barco2(int x1, int y1, int direccion, Tab tablero) {
		super(x1, y1, direccion, 2, tablero);
		x2 = -1;
		y2 = -1;
	}
	
	public Barco2(Barco b, Tab table) {
		super();
		this.clonar(b, table);
	}
	//setters
	
	//getters
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	//otros
	@Override
	public void calPos() {
		int x1 = super.getX1();
		int y1 = super.getY1();
		int direc = super.getDireccion();
		int value = 0;
		
		if(direc == 0 || direc == 3) value = 1; //0 = derecha, 3 = de 0 a 9
		else if(direc == 1 || direc == 2) value = -1; // 1 = izquierda, 2 = de 9 a 0
		
		switch (direc) {
		case 0:
		case 1:
			x2 = x1;
			y2 = y1 + value;
			break;
		case 2:
		case 3:
			x2 = x1 + value;
			y2 = y1;
		}
	}
	
	@Override
	public void visualizar() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "Barco de 2 posiciones: x1 = " + super.getX1() + " y1 = " + super.getY1() + " x2 = " + x2 + " y2 = " + y2 + " direccion = " + super.getDireccion() + " undit = " + super.getUndido() + ".";
	}
	
	@Override
	public void clonar(Barco b, Tab tablero) {
		super.setX1(b.getX1(), tablero);
		super.setY1(b.getY1(), tablero);
		super.setDireccion(b.getDireccion());
		super.setUndido(b.getUndido());
		super.setPosiciones(b.getPosiciones());
		this.x2 = ((Barco2) b).getX2();
		this.y2 = ((Barco2) b).getY2();
	}
	
	/*Comprueba en las posiciones x i y se encuentra este barco*/
	@Override
	public boolean comprobarBarco(int x, int y) {
		boolean ok = false;
		int p = 0;
		int xb = this.getX1();
		int yb = this.getY1();
		while(ok != true && p < 2) {
			p++;
			ok = Barco.igualPos(xb, yb, x, y);
			if(ok != true) {
				switch(p) {
				case 1:
					xb = this.getX2();
					yb = this.getY2();
					break;
				}
			}
		}
		return ok;
	}
	
	/*Comprueba si el barco esta undido*/
	@Override
	public boolean barcoOk(char[][] tablero) {
		boolean ok = true;
		int xb, yb;
		xb = this.getX1();
		yb = this.getY1();
		int p = 0;
		while(ok != false && p < 2) {
			p++;
			if(tablero[xb][yb] != 'B') ok = false;
			else {
				switch(p) {
				case 1:
					xb = this.getX2();
					yb = this.getY2();
					break;
				}
			}
		}
		if(ok == true) this.setUndido(true);
		return ok;
	}
}
