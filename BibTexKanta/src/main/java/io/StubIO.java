package io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private final List<String> linesToPrint;
    private final ArrayList<String> printedLines;
    private int i;

    public StubIO(List<String> lines) {
        this.linesToPrint = lines;
        printedLines = new ArrayList<>();
    }

    @Override
    public void print(String toPrint) {
        printedLines.add(toPrint);
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(linesToPrint.get(i++));
    }

    public List<String> getPrintedLines() {
        return printedLines;
    }

    public String getLastPrint() {
        return printedLines.get(printedLines.size() - 1);
    }

    public int howManyInputsRead() {
        return i;
    }

    public boolean outputsContainsLine(String line) {
        return printedLines.contains(line);
    }

    @Override
    public String readLine(String prompt) {
        print(prompt);
        if (i < linesToPrint.size()) {
            return linesToPrint.get(i++);
        }
        return "";
    }

    @Override
    public void printLines(String[] lines) {
        for (String line : lines) {
            print(line);
        }
    }
}
