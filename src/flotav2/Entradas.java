package flotav2;

import java.util.Random;
import java.util.Scanner;

public class Entradas {
	
	final static Scanner reader = new Scanner(System.in);
	final static Random rnd = new Random();
	
	//Random
	public static int RandomInt(int value) {
		return rnd.nextInt(value);
	}
	
	//Scanner
	public static char ScannerChar() {
		return reader.next().charAt(0);
	}
	
	public static int ScannerInt() {
		return reader.nextInt();
	}
	
	public static String ScannerLine() {
		return reader.nextLine();
	}
	
	//otras
	
	//permet la entrada de ints.
	public static int inpInt(String out) {
		boolean ok = false;
		int inp = 0;
		while(ok != true) {
			System.out.print(out);
			try {
				inp = ScannerInt();
				ok = true;
			}
			catch(Exception e) {
				System.out.println("Nomes és permeten numeros enters.");
				ScannerLine();
			}
		}
		return inp;
	}
	
	//permet la entrada de les direccions.
	public static int inpDir(String out) {
		boolean ok = false;
		int dir = -1;
		do {
			dir = inpInt(out);
			if(dir >= 0 && dir < 4) ok = true;
			else System.out.println("Aquesta opció no existeix.");
		}while(ok != true);
		return dir;
	}
	
	public static int inpDificult(String out) {
		boolean ok = false;
		int p = 0;
		do {
			p = Entradas.inpInt(out);
			if(p >= 1 && p < 3) ok = true;
			else System.out.println("L'opció no existeix. ");
		}while(ok != true);
		return p;
	}
	
	public static char inpChar(String out) {
		boolean ok = false;
		char input = 'S';
		do {
			System.out.print(out);
			try {
				input = ScannerChar();
				if(input >= 'a' && input <= 'z') input = (char) (input - 32);
				if(input >= 'A' && input <= 'Z') ok = true;
				else System.out.println("Nomes és permeten lletres.");
			}
			catch(Exception e) {
				System.out.println("Error, nomes és permeten lletres.");
				ScannerLine();
			}
		}while(ok != true);
		return input;
	}
}
