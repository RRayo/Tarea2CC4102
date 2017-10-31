import java.util.ArrayList;

public class PatriciaTrie {

    public static PTRoot root;


    public PatriciaTrie(PTRoot root) {
        this.root = root;
    }


    int search(String word){
        INode searchResult = Rsearch(this.root, word + "$", 0);
        if(searchResult == null || !searchResult.isLeaf()) {
            return -1;
        } else {
            PTLeaf result = (PTLeaf) searchResult;
            if(result.key.equals(word + "$")) {
                return result.value;
            }
        }
        return -1;
    }


    static INode Rsearch(INode node, String word, int index) {
        //Si se llega a una hoja se hace una comparacion directa
        if (node.isLeaf()) {
            return node;
        } else { // Se debe descender en el arbol
            PTEdge descendEdge = node.descend(word, index);
            if(descendEdge == null) { // o quedo a la mitad o en un nodo no pudo bajar
                return node;
            }
            //sigue descendiendo
            return Rsearch(descendEdge.node, word, index + descendEdge.word.length());
        }
    }



    void insert(String word, int value) {
        //Se realiza la busqueda y se ve hasta donde llego
        INode searchResult = Rsearch(this.root, word + "$", 0);
        PTLeaf leaf;
        if (searchResult.isLeaf()) {
            leaf = (PTLeaf) searchResult;
        } else {//fallo en un nodo
            leaf = firstLeaf(searchResult);
        }
        //va a descender desde la raiz hasta llegar a su ubicacion final
        insertFromLeaf(this.root, LCP(leaf.key, word), word, value, 0);
    }


    static void insertFromLeaf(INode node, String p, String word, int value, int index) {
        //veo si puedo descender
        PTEdge descendEdge = node.descend(word, index);
        String comparateWord = p.substring(index, p.length());
        if(descendEdge == null) { //no puedo descender, debo ver la razon
            PTNode insertNode = (PTNode)descendEdge.node;
            for (PTEdge edge : insertNode.sons) {
                if (!edge.prefixOf(word) && edge.word.charAt(0) == comparateWord.charAt(0)) {
                    //Quedo en mitad de una arista
                    //corta arista no se propaga, solo corta
                    PTNode splitNode = (PTNode)edge.node;//nodo el cual desciende de la arista

                    //Se crea nuevo nodo a la mitad de la aritsa
                    String newNodeEdgeWord = p.substring(p.length()-edge.word.length()+1, p.length());
                    PTNode newNode = new PTNode(insertNode);
                    PTEdge newEdge = new PTEdge(newNodeEdgeWord, newNode);

                    //nuevo arco es p corrido en indice

                    String leafEdgeWord = CLCP(p,word);
                    PTLeaf newLeaf = new PTLeaf(word, value);
                    PTEdge leafEdge = new PTEdge(leafEdgeWord, newLeaf);

                    //insertNode.replaceSon(node, newNode);
                    insertNode.sons.remove(edge);
                    insertNode.addSon(newEdge);
                    PTEdge nodeEdge = new PTEdge(CLCP(edge.word, newNodeEdgeWord),splitNode);
                    newNode.addSon(leafEdge);
                    newNode.addSon(nodeEdge);
                    return;
                }
            }
            //quedo en nodo
            String leafEdgeWord = word.substring(p.length(), word.length());
            PTLeaf leafNode = new PTLeaf(word, value);
            PTEdge leafEdge = new PTEdge(leafEdgeWord, leafNode);
            node.addSon(leafEdge);

        } else {//sigo descendiendo
            insertFromLeaf(descendEdge.node, p,  word, value,index + descendEdge.word.length());
        }

    }


    //Metodos auxiliares

    public static PTLeaf firstLeaf(INode node) {
        if(node.isLeaf()) {
            return (PTLeaf) node;
        }
        if(node.getFather() == null){
            PTRoot rtnode = (PTRoot) node;
            if(rtnode.sons.isEmpty()) {
                return null;
            }
            for(PTEdge sonNode : rtnode.sons) {
                PTLeaf result = firstLeaf(sonNode.node);
                if(result != null) {
                    return result;
                }
            }
        } else {
            PTNode ptnode = (PTNode) node;
            if(ptnode.sons.isEmpty()) {
                return null;
            }
            for(PTEdge sonNode : ptnode.sons) {
                PTLeaf result = firstLeaf(sonNode.node);
                if(result != null) {
                    return result;
                }
            }
        }
        return null;
    }



    public static String LCP (String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLength);
    }

    public static String CLCP (String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return (s1.length()>s2.length()) ? s1.substring(minLength, s1.length()) : s2.substring(minLength, s2.length());
    }

}