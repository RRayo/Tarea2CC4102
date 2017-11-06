package TextTools;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import TextTools.TextTools;

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
				//line = line.replaceAll("+['\"]", "");
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

	public static int count(String archivo) {
		return leerArchivo(archivo).size();

	}
	
	public static void llenarDiccionarioStatic(IDiccionarioStruct d, ArrayList<String> palabras, String s) { //String s $ para arboles, "" para hash
		int i = 0;
		int[] sizes = new int[]{1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576};
		int index= 0;
		for (String p : palabras) {
			d.insertar(p + s, i++);
			/*for (int j = index; j <= 10; j++) {
				if (i == sizes[j]) {
					System.out.println("Numero de palabras: " + i);
					index++;
					break;
				}
			}*/
		}
	}
	
	
}
