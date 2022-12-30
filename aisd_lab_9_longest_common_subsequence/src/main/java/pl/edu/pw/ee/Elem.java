package pl.edu.pw.ee;

public class Elem {
        private final int num;
        private final Integer prevHorizontalIndex;
        private final Integer prevVerticalIndex;
        private boolean belongsToSubsequence;
        
        public Elem(int num, int prevX, int prevY) {
            this.num = num;
            this.prevHorizontalIndex = prevX;
            this.prevVerticalIndex = prevY;
            this.belongsToSubsequence = false;
        }
        
        public Elem(int num) {
            this.num = num;
            this.prevHorizontalIndex = -10;
            this.prevVerticalIndex = -10;
            this.belongsToSubsequence = false;

        }
        
        public int getNum() {
            return num;
        }
        
        public int getPrevHorizontalIndex() {
            return prevHorizontalIndex;
        }
        
        public int getPrevVerticalIndex() {
            return prevVerticalIndex;
        }

        public boolean doesBelongToSubsequence() {
            return belongsToSubsequence;
        }

        public void setBelongToSubsequence(boolean doesBelong) {
            this.belongsToSubsequence = doesBelong;
        }
}
