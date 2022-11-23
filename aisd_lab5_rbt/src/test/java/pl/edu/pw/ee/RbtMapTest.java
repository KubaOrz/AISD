package pl.edu.pw.ee;

import org.junit.Test;

public class RbtMapTest {

	@Test
        public void test() {
            RbtMap<Integer, String> map = new RbtMap();
            
            map.setValue(0, "abc");
            map.getValue(0);
            
            try {
                map.setValue(null, null);
            } catch(Exception e) {
                
            }
            
            try {
                map.setValue(null, "abc");
            } catch(Exception e) {
                
            }
            
            try {
                map.setValue(0, null);
            } catch(Exception e) {
                
            }
            
            try {
                map.getValue(null);
            } catch(Exception e) {
                
            }
        }

}
