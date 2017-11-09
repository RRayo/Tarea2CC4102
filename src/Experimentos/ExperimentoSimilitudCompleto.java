package Experimentos;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import PatriciaTrie.PatriciaTrie;
import TernaryTree.ABTNullNode;
import TernaryTree.ABTree;
import TextTools.TextTools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase para el experimento de similitud entre 2 textos.
 */
public class ExperimentoSimilitudCompleto extends AbstractExperimento{

    public double[] simHash, simAbt, simPat;
    public long[] tiempoSimHash, tiempoSimAbt, tiempoSimPat;

    static int NUM_ARCHIVOS = 11;

    /**
     * Constructor para el experimento de similitud con parámetros internos.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     */
    public ExperimentoSimilitudCompleto (double factor) {


        this.simHash = new double[NUM_ARCHIVOS];
        this.simAbt = new double[NUM_ARCHIVOS];
        this.simPat = new double[NUM_ARCHIVOS];

        this.tiempoSimHash = new long[NUM_ARCHIVOS];
        this.tiempoSimAbt = new long[NUM_ARCHIVOS];
        this.tiempoSimPat = new long[NUM_ARCHIVOS];



        for (int i = 0; i<NUM_ARCHIVOS; i++) {

            this.simHash[i] = 0;
            this.simAbt[i] = 0;
            this.simPat[i] = 0;

            this.tiempoSimHash[i] = 0;
            this.tiempoSimAbt[i] = 0;
            this.tiempoSimPat[i] = 0;
        }

        int contador = 0;

        String[] directorios = new String[11];

        for(int i = 10; i <= 11; i++) {
            directorios[i-10] = String.valueOf((int)java.lang.Math.pow(2,i));

        }

        for(String dir : directorios) {
            if(dir == null) {
                continue;
            }
            ArrayList<String> palabras1 = TextTools.leerArchivo(dir + "/x00.txt");
            ArrayList<String> palabras2 = TextTools.leerArchivo(dir + "/x01.txt");

            int n1 = palabras1.size();
            int n2 = palabras2.size();
            int k1 = (int)(n1 * factor)+1;
            int k2 = (int)(n2 * factor)+1;

            double elementosHash, porcentajeLlenado;

            LinearProbing hashLinearProbing1 = new LinearProbing(k1);
            IDiccionarioStruct abTree1 = new ABTree();
            IDiccionarioStruct patriciaTree1 = new PatriciaTrie();

            LinearProbing hashLinearProbing2 = new LinearProbing(k2);
            IDiccionarioStruct abTree2 = new ABTree();
            IDiccionarioStruct patriciaTree2 = new PatriciaTrie();



            elementosHash = hashLinearProbing1.elementos;
            porcentajeLlenado = elementosHash / n1;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }

            elementosHash = hashLinearProbing2.elementos;
            porcentajeLlenado = elementosHash / n1;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }



            String[] simHashAux = super.similitud(palabras1, palabras2, hashLinearProbing1, hashLinearProbing2, "LinearProbing").split("&");
            String[] simAbtAux = super.similitud(palabras1, palabras2, abTree1, abTree2, "ABTree").split("&");
            String[] simPatrAux = super.similitud(palabras1, palabras2, patriciaTree1, patriciaTree2, "PatriciaTree").split("&");

            this.tiempoSimHash[contador] = Long.parseLong(simHashAux[0]);
            this.tiempoSimAbt[contador] = Long.parseLong(simAbtAux[0]);
            this.tiempoSimPat[contador] = Long.parseLong(simPatrAux[0]);

            this.simHash[contador] = Double.parseDouble(simHashAux[1]);
            this.simAbt[contador] = Double.parseDouble(simAbtAux[1]);
            this.simPat[contador] = Double.parseDouble(simPatrAux[1]);

        }


    }

    public static void main (String [ ] args) {
        ExperimentoSimilitudCompleto e = new ExperimentoSimilitudCompleto(2.5);
        for (int i = 0; i<11; i++) {
            System.out.println("hola");
            System.out.println(e.simAbt[i]);
            System.out.println(e.tiempoSimPat[i]);
        }

    }
}
