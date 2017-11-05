package PatriciaTrie;

import java.util.Stack;
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

    @Override
    public int getSize() {
        Stack<INode> stack = new Stack<>();
        INode node;
        stack.push(this);
        int totalSize = 0;
        while(!stack.empty()) {
            node = stack.pop();
            if(!node.isLeaf()){
                totalSize += 24; //tamaño base + puntero a padre
                PTNode ptnode = (PTNode)node;
                for(PTEdge edge : ptnode.sons){
                    totalSize += 4; //costo de un puntero
                    totalSize += edge.getSize();
                    if(edge.node.isLeaf()){
                        totalSize += edge.node.getSize();
                    } else {
                        stack.push(edge.node);
                    }
                }
            } else { //es hoja
                totalSize += node.getSize();
            }
        }
        return totalSize;
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
