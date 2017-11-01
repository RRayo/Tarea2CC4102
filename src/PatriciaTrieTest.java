import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatriciaTrieTest {

    @Test
    void Rsearch() {
        PTNode testRootNode = new PTNode();
        INode testleafLeft = new PTLeaf("romane$" , 1); //omane
        PTEdge testEdgeLeft = new PTEdge("romane$",testleafLeft);
        INode testleafRight = new PTLeaf("rubber$" , 2); //ubber
        PTEdge testEdgeRight = new PTEdge("rubber$",testleafRight);

        testRootNode.addSon(testEdgeLeft);
        testRootNode.addSon(testEdgeRight);


        PatriciaTrie ptTest = new PatriciaTrie(testRootNode);
        assertEquals(1,ptTest.search("romane"), "Should've return 1, as romane is in the left node");

        assertEquals(2,ptTest.search("rubber"), "Should've return 1, as rubber is in the right node");

        assertEquals(-1,ptTest.search("blah"), "Should've return -1, as blah is not in the trie");
    }


/*
    @Test
    void firstLeaf() {
        PTRoot testRootNode = new PTRoot();
        INode testleafLeft = new PTLeaf("romane$" , 1); //omane
        PTEdge testEdgeLeft = new PTEdge("romane$",testleafLeft);
        INode testleafRight = new PTLeaf("rubber$" , 2); //ubber
        PTEdge testEdgeRight = new PTEdge("rubber$",testleafRight);

        testRootNode.addSon(testEdgeLeft);
        testRootNode.addSon(testEdgeRight);

        PatriciaTrie ptTest = new PatriciaTrie(testRootNode);

        assertEquals("romane$",ptTest.firstLeaf(testRootNode).key, "Should've return romane as root has a left node that is a leaf");

        assertEquals("rubber$",ptTest.firstLeaf(testleafRight).key, "Should've return \"rubber$\", as right node is a leaf");

        testRootNode.sons.remove(testEdgeLeft);

        assertEquals("rubber$",ptTest.firstLeaf(testRootNode).key, "Should've return \"rubber$\", as root does not have a left node");
    }
*/
    @Test
    void LCP() {
        PTNode testRootNode = new PTNode();
        PatriciaTrie ptTest = new PatriciaTrie(testRootNode);
        assertEquals("rub", ptTest.LCP("rubber","rub"), "Should've return rub");
        assertEquals("", ptTest.LCP("rubber","jerry"), "Should've return empty");
        assertEquals("", ptTest.LCP("","jerry"), "Should've return empty");
    }


    @Test
    void insert() {
        PTNode testRootNode = new PTNode();
        PatriciaTrie ptTest = new PatriciaTrie(testRootNode);

        ptTest.insert("romane",1);
        assertEquals(1,ptTest.search("romane"), "Should've return 1, as romane is in the tree");

        ptTest.insert("romanus",2);
        assertEquals(2,ptTest.search("romanus"), "Should've return 2, as romanus is in the tree");

        ptTest.insert("romulus",3);
        assertEquals(3,ptTest.search("romulus"), "Should've return 3, as romulus is in the tree");

        ptTest.insert("rubens",4);
        assertEquals(4,ptTest.search("rubens"), "Should've return 4, as rubens is in the tree");

        ptTest.insert("ruber",5);
        assertEquals(5,ptTest.search("ruber"), "Should've return 5, as ruber is in the tree");

        ptTest.insert("rubicon",6);
        assertEquals(6,ptTest.search("rubicon"), "Should've return 6, as rubicon is in the tree");

        ptTest.insert("rubicundus",7);
        assertEquals(7,ptTest.search("rubicundus"), "Should've return 7, as rubicundus is in the tree");

        ptTest.insert("zar",8);
        assertEquals(8,ptTest.search("zar"), "Should've return 8, as zar is in the tree");

        ptTest.insert("zarajevo", 9);
        assertEquals(9,ptTest.search("zarajevo"), "Should've return 9, as zar is in the tree");

        ptTest.insert("hilter",10);
        assertEquals(10,ptTest.search("hilter"), "Should've return 10, as hilter is in the tree");

        ptTest.insert("himler",11);
        assertEquals(11,ptTest.search("himler"), "Should've return 11, as himler is in the tree");
    }

}