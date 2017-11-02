package TernaryTree;

import Dictionary.Diccionario;

import java.util.ArrayList;

public class ABTNode implements IABTNode{

    private char s = '\u0000';

    ArrayList<IABTNode> sons = new ArrayList<IABTNode>() {{
        add(new ABTNullNode());
        add(new ABTNullNode());
        add(new ABTNullNode());
    }};

    //Dictionary properties
    Diccionario diccionario = new Diccionario("",-1);
    //private String key = "";
    //private int value = -1;

    public ABTNode(char s) {
        this.s = s;
    }

    public int descend (char w) {
        if (this.s == w) {
            return 1;
        } else if (w < this.s) {
            return 0;
        } else {
            return 2;
        }
    }

    public void setKey (String key, int value) {
        this.diccionario = new Diccionario(key,value);
    }

    @Override
    public IABTNode getSon(int i) {
        return this.sons.get(i);
    }

    public IABTNode updateNode(String word, int index) {
        int descendNode = this.descend(word.charAt(index));
        IABTNode newNode;
        if(descendNode == 1){
            newNode = new ABTNode(word.charAt(index+1));
        } else {
            newNode = new ABTNode(word.charAt(index));
        }
        this.sons.set(descendNode, newNode);
        return this.sons.get(descendNode);
    }

    @Override
    public void printTree() {
        System.out.println("node with char: " + this.getChar() + " and key: " + this.getKey());
        int i = 0;
        for(IABTNode node : this.sons) {
            System.out.print("Son " + i + " :");
            node.printTree();
            i++;
        }
    }

    @Override
    public void addValue(int v) {
        this.diccionario.add(v);
    }

    public char getChar() {
        return this.s;
    }

    public String getKey() {
        return this.diccionario.key;
    }

    public ArrayList<Integer> getValue() {
        return this.diccionario.valor;
    }

    public boolean isNull(){
        return false;
    }
}
