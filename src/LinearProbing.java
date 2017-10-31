import java.util.ArrayList;

public class LinearProbing {
    int k;
    Diccionario[] tabla;

    public LinearProbing(int k) {
        this.k = k-1;
        this.tabla = new Diccionario[k];
    }

    public ArrayList<Integer> buscar(String s) { //devuelve el arreglo de posiciones (o null de no estar)
        int f = hash(s);
        int i = f % this.k;
        ArrayList<Integer> lista = null;
        while (tabla[i] != null) {
            if (tabla[i].key.equals(s)) {
                lista = tabla[i].valor;
                break;
            }
            i = (i+1)%k;
        }

        return lista;
    }

    public void insertar(String s, int posc) { // String a insertar y su posicion
        int f = hash(s);
        int i = f % this.k;
        int aux = i;
        while (tabla[i] != null) {
            i = (i+1)% this.k;
            if(tabla[i].key.equals(s)) {
                tabla[i].add(posc);
                return;
            }
            if(aux == i){
                System.err.println("Error, todo el arreglo lleno, no se pudo insertar");
                return;
            }
        }
        tabla[i] = new Diccionario(s);
    }

    public static int hash(String s) {
        int hash = 7;
        for(int i = 0; i<s.length(); i++) {
            hash = Math.abs(hash*31 + s.charAt(i));
        }
        return hash;
    }

    public static void main (String [ ] args) {
        LinearProbing L = new LinearProbing(200);
        Diccionario[] d = L.tabla;
        System.out.println("Largo tabla: "+d.length);
        String s = "Hola";
        System.out.println(L.buscar(s));
        L.insertar(s,1);
        System.out.println(L.buscar(s));
        L.insertar("Chao",5);
        L.insertar("caballo",8);
        System.out.println(L.buscar("caballo"));
        for(int i = 0; i<d.length; i++) {
            if(d[i] != null){
                System.out.println(i+":  key: " + d[i].key + "   |  valor: " + d[i].valor);
            } else {
                //System.out.println(i+":  "+d[i]);
            }
        }
        System.out.println(TextTools.count("shrek.txt"));

    }
}
