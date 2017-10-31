import junit.framework.TestCase;

class PTNodeTest {
    @org.junit.jupiter.api.Test
    void isLeaf() {
        INode leaf = new PTLeaf();
        TestCase.assertTrue("leaf should be a leaf",leaf.isLeaf());

        INode node = new PTNode();
        TestCase.assertFalse("node shouldn't be a leaf", node.isLeaf());

        node.addSon(new PTEdge("",leaf));
        TestCase.assertFalse("node shouldn't be a leaf anymore", node.isLeaf());

        INode root = new PTRoot();
        TestCase.assertFalse("root shouldn't be a leaf", root.isLeaf());

    }

    @org.junit.jupiter.api.Test
    void descend() {
        PTRoot testRootNode = new PTRoot();

        INode testleafLeft = new PTLeaf("romane" , 1); //omane
        PTEdge testEdgeLeft = new PTEdge("romane",testleafLeft);

        INode testleafRight = new PTLeaf("rubber" , 2); //ubber
        PTEdge testEdgeRight = new PTEdge("ubber",testleafRight);


        testRootNode.addSon(testEdgeLeft);
        testRootNode.addSon(testEdgeRight);

        TestCase.assertNotNull("romane should descend to left node, it gives null instead",
                testRootNode.descend("romane", 1));

        TestCase.assertSame("romane should descend to left node",testEdgeLeft.word,
                testRootNode.descend("romane", 1).word);

        TestCase.assertSame("Rub should descend to right node",testEdgeRight.word,
                testRootNode.descend("rubber", 1).word);

        TestCase.assertNull("Blah shouldn't descend",testRootNode.descend("blah", 1));
    }

}