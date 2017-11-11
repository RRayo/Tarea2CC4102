package PatriciaTrie;

import java.util.Stack;
import java.util.TreeSet;

/**
 * Implementacion de la interfaz INode
 */
public class PTNode implements INode{

        /**
         *sons: Un set de arcos con referencias a los nodos hijos
         **/
        TreeSet<PTEdge> sons = new TreeSet<PTEdge>();

        /**
         *father: Una referencia al nodo padre del PTNode
         **/
        INode father;


    /**
     * Constructor de PTNode
     * @param father un INode que representa al padre del nodo
     */
        PTNode(INode father){
            this.father = father;
        }

    /**
     * Constructor predeterminado de PTNode
     */
        PTNode() {}

    /**
     * isLeaf: Metodo que determina si el nodo es una hoja
     * @return false
     */
        @Override
        public boolean isLeaf() {
            return false;
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
     * @return un arco al cual se puede descender o null en caso contrario
     */
        @Override
        public PTEdge descend(String word, int index) {
            String compare = word.substring(index, word.length());
            for (PTEdge edge : sons){
                if (edge.prefixOf(compare)){
                    return edge;
                }
            }
            //No puede descender por ninguna de las hojas.
            return null;
        }


    /**
     * addSon: Este metodo agrega un nuevo arco a la coleccion presente en el nodo, estableciendo al nodo como padre del,
     * nodo al extremo del arco
     * @param sonEdge
     */
    @Override
    public void addSon(PTEdge sonEdge) {
        this.sons.add(sonEdge);
        sonEdge.setNodeFather(this);
            }


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
     * @return Una coleccion de arcos
     */
    @Override
    public TreeSet<PTEdge> getSons() {
        return this.sons;
    }

    /**
     * removeSon: Este metodo recibe un arco y lo elimina del set de hijos del nodo
     * @param edge: Un arco que se desea eliminar
     */
    @Override
    public void removeSon(PTEdge edge) {
        this.sons.remove(edge);
    }


    /**
     * getSize: Metodo que devuelve una estimacion del tamaño del nodo
     * @return un int con el tamaño en Bytes
     */
    @Override
    public int getSize() {
        Stack<INode> stack = new Stack<>();
        INode node;
        stack.push(this);
        int totalSize = 0;
        while(!stack.empty()) {
            node = stack.pop();
            if(!node.isLeaf()){
                totalSize += 24; //tamaño base + puntero a padre
                PTNode ptnode = (PTNode)node;
                for(PTEdge edge : ptnode.sons){
                    totalSize += 4; //costo de un puntero
                    totalSize += edge.getSize();
                    if(edge.node.isLeaf()){
                        totalSize += edge.node.getSize();
                    } else {
                        stack.push(edge.node);
                    }
                }
            } else { //es hoja
                totalSize += node.getSize();
            }
        }
        return totalSize;
    }


    /**
     * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
     */
    public void printTree() {
        for (PTEdge son : sons) {
            System.out.println("Node Edge: " + son.word);
            son.node.printTree();
        }
    }

    /**
     * descendRoot: Un metodo que se aplica cuado PTnode corresponde a la raiz, este determina que se puede descender a un
     * arco si posee un prefijo en comun
     * @param word la paralabra con la cual se desea descender
     * @param i un int que indica un index de la palabra
     * @return un arco por el cual se puede descender o null en caso contrario
     */
    PTEdge descendRoot(String word, int i){
        String compare = word.substring(i, word.length());
        for (PTEdge edge : sons){
            if (!PatriciaTrie.LCP(word, edge.word).equals("")){
                return edge;
            }
    }
    //No puede descender por ninguna de las hojas.
        return null;
}



}
