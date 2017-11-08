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
import java.util.Arrays;

import jdk.nashorn.internal.ir.debug.JSONWriter;

/**
 * Clase para el experimento de búsqueda en diccionarios.
 */
public class ExperimentoBusqueda extends AbstractExperimento {

    /**
     * Constructor para el experimento de búsqueda.
     * @param fileName Nombre del archivo a generar con los resultados.
     * @param factor Factor por el que se multiplicará la cantidad de palabras para asegurar
     *               el 40% máximo de llenado de hash. (Recomendado 2.5)
     * @param ini Inicio del rango de archivos (minimo 10).
     * @param fin Fin del rango de archivos (máximo 20).
     */
    public ExperimentoBusqueda (String fileName, double factor, int ini, int fin) { //factor para elegir el k en base al n
        String[] directorios = new String[11];
        ArrayList<String> lines = new ArrayList<>();
        lines.add("------ RESULTADOS EXPERIMENTO 1------");

        String[] ocurrenciasPatriciaTree = new String[51];
        Arrays.fill(ocurrenciasPatriciaTree, "");

        String[] ocurrenciasAbTree = new String[51];
        Arrays.fill(ocurrenciasAbTree, "");

        String[] ocurrenciasLinearProbing = new String[51];
        Arrays.fill(ocurrenciasLinearProbing, "");

        Path file = Paths.get(fileName + ".txt");

        for(int i = ini; i <= fin; i++) {
            directorios[i-10] = String.valueOf((int) Math.pow(2,i));

        }

        for(String dir : directorios) {
        	if(dir == null) {
        		continue;
        	}
            System.err.println("Tamaño del archivo: " + dir);
            lines.add("Tamaño: " + dir);

            ArrayList<String> palabras = TextTools.leerArchivo(dir + "/x00.txt");

            int n = palabras.size();
            int k = (int)(n * factor)+1;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            lines.add(" ->Tiempo de construccion de linearProbing: " + super.timeTesting(hashLinearProbing, palabras, "LinearProbing"));
            lines.add(" ->Espacio usado por linearProbing: " + String.valueOf(hashLinearProbing.getSize()));
            double elementosHash = hashLinearProbing.elementos;
            double porcentajeLlenado = elementosHash / n;
            lines.add(" ->Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de abTree: " + super.timeTesting(abTree, palabras, "ABTree"));
            lines.add(" ->Espacio usado por abTree: " + String.valueOf(abTree.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree: " + super.timeTesting(patriciaTree, palabras, "PatriciaTree"));
            lines.add(" ->Espacio usado por patriciaTree: " + String.valueOf(patriciaTree.getSize()));


            lines.add(" ->Tiempo de busqueda de linearProbing: ");
            String[] search = super.searchTesting(hashLinearProbing, palabras, "LinearProbing").split("\n");
            for (String line: search) {
                String[] index = line.split(":");
                if(index.length > 1){
                    String[] split = index[1].split("\t");
                    if(split.length > 1) {
                        int i = Integer.parseInt(split[0].trim());
                        ocurrenciasLinearProbing[i] = ocurrenciasLinearProbing[i].concat(split[2]).concat(",");
                    }
                }
            	lines.add("\t" + line);
            }

            lines.add(" ->Tiempo de busqueda de abTree: ");
            for (String line: search) {
                String[] index = line.split(":");
                if(index.length > 1){
                    String[] split = index[1].split("\t");
                    if(split.length > 1) {
                        int i = Integer.parseInt(split[0].trim());
                        ocurrenciasAbTree[i] = ocurrenciasAbTree[i].concat(split[2]).concat(",");
                    }
                }
            	lines.add("\t" + line);
            }
            lines.add(" ->Tiempo de busqueda de patriciaTree: ");
            search = super.searchTesting(patriciaTree, palabras, "PatriciaTree").split("\n");


            for (String line: search) {
                String[] index = line.split(":");
                if(index.length > 1){
                    String[] split = index[1].split("\t");
                    if(split.length > 1) {
                        int i = Integer.parseInt(split[0].trim());
                        ocurrenciasPatriciaTree[i] = ocurrenciasPatriciaTree[i].concat(split[2]).concat(",");
                    }
                }
            	lines.add("\t" + line);
            }
            System.out.println("jobs done");
        }
        lines.add(arrayToString(ocurrenciasLinearProbing));
        lines.add(arrayToString(ocurrenciasAbTree));
        lines.add(arrayToString(ocurrenciasPatriciaTree));
        System.out.println("Job's done");


        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }





    }



    public static void main (String [ ] args) {
        ExperimentoBusqueda e = new ExperimentoBusqueda("testJson",2.5, 10,20);
    	
    }
}
