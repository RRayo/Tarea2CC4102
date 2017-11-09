package Experimentos;


/*
faltaría hacer una clase que genere cada experimento un número de veces,
calcule el promedio por los elementos y escriba un archivo con los resultados
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MainExperiment {
    public static void main(String[] args){
        long[] tiempoInsHash = new long[11];
        long[] tiempoInsAbt = new long[11];
        long[] tiempoInsPat = new long[11];
        Arrays.fill(tiempoInsHash, 0);
        Arrays.fill(tiempoInsAbt, 0);
        Arrays.fill(tiempoInsPat, 0);

        int[] sizeHash = new int[11];
        int[] sizeAbt = new int[11];
        int[] sizePat = new int[11];
        Arrays.fill(sizeHash, 0);
        Arrays.fill(sizeAbt, 0);
        Arrays.fill(sizePat, 0);

        long[][] busqHash = new long[11][51];
        long[][] busqAbt = new long[11][51];
        long[][] busqPat = new long[11][51];
        for (int i = 0; i<11; i++) {
            for(int j=0; j< 51; j++) {
                busqHash[i][j] = 0;
                busqAbt[i][j] = 0;
                busqPat[i][j] = 0;
            }
        }


        double[] simHash = new double[11];
        double[] simAbt = new double[11];
        double[] simPat = new double[11];
        long[] tiempoSimHash = new long[11];
        long[] tiempoSimAbt = new long[11];
        long[] tiempoSimPat = new long[11];

        Arrays.fill(simHash, 0);
        Arrays.fill(simAbt, 0);
        Arrays.fill(simPat, 0);
        Arrays.fill(tiempoSimHash, 0);
        Arrays.fill(tiempoSimAbt, 0);
        Arrays.fill(tiempoSimPat, 0);


        long[][] SearchMissHash = new long[11][51];
        long[][] SearchMissAbt = new long[11][51];
        long[][] SearchMissPat = new long[11][51];
        for (int i = 0; i<11; i++) {
            for(int j=0; j< 51; j++) {
                SearchMissHash[i][j] = 0;
                SearchMissAbt[i][j] = 0;
                SearchMissPat[i][j] = 0;
            }
        }

        int i;
        for(i = 0; i<=5 ; i++) {

            ExperimentoBusquedaCompleto expBusqueda = new ExperimentoBusquedaCompleto(2.5);


            //suma nuevos tamaños
            //EXP BUSQUEDA
            tiempoInsHash = sumArrays(tiempoInsHash, expBusqueda.tiempoInsHash);
            tiempoInsAbt = sumArrays(tiempoInsAbt, expBusqueda.tiempoInsHash);
            tiempoInsPat = sumArrays(tiempoInsPat, expBusqueda.tiempoInsHash);

            sizeHash = sumArrays(sizeHash, expBusqueda.sizeHash);
            sizeAbt = sumArrays(sizeAbt, expBusqueda.sizeAbt);
            sizePat = sumArrays(sizePat, expBusqueda.sizePat);

            busqHash = sumArrays(busqHash, expBusqueda.busqHash);
            busqAbt = sumArrays(busqAbt, expBusqueda.busqAbt);
            busqPat = sumArrays(busqPat, expBusqueda.busqPat);



            //EXP SIMILITUD
            ExperimentoSimilitudCompleto exSimilitud = new ExperimentoSimilitudCompleto( 2.5);
            simHash = sumArrays(simHash, exSimilitud.simHash);
            simAbt = sumArrays(simAbt, exSimilitud.simAbt);
            simPat = sumArrays(simPat, exSimilitud.simPat);

            tiempoSimHash = sumArrays(tiempoSimHash, exSimilitud.tiempoSimHash);
            tiempoSimAbt = sumArrays(tiempoSimAbt, exSimilitud.tiempoSimAbt);
            tiempoSimPat = sumArrays(tiempoSimPat, exSimilitud.tiempoSimPat);


            //EXP SearchMiss
            ExperimentoSearchMissCompleto expSearchMiss = new ExperimentoSearchMissCompleto(2.5);
            SearchMissHash = sumArrays(SearchMissHash, expSearchMiss.SearchMissHash);
            SearchMissAbt = sumArrays(SearchMissAbt, expSearchMiss.SearchMissAbt);
            SearchMissPat = sumArrays(SearchMissPat, expSearchMiss.SearchMissPat);


            System.out.println("---------------------------------------------------------");
        }
        //sacar promedio, escribir
        ArrayList<String> lines = new ArrayList<>();
        Path file = Paths.get("results" + ".txt");

        /*
        Experimento busqueda:
        tiempoInsHash
        tiempoInsAbt
        tiempoInsPat
        sizeHash
        sizeAbt
        sizePat
        busqHash
        busqAbt
        busqPat

        Experimento similitud:
        simHash
        simAbt
        simPat
        tiempoSimHash
        tiempoSimAbt
        tiempoSimPat

        Experimento SearchMiss
        SearchMissHash
        SearchMissAbt
        SearchMissPat
         */

        lines.add(parseArray(meanArray(tiempoInsHash,i)));
        lines.add(parseArray(meanArray(tiempoInsAbt,i)));
        lines.add(parseArray(meanArray(tiempoInsPat,i)));

        lines.add(parseArray(meanArray(sizeHash,i)));
        lines.add(parseArray(meanArray(sizeAbt,i)));
        lines.add(parseArray(meanArray(sizePat,i)));

        lines.add(parseMatrix(meanArray(busqHash,i)));
        lines.add(parseMatrix(meanArray(busqAbt,i)));
        lines.add(parseMatrix(meanArray(busqPat,i)));



        lines.add(parseArray(meanArray(simHash,i)));
        lines.add(parseArray(meanArray(simAbt,i)));
        lines.add(parseArray(meanArray(simPat,i)));

        lines.add(parseArray(meanArray(tiempoSimHash,i)));
        lines.add(parseArray(meanArray(tiempoSimAbt,i)));
        lines.add(parseArray(meanArray(tiempoSimPat,i)));



        lines.add(parseMatrix(meanArray(SearchMissHash,i)));
        lines.add(parseMatrix(meanArray(SearchMissAbt,i)));
        lines.add(parseMatrix(meanArray(SearchMissPat,i)));

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static long[] sumArrays(long[] l1, long[] l2) {
        for(int i = 0; i<l1.length; i++) {
            l1[i] += l2[i];
        }
        return l1;
    }

    public static double[] sumArrays(double[] d1, double[] d2) {
        for(int i = 0; i<d1.length; i++) {
            d1[i] += d2[i];
        }
        return d1;
    }

    public static int[] sumArrays(int[] i1, int[] i2) {
        if(i1.length!=i2.length) {
            System.out.println(i1.length + "/" + i2.length);
        }
            for(int i = 0; i<i1.length; i++) {
            i1[i] += i2[i];
        }
        return i1;
    }

    public static long[][] sumArrays(long[][] ll1, long[][] ll2) {
        for(int i = 0; i<ll1.length; i++) {
            for(int j = 0; j< ll1[i].length; j++){
                ll1[i][j] += ll2[i][j];
            }
        }
        return ll1;
    }

    //----------------------------------}
    public static long[] meanArray(long[] l, int c) {
        for(int i = 0; i<l.length; i++) {
            l[i] += (long)l[i]/c;
        }
        return l;
    }

    public static double[] meanArray(double[] d, int c) {
        for(int i = 0; i<d.length; i++) {
            d[i] += (double)d[i]/c;
        }
        return d;
    }

    public static int[] meanArray(int[] ii, int c) {
        for(int i = 0; i<ii.length; i++) {
            ii[i] += (int)ii[i]/c;
        }
        return ii;
    }

    public static long[][] meanArray(long[][] ll, int c) {
        for(int i = 0; i<ll.length; i++) {
            for(int j = 0; j< ll[i].length; j++){
                ll[i][j] += (long)ll[i][j]/c;
            }
        }
        return ll;
    }
    //----------------------------------}

    public static String parseArray(int[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += array[i] + ",";
        }
        return result.substring(0,result.length()-1) + "]";
    }

    public static String parseArray(long[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += String.valueOf(array[i]) + ",";
        }
        return result.substring(0,result.length()-1) + "]";
    }

    public static String parseArray(double[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += String.valueOf(array[i]) + ",";
        }
        return result.substring(0,result.length()-1) + "]";
    }

    public static String parseMatrix(long[][] matrix) {
        String result = "[";
        for (int i = 0; i < matrix.length; i++) {
            String aux = parseArray(matrix[i]);
            aux = aux.substring(1,aux.length()); //escapar [
            aux = aux.replaceAll("]","");
            result += aux + ";";//escapar []
        }
        return result.substring(0,result.length()-1) + "]";
    }
    /*
    public static String parseStringArray(String[] values) {
        for(int i = 0; i < values.length; i++) {
            String[] values = part.split(",");
            int sum = 0;
            for(int i = 0; i < values.length; i++) {
                sum+=Integer.parseInt(values[i]);
            }
            result+=String.valueOf(sum) + ";";

        }

        return result + "}";
    }*/

    /*
    public static String parseStringArray(String array) {
        String result = "{";
        array = array.substring(1,array.length());//escape {
        array = array.replaceAll("}","");//escape }
        String[] parsed = array.split(";");
        for(String part : parsed) {
            String[] values = part.split(",");
            int sum = 0;
            for(int i = 0; i < values.length; i++) {
                sum+=Integer.parseInt(values[i]);
            }
            result+=String.valueOf(sum) + ";";

        }

        return result + "}";
    }*/
}

//[[1,2,3],[3,4,5]] -> [1,2,3;3,4,5] ?
