package pl.edu.pw.ee;

public class Surprise {
    public int countChanges(String row, int k) {
        
       int len = 0;
       int indexStart = 0;
       
       int maxLen = 0;
       int maxIndexStart = 0;
        
       for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == 'm') {
                if (len == 0) {
                    indexStart = i;
                }
                len++;
            } else {
                if (len > maxLen) {
                    maxLen = len;
                    maxIndexStart = indexStart;
                    len = 0;
                }
            }
       }
            
            if (len > maxLen) {
                    maxLen = len;
                    maxIndexStart = indexStart;
            }
            
            if (maxLen > k) {
                if (maxLen % k == 0) {
                    return maxLen / k - 1;
                } else {
                    return maxLen / k;
                }
            } else {
                int diff = k - maxLen;
                int indexLeft = maxIndexStart - 1;
                int changesLeft = 0;
                
                while (diff > 0) {
                    if (indexLeft >= 0) {
                        if (row.charAt(indexLeft) == 'k') {
                            changesLeft++;
                            diff--; 
                        }
                        else if (row.charAt(indexLeft) == 'm') {
                            diff--;
                        }
                        indexLeft--;
                    } else break;
                }
                if (row.charAt(indexLeft) == 'm') {
                    changesLeft++;
                }
                if (diff != 0) {
                    changesLeft = 0;
                }
                
                diff = k - maxLen;
                int indexRight = maxIndexStart + maxLen + 1;
                int changesRight = 0;
                
                while (diff > 0) {
                    if (indexRight < row.length()) {
                        if (row.charAt(indexRight) == 'k') {
                            changesLeft++;
                            diff--; 
                        }
                        else if (row.charAt(indexRight) == 'm') {
                            diff--;
                        }
                        indexRight++;
                    } else break;
                }
                if (row.charAt(indexRight) == 'm') {
                    changesRight++;
                }
                if (diff != 0) {
                    changesRight = 0;
                }
                
                if (changesRight == 0 && changesLeft == 0) {
                    return -1;
                }
       return changesLeft > changesRight ? changesLeft : changesRight;
    }
}
}
       
