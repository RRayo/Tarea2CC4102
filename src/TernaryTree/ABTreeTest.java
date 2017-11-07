package TernaryTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ABTreeTest {
    @Test
    void rsearch() {
        ABTNode testRoot = new ABTNode('i');

        ABTNode leftNode = new ABTNode('b');
        leftNode.setKey("b",1);

        ABTNode centralNode = new ABTNode('s');
        centralNode.setKey("is",2);

        ABTNode rightNode = new ABTNode('o');
        rightNode.setKey("o",3);

        testRoot.sons.set(0, leftNode);
        testRoot.sons.set(1, centralNode);
        testRoot.sons.set(2, rightNode);

        ABTree testTree = new ABTree(testRoot);

        assertEquals(testTree.buscar("b").get(0).intValue(), 1, "The search should've return 1, as ib is in the trie");
        assertEquals(testTree.buscar("is").get(0).intValue(), 2, "The search should've return 2, as is is in the trie");
        assertEquals(testTree.buscar("o").get(0).intValue(), 3, "The search should've return 3, as io is in the trie");
        assertTrue(testTree.buscar("z").isEmpty(), "The search should've return -1, as z isn't in the trie");

    }

    @Test
    void rinsert() {

        /*IABTNode testRoot = new ABTNullNode();
        ABTree testTree = new ABTree();
        testTree.insertar("b", 1);
        assertEquals(testTree.buscar("b").get(0).intValue(), 1,"The search should've return 1, as b is in the trie");
        assertEquals('b', ABTree.root.getChar(),"The root should have character 'b'");

        testTree = new ABTree(testRoot);
        System.out.println(testTree.getSize());

        testTree.insertar("is", 1);

        //ABTree.root.printTree();

        assertEquals(testTree.buscar("is").get(0).intValue(), 1,"The search should've return 1, as 'is' is in the trie");
        assertEquals('i', ABTree.root.getChar(),"The root should have character 'i'");
        assertEquals('s', ABTree.root.getSon(1).getChar(),"The middle son should have the character 's'");

        testTree.insertar("it", 2);
        assertEquals(testTree.buscar("it").get(0).intValue(), 2,"The search should've return 2, as 'it' is in the trie");

        testTree.insertar("in", 3);
        assertEquals(testTree.buscar("in").get(0).intValue(), 3,"The search should've return 3, as 'in' is in the trie");

        testTree.insertar("be", 4);
        assertEquals(testTree.buscar("be").get(0).intValue(), 4,"The search should've return 4, as 'be' is in the trie");

        testTree.insertar("by", 5);
        assertEquals(testTree.buscar("by").get(0).intValue(), 5,"The search should've return 5, as 'by' is in the trie");

        testTree.insertar("as", 6);
        assertEquals(testTree.buscar("as").get(0).intValue(), 6,"The search should've return 6, as 'as' is in the trie");

        testTree.insertar("at", 7);
        assertEquals(testTree.buscar("at").get(0).intValue(), 7,"The search should've return 7, as 'at' is in the trie");

        testTree.insertar("he", 8);
        assertEquals(testTree.buscar("he").get(0).intValue(), 8,"The search should've return 8, as 'he' is in the trie");

        testTree.insertar("on", 9);
        assertEquals(testTree.buscar("on").get(0).intValue(), 9,"The search should've return 9, as 'on' is in the trie");

        testTree.insertar("of", 10);
        assertEquals(testTree.buscar("of").get(0).intValue(), 10,"The search should've return 10, as 'of' is in the trie");

        testTree.insertar("or", 11);
        assertEquals(testTree.buscar("or").get(0).intValue(), 11,"The search should've return 11, as 'or' is in the trie");

        testTree.insertar("to", 12);
        assertEquals(testTree.buscar("to").get(0).intValue(), 12,"The search should've return 12, as 'to' is in the trie");

        testTree.insertar("to", 13);
        assertEquals(testTree.buscar("to").get(1).intValue(), 13,"The search should've return 13, as 'to' is twice in the trie");

        testTree.insertar("to", 14);
        assertEquals(testTree.buscar("to").get(2).intValue(), 14,"The search should've return 13, as 'to' is three times on the trie");

        testTree.insertar("ii", 15);
        assertEquals(testTree.buscar("ii").get(0).intValue(), 15,"The search should've return 15, as 'ii' is in the trie");
*/

        ABTree testTree = new ABTree();
        testTree.insertar("shrek$", 1);
        testTree.insertar("written$", 2);
        testTree.insertar("by$", 3);
        testTree.insertar("william$", 4);
        testTree.insertar("steig$", 5);
        testTree.insertar("ted$", 6);
        testTree.insertar("elliott$", 7);
        testTree.insertar("once$", 8);
        testTree.insertar("upon$", 9);
        testTree.insertar("a$", 10);
        testTree.insertar("time$", 11);
        testTree.insertar("there$", 12);
        testTree.insertar("was$", 13);
        testTree.insertar("lovely$", 14);
        testTree.insertar("princess$", 15);
        testTree.insertar("but$", 16);
        testTree.insertar("she$", 17);//
        testTree.insertar("had$", 18);
        testTree.insertar("an$", 19);
        testTree.insertar("enchantment$", 20);
        testTree.insertar("her$", 21);
        testTree.insertar("of$", 22);
        testTree.insertar("fearful$", 23);
        testTree.insertar("sort$", 24);
        testTree.insertar("which$", 25);
        testTree.insertar("could$", 26);
        testTree.insertar("only$", 27);
        testTree.insertar("be$", 28);
        testTree.insertar("broken$", 29);
        testTree.insertar("love$", 30);
        testTree.insertar("s$", 31);
        testTree.insertar("first$", 32);
        testTree.insertar("kiss$", 33);

        testTree.root.printTree();

        testTree.insertar("she$", 34);


        testTree.root.printTree();

        System.out.println(testTree.getSize());


    }

}