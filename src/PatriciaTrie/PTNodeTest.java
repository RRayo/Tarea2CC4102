package PatriciaTrie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PTNodeTest {
    @Test
    void isLeaf() {
        INode leaf = new PTLeaf();
        assertTrue(leaf.isLeaf(), "leaf should be a leaf");

        INode node = new PTNode();
        assertFalse(node.isLeaf(), "node shouldn't be a leaf");

        node.addSon(new PTEdge("",leaf));
        assertFalse(node.isLeaf(), "node shouldn't be a leaf anymore");

        INode root = new PTNode();
        assertFalse(root.isLeaf(), "root shouldn't be a leaf");

    }

    @Test
    void descend() {
        PTNode testRootNode = new PTNode();

        INode testleafLeft = new PTLeaf("romane" , 1); //omane
        PTEdge testEdgeLeft = new PTEdge("romane",testleafLeft);

        INode testleafRight = new PTLeaf("rubber" , 2); //ubber
        PTEdge testEdgeRight = new PTEdge("rubber",testleafRight);


        testRootNode.addSon(testEdgeLeft);
        testRootNode.addSon(testEdgeRight);

        assertNotNull(testRootNode.descend("romane", 1), "romane should descend to left node, it gives null instead");

        assertSame(testEdgeLeft.word,
                testRootNode.descend("romane", 1).word, "romane should descend to left node");

        /*
        TestCase.assertSame("Rub should descend to right node",testEdgeRight.word,
                testRootNode.descend("rubber", 1).word);
          */
        assertNull(testRootNode.descend("blah", 1), "Blah shouldn't descend");
    }

}