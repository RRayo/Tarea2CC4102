import java.util.HashSet;
import java.util.Set;

public class PTRoot implements INode {

    Set<PTEdge> sons = new HashSet<PTEdge>();

    public PTRoot() {}

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setFather(INode father) { }

    public void addSon(PTEdge sonEdge) {
        this.sons.add(sonEdge);
        sonEdge.setNodeFather(this);
    }

    @Override
    public INode getFather() {
        return null;
    }


    @Override
    public PTEdge descend(String word, int index) {
        String compare = word.substring(index, word.length());
        for (PTEdge edge : sons){
            if (edge.prefixOf(compare)){
                return edge;
            }
        }
        //No puede descender por ninguna de las hojas.
        return null;
    }
}
