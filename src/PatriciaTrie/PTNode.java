package PatriciaTrie;

import java.util.TreeSet;

public class PTNode implements INode{

        TreeSet<PTEdge> sons = new TreeSet<PTEdge>();
        INode father;


        //node
        public PTNode(INode father){
            this.father = father;
        }

        public PTNode() {}

        @Override
        public boolean isLeaf() {
            return false;
        }

        @Override
        public void setFather(INode father) {
            this.father = father;
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


    public void addSon(PTEdge sonEdge) {
        this.sons.add(sonEdge);
        sonEdge.setNodeFather(this);
            }

    @Override
    public INode getFather() {
        return this.father;
    }

    @Override
    public TreeSet<PTEdge> getSons() {
        return this.sons;
    }

    @Override
    public void removeSon(PTEdge edge) {
        this.sons.remove(edge);
    }

   /* public void printTree() {
        for (PTEdge son : sons) {
            System.out.println("Node Edge: " + son.word);
            son.node.printTree();
        }
    }*/

    public PTEdge descendRoot(String word, int i){
    String compare = word.substring(i, word.length());
        for (PTEdge edge : sons){
        if (!PatriciaTrie.LCP(word, edge.word).equals("")){
            return edge;
        }
    }
    //No puede descender por ninguna de las hojas.
        return null;
}



}
