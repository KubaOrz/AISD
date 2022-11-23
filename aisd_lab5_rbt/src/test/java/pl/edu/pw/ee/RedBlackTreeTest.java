package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree<Integer, String> rbt;
    
    @Before
    public void setUp() {
        rbt = new RedBlackTree<>();
    }
    
    @Test
    public void putAndPrintElements() {
        String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
        
        for (int i = 0; i < words.length; i++) {
            rbt.put(i, words[i]);
        }
        
        for (int i = 0; i < words.length; i++) {
            rbt.get(i);
        }
        
        rbt.deleteMax();
        
        System.out.println(rbt.getInOrder());
        System.out.println(rbt.getPostOrder());
        System.out.println(rbt.getPreOrder());
        
        rbt.put(1, "abc");
        rbt.put(5, "abcdef");
        rbt.put(3, "abcFWQFW");
        rbt.put(11212, "abcFSDFGHBRT");
        try {
        rbt.put(null, null);
        } catch(Exception e) {
            
        }
        try {
        rbt.put(1, null);
        } catch(Exception e) {
            
        }
        try {
        rbt.put(null, "abcd");
        } catch(Exception e) {
            
        }
    }
}
