package Experimentos;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import PatriciaTrie.PatriciaTrie;
import TernaryTree.ABTree;
import TextTools.TextTools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Clase para el experimento de searchMiss.
 */
public class ExperimentoSearchMiss extends  AbstractExperimento{

    /**
     * Campo estatico con un arerglo de palabras no presentes en lso textos.
     */
    private static ArrayList<String> palabrasSearchMiss = TextTools.searchMissTexts("searchMiss.txt");

    /**
     * Constructor predeterminado para el experimento de searchMiss.
     **/
    public ExperimentoSearchMiss() { }

    /**
     * Constructor para el experimento de searchMiss.
     * @param fileName Nombre del archivo a generar con los resultados.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     * @param ini Inicio del rango de archivos (minimo 10).
     * @param fin Fin del rango de archivos (máximo 20).
     */
    public ExperimentoSearchMiss (String fileName, double factor, int ini, int fin) { //factor para elegir el k en base al n
        String[] directorios = new String[11];
        ArrayList<String> lines = new ArrayList<>();
        lines.add("------ RESULTADOS EXPERIMENTO Search Miss------");
        Path file = Paths.get(fileName + ".txt");

        for(int i = ini; i <= fin; i++) {
            directorios[i-10] = String.valueOf((int)java.lang.Math.pow(2,i));

        }


        for(String dir : directorios) {
            if(dir == null) {
                continue;
            }

            ArrayList<String> palabrasMissExperiment = new ArrayList(palabrasSearchMiss.subList(0, Integer.parseInt(dir)/10));
            System.err.println("Tamaño del archivo: " + dir);
            lines.add("Tamaño: " + dir);

            ArrayList<String> palabras = TextTools.leerArchivo(dir + "/x00.txt");

            int n = palabras.size();
            int k = (int)(n * factor)+1;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            lines.add(" ->Tiempo de construccion de linearProbing: " + super.timeTesting(hashLinearProbing, palabras, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing: " + String.valueOf(hashLinearProbing.getSize()));
            double elementosHash = hashLinearProbing.elementos;
            double porcentajeLlenado = elementosHash / n; //TODO revisar si sirve
            lines.add(" ->Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de abTree: " + super.timeTesting(abTree, palabras, "ABTree"));
            lines.add(" ->Tamaño  de abTree: " + String.valueOf(abTree.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree: " + super.timeTesting(patriciaTree, palabras, "PatriciaTree"));
            lines.add(" ->Tamaño  de patriciaTree: " + String.valueOf(patriciaTree.getSize()));


            lines.add(" ->Tiempo de search Miss de linearProbing: ");
            String[] search = super.searchTesting(hashLinearProbing, palabrasMissExperiment, "LinearProbing").split("\n");
            for (String line: search) {
                lines.add("\t" + line);
            }
            lines.add(" ->Tiempo de search Miss de abTree: ");
            search = super.searchTesting(abTree, palabrasMissExperiment, "ABTree").split("\n");
            for (String line: search) {
                lines.add("\t" + line);
            }
            lines.add(" ->Tiempo de search Miss de patriciaTree: ");
            search = super.searchTesting(patriciaTree, palabrasMissExperiment, "PatriciaTree").split("\n");
            for (String line: search) {
                lines.add("\t" + line);
            }
        }
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main (String [ ] args) {
        ExperimentoSearchMiss e = new ExperimentoSearchMiss("testing",2.5, 10,10);

    }
}