package Dictionary;

import java.util.ArrayList;

/**
 * Clase para guardar un String y una lista con sus posiciones.
 */
public class Diccionario{
    public String key;
    public ArrayList<Integer> valor; //arreglo con las posiciones de la llave en el texto

    /**
     * Constructor por default vacío.
     */
    public Diccionario(){}

    /**
     * Constructor para generar un diccionario nuevo según un String y un Int.
     * @param s String que represenata la palabra.
     * @param i Posición del String en el texto.
     */
    public Diccionario(String s, int i) {
        this.key = s;
        this.valor = new ArrayList<Integer>();
        this.valor.add(i);
    }

    /**
     * Agrega la posición a la lista.
     * @param i posicion por agregar.
     */
    public void add(int i) {
        this.valor.add(i);
    }

    /**
     * Devuelve el tamaño estimado para esta estructura.
     * @return Tamaño de la estructura.
     */
    public int getSize(){
        return 16 + 2*this.key.length() + 4*this.valor.size();
        //16 base + 2 bytes por caracter + 4 bytes por int
    }
}
