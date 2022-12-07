package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

public class SurpriseTest {
    
    Surprise surprise;
    
    @Before
    public void setUp() {
        surprise = new Surprise();
    }
    
    @Test
    public void test() {
        System.out.println(surprise.countChanges("mkmmkmkk", 3));
    }
}
