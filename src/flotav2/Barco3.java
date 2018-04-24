package flotav2;

public class Barco3 extends Barco {
	private int x2;
	private int y2;
	private int x3;
	private int y3;
	
	//constructores
	public Barco3() {
		super();
		x2 = -1;
		y2 = -1;
		x3 = -1;
		y3 = -1;
	}
	
	public Barco3(int x1, int y1, int direccion, Tab tablero) {
		super(x1, y1, direccion, 3, tablero);
		x2 = -1;
		y2 = -1;
		x3 = -1;
		y3 = -1;
	}
	
	public Barco3(Barco b, Tab tablero) {
		super();
		this.clonar(b, tablero);
	}
	//setters
	
	//getters
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
	
	//Otros
	@Override
	public void clonar(Barco b, Tab tablero) {
		super.setX1(b.getX1(), tablero);
		super.setY1(b.getY1(), tablero);
		super.setDireccion(b.getDireccion());
		super.setUndido(b.getUndido());
		super.setPosiciones(b.getPosiciones());
		this.x2 = ((Barco3) b).getX2();
		this.y2 = ((Barco3) b).getY2();
		this.x3 = ((Barco3) b).getX3();
		this.y3 = ((Barco3) b).getY3();
	}
	
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
			x3 = x2;
			y3 = y2 + value;
			break;
		case 2:
		case 3:
			x2 = x1 + value;
			y2 = y1;
			x3 = x2 + value;
			y3 = y2;
		}
	}
	
	public void visualizar() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "Barco de 3 posiciones: x1 = " + super.getX1() + " y1 = " + super.getY1() + " x2 = " + this.getX2() + " y2 = " + this.getY2() + " x3 = " + this.getX3() + " y3 = " + this.getY3() + " direccion = " + super.getDireccion() + " undido = " + super.getUndido() + ".";
	}
}
