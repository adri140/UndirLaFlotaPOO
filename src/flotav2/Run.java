package flotav2;

public class Run {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tab tabMaquina = new Tab(Player.MAQUINA);
		tabMaquina.genTab(Player.MAQUINA);
		
		
		Tab tabPlayer = new Tab(Player.PLAYER);		
		tabPlayer.genTab(Player.PLAYER);
		
		int player = 1; //1, 2
		char salir = 'N';
		
		boolean TabComplet = false;
		boolean undit = false;
		
		tabPlayer.getIA().setDificultad(Entradas.inpDificult("Introdueix el nivell de la dificultat (1: normal, 2: dificil) "));
		
		//tabMaquina.viewBarcos();
		
		while(TabComplet != true && salir != 'S') {
			player = 1;
			System.out.println("El teu taulell.");
			tabPlayer.viewTab();
			System.out.println("");
			System.out.println("El taulell de l'oponent.");
			tabMaquina.viewTab();
			System.out.println("");
			while(player < 3 && TabComplet != true) {
				undit = false;
				switch (player){
				case 1:
					int x;
					int y;
					boolean ok = false;
					do {
						x = Tab.inpPos("Introdueix la posició X: ");
						y = Tab.inpPos("Introdueix la posició Y: ");
						ok = tabMaquina.gestDispar(x, y, Player.PLAYER);
						if(ok != true) System.out.println("Aquesta posició no és valida.\n");
						else {
							if(tabMaquina.getPos(x, y) == 'B') {
								undit = tabMaquina.undit(x, y);
								if(undit== true) System.out.println("Baixell enemic enfonsat.\n");
								else System.out.println("Baixell enemic tocat.");
							}
						}
					}while(ok != true);
					break;
				case 2:
					undit = tabPlayer.iaArm();
					break;
				}
				player++;
				
				if(undit == true) {
					TabComplet = tabPlayer.comprobarBarcosTodos();
					if(TabComplet == true) {
						System.out.println("Guanya la maquina, press intro.");
						Entradas.ScannerLine();
					}
					else { 
						TabComplet = tabMaquina.comprobarBarcosTodos();
						if(TabComplet == true) {
							System.out.println("Guanya el jugador, press intro.");
							Entradas.ScannerLine();
						}
					}
				}
				
			}
			if(TabComplet != true) {
				salir = Entradas.inpChar("Vols sortir de la partida? (N: no, S: si)");
			}
		}
		
		System.out.println("El teu taulell i els logs.");
		tabPlayer.viewTab();
		System.out.println("");
		tabPlayer.viewHistory();
		System.out.println("");
		if(TabComplet)	tabPlayer.viewBarcos();
		System.out.println("");
		System.out.println("El taulell de l'oponent iu els logs.");
		tabMaquina.viewTab();
		System.out.println("");
		tabMaquina.viewHistory();
		System.out.println("");
		if(TabComplet) tabMaquina.viewBarcos();
	}
	
	public static void newMenu() { //proximamente
		// TODO Auto-generated method stub
		char salir = 'N';
		char salir2 = 'N';
		
		boolean TabComplet = false;
		boolean undit = false;
		boolean cargar = false;
		boolean guardar = false;
		
		do {
			salir2 = newMenu(); //pregunta si quiere jugar, salir o cargar una partida.
			if(salir2 != 'S') {
				
				if(salir2 == 'C') cargar = true;
				
				Tab tabMaquina = null;
				
				Tab tabPlayer = null;
				
				if(cargar == true) {					
					Entradas.Cargar(tabMaquina, tabPlayer);
				}
				else {
					tabMaquina = new Tab(Player.MAQUINA);
					tabMaquina.genTab(Player.MAQUINA);
					
					tabPlayer = new Tab(Player.PLAYER);		
					tabPlayer.genTab(Player.PLAYER);
					
					tabPlayer.getIA().setDificultad(Entradas.inpDificult("Introdueix el nivell de la dificultat (1: normal, 2: dificil) "));
				}
				
				int player = 1; //1, 2
		
				//tabMaquina.viewBarcos();
		
				while(TabComplet != true && salir != 'S') {
					player = 1;
					System.out.println("El teu taulell.");
					tabPlayer.viewTab();
					System.out.println("");
					System.out.println("El taulell de l'oponent.");
					tabMaquina.viewTab();
					System.out.println("");
					while(player < 3 && TabComplet != true) {
						undit = false;
						switch (player){
						case 1:
							int x;
							int y;
							boolean ok = false;
							do {
								x = Tab.inpPos("Introdueix la posició X: ");
								y = Tab.inpPos("Introdueix la posició Y: ");
								ok = tabMaquina.gestDispar(x, y, Player.PLAYER);
								if(ok != true) System.out.println("Aquesta posició no és valida.\n");
								else {
									if(tabMaquina.getPos(x, y) == 'B') {
										undit = tabMaquina.undit(x, y);
										if(undit== true) System.out.println("Baixell enemic enfonsat.\n");
										else System.out.println("Baixell enemic tocat.");
									}
								}
							}while(ok != true);
							break;
						case 2:
							undit = tabPlayer.iaArm();
							break;
						}
						player++;
				
						if(undit == true) {
							TabComplet = tabPlayer.comprobarBarcosTodos();
							if(TabComplet == true) {
								System.out.println("Guanya la maquina, press intro.");
								Entradas.ScannerLine();
							}
							else { 
								TabComplet = tabMaquina.comprobarBarcosTodos();
								if(TabComplet == true) {
									System.out.println("Guanya el jugador, press intro.");
									Entradas.ScannerLine();
								}
							}
						}
				
					}
					if(TabComplet != true) {
						salir = Entradas.inpChar("Vols sortir de la partida? (N: no, S: si)");
					}
				}
		
				System.out.println("El teu taulell i els logs.");
				tabPlayer.viewTab();
				System.out.println("");
				tabPlayer.viewHistory();
				System.out.println("");
				if(TabComplet)	tabPlayer.viewBarcos();
				System.out.println("");
				System.out.println("El taulell de l'oponent iu els logs.");
				tabMaquina.viewTab();
				System.out.println("");
				tabMaquina.viewHistory();
				System.out.println("");
				if(TabComplet) tabMaquina.viewBarcos();
				
				if(TabComplet != true) {
					guardar = Entradas.guardar();
					if(guardar = true) {
						Entradas.Grabar(tabMaquina, tabPlayer);
					}
				}
			}
		}while(salir2 != 'S');
	}
}
