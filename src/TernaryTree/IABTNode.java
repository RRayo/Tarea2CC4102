package TernaryTree;

import java.util.ArrayList;

/**
 * Interfaz para las estructuras de los nodos que componen los ABTree
 */
public interface IABTNode {

    /**
     * isNull: Este metodo indica si el nodo que lo invoco es nulo
     * @return un boolean indicando si el nodo es nulo
     */
    boolean isNull();

    /**
     * descend: Este metodo indica sobre cual de los tres hijos es posible descender
     * @param w char de la palaabra la cual se desea conocer si es posible descender del nodo actual
     * @return un int indicando si se puede bajar por el hijo izquierdo(0), el centrar(1), o el derecho(2)
     */
    int descend(char w);

    /**
     * getChar: Metodo getter del caracter presente en el nodo
     * @return el caracter que represetna al nodo
     */
    char getChar();

    /**
     * getKey: Metodo getter de la llave presente en el nodo si este es un nodo hoja
     * @return
     */
    String getKey();

    /**
     * getValue: Metodo getter de los valores del diccionario asociado al nodo
     * @return un arreglo de valores int
     */
    ArrayList<Integer> getValue();

    /**
     * setKey: Metodo que setea una llave y un valor para el diccionario presente en el nodo hoja.
     * @param key: String con la llave del diccionario
     * @param value: int con un valor asociado al diccionario
     */
    void setKey (String key, int value);

    /**
     * getSon: Metodo getter que devuelve el hijo inidicado
     * @param i int que representa el hijo a devolver : el hijo izquierdo(0), el centrar(1), o el derecho(2)
     * @return un IABTNode con el hijo solicitado
     */
    IABTNode getSon (int i);

    /**
     *updateNode: Establece un nuevo nodo sobre el cual descender. Reemplazando el hijo previemente existente.
     * @param substring: String que representa el maximo string comun entre la palabra a insertar y el nodo al cual
     *                 se descendio.
     * @param word: String con la palabra a descender.
     * @param index: int indicando el caracter a comparar de la palabra.
     * @return un nodo sobre el cual se puede descender
     */
    IABTNode updateNode(String substring, String word, int index);

    /**
     * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
     */
    void printTree();

    /**
     * addValue: Metodo para agregar un nuevo valor al diccionario presente en el nodo.
     * @param v: int con el valor a insertar
     */
    void addValue(int v);

    /**
     * getSize: Implementacion del getSize insertar de la interfaz IDiccionarioStruct, llama recursivamente la funcion
     * getSize en el nodo raiz
     * @return un int que representa el tamaño en Bytes
     */
    int getSize();
}
