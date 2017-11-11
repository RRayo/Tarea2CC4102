package PatriciaTrie;

/**
 * Clase PTedge encargada de represetntar un arco en el arbol patricia. Implementa comparable.
 */
public class PTEdge implements Comparable<PTEdge> {

    /**
     * word: String con la palabra que representara la comparacion a realizar en el arco
     */
    String word;
    /**
     * node: Referencia a nodo en el extremo del arco
     */
    INode node;

    /**
     * Constructor de PtEdge
     * @param word : La palabra o string que representara el salto en el arco
     * @param node : Una referencia a un nodo.
     */
    public PTEdge(String word, INode node) {
        this.word = word;
        this.node = node;
    }

    /**
     * setFather: Determina una referencia a un nodo padre en el nodo presente en el arco.
     * @param node: nuevo nodo padre para el nodo del arco que invoco el metodo
     */
    public void setNodeFather(INode node) {
        this.node.setFather(node);
    }

    /**
     * prefixOf: Determina si la palabra presente en el arco es prefijo del input entregado
     * @param word: String con una palabra a comparar con la del arco
     * @return un booleano indicando si la palabra del arco es prefijo o no.
     */
    public boolean prefixOf(String word) {
        return word.startsWith(this.word);
    }

    /**
     * compareTo: Implementacion del metodo compareTo de la clase Comparable
     * @param edge un arco a comparar
     * @return retorna un int indicando la posiciion relativa del arco con respecto al arco input
     */
    @Override
    public int compareTo(PTEdge edge) {
        int result = this.word.compareTo(edge.word);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * getSize: Metodo que devuelve una estimacion del tamaño del nodo
     * @return un int con el tamaño en Bytes
     */
    public int getSize(){
        return 16 + 2*this.word.length() + 8;
        //16 base + 2 bytes por caracter + 8 de referencia nodo + tamaño del nodo
    }
}