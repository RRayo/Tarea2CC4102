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
public class ExperimentoSimilitud extends AbstractExperimento{

    /**
     * Constructor para el experimento de similitud.
     * @param fileName Nombre del archivo a generar con los resultados.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     * @param ini Inicio del rango de archivos (minimo 10).
     * @param fin Fin del rango de archivos (máximo 20).
     */
    public ExperimentoSimilitud (String fileName, double factor, int ini, int fin) {

        String[] directorios = new String[11];
        ArrayList<String> lines = new ArrayList<>();
        lines.add("------ RESULTADOS EXPERIMENTO 1------");
        Path file = Paths.get(fileName + ".txt");

        for(int i = ini; i <= fin; i++) {
            directorios[i-10] = String.valueOf((int)java.lang.Math.pow(2,i));

        }

        for(String dir : directorios) {
        	if(dir == null) {
        		continue;
        	}
            lines.add("Tamaño: " + dir);

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

            lines.add(" ->Tiempo de construccion de linearProbing para T1: " + super.timeTesting(hashLinearProbing1, palabras1, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing1: " + String.valueOf(hashLinearProbing1.getSize()));
            elementosHash = hashLinearProbing1.elementos;
            porcentajeLlenado = elementosHash / n1;
            lines.add(" ->Porcentaje de llenado de tabla hash1:\t" + porcentajeLlenado);


            lines.add(" ->Tiempo de construccion de linearProbing para T2: " + super.timeTesting(hashLinearProbing2, palabras2, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing2: " + String.valueOf(hashLinearProbing2.getSize()));
            elementosHash = hashLinearProbing2.elementos;
            porcentajeLlenado = elementosHash / n2;
            lines.add(" ->Porcentaje de llenado de tabla hash2:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de abTree para T1: " + super.timeTesting(abTree1, palabras1, "ABTree"));
            lines.add(" ->Tamaño  de abTree1: " + String.valueOf(abTree1.getSize()));
            lines.add(" ->Tiempo de construccion de abTree para T2: " + super.timeTesting(abTree2, palabras2, "ABTree"));
            lines.add(" ->Tamaño  de abTree2: " + String.valueOf(abTree2.getSize()));



            lines.add(" ->Tiempo de construccion de patriciaTree para T1: " + super.timeTesting(patriciaTree1, palabras1, "PatriciaTree"));
            lines.add(" ->Tamaño  de patriciaTree1: " + String.valueOf(patriciaTree1.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree para T2: " + super.timeTesting(patriciaTree2, palabras2, "PatriciaTree"));
            lines.add(" ->Tamaño  de patriciaTree2: " + String.valueOf(patriciaTree2.getSize()));


            String[] simHash = super.similitud(palabras1, palabras2, hashLinearProbing1, hashLinearProbing2, "LinearProbing").split("&");
            String[] simAbt = super.similitud(palabras1, palabras2, abTree1, abTree2, "ABTree").split("&");
            String[] simPatr = super.similitud(palabras1, palabras2, patriciaTree1, patriciaTree2, "PatriciaTree").split("&");

            lines.add(" ->Tiempo de calculo de similitud con linearProbing: " + simHash[0]);
            lines.add(" ->Tiempo de calculo de similitud con abTree: " + simAbt[0]);
            lines.add(" ->Tiempo de calculo de similitud con patriciaTree: " + simPatr[0]);

            lines.add(" ->Similitud con linearProbing: " + simHash[1]);
            lines.add(" ->Similitud con abTree: " + simAbt[1]);
            lines.add(" ->Similitud con patriciaTree: " + simPatr[1]);

        }
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void main (String [ ] args) {
        ExperimentoSimilitud e = new ExperimentoSimilitud("exS-1024",2.5, 12,12);
    	
    }
}
