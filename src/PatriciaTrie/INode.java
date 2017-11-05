package PatriciaTrie;

import java.util.TreeSet;

public interface INode {
     boolean isLeaf();
     void setFather(INode father);
     PTEdge descend(String word, int index);
     void addSon(PTEdge edge);
     INode getFather();
     TreeSet<PTEdge> getSons();
     void removeSon(PTEdge edge);
     int getSize();
     void printTree();
}
