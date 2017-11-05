package PatriciaTrie;

public class PTEdge implements Comparable<PTEdge> {
    String word;
    INode node;

    public PTEdge(String word, INode node) {
        this.word = word;
        this.node = node;
    }

    public void setNodeFather(INode node) {
        this.node.setFather(node);
    }

    public boolean prefixOf(String word) {
        return word.startsWith(this.word);
    }

    @Override
    public int compareTo(PTEdge edge) {
        int result = this.word.compareTo(edge.word);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getSize(){
        return 16 + 2*this.word.length() + 8;
        //16 base + 2 bytes por caracter + 8 de referencia nodo + tamaño del nodo
    }
}