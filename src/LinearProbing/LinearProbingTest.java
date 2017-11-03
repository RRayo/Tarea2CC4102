package LinearProbing;

import TernaryTree.ABTNode;
import TernaryTree.ABTNullNode;
import TernaryTree.ABTree;
import TernaryTree.IABTNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearProbingTest {
    @Test
    void create() {

        LinearProbing linPtest10 = new LinearProbing(10);

        assertEquals(linPtest10.k, 9, "No se guarda el valor correcto de k");
        assertEquals(linPtest10.elementos, 0, "Incorrecto numero de elementos ");
        assertNotNull(linPtest10.tabla, "Tabla nula");

        int hashTest = LinearProbing.hash("qwertyasdfgh");
        assertTrue(hashTest >= 0 && hashTest <= Integer.MAX_VALUE);

    }

    @Test
    void insertYbuscar() {

        LinearProbing linPtest10 = new LinearProbing(10);

        linPtest10.insertar("barco", 1);
        assertEquals(linPtest10.buscar("barco").get(0), 1, "La busqueda de barco debio retornar 1");


        assertNull(linPtest10.buscar("caballo"), "Al no encontrar el elemento debio retornar null");

        linPtest10.insertar("aaaaaaa", 9999999);
        assertEquals(linPtest10.buscar("aaaaaaa").get(0), 9999999, "La busqueda de aaaaaaa debio retornar 9999999");

        linPtest10.insertar("barco", 2);
        assertEquals(linPtest10.buscar("barco").get(0), 1, "La busqueda de barco debio retornar 1, " +
                "luego de insertar barco de nuevo");
        assertEquals(linPtest10.buscar("barco").get(1), 2, "La busqueda de barco debio retornar 2" +
                "como segundo elemento");


    }

}