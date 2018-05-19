package flotav2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public static Tab Cargar(int p) {
		String rutaFile = inputRutaFile(p);
		File f = new File(rutaFile);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Tab tablero = new Tab();
		
		try {
			if(f.exists()) {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
		
			tablero.TabClass_Clone((Tab) ois.readObject());
			}
			else {
				System.out.println("No existeix el fitxer.");
				return null;
			}
		
			if(ois != null) {
				ois.close();
				fis.close();
			}
		
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		catch(ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				if(ois != null) {
					ois.close();
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tablero;
	}
	
	public static String inputRutaFile(int p) {
		String ruta;
		File f;
		System.out.print("Introdueix la ruta absoluta al fitxer. ");
		ruta = Entradas.ScannerLine();
		
		f = new File(ruta);
		if(f.exists()) {
			if(f.isDirectory()) {
				System.out.print("Introdueix el nom del fitxer. ");
				ruta = ruta + "/" + Entradas.ScannerLine();
				f = new File(ruta);
			}
		}
		
		ruta = ruta.trim();
		
		if(p == 1) {
			ruta = ruta + ".part1";
		}
		else {
			ruta = ruta + ".part2";
		}
		
		f = new File(ruta);
		
		if(f.exists()) {
			char a = Entradas.inpChar("Vols eliminar-lo? ");
			Entradas.ScannerLine();
			switch(a) {
			case 'S':
				if(f.delete()) {
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fitxer borrat.");
				}
				else System.out.println("Error al eliminar el fitxer.");
				break;
			}
		}
		else {
			try {
				if(f.createNewFile() != true) System.out.println("No s'ha pogut crear el nou fitxer.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ruta;
	}
	
	public static boolean guardar() {
		char option = 'A';
		do {
			option = inpChar("Vols guardar la partida (S|N) ");
		}while(option != 'S' && option != 'N');
		if(option == 'S') return true;
		else return false;
	}
	
	public static void grabar(int p, Tab tablero) {
		String rutaFile = inputRutaFile(p);
		File f = new File(rutaFile);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject((Tab) tablero);
			
			if(oos != null) {
				oos.close();
				fos.close();
			}
			
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				if(oos != null) {
					oos.close();
					fos.close();
				}
			}
			catch(IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
