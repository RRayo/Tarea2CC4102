import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TextTools {
	
	public static ArrayList<String> leerArchivo(String archivo) {
		
		//read text from a file "archivo". The file is located in a directory "texts" relative to the current working directory
		Path path = FileSystems.getDefault().getPath("texts", archivo); 
		
		ArrayList<String> palabras = new ArrayList<String>();
		String regex = "[^a-zA-Z ]"; //cualquier caracter que no sea letra o espacio
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	line = line.toLowerCase();
		        line = line.replaceAll(regex, "");
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
	
	public static int count(String palabra, String archivo) { //TODO generar diccionario a partir del archivo y buscar la palabra en el
		return 0;
		
	}
	
	
	public static int count(String archivo) {
		return leerArchivo(archivo).size();
		
	}
	
	
	
	
}
