package flota;

public class Barco {
	private int numPlayer;
	private Barco2 lanchas = new Barco2();
	private Barco3 submarino = new Barco3();
	private Barco3 destructor = new Barco3();
	private Barco4 acorazado = new Barco4();
	private Barco5 portaviones = new Barco5();
	
	//constructor
	public Barco(int Player) {
		if(Player == 0 || Player == 1) numPlayer = Player;
		else numPlayer = 0;
	}
	
	public Barco(int Player, Barco2 lancha, Barco3 submarino, Barco3 destructor, Barco4 acorazado, Barco5 portaviones) {
		this(Player);
		this.lanchas = lancha;
		this.submarino = submarino;
		this.destructor = destructor;
		this.acorazado = acorazado;
		this.portaviones = portaviones;
	}
	
	//setters
	public void setNumPlayer(int Player) {
		if(Player == 0 || Player == 1) numPlayer = Player;
		else numPlayer = 0;
	}
	
	public void setLanchas(Barco2 barco) {
		lanchas = barco;
	}
	
	public void setSubmarino(Barco3 barco) {
		submarino = barco;
	}
	
	public void setDestructor(Barco3 barco) {
		destructor = barco;
	}
	
	public void setAcorazado(Barco4 barco) {
		acorazado = barco;
	}
	
	public void setPortaviones(Barco5 barco) {
		portaviones = barco;
	}
	
	//getters
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public Barco2 getLanchas() {
		return lanchas;
	}
	
	public Barco3 getSubmarino() {
		return submarino;
	}
	
	public Barco3 getDestructor() {
		return destructor;
	}
	
	public Barco4 getAcorazado() {
		return acorazado;
	}
	
	public Barco5 getPortaviones() {
		return portaviones;
	}
	
	//otros
	public boolean comprLan(Tab tablero) {
		boolean ok = false;
		ok = calPosFin(lanchas.getX1(), lanchas.getY1(), 2, tablero, lanchas.getDireccion());
		if(ok) lanchas.calPosiciones();
		return ok;
	}
	
	public boolean comprDes(Tab tablero) {
		boolean ok = false;
		ok = calPosFin(destructor.getX1(), destructor.getY1(), 3, tablero, destructor.getDireccion());
		if(ok) destructor.calPosiciones();
		return ok;
	}
	
	public boolean comprSub(Tab tablero) {
		boolean ok = false;
		ok = calPosFin(submarino.getX1(), submarino.getY1(), 3, tablero, submarino.getDireccion());
		if(ok) submarino.calPosiciones(); //revisar
		return ok;
	}
	
	public boolean comprAco(Tab tablero) {
		boolean ok = false;
		ok = calPosFin(acorazado.getX1(), acorazado.getY1(), 4, tablero, acorazado.getDireccion());
		if(ok) acorazado.calPosiciones(); //revisar
		return ok;
	}
	
	public boolean comprPor(Tab tablero) {
		boolean ok = false;
		ok = calPosFin(portaviones.getX1(), portaviones.getY1(), 5, tablero, portaviones.getDireccion());
		//System.out.println(ok);
		if(ok) portaviones.calPosiciones(); //revisar
		return ok;
	}
	
	private boolean calPosFin(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = false;
		switch(direc) {
		case 0:
			y1 = y1 + pos;
			break;
		case 1:
			y1 = y1 - (pos - 1);
			break;
		case 2:
			x1 = x1 - (pos - 1);
			break;
		case 3:
			x1 = x1 + pos;
		}
		if((x1 >= 0 && x1 < tablero.getMax()) && (y1 >= 0 && y1 < tablero.getMax())) {
			ok = comprovarPosiciones(x1, y1, pos, tablero, direc);
			if(ok != false) escribir(tablero, x1, y1, direc, pos);
		}
		return ok;
	}
	
	private boolean comprovarPosiciones(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = true;
		if(direc == 0 || direc == 1) ok = comprovarPos01(x1, y1, pos, tablero);
		else ok = comprovarPos23(x1, y1, pos, tablero);
		return ok;
	}
	
	private boolean comprovarPos01(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = tablero.getMax(); //temporal
		if(x1 == 0 && y1 == 0) {
			while(pos > 0 && y1 <= max - 1 && ok != false) {
				if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B') {
					pos--;
					y1++;
				}
				else ok = false;
			}
			if(ok != false && y1 <= max - 1) if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;
		}
		else {
			if(x1 == 0 && y1 == max - 1) {
				if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B') if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;
				else ok = false;
			}
			else {
				if(x1 == max - 1 && y1 == 0) {
					while(pos > 0 && y1 <= max - 1 && ok != false) {
						if(map[x1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
							pos--;
							y1++;
						}
						else ok = false;
					}
					if(ok != false && y1 <= max - 1) if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
				}
				else {
					if(x1 == max - 1 && y1 == max - 1) {
						if(map[x1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
						else ok = false;
					}
					else {
						if(x1 == 0) {
							if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B') {
								while(pos > 0 && y1 <= max - 1 && ok != false) {
									if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B') {
										pos--;
										y1++;
									}
									else ok = false;
								}
								if(ok != false && y1 <= max - 1) if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;
								else ok = false;
							}
							else ok = false;
						}
						else {
							if(x1 == max - 1) {
								if(map[x1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
									while(pos > 0 && y1 <= max - 1 && ok != false) {
										if(map[x1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
											pos--;
											y1++;
										}
										else ok = false;
									}
									if(ok != false && y1 <= max - 1) if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
									else ok = false;
								}
								else ok = false;
							}
							else {
								if(y1 == 0) {
									while(ok != false && y1 <= max - 1 && pos > 0) {
										if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
											y1++;
											pos--;
										}
										else ok = false;
									}
									if(ok != false && y1 <= max - 1) if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
								}
								else {
									if(y1 == max - 1) {
										
									}
									else {
										
									}
								}
							}
						}
					}
				}
			}
		}
		return ok;
	}
	
	private boolean comprovarPos23(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = tablero.getMax(); //temporal
	
		return ok;
	}
	
	private void escribir(Tab tablero, int x1, int y1, int dir, int pos) {
		char[][] map = tablero.getTablero();
		if(dir == 0 || dir == 1) {
			while(pos > 0) {
				map[x1][y1] = 'B';
				y1++;
				pos--;
			}
		}
		else {
			while(pos > 0) {
				map[x1][y1] = 'B';
				x1++; //ojooooooooooo
				pos--;
			}
		}
	}
	
}
