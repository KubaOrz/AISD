package pl.edu.pw.ee;

public class Elem {
        private int num;
        private Integer prevX;
        private Integer prevY;
        
        Elem(int num, int prevX, int prevY) {
            this.num = num;
            this.prevX = prevX;
            this.prevY = prevY;
        }
        
        Elem(int num) {
            this.num = num;
        }
        
        int getNum() {
            return num;
        }
        
        int getPrevX() {
            return prevX;
        }
        
        int getPrevY() {
            return prevY;
        }
}
