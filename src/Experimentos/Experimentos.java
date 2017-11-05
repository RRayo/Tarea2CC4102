package Experimentos;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import PatriciaTrie.PatriciaTrie;
import PatriciaTrie.PTNode;
import TernaryTree.ABTNullNode;
import TernaryTree.ABTree;
import TextTools.TextTools;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Experimentos {

    public Experimentos (String archivo, int factor) { //factor para elegir el k en base al n
        ArrayList<String> palabras = TextTools.leerArchivo(archivo);

        int n = palabras.size();
        int k = n*factor;

        LinearProbing hashLinearProbing = new LinearProbing(k);
        IDiccionarioStruct abTree = new ABTree(new ABTNullNode());
        IDiccionarioStruct patriciaTree = new PatriciaTrie(new PTNode());

        this.timeTesting(hashLinearProbing, palabras, "LinearProbing");
        this.timeTesting(abTree, palabras, "ABTree");
        this.timeTesting(patriciaTree, palabras, "PatriciaTree");

        double elementosHash = hashLinearProbing.elementos;
        double porcentajeLlenado = elementosHash/n; //TODO revisar si sirve
        System.out.println("Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);


        this.searchTesting(hashLinearProbing, palabras, "LinearProbing", n);
        this.searchTesting(abTree, palabras, "ABTree", n);
        this.searchTesting(patriciaTree, palabras, "PatriciaTree", n);

    }

    public Experimentos (String archivo1, String archivo2, int factor) {
        ArrayList<String> palabras1 = TextTools.leerArchivo(archivo1);
        ArrayList<String> palabras2 = TextTools.leerArchivo(archivo2);

        int n = palabras1.size() + palabras2.size();
        int k = n*factor;

        LinearProbing hashLinearProbing = new LinearProbing(factor);
        IDiccionarioStruct abTree = new ABTree(new ABTNullNode());
        IDiccionarioStruct patriciaTree = new PatriciaTrie(new PTNode());

        TextTools.similitud(palabras1, palabras2, hashLinearProbing, "", "LinearProbing");
        TextTools.similitud(palabras1, palabras2, abTree, "$", "ABTree");
        TextTools.similitud(palabras1, palabras2, patriciaTree, "", "PatriciaTree");

    }


    public void timeTesting (IDiccionarioStruct d, ArrayList<String> palabras, String tipo) {
        String letraFinal = "";
        if (tipo.equals("ABTree")) {
            letraFinal = "$";
        }
        long i = System.currentTimeMillis();
        TextTools.llenarDiccionarioStatic(d, palabras, letraFinal);
        long f = System.currentTimeMillis();

        System.out.println("Tiempo total de insercion para " + tipo + ":\t" + (f-i));
    }

    public void timeTesting (IDiccionarioStruct d, ArrayList<String> palabras1,
                             ArrayList<String> palabras2 , String tipo) {
        String letraFinal = "";
        if (tipo.equals("ABTree")) {
            letraFinal = "$";
        }
        long i = System.currentTimeMillis();
        TextTools.llenarDiccionarioStatic(d, palabras1, letraFinal);
        TextTools.llenarDiccionarioStatic(d, palabras2, letraFinal);
        long f = System.currentTimeMillis();

        System.out.println("Tiempo total de insercion para " + tipo + ":\t" + (f-i));

    }

    public int[] searchTesting (IDiccionarioStruct d, ArrayList<String> palabras, String tipo, int n) {
        Stack st = this.randomNumbersStack(0, n, n/10);


        String[] tiemposPorLargo = new String[50]; //arreglo con la contabilidad de tiempo en base al largo


        while (!st.empty()) {
            int p = (Integer) st.pop();
            long i = System.currentTimeMillis();
            d.buscar(palabras.get(p));
            long f = System.currentTimeMillis();
            tiemposPorLargo[palabras.get(p).length()] += i + "_" +f + "$";
        }

        int[] tiemposPromedios = new int[50];
        for (int i = 0; i<=50 ; i++) {
            String[] auxSplit1 = tiemposPorLargo[i].split("$");
            int total = 0;
            int size = 0;
            for(String timeLapse : auxSplit1) {
                String[] auxSplit2 = timeLapse.split("_");
                total += Integer.parseInt(auxSplit2[1]) - Integer.parseInt(auxSplit2[0]);
                size++;
            }
            tiemposPromedios[i] = total/size;
        }

        return tiemposPromedios;
        //System.out.println("Tiempo total de busqueda para " + tipo + "con " + n/10 + " elementos" + ":\t" + (f-i) );
    }

    public Stack randomNumbersStack(int min, int max, int n) {
        Stack st = new Stack();
        for(int i = 0; i<n; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            st.push(randomNum);
        }
        return st;
    }
}
