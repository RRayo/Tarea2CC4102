
public class LinearProbing {
    int k;
    Diccionario[] tabla;

    public LinearProbing(int k) {
        this.k = k;
        this.tabla = new Diccionario[k];
    }

    public boolean buscar(String s) {
        int f = hash(s);
        int i = f % this.k;
        boolean cond = false;
        while (tabla[i] != null) {
            if (tabla[i].equals(s)) {
                cond = true;
                break;
            }
            i = (i+1)%k;
        }

        return cond;
    }

    public void insertar(String s) {
        int f = hash(s);
        int i = f % this.k;
        int aux = i;
        while (tabla[i] != null) {
            i = (i+1)%k;
            if(aux == i){
                System.err.println("Error, todo el arreglo lleno, no se pudo insertar");
                return;
            }
        }
        tabla[i] = new Diccionario(s,0); // TODO cambiar el 0
    }

    public static int hash(String s) {
        int hash = 7;
        for(int i = 0; i<s.length(); i++) {
            hash = hash*31 + s.charAt(i);
        }
        return hash;
    }
}
