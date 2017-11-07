package Experimentos;

import Dictionary.IDiccionarioStruct;
import TextTools.TextTools;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase abstracta para implemetar los experimentos.
 */
public abstract class AbstractExperimento {

    /**
     * Clase para que las hijas puedan llamar a los métodos de laclase abstracta.
     */
    public AbstractExperimento () {
        System.err.println("Comienza experimento");
    }

    /**
     * Constructor genérico para extender la clase a las hijas.
     * @param fileName Nombre del archivo.
     * @param factor Factor por el cual es multiplicado el numero de palabras para inicializar el hash.
     */
    public AbstractExperimento (String fileName, int factor) {
        System.err.println("Comienza experimento para archivo: " + fileName + "con factor: " + factor);
    }

    /**
     * Test para medir tiempo de inserción desde las palabras de 1 archivo en un IDiccionarioStruct.
     * @param d Diccionario que se llenará.
     * @param palabras Lista de palabras que se insertarán en el diccionario.
     * @param tipo Tipo de diccionario que se usará.
     * @return Retorna tiempo de ejecución en nanosegundos en forma de String.
     */
    public String timeTesting (IDiccionarioStruct d, ArrayList<String> palabras, String tipo) {
        System.err.println("Inicio de test de tiempo de inserción (1 archivo) para: " + tipo);
        String letraFinal = "$";
        if (tipo.equals("LinearProbing")) {
            letraFinal = "";
        }
        long i = System.nanoTime();
        TextTools.llenarDiccionario(d, palabras, letraFinal);
        long f = System.nanoTime();

        System.err.println("Test finalizado");
        return ""+(f-i);
    }

    /**
     * Test para medir tiempo de inserción desde las palabras de 2 archivos en un IDiccionarioStruct.
     * @param d Diccionario que se llenará.
     * @param palabras1 Lista de palabras del 1er archivo que se insertarán en el diccionario.
     * @param palabras2 Lista de palabras del 2do archivo que se insertarán en el diccionario.
     * @param tipo Tipo de diccionario que se usará.
     * @return Retorna tiempo de ejecución en nanosegundos en forma de String.
     */
    public String timeTesting (IDiccionarioStruct d, ArrayList<String> palabras1,
                             ArrayList<String> palabras2 , String tipo) {
        System.err.println("Inicio de test de tiempo de inserción (2 archivos) para: " + tipo);
        String letraFinal = "$";
        if (tipo.equals("LinearProbing")) {
            letraFinal = "";
        }
        long i = System.nanoTime();
        TextTools.llenarDiccionario(d, palabras1, letraFinal);
        TextTools.llenarDiccionario(d, palabras2, letraFinal);
        long f = System.nanoTime();

        System.err.println("Test finalizado");
        return "" + (f-i);
    }

    /**
     * Test para la búsqueda de n/10 palabras seleccionadas al azar desde una lista.
     * @param d Diccionario donde se buscará.
     * @param palabras Lista de palabras de las que se extraerán palabras para buscar en el diccionario.
     * @param tipo Tipo de diccionario.
     * @return String con los tiempos de búsqueda asociados a cada largo de las palabras.
     */
    public String searchTesting (IDiccionarioStruct d, ArrayList<String> palabras, String tipo) {
        System.err.println("Inicio de test de búsqueda de palabras al azar para: " + tipo);

        int n = palabras.size();
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

        for (int i = 0; i<50 ; i++) {
        	if(tiemposPorLargo[i] == ""){
        		continue;
        	}
        	String[] auxSplit1 = tiemposPorLargo[i].split("\\$");
            formatoVertical += "largo: " + i + "\t";
            for(String timeLapse : auxSplit1) {
                String[] auxSplit2 = timeLapse.split("_");
                formatoVertical += (Long.parseLong(auxSplit2[1]) - Long.parseLong(auxSplit2[0])) + ", ";
            }
            formatoVertical += "\n";
        }

        System.err.println("Test finalizado");
        return formatoVertical;
    }

    /**
     * Generador aleatorios de Integers en un rango
     * @param min Mínimo número posible generado.
     * @param max Máximo némero posible generado.
     * @param n Total de números generados.
     * @return Retorna un Stack con los números enteros generados.
     */
    public Stack randomNumbersStack(int min, int max, int n) {
        Stack st = new Stack();
        for(int i = 0; i<=n; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            st.push(randomNum);
        }
        return st;
    }

    /**
     * Función que calcula la similitud entre 2 conjuntos de palabras a través de un diccionario.
     * @param palabrasT1 Lista de palabras del 1er archivo que se insertarán en el diccionario.
     * @param palabrasT2 Lista de palabras del 2do archivo que se insertarán en el diccionario.
     * @param D1 Diccionario para las palabras del texto 1.
     * @param D2 Diccionario para las palabras del texto 2.
     * @param tipo Tipo de diccionario.
     * @return Retorna un String con el tiempo de ejecución y la similitud entre los archivos.
     */
    public String similitud(ArrayList<String> palabrasT1, ArrayList<String> palabrasT2
            , IDiccionarioStruct D1, IDiccionarioStruct D2, String tipo) {

        System.err.println("Inicio de test de similitud entre 2 archivos para: " + tipo);

        double total = 0;

        double sum = 0;

        long i = System.nanoTime();
        for (String p : palabrasT1) {
            int c1 = count(p, D1);
            int c2 = count(p, D2);
            sum += c1 + c2;
            total += Math.abs(c1 - c2);
        }

        for (String p : palabrasT2) {
            int c1 = count(p, D1);
            int c2 = count(p, D2);
            sum += c1 + c2;
            total += Math.abs(c1 - c2);
        }
        long f = System.nanoTime();

        System.err.println("Test finalizado");

        //tiempo & resultados
        return (f-i) + "&" + (1 - (total / sum));
    }


    /**
     * Devuelve el número de posiciones que tiene una palabra en el Diccionario.
     * @param palabra Palabra que se buscará.
     * @param D Diccionario donde se buscará.
     * @return Retorna la cantidad de veces que aparece la palabra en el texto original.
     */
    public int count(String palabra, IDiccionarioStruct D) {
        int aux = D.buscar(palabra + "$").size();

        if(palabra.equals("yabadaba")){
            System.out.println(aux);
            System.out.println(D.getSize());
        }

        return aux;
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
