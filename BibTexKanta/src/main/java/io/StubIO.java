package io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private List<String> lines;
    private int i;
    private ArrayList<String> prints;
    private String print;
    private String testString;
    private boolean testStringPrinted;

    public StubIO(List<String> values) {
        this.lines = values;
        prints = new ArrayList<>();
        testStringPrinted = false;
        this.testString = "";
    }
    
    public void setTestString(String testString) {
        this.testString = testString;
    }
    
    public boolean testHasBeenPrinted() {
        if (testStringPrinted)
            return true;
        return false;
    }

    public void print(String toPrint) {
        prints.add(toPrint);
        print = toPrint;
        if (toPrint.matches(testString))
            testStringPrinted = true;
    }

    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(lines.get(i++));
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    public String getLastPrint() {
        return print;
    }

    public String readLine(String prompt) {
        print(prompt);
        if (i < lines.size()) {
            return lines.get(i++);
        }
        return "";
    }
}
