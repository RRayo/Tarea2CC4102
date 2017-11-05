package TernaryTree;

import Dictionary.Diccionario;
import Dictionary.IDiccionarioStruct;
import PatriciaTrie.PTNode;

import java.util.ArrayList;

public class ABTree implements IDiccionarioStruct{

    public static IABTNode root = new ABTNullNode();
    public ABTree() { }
    public ABTree(IABTNode root) {
        this.root = root;
    }

    @Override
    public ArrayList<Integer> buscar (String s) {
        IABTNode result = Rsearch(root, s, 0);
        if(!result.getKey().equals(s)){
            return new ArrayList<Integer>();
        } else {
            return result.getValue();
        }
    }

    public static IABTNode Rsearch (IABTNode node, String word, int index) {
        if (index == word.length()) {
            return node;
        } else {
            IABTNode descendNode = node.getSon(node.descend(word.charAt(index)));
            int newIndex = index;
            if (node.getChar() == word.charAt(index)) {
                newIndex += 1;
            }
            return descendNode.isNull() ? node : Rsearch(descendNode, word, newIndex);
            }
        }


    @Override
    public void insertar(String s, int posc) {
        if(root.isNull()) {
            root = new ABTNode(s.charAt(0));
        }
        IABTNode searchResult = Rsearch(root, s, 0);
        String subString = s.substring(LCP(searchResult.getKey(),s).length(), s.length());
        if(subString.equals("")){
            updateValue(root, s, posc,0);
        } else {
            Rinsert(searchResult, subString, 0, s, posc);
        }

    }

    @Override
    public int getSize() {
        return 16 + 8 + root.getSize();
        //16 base + 8 ref raiz + tamaño arbol
    }

    public static void Rinsert(IABTNode node, String subString, int index, String word, int value) {
        if(index == subString.length() - 1 && node.getChar() == subString.charAt(index)) {
            node.setKey(word, value);
        } else {
            int descend = node.descend(subString.charAt(index));
            IABTNode descendNode = node.getSon(descend);
            if(descendNode.isNull()) {
                if(descend == 1){
                    Rinsert(node.updateNode(subString, index), subString, index + 1, word, value);
                } else {
                    Rinsert(node.updateNode(subString, index), subString, index, word, value);
                }

            } else {
                Rinsert(descendNode, subString, index, word, value);
            }
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

    public static void updateValue (IABTNode node, String word, int value, int index) {
        if (index == word.length()) {
            node.addValue(value);
        } else {
            IABTNode descendNode = node.getSon(node.descend(word.charAt(index)));
            int newIndex = index;
            if (node.getChar() == word.charAt(index)) {
                newIndex += 1;
                if (descendNode.getKey().equals(word)) {
                    descendNode.addValue(value);
                    return;
                }
            }
            updateValue(descendNode, word, value, newIndex);
        }
    }
//<:()<-<
}
