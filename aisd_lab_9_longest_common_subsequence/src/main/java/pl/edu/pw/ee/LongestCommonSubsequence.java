package pl.edu.pw.ee;

class LongestCommonSubsequence {
    
    private String topStr;
    private String leftStr;
    
    int topSize;
    int leftSize;
    
    private Elem [][] tab;

    public LongestCommonSubsequence(String topStr, String leftStr){
	this.topStr = topStr;
        this.leftStr = leftStr;
        this.topSize = topStr.length() + 1;
        this.leftSize = leftStr.length() + 1;
        this.tab = new Elem[leftSize][topSize];
        initializeTab();
    }
    
    private void initializeTab() {
        for (int i = 0; i < topSize; i++) {
            tab[0][i] = new Elem(0);
        }
        
        for (int i = 0; i < leftSize; i++) {
            tab[i][0] = new Elem(0);
        }
    }

    public String findLCS(){
	for (int i = 1; i < leftSize; i++) {
            for (int j = 1; j < topSize; j++) {
                if (topStr.charAt(j - 1) == leftStr.charAt(i - 1)) {
                    int newElemNum = tab[i - 1][j - 1].getNum() + 1;
                    Integer prevX = i - 1;
                    Integer prevY = j - 1;
                    tab[i][j] = new Elem(newElemNum, prevX, prevY);
                } else if (tab[i][j - 1].getNum() > tab[i - 1][j].getNum()) {
                    int newElemNum = tab[i][j - 1].getNum();
                    Integer prevX = i;
                    Integer prevY = j - 1;
                    tab[i][j] = new Elem(newElemNum, prevX, prevY);
                } else {
                    int newElemNum = tab[i - 1][j].getNum();
                    Integer prevX = i - 1;
                    Integer prevY = j;
                    tab[i][j] = new Elem(newElemNum, prevX, prevY);
                }
            }
        }
        String revSubSequence = "";
        int i = leftSize - 1;
        int j = topSize - 1;
        while (tab[i][j].getNum() != 0) {
            Elem current = tab[i][j];
            Integer prevX = current.getPrevX();
            Integer prevY = current.getPrevY();
            if (prevX == i - 1 && prevY == j - 1) {
                revSubSequence += topStr.charAt(i);
            }
            i = prevX;
            j = prevY;
        }
        String subSequence = "";
        for (int k = revSubSequence.length() - 1; k >= 0; k--) {
             subSequence += revSubSequence.charAt(k);
        }
        return subSequence;
    }

    public void display(){
	for (int i = 0; i < leftSize; i++) {
            for (int j = 0; j < topSize; j++) {
                System.out.print(tab[i][j].getNum() + " ");
            }
            System.out.println();
        }
    }

}
