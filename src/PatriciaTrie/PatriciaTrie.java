package PatriciaTrie;

import Dictionary.IDiccionarioStruct;

import java.util.ArrayList;

public class PatriciaTrie implements IDiccionarioStruct{

    public static PTNode root = new PTNode();


    public PatriciaTrie(PTNode root) {
        this.root = root;
    }

    public PatriciaTrie() { }


    @Override
    public ArrayList<Integer> buscar(String s){
        INode searchResult = Rsearch(this.root, s + "$", 0);
        if(searchResult == null || !searchResult.isLeaf()) {
            return new ArrayList<Integer>();
        } else {
            PTLeaf result = (PTLeaf) searchResult;
            if(result.getKey().equals(s + "$")) {
                return result.getValues();
            }
        }
        return new ArrayList<Integer>();
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
            //sigue descendiendo}
            return Rsearch(descendEdge.node, word, index + descendEdge.word.length());
        }
    }


    @Override
    public void insertar(String s, int posc) {
        //Se realiza la busqueda y se ve hasta donde llego
        s = s + "$";
        if (this.root.descendRoot(s,0) == null){
            PTLeaf leafNode = new PTLeaf(s, posc);
            PTEdge leafEdge = new PTEdge(s , leafNode);
            this.root.addSon(leafEdge);
        } else {
            INode searchResult = Rsearch(this.root, s, 0);
            PTLeaf leaf;
            if (searchResult.isLeaf()) {
                leaf = (PTLeaf) searchResult;
            } else {//fallo en un nodo

                leaf = firstLeaf(searchResult, s);
            }
            //va a descender desde la raiz hasta llegar a su ubicacion final
            insertFromLeaf(this.root, LCP(leaf.getKey(), s), s, posc, 0);
        }
    }



    static void insertFromLeaf(INode node, String p, String word, int value, int index) {
        //veo si puedo descender
        PTEdge descendEdge = node.descend(word, index);
        String comparateWord = p.substring(index, p.length());
        if(descendEdge == null) { //no puedo descender, debo ver la razon
            for (PTEdge edge : node.getSons()) {
                if (edge.word.startsWith(comparateWord)) {
                    //Quedo en mitad de una arista
                    //corta arista no se propaga, solo corta


                    //Se crea nuevo nodo a la mitad de la aritsa
                    String newNodeEdgeWord = LCP(comparateWord, edge.word);
                    PTNode newNode = new PTNode(node);
                    PTEdge newEdge = new PTEdge(newNodeEdgeWord, newNode);

                    //nuevo arco es p corrido en indice

                    String leafEdgeWord = CLCP(p,word);
                    PTLeaf newLeaf = new PTLeaf(word, value);
                    PTEdge leafEdge = new PTEdge(leafEdgeWord, newLeaf);

                    //insertNode.replaceSon(node, newNode);
                    node.removeSon(edge);
                    node.addSon(newEdge);

                    PTEdge nodeEdge = new PTEdge(CLCP(edge.word, newNodeEdgeWord),edge.node);
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
            int newIndex = index + descendEdge.word.length() > p.length() ?  index + comparateWord.length(): index + descendEdge.word.length();
            if(descendEdge.node.isLeaf()){
                PTLeaf leaf = (PTLeaf) descendEdge.node;
                if(leaf.getKey().equals(word)){
                    leaf.addValue(value);
                    PTEdge newEdge = new PTEdge(descendEdge.word, leaf);
                    node.removeSon(descendEdge);
                    node.addSon(newEdge);
                    return;
                }
            }
            insertFromLeaf(descendEdge.node, p,  word, value, newIndex);
        }

    }

    @Override
    public int getSize() {
        return 16 + 8 + root.getSize();
    }

    //Metodos auxiliares

    //Busca la mejor hoja


    public static PTLeaf firstLeaf(INode node, String word) {
        if(node.isLeaf()) {
            return (PTLeaf) node;
        } else {
            int bestLength = 0;
            PTLeaf bestLeaf = null;
            for(PTEdge edge : node.getSons()) {
                PTLeaf candidateLeaf = firstLeaf(edge.node, word);
                if (candidateLeaf!= null && LCP(candidateLeaf.getKey(), word).length() > bestLength) {
                    bestLeaf = candidateLeaf;
                }
            }
            return bestLeaf;
        }
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