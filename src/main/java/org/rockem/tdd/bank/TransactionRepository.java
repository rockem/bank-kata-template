package org.rockem.tdd.bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionRepository implements Iterable<Transaction> {

    private final Clock clock;
    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void add(int amount) {
        transactions.add(new Transaction(amount, clock.now()));
    }

    @Override
    public Iterator<Transaction> iterator() {
        return transactions.iterator();
    }
}
