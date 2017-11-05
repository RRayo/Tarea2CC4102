package Experimentos;

import Dictionary.IDiccionarioStruct;
import LinearProbing.LinearProbing;
import PatriciaTrie.PatriciaTrie;
import PatriciaTrie.PTNode;
import TernaryTree.ABTNullNode;
import TernaryTree.ABTree;
import TextTools.TextTools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractExperimento {

    public AbstractExperimento () {
        System.err.println("Comienza experimento");
    }

    public AbstractExperimento (String fileName, int factor) {
        System.err.println("Comienza experimento para archivo: " + fileName + "con factor: " + factor);
    }

    public String timeTesting (IDiccionarioStruct d, ArrayList<String> palabras, String tipo) {
        String letraFinal = "";
        if (tipo.equals("ABTree")) {
            letraFinal = "$";
        }
        long i = System.nanoTime();
        TextTools.llenarDiccionarioStatic(d, palabras, letraFinal);
        long f = System.nanoTime();

       return ""+(f-i);
    }

    public String timeTesting (IDiccionarioStruct d, ArrayList<String> palabras1,
                             ArrayList<String> palabras2 , String tipo) {
        String letraFinal = "";
        if (tipo.equals("ABTree") || tipo.equals("PTrie")) {
            letraFinal = "$";
        }
        long i = System.nanoTime();
        TextTools.llenarDiccionarioStatic(d, palabras1, letraFinal);
        TextTools.llenarDiccionarioStatic(d, palabras2, letraFinal);
        long f = System.nanoTime();

        return "" + (f-i);

    }

    public String searchTesting (IDiccionarioStruct d, ArrayList<String> palabras, int n) {
        Stack st = this.randomNumbersStack(0, n-1, n/10);


        String[] tiemposPorLargo = new String[50]; //arreglo con la contabilidad de tiempo en base al largo
        Arrays.fill(tiemposPorLargo, "");


        while (!st.empty()) {
            int p = (Integer) st.pop();
            long i = System.nanoTime();
            d.buscar(palabras.get(p));
            long f = System.nanoTime();
            tiemposPorLargo[palabras.get(p).length()] += i + "_" +f + "$";
        }

        String formatoVertical = "Tiempos segun largos:\n";
        //String formatoVectorMatlab = "";


        //int[] tiemposPromedios = new int[50];
        for (int i = 0; i<50 ; i++) {
        	if(tiemposPorLargo[i] == ""){
        		continue;
        	}
        	String[] auxSplit1 = tiemposPorLargo[i].split("\\$");
            //int total = 0;
            //int size = 0;
            formatoVertical += "largo: " + i + "\t";
            for(String timeLapse : auxSplit1) {
                String[] auxSplit2 = timeLapse.split("_");
                //total += Integer.parseInt(auxSplit2[1]) - Integer.parseInt(auxSplit2[0]);
                formatoVertical += (Long.parseLong(auxSplit2[1]) - Long.parseLong(auxSplit2[0])) + ", ";
                //size++;
            }
            formatoVertical += "\n";
            //tiemposPromedios[i] = total/size;
        }

        return formatoVertical;
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

    public String similitud(ArrayList<String> palabrasT1, ArrayList<String> palabrasT2
            , IDiccionarioStruct D, String s, String tipo) {

        double sum = 0;


        long i = System.nanoTime();
        for (String p : palabrasT1) {
            sum += Math.abs(count(p, D)- count(p, D)); //TODO se puede optimizar solo sumando el largo de palabrasT1 en este caso y solo buscar en D2
        }

        for (String p : palabrasT2) {
            sum += Math.abs(count(p, D)- count(p, D));
        }
        long f = System.nanoTime();

        //tiempo + resultados
        return (f-i) + "&" + (1 - (sum / (palabrasT1.size()+palabrasT2.size())));
    }


    public int count(String palabra, IDiccionarioStruct D) {
        return D.buscar(palabra).size();
    }

    public static void main(String[] args) {
        /*int factor = 3;

        String[] directorios = new String[11];
        ArrayList<String> lines = new ArrayList<>();
        lines.add("------ RESULTADOS ------");
        Path file = Paths.get("results.txt");


        for(int i = 10; i <= 20; i++) {
            directorios[i-10] = String.valueOf((int)java.lang.Math.pow(2,i));

        }


        for(String dir : directorios) {


            lines.add("Tamaño: " + dir);

            ArrayList<String> palabras = TextTools.leerArchivo(dir + "/x00.txt");

            int n = palabras.size();
            int k = n * factor;

            LinearProbing hashLinearProbing = new LinearProbing(k);
            IDiccionarioStruct abTree = new ABTree();
            IDiccionarioStruct patriciaTree = new PatriciaTrie();

            lines.add(" ->Tiempo de construccion de linearProbing: " + timeTesting(hashLinearProbing, palabras, "LinearProbing"));
            lines.add(" ->Tamaño  de linearProbing: " + String.valueOf(hashLinearProbing.getSize()));
            double elementosHash = hashLinearProbing.elementos;
            double porcentajeLlenado = elementosHash / n; //TODO revisar si sirve
            lines.add(" ->Porcentaje de llenado de tabla hash:\t" + porcentajeLlenado);

            lines.add(" ->Tiempo de construccion de abTree: " + timeTesting(abTree, palabras, "ABTree"));
            lines.add(" ->Tamaño  de abTree: " + String.valueOf(abTree.getSize()));

            lines.add(" ->Tiempo de construccion de patriciaTree: " + timeTesting(patriciaTree, palabras, "LinearProbing"));
            lines.add(" ->Tamaño  de patriciaTree: " + String.valueOf(patriciaTree.getSize()));
        }
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
