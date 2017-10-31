public class PTEdge {
    String word;
    INode node;

    public PTEdge(String word, INode node) {
        this.word = word;
        this.node = node;
    }

    public void setNodeFather(INode node) {
        this.node.setFather(node);
    }

    public boolean prefixOf (String word) {
        return word.startsWith(this.word);
    }
}
