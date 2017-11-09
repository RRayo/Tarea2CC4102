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

    public long[] tiempoInsHash, tiempoInsAbt, tiempoInsPat;
    public double[] simHash, simAbt, simPat;
    public long[] tiempoSimHash, tiempoSimAbt, tiempoSimPat;
    public int[] sizeHash, sizeAbt, sizePat;

    static int NUM_ARCHIVOS = 11;

    /**
     * Constructor para el experimento de similitud con parámetros internos.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     */
    public ExperimentoSimilitudCompleto (double factor) {
        this.tiempoInsHash = new long[NUM_ARCHIVOS]; //guardar tiempos en arreglo de 11
        this.tiempoInsAbt = new long[NUM_ARCHIVOS];
        this.tiempoInsPat = new long[NUM_ARCHIVOS];

        this.simHash = new double[NUM_ARCHIVOS];
        this.simAbt = new double[NUM_ARCHIVOS];
        this.simPat = new double[NUM_ARCHIVOS];

        this.tiempoSimHash = new long[NUM_ARCHIVOS];
        this.tiempoSimAbt = new long[NUM_ARCHIVOS];
        this.tiempoSimPat = new long[NUM_ARCHIVOS];

        this.sizeHash = new int[NUM_ARCHIVOS]; //guardar tamaños en arreglos de 11
        this.sizeAbt = new int[NUM_ARCHIVOS];
        this.sizePat = new int[NUM_ARCHIVOS];

        for (int i = 0; i<NUM_ARCHIVOS; i++) {
            this.tiempoInsAbt[i] = 0;
            this.tiempoInsAbt[i] = 0;
            this.tiempoInsAbt[i] = 0;

            this.sizeHash[i] = 0;
            this.sizeAbt[i] = 0;
            this.sizePat[i] = 0;

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



            this.tiempoInsHash[contador] += Long.parseLong(super.timeTesting(hashLinearProbing1, palabras1, "LinearProbing"));
            this.sizeHash[contador] += hashLinearProbing1.getSize();
            elementosHash = hashLinearProbing1.elementos;
            porcentajeLlenado = elementosHash / n1;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }

            this.tiempoInsHash[contador] += Long.parseLong(super.timeTesting(hashLinearProbing2, palabras2, "LinearProbing"));
            this.sizeHash[contador] += hashLinearProbing2.getSize();
            elementosHash = hashLinearProbing2.elementos;
            porcentajeLlenado = elementosHash / n1;
            if (porcentajeLlenado > 0.4) {
                System.err.println("\n\nFactor insuficiente, introducir factor mayor");
                System.exit(-1);
            }


            this.tiempoInsAbt[contador] += Long.parseLong(super.timeTesting(abTree1, palabras1, "ABTree"));
            this.sizeAbt[contador] += abTree1.getSize();

            this.tiempoInsAbt[contador] += Long.parseLong(super.timeTesting(abTree2, palabras2, "ABTree"));
            this.sizeAbt[contador] += abTree1.getSize();

            this.tiempoInsPat[contador] += Long.parseLong(super.timeTesting(patriciaTree1, palabras1, "PatriciaTree"));
            this.sizePat[contador] += patriciaTree1.getSize();

            this.tiempoInsPat[contador] += Long.parseLong(super.timeTesting(patriciaTree2, palabras2, "PatriciaTree"));
            this.sizePat[contador] += patriciaTree2.getSize();


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
            System.out.println(e.sizeAbt[i]);
            System.out.println(e.tiempoInsAbt[i]);
            System.out.println(e.tiempoSimPat[i]);
        }

    }
}
