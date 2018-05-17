package flotav2;

public class Tab implements java.io.Serializable {
	private Player propietaryTab;
	private char[][] tablero;
	private Barco[] barcos;
	private int numBarco = 0;
	private History[] historial; //almacena las tiradas de los jugadores sobre este tablero
	private int numHistorial = 0;
	private IAPlayer IA;
	
	private static int maxHistorial; //indica el numero maximo de historiales almacenados en este tablero
	private static int maxBarco = 5; //indica el numero maximo de varcos por tablero
	private static int max; //indica el maximo tamanyo del tablero quadrado
	
	//Constructores
	public Tab() {
		max = 10;
		tablero = new char[max][max];
		barcos = new Barco[maxBarco];
		this.iniTablero('A');
		historial = new History[(max * max)];
		maxHistorial = (max * max);
		propietaryTab = Player.MAQUINA;
		this.IA = null;
	}
	
	public Tab(int max, Player propietario) {
		this();
		if(compMax(max)) { 
			tablero = new char[max][max];
			this.iniTablero('A');
			Tab.setMax(max);
			historial = new History[(max * max)];
			maxHistorial = (max * max);
		}
		propietaryTab = propietario;
		if(propietaryTab == Player.PLAYER) {
			this.IA = new IAPlayer();
		}
		else this.IA = null;
	}
	
	public Tab(Player propietario) {
		this();
		propietaryTab = propietario;
		if(propietaryTab == Player.PLAYER) {
			this.IA = new IAPlayer();
		}
		else this.IA = null;
	}
	
	//Setters
	public void setNumBarco(int num) {
		if(num >= 0 && num < maxBarco) numBarco = num;
	}
	
	public static void setMax(int max) {
		Tab.max = max;
	}
	
	public void setIA(IAPlayer inp) {
		this.IA = inp;
	}
	
	//getters
	public char[][] getTablero(){
		return tablero;
	}
	
	public Player getPropietaryTab() {
		return propietaryTab;
	}
	
	public static int getMax() {
		return Tab.max;
	}
	
	public static int getMaxBarco() {
		return maxBarco;
	}
	
	public Barco[] getBarcos(){
		return barcos;
	}
	
	public int getNumBarco() {
		return numBarco;
	}
	
	public IAPlayer getIA() {
		return IA;
	}
	
	public History[] getHistorial() {
		return historial;
	}
	
	public int getNumHistorial() {
		return numHistorial;
	}
	
	//Otros
	public void TabClass_Clone(Tab tablero) {
		this.barcos = tablero.getBarcos();
		this.tablero = tablero.getTablero();
		this.IA = tablero.getIA();
		this.propietaryTab = tablero.getPropietaryTab();
		this.numBarco = tablero.getNumBarco();
		this.historial = tablero.getHistorial();
		this.numHistorial = tablero.getNumHistorial();
	}
	
	/**
	 * Clona un barco en el array de los barcos
	 * @param b
	 * @return
	 */
	public boolean insertarBarco(Barco b) {
		if(numBarco < maxBarco) {
			if(b instanceof Barco2 != false) barcos[numBarco] = new Barco2(b, this);
			else {
				if(b instanceof Barco3 != false) barcos[numBarco] = new Barco3(b, this);
				else {
					if(b instanceof Barco4 != false) barcos[numBarco] = new Barco4(b, this);
					else {
						if(b instanceof Barco5 != false) barcos[numBarco] = new Barco5(b, this);
					}
				}
			}
			numBarco++;
			return true;//añadido
		}
		return false; //no añadido
	}
	
	/**
	 * Devuelve el valor de una posicion del tablero
	 * @param x
	 * @param y
	 * @return
	 */
	public char getPos(int x, int y) { //retorna una posicio del taulell
		if((x >= 0 && x < max) && (y >= 0 && y < max)) {
			return tablero[x][y];
		}
		return 'A';
	}
	
	/*Comproba si un balor és mes gran que el valor per defecte de max*/
	private boolean compMax(int value) {
		if(value > max) return true;
		else return false;
	}
	
	/*Mostra el taulell per consola*/
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
		System.out.println("");
	}
	
	/*Inicia el taulell amb el caracter pasat per parametre*/
	private void iniTablero(char car) {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = car; // ?
			}
		}
	}
	
	/**
	 * Inici la comprovacion de si el barco puede posicionarse o no en esas posiciones
	 * @param num
	 * @return
	 */
	public boolean comprovar(int num) {
		boolean ok = false;
		if(barcos[num].getX1() != -1 && barcos[num].getY1() != -1 && barcos[num].getDireccion() != -1) {
			if(num >= 0 && num < maxBarco) {
				ok = calPosFin(barcos[num].getX1(), barcos[num].getY1(), barcos[num].getPosiciones(), this, barcos[num].getDireccion());
				if(ok == true) {
					if(barcos[num] instanceof Barco2 != false) ((Barco2) barcos[num]).calPos();
					else {
						if(barcos[num] instanceof Barco3 != false) ((Barco3) barcos[num]).calPos();
						else {
							if(barcos[num] instanceof Barco4 != false) ((Barco4) barcos[num]).calPos();
							else {
								if(barcos[num] instanceof Barco5 != false) ((Barco5) barcos[num]).calPos();
							}
						}
					}
				}
			}
		}
		return ok;
	}
	
	//calcula la posicio final hon terminara el baixell, per la seva comprovació
	private boolean calPosFin(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = false;
		int x = x1;
		int y = y1;
		switch(direc) { 
		case 0:
			y = y + (pos - 1);
			break;
		case 1:
			y = y - (pos - 1);
			break;
		case 2:
			x = x - (pos - 1);
			break;
		case 3:
			x = x + (pos - 1);
		}
		if((x >= 0 && x < Tab.getMax()) && (y >= 0 && y < Tab.getMax())) {
			switch(direc) { //calcula la posició inicial, on començara les comprovacions i on començara a escribir
			case 1:
				y1 = y1 - (pos - 1);
				break;
			case 2:
				x1 = x1 - (pos - 1);
				break;
			}
			ok = comprovarPosiciones(x1, y1, pos, tablero, direc);
			if(ok != false) { escribir(tablero, x1, y1, direc, pos);}
		}
		return ok;
	}
	
	//comprova la posicio pasada per parametre al taulell pasat per parametre segons la direccio pasada per parametre, pos és el numero de posicions que te el baixell.
	private boolean comprovarPosiciones(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = true;
		if(direc == 0 || direc == 1) ok = comprovarPos01(x1, y1, pos, tablero);
		else ok = comprovarPos23(x1, y1, pos, tablero);
		return ok;
	}
	
	//comprova la posicio pasada per parametre al taulell pasat per parametre per les direccions 0 i 1 'dreta o esquerra', pos és el numero de posicions que te el baixell.
	private boolean comprovarPos01(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = Tab.getMax(); //temporal
		if(x1 == 0 && y1 == 0) {
			while(pos > 0 && y1 <= max - 1 && ok != false) {
				if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B') {
					pos--;
					y1++;
				}
				else ok = false;
			}
			if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
		}
		else {
			if(x1 == 0 && y1 == max - 1) {
				if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
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
					if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
				}
				else {
					if(x1 == max - 1 && y1 == max - 1) {
						if(map[x1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
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
								if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
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
									if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
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
									if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
								}
								else {
									if(y1 == max - 1) {
										if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
										else ok = false;
									}
									else {
										if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(y1 <= max - 1 && pos > 0 && ok != false) {
												if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
													pos--;
													y1++;
												}
												else ok = false;
											}
											if(y1 <= max - 1 && ok != false) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
										}
										else ok = false;
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
	
	//comprova la posicio pasada per parametre al taulell pasat per parametre per les direccions 2 i 3 'de 9 a 0 o de 0 a 9', pos és el numero de posicions que te el baixell.
	private boolean comprovarPos23(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = Tab.getMax(); //temporal
		if(x1 == 0 && y1 == 0) {
			while(ok != false && pos > 0 && x1 <= max - 1) {
				if(map[x1][y1] != 'B' && map[x1][y1 + 1] != 'B') {
					x1++;
					pos--;
				}
				else ok = false;
			}
			if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
		}
		else {
			if(x1 == 0 && y1 == max - 1) {
				while(ok != false && pos > 0 && x1 <= max - 1) {
					if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B') {
						x1++;
						pos--;
					}
					else ok = false;
				}
				if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
			}
			else {
				if(x1 == max - 1 && y1 == 0) {
					if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 + 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
					else ok = false;
				}
				else {
					if(x1 == max - 1 && y1 == max - 1) {
						if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
						else ok = false;
					}
					else {
						if(x1 == 0) {
							while(ok != false && pos > 0 && x1 <= max - 1) {
								if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B' && map[x1][y1 + 1] != 'B') {
									x1++;
									pos--;
								}
								else ok = false;
							}
							if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
						}
						else {
							if(x1 == max - 1) {
								if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B' && map[x1 - 1][y1 + 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
								else ok = false;
							}
							else {
								if(y1 == 0) {
									while(ok != false && pos > 0 && x1 <= max - 1) {
										if(map[x1][y1] != 'B' && map[x1][y1 + 1] != 'B') {
											x1++;
											pos--;
										}
										else ok = false;
									}
									if(ok != false && x1 <= max - 1) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
								}
								else {
									if(y1 == max - 1) {
										if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(ok != false && pos > 0 && x1 <= max - 1) {
												if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B') {
													x1++;
													pos--;
												}
												else ok = false;
											}
											if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
										}
										else ok = false;
									}
									else {
										if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 + 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(ok != false && pos > 0 && x1 <= max - 1) {
												if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B' && map[x1][y1 + 1] != 'B') {
													x1++;
													pos--;
												}
												else ok = false;
											}
											if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
										}
										else ok = false;
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
	
	/**
	 * escribe en el tablero (matriz de caracteres), aquest methode nomes s'utilitza d'urant la generació aleatoria per comrpobar que les posicions dels baixells compleixen les normes.
	 */
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
	/**
	 * Rescriu el taulell, amb el caracter ?
	 */
	public void reiniTab() {
		this.iniTablero('?');
	}
	
	/**
	 * Devuelve un barco del array barcos
	 * @param num
	 * @return
	 */
	public Barco retBarco(int num) {
		if(numBarco > 0) {
			return barcos[num];
		}
		else return null;
	}
	
	/**
	 * Inserta un log en el historial
	 * @param h
	 */
	public void insertarHistorial(History h) {
		historial[numHistorial] = h;
		numHistorial++;
	}
	
	/**
	 * Gestiona la tirada, si devuelve false es que la posicion lla esta descubierta, si devuelve true indica que la tirada es coorecte
	 * @param x
	 * @param y
	 * @param player
	 * @return
	 */
	public boolean gestDispar(int x, int y, Player player) {
		boolean ok = false;
		boolean b = false;
		if((x >= 0 && x < max) && (y >= 0 && y < max)) {
			ok = this.disparar(x, y);
			if(ok == true) {
				if(numHistorial < maxHistorial) {
					if(tablero[x][y] == 'B') b = true;
					historial[numHistorial] = new History(player, x, y, b, this);
					numHistorial++;
				}
			}
		}
		return ok; //si devuelve un true es que se ha disparado a la posicion, si devuelve un false es que en la posicion se ha disparado antes
	}
	
	/**
	 * Comprueba si hay algun barco en la casilla, si hay escribira 'B' si no 'A', pero primero comprueba que no este descubierta, si lo esta devuelve false
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean disparar(int x, int y) {
		boolean ok = false;
		if(tablero[x][y] == '?') {
			int i = 0;
			for(i = 0; i < maxBarco && ok != true; i++) {
				if(barcos[i] instanceof Barco2 != false) {
					ok = ((Barco2) barcos[i]).comprobarBarco(x, y);
				}
				else {
					if(barcos[i] instanceof Barco3 != false) {
						ok = ((Barco3) barcos[i]).comprobarBarco(x, y);
					}
					else {
						if(barcos[i] instanceof Barco4 != false) {
							ok = ((Barco4) barcos[i]).comprobarBarco(x, y);
						}
						else {
							if(barcos[i] instanceof Barco5 != false) {
								ok = ((Barco5) barcos[i]).comprobarBarco(x, y);
							}
						}
					}
				}
			}
			if(ok == true) {
				tablero[x][y] = 'B';
			}
			else {
				tablero[x][y] = 'A';
				ok = true;
			}
			/*falta llamar a comprovar el barco*/
		}
		return ok;
	}
	
	/**
	 * Muestra el hostorial de disparos realizados sobre este tablero
	 */
	public void viewHistory() {
		for(int i = numHistorial - 1; i >= 0; i--) {
			System.out.print(i + " Tirades realitzades sobre el taulell del propietari " + this.getPropietaryTab() + " ");
			historial[i].visualizar();
		}
	}
	
	/*Mostra un baixell del array de  baixells*/
	public void viewBarcos() {
		for(int p = 0; p < maxBarco; p++){
			barcos[p].visualizar();
		}
	}
	
	/**
	 * Genera i implementa els vaixells a l'array de vaixells un cop aquests siguin coorectes
	 * @param pla
	 */
	public void genTab(Player pla) {
		int x1, y1, dir;
		boolean ok = false;
		if(pla == Player.PLAYER) {
			this.viewTab();
			System.out.println("Introdueix les posicions pel vaixell de 2 posicions: ");
		}
		int i = 0;
		while(i < Tab.getMaxBarco()) {
			ok = false;
			if(pla == Player.MAQUINA) {
				x1 = Entradas.RandomInt(Tab.getMax());
				y1 = Entradas.RandomInt(Tab.getMax());
				dir = Entradas.RandomInt(4);
			}
			else {
				x1 = Tab.inpPos("Introdueix la posició x: ");
				y1 = Tab.inpPos("Introdueix la posició y: ");
				dir = Entradas.inpDir("Introdueix la direcció del vaixell \n0 - Cap a la dreta.\n1 - Cap a la esquerra.\n2 - Cap a adalt.\n3 - Cap a abaix.\nOpció: ");
			}
			switch(i) {
			case 0: //barco2
				this.insertarBarco(new Barco2(x1, y1, dir, this));
				ok = this.comprovar(i);
				if(pla == Player.PLAYER) System.out.println("Introdueix les posicions pel vaixell de 3 posicions: ");
				break;
			case 1: //barco3
			case 2:
				this.insertarBarco(new Barco3(x1, y1, dir, this));
				ok = this.comprovar(i);
				if(pla == Player.PLAYER && i == 1) System.out.println("Introdueix les posicions pel vaixell de 3 posicions: ");
				else {
					if(pla == Player.PLAYER) System.out.println("Introdueix les posicions pel vaixell de 4 posicions: ");
				}
				break;
			case 3: //barco4
				this.insertarBarco(new Barco4(x1, y1, dir, this));
				ok = this.comprovar(i);
				if(pla == Player.PLAYER) System.out.println("Introdueix les posicions pel vaixell de 5 posicions: ");
				break;
			case 4: //barco5
				this.insertarBarco(new Barco5(x1, y1, dir, this));
				ok = this.comprovar(i);
				break;
			}
			if(ok == true && pla == Player.PLAYER) {
				i++;
				this.viewTab();
				System.out.println("");
			}
			else {
				if(ok == true) i++;
				else this.setNumBarco(this.getNumBarco() - 1);
			}
		}
		this.reiniTab();
	}
	
	//permet la entrada de les posicions on és trobaran els baixells.
	public static int inpPos(String out) {
		boolean ok = false;
		int pos = -1;
		do {
			pos = Entradas.inpInt(out);
			if(pos >= 0 && pos < Tab.getMax()) ok = true;
			else System.out.println("Aquesta fila o columna no existeix");
		}while(ok != true);
		return pos;
	}
	
	
	//crida a la ia per gestionar una tirada, nomes si el propietari del taulell és la maquina.
	public boolean iaArm() {
		boolean undit = false;
		if(IA != null && propietaryTab == Player.PLAYER) {
			undit = this.IA.armIA(this);
		}
		return undit;
	}
	
	//Comprova si el baixell de la posicio pasada per parametre esta undit.
	public boolean undit(int x, int y) {
		boolean ok = false;
		int p = 0;
		while(p < Tab.maxBarco && ok != true) {
			ok = this.compUndido(x, y, p);
			p++;
		}
		p = p - 1;
		ok = this.comprobarBarcos(p); //si es true barco undido
		return ok;
	}
	
	//Busca el baixell al qual pertany les posicions pasades per paramete, p indica la posicio del baixell a l'array de baixells
	private boolean compUndido(int x, int y, int p) {
		boolean ok = false;
		if(barcos[p] instanceof Barco2 != false) {
			ok = ((Barco2) barcos[p]).comprobarBarco(x, y);
		}
		else {
			if(barcos[p] instanceof Barco3 != false) {
				ok = ((Barco3) barcos[p]).comprobarBarco(x, y);
			}
			else {
				if(barcos[p] instanceof Barco4 != false) {
					ok = ((Barco4) barcos[p]).comprobarBarco(x, y);
				}
				else {
					if(barcos[p] instanceof Barco5 != false) {
						ok = ((Barco5) barcos[p]).comprobarBarco(x, y);
					}
				}
			}
		}
		return ok;
	}
	
	/**
	 * Comprueba si el barco esta undido 
	 * @param pos
	 */
	private boolean comprobarBarcos(int pos) {
		boolean ok = false;
		if(barcos[pos] instanceof Barco2 != false) {
			ok = ((Barco2) barcos[pos]).barcoOk(tablero);
		}
		else {
			if(barcos[pos] instanceof Barco3 != false) {
				ok = ((Barco3) barcos[pos]).barcoOk(tablero);
			}
			else {
				if(barcos[pos] instanceof Barco4 != false) {
					ok = ((Barco4) barcos[pos]).barcoOk(tablero);
				}
				else {
					if(barcos[pos] instanceof Barco5 != false) {
						ok = ((Barco5) barcos[pos]).barcoOk(tablero);
					}
				}
			}
		}
		return ok;
	}
	
	//indica si todos los barcos estan undidos.
	public boolean comprobarBarcosTodos() {
		boolean ok = true;
		for(int i = 0; i < Tab.getMaxBarco() && ok != false; i++) {
			if(barcos[i].getUndido() != true) ok = false;
		}
		return ok;
	}
	
}
