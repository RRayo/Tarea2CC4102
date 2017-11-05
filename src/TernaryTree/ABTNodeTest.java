package TernaryTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ABTNodeTest {
    @Test
    void descend() {
        ABTNode testRoot = new ABTNode('b');
        assertEquals(1, testRoot.descend('b'),"Should have descended towards the center");
        assertEquals(0, testRoot.descend('a'),"Should have descended towards left son");
        assertEquals(2, testRoot.descend('c'),"Should have descended towards right son");
    }

    @Test
    void setKey() {
        ABTNode testRoot = new ABTNode('b');
        testRoot.setKey("key", 1);
        assertEquals(testRoot.getKey(), "key", "Should have returned 'key' as the key");
    }

    @Test
    void updateNode() {
        /*
        ABTNode testRoot = new ABTNode('b');
        IABTNode newSon = testRoot.updateNode("a", 0);
        assertEquals(newSon.getChar(), 'a',"The new son should have 'a' as key");
        assertEquals(testRoot.getSon(0).getChar(), 'a',"The root should've had a left son with char a");
        testRoot.updateNode("bs", 0);
        assertEquals(testRoot.getSon(1).getChar(), 's',"The root should've had a central son with char s");*/
    }

}