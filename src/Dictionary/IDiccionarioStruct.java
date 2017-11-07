package Dictionary;

import java.util.ArrayList;

/**
 * Interfaz para las estructuras de Diccionarios.
 */
public interface IDiccionarioStruct {

	/**
	 * Busca un String dentro de la estructura y devuelve lista con posiciones.
	 * @param s String a buscar.
	 * @return lista con posiciones del String en el texto original. Si no está devuelve una lista vacía.
	 */
	ArrayList<Integer> buscar(String s);

	/**
	 * Inserta el String dado en la estructura con su posicion respectiva.
	 * @param s String por insertar.
	 * @param posc posición en el texto del String.
	 */
	void insertar(String s, int posc);

	/**
	 * Devuelve el tamaño estimado de la estructura total del diccionario.
	 * @return Tamaño total de la estructura.
	 */
	int getSize();
	
}
