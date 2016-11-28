package org.rockem.tdd.bank;

public class StatementPrinter {

    public static final String LINE_FORMAT = "%-10s | %-6s | %s";

    private final Printer printer;
    private int subTotal = 0;

    public StatementPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(Iterable<Transaction> transactions) {
        printHeader();
        printTransactions(transactions);
    }

    private void printHeader() {
        printer.printLine("DATE       | AMOUNT | BALANCE");
    }

    private void printTransactions(Iterable<Transaction> transactions) {
        subTotal = 0;
        transactions.forEach(t -> {
            printer.printLine(String.format(LINE_FORMAT, t.getDate(), t.getAmount(), subTotal += t.getAmount()));
        });
    }
}
