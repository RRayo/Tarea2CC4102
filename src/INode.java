public interface INode {
     boolean isLeaf();
     void setFather(INode father);
     PTEdge descend(String word, int index);
     void addSon(PTEdge edge);
     INode getFather();
}
