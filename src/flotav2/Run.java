package flotav2;

import java.util.Scanner;

public class Run {
	
	final static Scanner reader = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tab tablero = new Tab();
		tablero = genTab();
		tablero.reiniTab();
		System.out.println("");
		tablero.viewTab();
		System.out.println("");
		boolean ok = false;
		boolean tmp = false;
		while(ok != true) {
			tmp = false;
			int x = 0;
			int y = 0;
			int aux = 0;
			while(tmp == false) {
				x = Tab.inpPos("Introdueix la posició x a la que disparar: ");
				y = Tab.inpPos("Introdueix la posició y a la que disparar: ");
				tmp = tablero.gestDispar(x, y, Player.PLAYER);
				if(tmp == false) {
					System.out.println("Tria un altre casella");
					reader.nextLine();
				}
			}
			System.out.println("");
			tablero.viewTab();
			System.out.println("");
			aux = inpSalir("Vols sortir (1 = continuar, 2 = salir): ");
			if(aux == 1) ok = false;
			else ok = true;
		}
		tablero.viewBarcos();
		System.out.println("");
		tablero.viewHistory();
	}
	
	private static Tab genTab() {
		Tab TMP = new Tab();
		TMP.genTab(Player.MAQUINA);
		return TMP;
	}
	
	private static int inpSalir(String out) {
		boolean ok = false;
		int p = 0;
		do {
			p = Tab.inpInt(out);
			if(p >= 1 && p < 3) ok = true;
			else System.out.println("L'opció no existeix. ");
		}while(ok != true);
		return p;
	}
}
