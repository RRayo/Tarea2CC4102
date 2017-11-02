package TernaryTree;

import java.util.ArrayList;

public class ABTNullNode implements IABTNode{
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public int descend(char w) {
        return 0;
    }

    @Override
    public char getChar() {
        return '\u0000';
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public ArrayList<Integer> getValue() {
        return null;
    }


    @Override
    public void setKey(String key, int value) {

    }

    @Override
    public IABTNode getSon(int i) {
        return null;
    }

    @Override
    public IABTNode updateNode(String word, int index) {
        return null;
    }

    @Override
    public void printTree() {

    }

    @Override
    public void addValue(int v) {

    }

}
