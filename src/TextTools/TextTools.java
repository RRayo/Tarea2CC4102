package TextTools;

import Dictionary.IDiccionarioStruct;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Clase con herramientas para leer archivos y extraer palabras y llenar Diccionarios.
 */
public class TextTools {

	/**
	 * Método para leer un archivo y extraer sus palabras.
	 * Deja solo las letras de la "a" a la "z" borrando comillas y reemplazando el resto por espacios.
	 * @param archivo Nombre del archivo por leer (se asume que el archivo está en la misma carpeta que la carpeta de java)
	 * @return Retorna la lista de palabras del texto.
	 */
	public static ArrayList<String> leerArchivo(String archivo) {
		
		//read text from a file "archivo". The file is located in a directory "texts" relative to the current working directory
		Path path = FileSystems.getDefault().getPath("texts", archivo); 
		
		ArrayList<String> palabras = new ArrayList<String>();
		String regex = "[^a-zA-Z ]"; //cualquier caracter que no sea letra o espacio
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	line = line.toLowerCase();
				//line = line.replaceAll("[\'\"]+", ""); //borra las comillas
		        line = line.replaceAll(regex, " "); //reemplaza todos los caracteres que no son letras por espacios
		        line = line.trim().replaceAll(" +", " "); //reemplaza multiples espacios por 1
		        String[] lineas = line.split(" "); //separa por los espacios
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


	/**
	 * Método para obtener el número de palabras de un archivo
	 * @param archivo Archivo del que se contrán las palabras.
	 * @return Retorna cantidad de palabras del archivo (considera repetidas de existir).
	 */
	public static int countWords(String archivo) {
		return leerArchivo(archivo).size();

	}

	/**
	 * Método para llenar un diccionario a partir de una lista de palabras.
	 * @param d Diccionario que se llenará con palabras.
	 * @param palabras Lista de palabras por insertar.
	 * @param s Caracter final, "" para linearProbing y "$" para los árboles.
	 */
	public static void llenarDiccionario(IDiccionarioStruct d, ArrayList<String> palabras, String s) { //String s $ para arboles, "" para hash
		int i = 0;
		for (String p : palabras) {
			if(p.equals("yabadaba")){
				System.out.println("Meh");
			}
			d.insertar(p + s, i++);
		}
		System.out.println(i);
	}


	//Cuenta las palabras de los archivos
	public static void main (String [ ] args) {
		String[] directorios = new String[11];
		for(int i = 10; i <= 20; i++) {
			directorios[i-10] = String.valueOf((int)java.lang.Math.pow(2,i));

		}

		for(String dir : directorios) {
			if (dir == null) {
				continue;
			}
			System.out.println(dir+"/x00.txt" + "\t n° palabras: \t" + TextTools.countWords(dir+"/x00.txt"));
			System.out.println(dir+"/x01.txt" + "\t n° palabras: \t" + TextTools.countWords(dir+"/x01.txt"));
		}
	}
	
	
}
