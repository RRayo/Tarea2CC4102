package PatriciaTrie;

import Dictionary.Diccionario;

import java.util.ArrayList;
import java.util.TreeSet;

public class PTLeaf implements INode{

    Diccionario diccionario = new Diccionario();

    INode father;

    public PTLeaf(){}

    public PTLeaf(String key, int value) {
        this.diccionario = new Diccionario(key, value);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public void setFather(INode father) {
        this.father = father;
    }

    @Override
    public PTEdge descend(String word, int index) {
        //no puede descender
        return null;
    }

    @Override
    public void addSon(PTEdge edge) {

    }

    @Override
    public INode getFather() {
        return this.father;
    }

    @Override
    public TreeSet<PTEdge> getSons() {
        return null;
    }

    @Override
    public void removeSon(PTEdge edge) {
    }

    public String getKey(){
        return this.diccionario.key;
    }

    public void addValue(int v) {
        this.diccionario.add(v);
    }

    public ArrayList<Integer> getValues(){
        return this.diccionario.valor;
    }
    /*@Override
    public void printTree() {
        System.out.println("Leaf: key: " + key + " value :" + value);
    }
*/

}
