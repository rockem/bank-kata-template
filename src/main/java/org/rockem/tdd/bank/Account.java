package org.rockem.tdd.bank;

public class Account {

    private final TransactionRepository transactionRepository;
    private final Printer printer = new ConsolePrinter();

    public Account(Clock clock) {
        transactionRepository = new TransactionRepository(clock);
    }

    public void deposit(int amount) {
        transactionRepository.add(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.add(-amount);
    }

    public void printStatement() {
        new StatementPrinter(printer).print(transactionRepository);
    }

}
