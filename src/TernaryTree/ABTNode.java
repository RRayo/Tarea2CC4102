package TernaryTree;

import Dictionary.Diccionario;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Implementacion de la interfaz IABTNode
 */
public class ABTNode implements IABTNode{

    /**
     * s: Un caracter asociado al nodo, inicialmente es seteado como el caracter de menor valor.
     */
    private char s = '\u0000';


    /**
     * sons: Arreglo de IATBNodes con lso nodos hijos, inicialmente son seteados como ABTNullNodes
     */
    ArrayList<IABTNode> sons = new ArrayList<IABTNode>() {{
        add(new ABTNullNode());
        add(new ABTNullNode());
        add(new ABTNullNode());
    }};


    /**
     * diccionario: Objeto diccionario presente en el nodo, se utiliza si es un nodo hoja
     */
    Diccionario diccionario = new Diccionario("",-1);

    /**
     * Constructor del ABTNode: Recibe como parametro un caracter para que se asocie al nodo
     * @param s un caracter
     */
    public ABTNode(char s) {
        this.s = s;
    }

    /**
     * * descend: Este metodo indica sobre cual de los tres hijos es posible descender
     * @param w char de la palaabra la cual se desea conocer si es posible descender del nodo actual
     * @return un int indicando si se puede bajar por el hijo izquierdo(0), el centrar(1), o el derecho(2)
     */
    public int descend (char w) {
        if (this.s == w) {
            return 1;
        } else if (w < this.s) {
            return 0;
        } else {
            return 2;
        }
    }

    /**
     * setKey: Metodo que setea una llave y un valor para el diccionario presente en el nodo hoja.
     * @param key:  String con la llave del diccionario
     * @param value: int con un valor asociado al diccionario
     */
    public void setKey (String key, int value) {
        this.diccionario = new Diccionario(key,value);
    }

    /**
     * getSon: Metodo getter que devuelve el hijo inidicado
     * @param i int que representa el hijo a devolver : el hijo izquierdo(0), el centrar(1), o el derecho(2)
     * @return un IABTNode con el hijo solicitado
     */
    @Override
    public IABTNode getSon(int i) {
        return this.sons.get(i);
    }

    /**
     *updateNode: Establece un nuevo nodo sobre el cual descender. Reemplazando el hijo previemente existente.
     * @param substring: String que representa el maximo string comun entre la palabra a insertar y el nodo al cual
     * se descendio.
     * @param word: String con la palabra a descender.
     * @param index: int indicando el caracter a comparar de la palabra.
     * @return un nodo sobre el cual se puede descender
     */
    @Override
    public IABTNode updateNode(String substring, String word,  int index) {
        int descendNode = this.descend(substring.charAt(index));
        IABTNode newNode;
        if(descendNode == 1){
            newNode = new ABTNode(substring.charAt(index+1));
        } else {
            newNode = new ABTNode(substring.charAt(index));
        }
        newNode.setKey(word, -1);
        this.sons.set(descendNode, newNode);
        return this.sons.get(descendNode);
    }

    /**
     * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
     */
    @Override
    public void printTree() {
        System.out.println("node with char: " + this.getChar() + " and key: " + this.getKey());
        int i = 0;
        for(IABTNode node : this.sons) {
            if(!node.isNull()) {
                System.out.print("Son " + i + " :");
                node.printTree();
            }
            i++;
        }
    }

    /**
     * addValue: Metodo para agregar un nuevo valor al diccionario presente en el nodo.
     * @param v: int con el valor a insertar
     */
    @Override
    public void addValue(int v) {
        this.diccionario.add(v);
    }

    /**
     * getSize: Implementacion del getSize insertar de la interfaz IDiccionarioStruct, llama recursivamente la funcion
     * getSize en el nodo raiz
     * @return un int que representa el tamaño en Bytes
     */
    @Override
    public int getSize() {
        Stack<IABTNode> stack = new Stack<>();
        IABTNode node;
        stack.push(this);
        int totalSize = 0;
        while(!stack.empty()) {
            node = stack.pop();
            totalSize += 16; //tamaño base
            if(!node.isNull()){
                ABTNode abtnode = (ABTNode)node;
                for(IABTNode son : abtnode.sons){
                    totalSize += 4; //costo de un puntero
                    if(son.isNull()){
                        totalSize+=16;
                    } else {
                        stack.push(son);
                    }
                }
            }
        }
        return totalSize;
    }

    /**
     * getChar: Metodo getter que permite obtener el valor del char asociado a el nodo
     * @return el char asociado al nodo
     */
    public char getChar() {
        return this.s;
    }

    /**
     * getKey: Metodo getter que permite obtener la llave asociada al diccionario presente en el nodo
     * @return la llave del objeto diccionario
     */
    public String getKey() {
        return this.diccionario.key;
    }

    /**
     * getValue: Metodo getter que permite obtener los valores asociados al diccionario presente en el nodo
     * @return el arreglo de valores del objeto diccionario
     */
    public ArrayList<Integer> getValue() {
        return this.diccionario.valor;
    }

    /**
     * isNull: Este metodo indica si el nodo que lo invoco es nulo
     * @return false ya que no es un nodo nulo
     */
    public boolean isNull(){
        return false;
    }
}
