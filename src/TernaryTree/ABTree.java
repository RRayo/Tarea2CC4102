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
        IABTNode result = Isearch(root, s, 0);
        if(!result.getKey().equals(s)){
            return new ArrayList<Integer>();
        } else {
            return result.getValue();
        }
    }

    public static IABTNode Isearch (IABTNode node, String word, int index) {
        while(!(index == word.length())) {
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

    @Override
    public void insertar(String s, int posc) {
        //System.out.println(s);
        if(root.isNull()) {
            root = new ABTNode(s.charAt(0));
        }
        IABTNode searchResult = Isearch(root, s, 0);
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
        while (!(index == subString.length() - 1 && node.getChar() == subString.charAt(index))) {
            int descend = node.descend(subString.charAt(index));
            IABTNode descendNode = node.getSon(descend);
            if (descendNode.isNull()) {
                node = node.updateNode(subString, index);
                if (descend == 1) {
                    index += 1;
                }
            } else {
                node = descendNode;
            }
        }
        node.setKey(word, value);
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
        while(!(index == word.length())) {
            IABTNode descendNode = node.getSon(node.descend(word.charAt(index)));
            int newIndex = index;
            if (node.getChar() == word.charAt(index)) {
                newIndex += 1;
                if (descendNode.getKey().equals(word)) {
                    descendNode.addValue(value);
                    return;
                }
            }
            index = newIndex;
            node = descendNode;
        }
        node.addValue(value);
    }
//<:()<-<
}
