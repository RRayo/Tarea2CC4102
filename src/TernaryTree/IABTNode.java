package TernaryTree;

import java.util.ArrayList;

public interface IABTNode {
    boolean isNull();
    int descend(char w);
    char getChar();
    String getKey();
    ArrayList<Integer> getValue();
    void setKey (String key, int value);
    IABTNode getSon (int i);
    IABTNode updateNode(String word, int index);
    void printTree();
    void addValue(int v);
    int getSize();
}
