package flotav2;

import java.util.Scanner;

public class Run {
	
	final static Scanner reader = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tab tablero = new Tab();
		tablero = genTab(Player.MAQUINA);
		tablero.reiniTab();
		System.out.println("");
		tablero.viewTab();
		System.out.println("");
		boolean ok = false;
		boolean tmp = false;
		int inp = 0;
		while(ok != true) {
			tmp = false;
			int x = 0;
			int y = 0;
			int aux = 0;
			/*while(tmp == false) {*/
				/*x = Tab.inpPos("Introdueix la posició x a la que disparar: ");
				y = Tab.inpPos("Introdueix la posició y a la que disparar: ");
				tmp = tablero.gestDispar(x, y, Player.PLAYER);
				if(tmp == false) {
					System.out.println("Tria un altre casella");
					reader.nextLine();
				}*/
				tablero.iaArm();
			//}
			System.out.println("");
			tablero.viewTab();
			System.out.println("");
			aux = inpSalir("Vols sortir (1 = continuar, 2 = salir): ");
			if(aux == 1) ok = false;
			else ok = true;
			inp++;
		}
		System.out.println("Disparos de la maquina: " + inp);
		tablero.viewBarcos();
		System.out.println("");
		tablero.viewHistory();
	}
	
	private static Tab genTab(Player propietario) {
		Tab TMP = new Tab(propietario);
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
