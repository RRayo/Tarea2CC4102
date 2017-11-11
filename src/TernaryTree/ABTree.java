package TernaryTree;

import Dictionary.Diccionario;
import Dictionary.IDiccionarioStruct;
import PatriciaTrie.PTNode;

import java.util.ArrayList;

/**
 * Implementacion de la interfaz IDiccionarioStruct
 */
public class ABTree implements IDiccionarioStruct{

    /**
     * root: Nodo IABTNode que representa a la raiz del arbol
     */
    public IABTNode root = new ABTNullNode();


    /**
     * Constructor predeterminado del ABTree
     */
    public ABTree() { }

    /**
     * Constructor del ABTree
     * @param root: Un nodo para que sea la raiz del arbol
     */
    public ABTree(IABTNode root) {
        this.root = root;
    }
    //public ArrayList<String> palabras = new ArrayList<>();

    /**
     * buscar: Implementacion del metodo buscar de la interfa IDiccionarioStruct
     * Busca un String dentro de la estructura y devuelve lista con posiciones.
     * @param s String a buscar.
     * @return lista con posiciones del String en el texto original. Si no está devuelve una lista vacía.
     */
    @Override
    public ArrayList<Integer> buscar (String s) {
        IABTNode result = Isearch(root, s, 0);
        if(result.isNull() || !result.getKey().equals(s)){
            return new ArrayList<Integer>();
        } else {
            return result.getValue();
        }
    }

    /**
     * Isearch : Funcion que realiza una busqueda iterativa sobre el ABTree
     * @param node: IABTNode sobre el cual se parte la busqueda.
     * @param word: String con la palabra a buscar.
     * @param index: int indicando el caracter a comparar de la palabra.
     * @return un INode que representa a el ultimo nodo al cual se pudo descender.
     */
    public static IABTNode Isearch (IABTNode node, String word, int index) {
        while(!(index == word.length())) {
            if(node.isNull()){
                break;
            }
            IABTNode descendNode = node.getSon(node.descend(word.charAt(index)));
            int newIndex = index;
            if (node.getChar() == word.charAt(index)) {
                newIndex += 1;
            }

            if(descendNode.isNull()) {
                break;
            } else {
                index = newIndex;
                node = descendNode;
            }
        }
        return node;
    }

    /**
     * insertar : Implementacion del metodo insertar de la interfa IDiccionarioStruct, realiza una llamada a
     * insertFromLeaf en caso de una busqueda infructuosa
     * @param s    String por insertar.
     * @param posc posición en el texto del String.
     */
    @Override
    public void insertar(String s, int posc) {
        if(root.isNull()) {
            root = new ABTNode(s.charAt(0));
        }
        IABTNode searchResult = Isearch(root, s, 0);
        String subString = s.substring(LCP(searchResult.getKey(),s).length(), s.length());
        if(searchResult.getKey().equals(s)){
            searchResult.addValue(posc);
        } else {
            Iinsert(searchResult, subString, 0, s, posc);
        }

    }

    /**
     * getSize: Implementacion del getSize insertar de la interfaz IDiccionarioStruct, llama recursivamente la funcion
     * getSize en el nodo raiz
     * @return un int que representa el tamaño en Bytes
     */
    @Override
    public int getSize() {
        return 16 + 8 + root.getSize();
        //16 base + 8 ref raiz + tamaño arbol
    }

    /**
     * Iinsert: Este metodo realiza una insercion iterativa en el arbol.
     * @param node: IABTNode del cual se comienza la iteracion
     * @param subString: String que representa el maximo string comun entre la palabra a insertar y el nodo al cual
     *                 se descendio.
     * @param index: int que indica el caracter a comaparar en la palabra
     * @param word: String con la palabra a insertar
     * @param value: int con valor a insertar en el diccionario
     */
    public static void Iinsert(IABTNode node, String subString, int index, String word, int value) {
        while (!(index == subString.length() - 1 && node.getChar() == subString.charAt(index))) {


            int descend = node.descend(subString.charAt(index));
            IABTNode descendNode = node.getSon(descend);


            if (descendNode.isNull()) {
                node = node.updateNode(subString, word, index);
                if (descend == 1) {
                    index += 1;
                }
            } else {
                node = descendNode;
            }
        }
        node.setKey(word, value);
    }

    /**
     * LCP: Calcula el maximo prefijo en comun entre dos strings.
     * @param s1: Primer string.
     * @param s2: Segundo String.
     * @return String con el maximo prefijo comun obtenido.
     */
    public static String LCP (String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLength);
    }

//<:()<-<
}
