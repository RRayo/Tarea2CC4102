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

public class ExperimentoSimilitud extends AbstractExperimento{

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

            int n = palabras1.size() + palabras2.size();
            int k = (int)(n * factor)+1;

            double elementosHash, porcentajeLlenado;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            lines.add(" ->Tiempo de construccion de linearProbing para T1: " + super.timeTesting(hashLinearProbing, palabras1, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing: " + String.valueOf(hashLinearProbing.getSize()));
            elementosHash = hashLinearProbing.elementos;
            porcentajeLlenado = elementosHash / n; //TODO revisar si sirve
            lines.add(" ->Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de linearProbing para T2: " + super.timeTesting(hashLinearProbing, palabras2, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing: " + String.valueOf(hashLinearProbing.getSize()));
            elementosHash = hashLinearProbing.elementos;
            porcentajeLlenado = elementosHash / n; //TODO revisar si sirve
            lines.add(" ->Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de abTree para T1: " + super.timeTesting(abTree, palabras1, "ABTree"));
            lines.add(" ->Tamaño  de abTree: " + String.valueOf(abTree.getSize()));
            lines.add(" ->Tiempo de construccion de abTree para T2: " + super.timeTesting(abTree, palabras2, "ABTree"));
            lines.add(" ->Tamaño  de abTree: " + String.valueOf(abTree.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree para T1: " + super.timeTesting(patriciaTree, palabras1, "LinearProbing"));
            lines.add(" ->Tamaño  de patriciaTree: " + String.valueOf(patriciaTree.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree para T2: " + super.timeTesting(patriciaTree, palabras2, "LinearProbing"));
            lines.add(" ->Tamaño  de patriciaTree: " + String.valueOf(patriciaTree.getSize()));


            String[] simHash = super.similitud(palabras1, palabras2, hashLinearProbing, "", "LinearProbing").split("&");
            String[] simAbt = super.similitud(palabras1, palabras2, abTree, "$", "ABTree").split("&");
            String[] simPatr = super.similitud(palabras1, palabras2, patriciaTree, "", "PatriciaTree").split("&");

            lines.add(" ->Tiempo de calculo de similitud de linearProbing: " + simHash[0]);
            lines.add(" ->Tiempo de calculo de similitud de abTree: " + simAbt[0]);
            lines.add(" ->Tiempo de calculo de similitud de patriciaTree: " + simPatr[0]);

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
        ExperimentoSimilitud e = new ExperimentoSimilitud("exS-1024",2.5, 10,10);
    	
    }
}
