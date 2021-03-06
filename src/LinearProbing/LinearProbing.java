package LinearProbing;

import Dictionary.Diccionario;
import Dictionary.IDiccionarioStruct;
import TextTools.TextTools;

import java.util.ArrayList;

/**
 * Clase para la creación de diccionario que utiliza el método de Hash.
 */
public class LinearProbing implements IDiccionarioStruct {
    public int k, elementos;
    public Diccionario[] tabla;

    /**
     * Contructor para un nuevo diccionario de tipo Hash con linear probing.
     * @param k Tamaño de la tabla de hash.
     */
    public LinearProbing(int k) {
        this.k = k-1;
        this.elementos = 0; //nuemero de elementos
        this.tabla = new Diccionario[k];
    }

    /**
     * Busca un String dentro de la estructura y devuelve lista con posiciones.
     * @param s String a buscar.
     * @return lista con posiciones del String en el texto original. Si no está devuelve una lista vacía.
     */
    public ArrayList<Integer> buscar(String s) { //devuelve el arreglo de posiciones
        int f = hash(s);
        int i = f % this.k;
        ArrayList<Integer> lista = new ArrayList<Integer>();
        while (tabla[i] != null) {
            if (tabla[i].key.equals(s)) {
                lista = tabla[i].valor;
                break;
            }
            i = (i+1)%k;
        }

        return lista;
    }

    /**
     * Inserta el String dado en la estructura con su posicion respectiva.
     * @param s String por insertar.
     * @param posc posición en el texto del String.
     */
    public void insertar(String s, int posc) { // String a insertar y su posicion
        int f = hash(s);
        int i = f % this.k;
        int aux = i;
        while (tabla[i] != null) {
            
            if(tabla[i].key.equals(s)) {
                tabla[i].add(posc);
                return;
            }
            
            i = (i+1)% this.k;
            if(aux == i){
                System.err.println("Error, todo el arreglo lleno, no se pudo insertar");
                return;
            }
        }
        tabla[i] = new Diccionario(s, posc);
        this.elementos++;
    }

    @Override
    public int getSize() {
        int dictionarySize = 16; //arreglo
        for(Diccionario dict : this.tabla) {
            if(dict != null){ dictionarySize +=  8 + dict.getSize(); }
            //referencia diccionaro + su peso
        }
        return 16 + 4 + 4 + dictionarySize;
        //base + 4 int + 4 elementos + tamaño tabla
    }

    /**
     * Función para calcular el hash de un String
     * @param s String con el que se calculará el hash.
     * @return valor del hash del String.
     */
    public static int hash(String s) {
        int hash = 7;
        for(int i = 0; i<s.length(); i++) {
            hash = Math.abs(hash*31 + s.charAt(i));
        }
        return hash;
    }

    public static void main (String [ ] args) {
        /*
        LinearProbing L = new LinearProbing(200);
        Diccionario[] d = L.tabla;
        System.out.println("Largo tabla: "+d.length);
        String s = "Hola";
        System.out.println(L.buscar(s));
        L.insertar(s,1);
        System.out.println(L.buscar(s));
        L.insertar("Chao",5);
        L.insertar("caballo",8);
        L.insertar("caballo",7);
        System.out.println(L.buscar("caballo"));
        for(int i = 0; i<d.length; i++) {
            if(d[i] != null){
                System.out.println(i+":  key: " + d[i].key + "   |  valor: " + d[i].valor);
            } else {
                //System.out.println(i+":  "+d[i]);
            }
        }


        */

        for(int i = 0; i<10; i++){
            String prefix = i<=9 ? "0" : "";
            System.out.println("x" + prefix +i + ": " + TextTools.countWords("/lyrics/x"+ prefix + i + ".txt"));
        }

        //System.out.println("Lyrics1: " + TextTools.countWords("lyrics1.txt"));
        //System.out.println("Lyrics2: " + TextTools.countWords("lyrics2.txt"));

        //System.out.println("Bible: " + TextTools.countWords("524288/big.txt"));

        //System.out.println("x01: " + TextTools.countWords("/65536/80day10.txt"));

        /*System.out.println("Shrek: " + TextTools.countWords("shrek.txt"));
        System.out.println("Bane rises: " + TextTools.countWords("baneRises.txt"));

        System.out.println("Shakespeare: " + TextTools.countWords("shakespeare.txt"));
        System.out.println("Bible: " + TextTools.countWords("bible.txt"));
*/
    }
}
