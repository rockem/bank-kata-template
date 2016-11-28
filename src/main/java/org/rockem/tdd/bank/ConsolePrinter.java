package org.rockem.tdd.bank;

public class ConsolePrinter implements Printer {

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }
}
