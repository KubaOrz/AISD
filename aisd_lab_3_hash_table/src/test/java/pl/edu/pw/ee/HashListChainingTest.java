package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    
    private HashListChaining hashList;
    
    @Before
    public void setUp() {
        hashList = new HashListChaining(3);
    }
    
    @Test
    public void jakisTest() {
        Double liczba = 3.0;
        hashList.add(liczba);
        System.out.println(hashList.get(liczba));
        hashList.delete(liczba);
        System.out.println(hashList.get(liczba));
    }

}
