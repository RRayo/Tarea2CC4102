package TernaryTree;

import java.util.ArrayList;

/**
 * Implementacion de la interfaz IABTNode
 */
public class ABTNullNode implements IABTNode{

    /**
     * isNull: Este metodo indica si el nodo que lo invoco es nulo
     * @return true ya que es un nodo nulo
     */
    @Override
    public boolean isNull() {
        return true;
    }

    /**
     * descend: Este metodo indica sobre cual de los tres hijos es posible descender
     * @param w char de la palaabra la cual se desea conocer si es posible descender del nodo actual
     * @return 0, en una hoja no es posible descender
     */
    @Override
    public int descend(char w) {
        return 0;
    }

    /**
     * getChar: Metodo getter que permite obtener el valor del char asociado a el nodo
     * @return un char con el valor mas bajo
     */
    @Override
    public char getChar() {
        return '\u0000';
    }


    /**
     * getKey: Metodo getter que permite obtener la llave asociada al diccionario presente en el nodo
     * @return null, ya que una hoja no posee un objeto diccionario
     */
    @Override
    public String getKey() {
        return null;
    }

    /**
     * getValue: Metodo getter que permite obtener los valores asociados al diccionario presente en el nodo
     * @return null, ya que una hoja no posee un objeto diccionario
     */
    @Override
    public ArrayList<Integer> getValue() {
        return null;
    }

    /**
     * setKey: Metodo que setea una llave y un valor para el diccionario presente en el nodo hoja.
     * @param key:  String con la llave del diccionario
     * @param value: int con un valor asociado al diccionario
     */
    @Override
    public void setKey(String key, int value) {

    }

    /**
     * getSon: Metodo getter que devuelve el hijo inidicado
     * @param i int que representa el hijo a devolver : el hijo izquierdo(0), el centrar(1), o el derecho(2)
     * @return null, ya que una hoja no posee hijos
     */
    @Override
    public IABTNode getSon(int i) {
        return null;
    }

    /**
     *updateNode: Establece un nuevo nodo sobre el cual descender. Reemplazando el hijo previemente existente.
     * @param substring: String que representa el maximo string comun entre la palabra a insertar y el nodo al cual
     * se descendio.
     * @param word: String con la palabra a descender.
     * @param index: int indicando el caracter a comparar de la palabra.
     * @return null, ya que una hoja no posee hijos
     */
    @Override
    public IABTNode updateNode(String substring, String word, int index) {
        return null;
    }

    /**
     * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
     */
    @Override
    public void printTree() {

    }

    /**
     * addValue: Metodo para agregar un nuevo valor al diccionario presente en el nodo.
     * @param v: int con el valor a insertar
     */
    @Override
    public void addValue(int v) {

    }

    /**
     * getSize: Implementacion del getSize insertar de la interfaz IDiccionarioStruct, llama recursivamente la funcion
     * getSize en el nodo raiz
     * @return un int que representa el tamaño en Bytes
     */
    @Override
    public int getSize() {
        return 16;
        //tamaño base
    }

}
