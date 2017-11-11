package PatriciaTrie;

import Dictionary.Diccionario;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Implementacion de la interfaz INode
 */
public class PTLeaf implements INode{


    /**
     *diccionario: Una instancia del objeto diccionario que alamcena una llave y un arreglo de valores
     **/
    Diccionario diccionario = new Diccionario();

    /**
     *father: Una referencia al nodo padre del PTNode
     **/
    INode father;

    /**
     * Constructor predeterminado de PTLeaf
     */
    public PTLeaf(){}


    /**
     * Constructor de PTleaf
     * @param key: Un string para ser llave de un diccionario
     * @param value un int para ser agregado al arreglo de valores del diccionario
     */
    public PTLeaf(String key, int value) {
        this.diccionario = new Diccionario(key, value);
    }

    /**
     * isLeaf: Metodo que determina si el nodo es una hoja
     * @return true
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     * setFather: Determina una referencia a un nodo padre.
     * @param father: nuevo nodo padre para el nodo que invoco el metodo
     */
    @Override
    public void setFather(INode father) {
        this.father = father;
    }

    /**
     * descend: Este metodo indica sobre cual de los arcos hijos se puede descender, dado una palabra y un indice de esta
     * @param word  Un string con la palabra que se usa para descender en el arbol
     * @param index Un int con el indice de la posicion de la palabra que se desea comparar
     * @return null, ya que no se puede descender de una hoja
     */
    @Override
    public PTEdge descend(String word, int index) {
        //no puede descender
        return null;
    }

    /**
     * addSon: Este metodo agrega un nuevo arco a la coleccion presente en el nodo, estableciendo al nodo como padre del,
     * nodo al extremo del arco
     * @param edge Un arco
     */
    @Override
    public void addSon(PTEdge edge) { }

    /**
     * getFather: Funcion getter para obtener el padre de un nodo
     * @return un INode con el nodo padre o nulo si no se tiene uno
     */
    @Override
    public INode getFather() {
        return this.father;
    }

    /**
     * getSons: Metodo getter para obtener la coleccion de arcos de un nodo
     * @return null, ya que una hoja no tiene hijos
     */
    @Override
    public TreeSet<PTEdge> getSons() {
        return null;
    }

    /**
     * removeSon: Este metodo recibe un arco y lo elimina del set de hijos del nodo
     * @param edge: Un arco que se desea eliminar
     */
    @Override
    public void removeSon(PTEdge edge) { }

    /**
     * getSize: Metodo que devuelve una estimacion del tamaño del nodo
     * @return un int con el tamaño en Bytes
     */
    @Override
    public int getSize() {
        return 16 + this.diccionario.getSize() + 8;
        //16 bytes de base + tamaño diccionario + 8 bytes ref padre
    }

    /**
     * getKey: Metodo getter de la llave del objeto diccionario almacenado en la hoja
     * @return un String con la lalve del diccionario
     */
    public String getKey(){
        return this.diccionario.key;
    }

    /**
     * addValue: Añade un nuevo valor al objeto diccionario
     * @param v un int para añadir al diccionario.
     */
    void addValue(int v) {
        this.diccionario.add(v);
    }

    /**
     * getValues: Metodo getter del arreglo de valores almacenado en el diccionario,
     * @return un arraylits con los valores almacenados en el diccionario
     */
    ArrayList<Integer> getValues(){
        return this.diccionario.valor;
    }

    /**
     * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
     */
    @Override
    public void printTree() {
        System.out.println("Leaf: key: " + this.diccionario.key );
    }


}
