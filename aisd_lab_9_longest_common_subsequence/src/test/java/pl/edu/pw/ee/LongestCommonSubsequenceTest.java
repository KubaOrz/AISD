package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    
    private LongestCommonSubsequence lcs;
    
    @Before
    public void setUp() {
        lcs = new LongestCommonSubsequence("mikołaj", "ikona");
    }
    
    @Test
    public void test() {
        System.out.println(lcs.findLCS());
        lcs.display();
    }
}
