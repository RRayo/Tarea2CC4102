package TextTools;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TextTools {
	
	public ArrayList<String> palabras;
	
	public int numPalabras;
	
	public String REGEX = "[^a-zA-Z ]"; //cualquier caracter que no sea letra o espacio
	
	
	// TODO replantearse esto
	public IDiccionarioStruct patriciaTree;
	public IDiccionarioStruct ABT;
	public IDiccionarioStruct hashLinearProb;
	
	public TextTools(String archivo, int k) {
		this.palabras = leerArchivo(archivo);
		this.numPalabras = 0;
		
		// this.patriciaTree; //TODO inicializar
		// this.ABT; //TODO inicializar
		this.hashLinearProb = new LinearProbing(k);
		
		//this.llenarDiccionarios(archivo);
	}
	
	
	
	
	public void llenarDiccionarios(String archivo) {
		//read text from a file "archivo". The file is located in a directory "texts" relative to the current working directory
				Path path = FileSystems.getDefault().getPath("texts", archivo); 
				
				try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						line = line.toLowerCase();
						line = line.replaceAll("['\"]+", "");
						line = line.replaceAll(this.REGEX, " ");
						line = line.trim().replaceAll(" +", " ");
						String[] lineas = line.split(" ");
						if(lineas[0].length() == 0) {
							continue;
						}
						for (String p: lineas) {
							this.patriciaTree.insertar(p, this.numPalabras);
							this.ABT.insertar(p, this.numPalabras);
							this.hashLinearProb.insertar(p, this.numPalabras);
							this.numPalabras++;
						}
					}
				} catch (IOException x) {
				    System.err.format("IOException: %s%n", x);
				}
				
	}
	
	
	
	
	// De aqui a abajo son utiles
	
	
	public static ArrayList<String> leerArchivo(String archivo) {
		
		//read text from a file "archivo". The file is located in a directory "texts" relative to the current working directory
		Path path = FileSystems.getDefault().getPath("texts", archivo); 
		
		ArrayList<String> palabras = new ArrayList<String>();
		String regex = "[^a-zA-Z ]"; //cualquier caracter que no sea letra o espacio
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	line = line.toLowerCase();
				line = line.replaceAll("+['\"]", "");
		        line = line.replaceAll(regex, " ");
		        line = line.trim().replaceAll(" +", " ");
		        String[] lineas = line.split(" ");
		        if(lineas[0].length() == 0) {
		        	continue;
		        }
		        for (String p: lineas) {
		        	palabras.add(p);
		        }
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return palabras;
	}
	
	public void llenarDiccionario(IDiccionarioStruct d, String s) { //String s $ para arboles, "" para hash
		int i = 0;
		for( String p : this.palabras) {
			d.insertar(p+s, i++);
		}
	}
	
	
	
	
	
	public static void llenarDiccionarioStatic(IDiccionarioStruct d, ArrayList<String> palabras, String s) { //String s $ para arboles, "" para hash
		int i = 0;
		for( String p : palabras) {
			d.insertar(p+s, i++);
		}
	}
	
	
	public static double similitud(String T1, String T2, IDiccionarioStruct D1, IDiccionarioStruct D2, String s) {
		ArrayList<String> palabrasT1 = leerArchivo(T1);
		ArrayList<String> palabrasT2 = leerArchivo(T2);

		//TODO medir el tiempo
		llenarDiccionarioStatic(D1, palabrasT1, s);
		llenarDiccionarioStatic(D2, palabrasT2, s);
		//TODO medir el tiempo


		double sum = 0;


		//TODO medir el tiempo
		for (String p : palabrasT1) {
			sum += Math.abs(count(p, D1)- count(p, D2)); //TODO se puede optimizar solo sumando el largo de palabrasT1 en este caso y solo buscar en D2
		}
		
		for (String p : palabrasT2) {
			sum += Math.abs(count(p, D1)- count(p, D2));
		}
		//TODO medir el tiempo

		return 1 - (sum / (palabrasT1.size()+palabrasT2.size()));
	}
	
	
	public static int count(String palabra, IDiccionarioStruct D) {
		return D.buscar(palabra).size();
	}
	
	
	//TODO ver si es necesaria
	public static int count(String archivo) {
		return leerArchivo(archivo).size();
		
	}
	
	
	
	
}
