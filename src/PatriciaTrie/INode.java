package PatriciaTrie;

import java.util.TreeSet;

/**
 * Interfaz para las estructuras de los nodos que componen lso Patricia Tries.
 */
public interface INode {
     /**
      * isLeaf: Entrega un valor booleano indicando si el nodo es una hoja.
      * @return: true si es una hoja o false en el caso contrario
      */
     boolean isLeaf();


     /**
      * setFather: Establece una referencia a un nodo padre en el nodo sobre el cual fue invocado.
      * @param father: nuevo nodo padre para el nodo que invoco el metodo
      */
     void setFather(INode father);


     /**
      * descend: Este metodo indica
      * @param word
      * @param index
      * @return
      */
     PTEdge descend(String word, int index);
     void addSon(PTEdge edge);
     INode getFather();
     TreeSet<PTEdge> getSons();
     void removeSon(PTEdge edge);
     int getSize();
     void printTree();
}
