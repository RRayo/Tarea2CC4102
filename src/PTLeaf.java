import java.util.Set;
import java.util.TreeSet;

public class PTLeaf implements INode{

    String key;
    int value;

    INode father;

    public PTLeaf(){}

    public PTLeaf(String key, int value) {
        this.key = key;
        this.value = value;
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

    @Override
    public void printTree() {
        System.out.println("Leaf: key: " + key + " value :" + value);
    }


}
