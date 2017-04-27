package io;

import java.util.Scanner;

public class ConsoleIO implements IO {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public int readInt(String prompt) {
        int response;
        System.out.println(prompt);
        try {
            response = Integer.parseInt(scanner.nextLine());
            return response;
        } catch (Exception e) {
            return -1;
        }
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public void printLines(String[] lines) {
        for (String line : lines) {
            print(line);
        }
    }

}
