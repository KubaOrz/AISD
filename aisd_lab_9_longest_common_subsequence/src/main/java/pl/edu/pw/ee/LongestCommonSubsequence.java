package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private static final int CELL_WIDTH = 5;
    private final String topStr;
    private final String leftStr;

    private final int topSize;
    private final int leftSize;

    private final Elem[][] tab;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateInput(topStr, leftStr);
        this.topStr = topStr;
        this.leftStr = leftStr;
        this.topSize = topStr.length() + 1;
        this.leftSize = leftStr.length() + 1;
        this.tab = new Elem[leftSize][topSize];
        initializeTab();
    }

    private void validateInput(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("String cannot be null!");
        }
    }

    private void initializeTab() {
        for (int i = 0; i < topSize; i++) {
            tab[0][i] = new Elem(0);
        }

        for (int i = 0; i < leftSize; i++) {
            tab[i][0] = new Elem(0);
        }
    }

    public String findLCS() {
        fillLcsTable();

        StringBuilder subSequence = new StringBuilder();
        int i = leftSize - 1;
        int j = topSize - 1;
        while (tab[i][j].getNum() != 0) {
            Elem current = tab[i][j];
            current.setBelongToSubsequence(true);
            int prevX = current.getPrevHorizontalIndex();
            int prevY = current.getPrevVerticalIndex();
            if (prevX == j - 1 && prevY == i - 1) {
                subSequence.insert(0, leftStr.charAt(i - 1));
            }
            j = prevX;
            i = prevY;
        }
        return subSequence.toString();
    }

    private void fillLcsTable() {
        int newElemNum, prevHIndex, prevVIndex;
        for (int vIndex = 1; vIndex < leftSize; vIndex++) {
            for (int hIndex = 1; hIndex < topSize; hIndex++) {
                if (topStr.charAt(hIndex - 1) == leftStr.charAt(vIndex - 1)) {
                    newElemNum = tab[vIndex - 1][hIndex - 1].getNum() + 1;
                    prevHIndex = hIndex - 1;
                    prevVIndex = vIndex - 1;
                } else if (tab[vIndex][hIndex - 1].getNum() > tab[vIndex - 1][hIndex].getNum()) {
                    newElemNum = tab[vIndex][hIndex - 1].getNum();
                    prevHIndex = hIndex - 1;
                    prevVIndex = vIndex;
                } else {
                    newElemNum = tab[vIndex - 1][hIndex].getNum();
                    prevHIndex = hIndex;
                    prevVIndex = vIndex - 1;
                }
                tab[vIndex][hIndex] = new Elem(newElemNum, prevHIndex, prevVIndex);
            }
        }
    }

    public void display() {
        drawHorizontalBorder();
        for (int i = 0; i <= leftSize; i++) {
            drawFirstRow(i);
            drawSecondRow(i);
            drawThirdRow();
            drawHorizontalBorder();
        }
    }

    private void drawHorizontalBorder() {
        System.out.println("+" +
                "-".repeat(CELL_WIDTH + 2) + "+" +
                ("-".repeat(CELL_WIDTH) + "+").repeat(topSize));
    }

    private void drawFirstRow(int verticalIndex) {
        StringBuilder firstRow = new StringBuilder("|" + centerString(CELL_WIDTH + 2, ""));
        for (int i = 0; i < topSize; i++) {
            if (verticalIndex == 0) {
                firstRow.append(centerString(CELL_WIDTH, ""));
            } else {
                Elem currentElem = tab[verticalIndex - 1][i];
                firstRow.append(drawFirstRowCell(currentElem, verticalIndex - 1, i));
            }
        }
        System.out.println(firstRow);
    }

    private void drawSecondRow(int verticalIndex) {
        StringBuilder secondRow = new StringBuilder();
        if (verticalIndex < 2) {
            secondRow.append("|");
            secondRow.append(centerString(CELL_WIDTH + 2, ""));
        } else {
            String charToWrite = String.valueOf(leftStr.charAt(verticalIndex - 2));
            secondRow.append("|");
            secondRow.append(centerString(CELL_WIDTH + 2, convertChar(charToWrite)));
        }

        for (int i = 0; i < topSize; i++) {
            if (verticalIndex == 0) {
                if (i == 0) {
                    secondRow.append(centerString(CELL_WIDTH, ""));
                } else {
                    String charToWrite = String.valueOf(topStr.charAt(i - 1));
                    secondRow.append(centerString(CELL_WIDTH, convertChar(charToWrite)));
                }
            } else {
                Elem currentElem = tab[verticalIndex - 1][i];
                secondRow.append(drawSecondRowCell(currentElem, verticalIndex - 1, i));
            }
        }
        System.out.println(secondRow);
    }

    private void drawThirdRow() {
        System.out.println("|" +
                centerString(CELL_WIDTH + 2, "") +
                centerString(CELL_WIDTH, "").repeat(topSize)
        );
    }

    private String drawFirstRowCell(Elem elem, int verticalIndex, int horizontalIndex) {
        if (!elem.doesBelongToSubsequence()) {
            return centerString(CELL_WIDTH, "");

        } else if (elem.getPrevHorizontalIndex() == horizontalIndex && elem.getPrevVerticalIndex() == verticalIndex - 1) {
            return centerString(CELL_WIDTH, "^");

        } else if (elem.getPrevHorizontalIndex() == horizontalIndex - 1 && elem.getPrevVerticalIndex() == verticalIndex - 1) {
            return "\\" + centerString(CELL_WIDTH - 1, "");

        } else {
            return centerString(CELL_WIDTH, "");

        }
    }

    private String drawSecondRowCell(Elem elem, int verticalIndex, int horizontalIndex) {
        String numberToPrint = String.valueOf(tab[verticalIndex][horizontalIndex].getNum());
        if (!elem.doesBelongToSubsequence()) {
            return centerString(CELL_WIDTH, numberToPrint);

        }
        if (elem.getPrevHorizontalIndex() == horizontalIndex - 1 && elem.getPrevVerticalIndex() == verticalIndex) {
            return "<" + centerString(CELL_WIDTH - 1, numberToPrint);

        } else {
            return centerString(CELL_WIDTH, numberToPrint);

        }
    }

    private String centerString(int length, String toCenter) {
        int spaceToFill = length - toCenter.length();
        int prefixSize = (spaceToFill + 1) / 2;
        int suffixSize = spaceToFill / 2;
        if (spaceToFill > 0) {
            return " ".repeat(prefixSize) + toCenter + " ".repeat(suffixSize) + "|";
        } else return toCenter;
    }

    private String convertChar(String character) {
        switch (character) {
            case "\n":
                return "\\n";
            case "\t":
                return "\\t";
            case "\b":
                return "\\b";
            case "\r":
                return "\\r";
            case " ":
                return "' '";
            case "\f":
                return "\\f";
            default:
                return character;
        }
    }

}
