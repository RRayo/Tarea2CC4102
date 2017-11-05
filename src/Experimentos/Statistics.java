package Experimentos;

import java.util.Arrays;

/**
 * Clase para el manejo de las estadisticas.
 */
class Statistics
{

    /**
     * Constructor del cual se obtienen distintas estadisticas.
     * @param data Arreglo con los datos que se usaran para calcularlas.
     */

    /**
     * Calcula el promedio de los datos.
     * @return Promedio de los datos.
     */
    public double getMean(int[] data, int size)
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/size;
    }

    /**
     * Calcula la varianza de los datos.
     * @return Varianza de los datos.
     */
    double getVariance(int[] data, int size)
    {
        double mean = getMean(data, size);
        double temp = 0;
        for(double a :data)
            temp += (a-mean)*(a-mean);
        return temp/(size-1);
    }

    /**
     * Calcula la desviacion estandar de los datos.
     * @return Desviacion estandar de los datos.

    double getStdDev(int[] data, int size)
    {
        return Math.sqrt(getVariance());
    }
*/
    /**
     * Calcula la media de los datos.
     * @return Media de los datos.
     */
    double median(int[] data, int size)
    {
        Arrays.sort(data);

        if (data.length % 2 == 0)
        {
            return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
        }
        return data[data.length / 2];
    }
}