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
      * descend: Este metodo indica sobre cual de los arcos hijos se puede descender, dado una palabra y un indice de esta
      * @param word Un string con la palabra que se usa para descender en el arbol
      * @param index Un int con el indice de la posicion de la palabra que se desea comparar
      * @return Un arco donde es posible descender o nulo en caso contrario
      */
     PTEdge descend(String word, int index);


     /**
      * addSon: Este metodo agrega un nuevo arco a la coleccion presente en el nodo, estableciendo al nodo como padre del,
      * nodo al extremo del arco
      * @param edge Un arco
      */
     void addSon(PTEdge edge);


     /**
      * getFather: Funcion getter para obtener el padre de un nodo
      * @return un INode con el nodo padre o nulo si no se tiene uno
      */
     INode getFather();

     /**
      * getSons: Metodo getter para obtener la coleccion de arcos de un nodo
      * @return Una coleccion de arcos
      */
     TreeSet<PTEdge> getSons();


     /**
      * removeSon: Este metodo recibe un arco y lo elimina del set de hijos del nodo
      * @param edge: Un arco que se desea eliminar
      */
     void removeSon(PTEdge edge);

     /**
      * getSize: Metodo que devuelve una estimacion del tamaño del nodo
      * @return un int con el tamaño en Bytes
      */
     int getSize();

     /**
      * printTree: Una funcion de debugging para imprimir el arbol de ser necesario
      */
     void printTree();
}
