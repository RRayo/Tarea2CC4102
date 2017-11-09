package Experimentos;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import PatriciaTrie.PatriciaTrie;
import TernaryTree.ABTree;
import TextTools.TextTools;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Clase para el experimento de búsqueda en diccionarios.
 */
public class ExperimentoBusquedaCompleto extends AbstractExperimento {

    public long[] tiempoInsHash, tiempoInsAbt, tiempoInsPat;
    public int[] sizeHash, sizeAbt, sizePat;
    public String[] busqHash, busqAbt, busqPat;

    static int NUM_ARCHIVOS = 11;

    /**
     * Constructor para el experimento de búsqueda para los 10 archivos en potencias de 2.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     */
    public ExperimentoBusquedaCompleto (double factor) { //factor para elegir el k en base al n
        this.tiempoInsHash = new long[NUM_ARCHIVOS]; //guardar tiempos en arreglo de 11
        this.tiempoInsAbt = new long[NUM_ARCHIVOS];
        this.tiempoInsPat = new long[NUM_ARCHIVOS];

        this.sizeHash = new int[NUM_ARCHIVOS]; //guardar tamaños en arreglos de 11
        this.sizeAbt = new int[NUM_ARCHIVOS];
        this.sizePat = new int[NUM_ARCHIVOS];

        this.busqHash = new String[NUM_ARCHIVOS];
        this.busqAbt = new String[NUM_ARCHIVOS];
        this.busqPat = new String[NUM_ARCHIVOS];

        for (int i = 0; i<NUM_ARCHIVOS; i++) {
            this.tiempoInsAbt[i] = 0;
            this.tiempoInsAbt[i] = 0;
            this.tiempoInsAbt[i] = 0;

            this.sizeHash[i] = 0;
            this.sizeAbt[i] = 0;
            this.sizePat[i] = 0;

            this.busqHash[i] = "";
            this.busqAbt[i] = "";
            this.busqPat[i] = "";
        }


        int contador = 0;

        String[] directorios = new String[11];

        for(int i = 10; i <= 11; i++) {
            directorios[i-10] = String.valueOf((int) Math.pow(2,i));

        }

        for(String dir : directorios) {
            if(dir == null) {
                continue;
            }
            System.err.println("Tamaño del archivo: " + dir);

            ArrayList<String> palabras = TextTools.leerArchivo(dir + "/x00.txt");

            int n = palabras.size();
            int k = (int)(n * factor)+1;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            this.tiempoInsHash[contador] = Long.parseLong(super.timeTesting(hashLinearProbing, palabras, "LinearProbing"));
            this.sizeHash[contador] = hashLinearProbing.getSize();
            double elementosHash = hashLinearProbing.elementos;
            double porcentajeLlenado = elementosHash / n;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }

            this.tiempoInsAbt[contador] = Long.parseLong(super.timeTesting(abTree, palabras, "ABTree"));
            this.sizeAbt[contador] = abTree.getSize();

            this.tiempoInsPat[contador] = Long.parseLong(super.timeTesting(patriciaTree, palabras, "PatriciaTree"));
            this.sizePat[contador] = patriciaTree.getSize();

            String[] search;
            String s;
            int aux;

            s = super.searchTesting(hashLinearProbing, palabras, "LinearProbing").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.busqHash[contador] += index[2];
                } else {
                    this.busqHash[contador] += "0";
                }
                this.busqHash[contador] += ",";
                aux++;
            }
            this.busqHash[contador] = this.busqHash[contador].substring(0, this.busqHash[contador].length() - 1);

            s = super.searchTesting(abTree, palabras, "ABTree").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.busqAbt[contador] += index[2];
                } else {
                    this.busqAbt[contador] += "0";
                }
                this.busqAbt[contador] += ",";
                aux++;
            }
            this.busqAbt[contador] = this.busqAbt[contador].substring(0, this.busqAbt[contador].length() - 1);

            s = super.searchTesting(patriciaTree, palabras, "PatriciaTree").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.busqPat[contador] += index[2];
                } else {
                    this.busqPat[contador] += "0";
                }
                this.busqPat[contador] += ",";
                aux++;
            }
            this.busqPat[contador] = this.busqPat[contador].substring(0, this.busqPat[contador].length() - 1);


            System.out.println("jobs done");

            contador++;
        }

        System.out.println("Job's done");

    }



    public static void main (String [ ] args) {
        //String s = "largo: 1\t\t1";
        //String[] p = new String[11];
        //System.out.println(p[1]+"hola");

        ExperimentoBusquedaCompleto e = new ExperimentoBusquedaCompleto(2.5);
        for (int i = 0; i<11; i++) {
            System.out.println("hola");
            System.out.println(e.busqHash[i]);
            System.out.println(e.tiempoInsAbt[i]);
            System.out.println(e.sizeHash[i]);
        }

    }
}
