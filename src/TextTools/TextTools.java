package TextTools;

import Dictionary.IDiccionarioStruct;
import Experimentos.AbstractExperimento;
import Experimentos.ExperimentoBusqueda;
import Experimentos.ExperimentoSearchMiss;
import PatriciaTrie.PatriciaTrie;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			d.insertar(p + s, i++);
		}
		System.out.println(i);
	}


	/**
	 * Método para obtener un arreglo con palabras no presentes en los textos.
	 * @param archivo Archivo del que se extraeran las palabras.
	 * @return Retorna un arreglo de palabras.
	 */
	public static ArrayList<String> searchMissTexts(String archivo) {

		ArrayList<String> palabras = new ArrayList<String>();
		IDiccionarioStruct patriciaTree = new PatriciaTrie();
		AbstractExperimento abs = new ExperimentoSearchMiss();

		ArrayList<String> hobbit = TextTools.leerArchivo("/1048576" + "/x00.txt");
		abs.timeTesting(patriciaTree, hobbit, "PatriciaTree");


		String regex = "[^a-zA-Z ]"; //cualquier caracter que no sea letra o espacio
			Path path2 = FileSystems.getDefault().getPath("texts", archivo);
			try (BufferedReader reader = Files.newBufferedReader(path2, StandardCharsets.UTF_8)) {
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
						if(patriciaTree.buscar(p + "$").size() == 0) { //no esta en el hobbit
							palabras.add(p);
						}

					}
				}
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}


		return palabras;

	}


	//Cuenta las palabras de los archivos
	public static void main (String [ ] args) {
		System.out.println(searchMissTexts("searchMiss.txt").size());
		}




	
}
