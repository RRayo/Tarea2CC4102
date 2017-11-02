package Dictionary;

import java.util.ArrayList;

public class Diccionario{
    public String key;
    public ArrayList<Integer> valor; //arreglo con las posiciones de la llave en el texto

    public Diccionario(){}

    public Diccionario(String s, int i) {
        this.key = s;
        this.valor = new ArrayList<Integer>();
        this.valor.add(i);
    }

    public void add(int i) {
        this.valor.add(i);
    }
}
