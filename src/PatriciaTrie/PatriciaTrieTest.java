package PatriciaTrie;

import org.junit.jupiter.api.Test;

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
        assertEquals(1,ptTest.buscar("romane").get(0).intValue(), "Should've return 1, as romane is in the left node");

        assertEquals(2,ptTest.buscar("rubber").get(0).intValue(), "Should've return 1, as rubber is in the right node");

        assertTrue(ptTest.buscar("blah").isEmpty(), "Should've return -1, as blah is not in the trie");
    }


/*
    @Test
    void firstLeaf() {
        PTRoot testRootNode = new PTRoot();
        PatriciaTrie.INode testleafLeft = new PatriciaTrie.PTLeaf("romane$" , 1); //omane
        PatriciaTrie.PTEdge testEdgeLeft = new PatriciaTrie.PTEdge("romane$",testleafLeft);
        PatriciaTrie.INode testleafRight = new PatriciaTrie.PTLeaf("rubber$" , 2); //ubber
        PatriciaTrie.PTEdge testEdgeRight = new PatriciaTrie.PTEdge("rubber$",testleafRight);

        testRootNode.addSon(testEdgeLeft);
        testRootNode.addSon(testEdgeRight);

        PatriciaTrie.PatriciaTrie ptTest = new PatriciaTrie.PatriciaTrie(testRootNode);

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

        ptTest.insertar("romane",1);
        assertEquals(1,ptTest.buscar("romane").get(0).intValue(), "Should've return 1, as romane is in the tree");

        ptTest.insertar("romanus",2);
        assertEquals(2,ptTest.buscar("romanus").get(0).intValue(), "Should've return 2, as romanus is in the tree");

        ptTest.insertar("romulus",3);
        assertEquals(3,ptTest.buscar("romulus").get(0).intValue(), "Should've return 3, as romulus is in the tree");

        ptTest.insertar("rubens",4);
        assertEquals(4,ptTest.buscar("rubens").get(0).intValue(), "Should've return 4, as rubens is in the tree");

        ptTest.insertar("ruber",5);
        assertEquals(5,ptTest.buscar("ruber").get(0).intValue(), "Should've return 5, as ruber is in the tree");

        ptTest.insertar("rubicon",6);
        assertEquals(6,ptTest.buscar("rubicon").get(0).intValue(), "Should've return 6, as rubicon is in the tree");

        ptTest.insertar("rubicundus",7);
        assertEquals(7,ptTest.buscar("rubicundus").get(0).intValue(), "Should've return 7, as rubicundus is in the tree");

        ptTest.insertar("zar",8);
        assertEquals(8,ptTest.buscar("zar").get(0).intValue(), "Should've return 8, as zar is in the tree");

        ptTest.insertar("zarajevo", 9);
        assertEquals(9,ptTest.buscar("zarajevo").get(0).intValue(), "Should've return 9, as zar is in the tree");

        ptTest.insertar("hilter",10);
        assertEquals(10,ptTest.buscar("hilter").get(0).intValue(), "Should've return 10, as hilter is in the tree");

        ptTest.insertar("himler",11);
        assertEquals(11,ptTest.buscar("himler").get(0).intValue(), "Should've return 11, as himler is in the tree");

        ptTest.insertar("romane",12);
        assertEquals(12,ptTest.buscar("romane").get(1).intValue(), "Should've return 12, as romane is twice in the tree");

        ptTest.insertar("hilter",13);
        assertEquals(13,ptTest.buscar("hilter").get(1).intValue(), "Should've return 13, as hilter is twice  in the tree");
    }

}