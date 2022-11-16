package pl.edu.pw.ee;

public class ElemEqualHashCode implements Comparable<ElemEqualHashCode> {

    public int value;

    ElemEqualHashCode(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ElemEqualHashCode) {
            ElemEqualHashCode comparedObj = (ElemEqualHashCode) obj;
            return value == comparedObj.value;
        }
        return false;
    }

    @Override
    public int compareTo(ElemEqualHashCode o) {
        return o.value - value;
    }
}
