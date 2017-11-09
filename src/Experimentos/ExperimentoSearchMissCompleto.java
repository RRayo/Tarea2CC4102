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
public class ExperimentoSearchMissCompleto extends AbstractExperimento {

    public String[] SearchMissHash, SearchMissAbt, SearchMissPat;

    static int NUM_ARCHIVOS = 11;

    private static ArrayList<String> palabrasSearchMiss = TextTools.searchMissTexts("searchMiss.txt");

    /**
     * Constructor para el experimento de searchMiss para los 10 archivos en potencias de 2.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     */
    public ExperimentoSearchMissCompleto(double factor) { //factor para elegir el k en base al n

        this.SearchMissHash = new String[NUM_ARCHIVOS];
        this.SearchMissAbt = new String[NUM_ARCHIVOS];
        this.SearchMissPat = new String[NUM_ARCHIVOS];

        for (int i = 0; i<NUM_ARCHIVOS; i++) {
            this.SearchMissHash[i] = "";
            this.SearchMissAbt[i] = "";
            this.SearchMissPat[i] = "";
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

            ArrayList<String> palabrasMissExperiment = new ArrayList(palabrasSearchMiss.subList(0, Integer.parseInt(dir)/10));

            System.err.println("Tamaño del archivo: " + dir);


            int n = palabrasMissExperiment.size();
            int k = (int)(n * factor)+1;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            double elementosHash = hashLinearProbing.elementos;
            double porcentajeLlenado = elementosHash / n;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }


            String[] search;
            String s;
            int aux;

            s = super.searchTesting(hashLinearProbing, palabrasMissExperiment, "LinearProbing").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.SearchMissHash[contador] += index[2];
                } else {
                    this.SearchMissHash[contador] += "0";
                }
                this.SearchMissHash[contador] += ",";
                aux++;
            }
            this.SearchMissHash[contador] = this.SearchMissHash[contador].substring(0, this.SearchMissHash[contador].length() - 1);

            s = super.searchTesting(abTree, palabrasMissExperiment, "ABTree").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.SearchMissAbt[contador] += index[2];
                } else {
                    this.SearchMissAbt[contador] += "0";
                }
                this.SearchMissAbt[contador] += ",";
                aux++;
            }
            this.SearchMissAbt[contador] = this.SearchMissAbt[contador].substring(0, this.SearchMissAbt[contador].length() - 1);

            s = super.searchTesting(patriciaTree, palabrasMissExperiment, "PatriciaTree").split("\n", 2)[1];
            search = s.split("\n");
            aux = 0;
            for (String line: search) {
                String[] index = line.split("\t");
                if(index.length > 1){
                    this.SearchMissPat[contador] += index[2];
                } else {
                    this.SearchMissPat[contador] += "0";
                }
                this.SearchMissPat[contador] += ",";
                aux++;
            }
            this.SearchMissPat[contador] = this.SearchMissPat[contador].substring(0, this.SearchMissPat[contador].length() - 1);



            contador++;
        }

        System.out.println("Job's done");

    }



    public static void main (String [ ] args) {
        //String s = "largo: 1\t\t1";
        //String[] p = new String[11];
        //System.out.println(p[1]+"hola");

        ExperimentoSearchMissCompleto e = new ExperimentoSearchMissCompleto(2.5);

    }
}
