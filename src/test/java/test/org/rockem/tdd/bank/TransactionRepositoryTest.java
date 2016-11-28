package test.org.rockem.tdd.bank;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.rockem.tdd.bank.Clock;
import org.rockem.tdd.bank.Transaction;
import org.rockem.tdd.bank.TransactionRepository;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionRepositoryTest {

    private final Clock clock = mock(Clock.class);
    private final TransactionRepository transactionRepository = new TransactionRepository(clock);

    @Test
    public void addTransactionsWithCurrentDay() throws Exception {
        when(clock.now()).thenReturn("12/03/2011");
        transactionRepository.add(20);
        transactionRepository.add(10);
        assertThat(transactionRepository, IsIterableContainingInOrder.contains(
                new Transaction(20, "12/03/2011"),
                new Transaction(10, "12/03/2011")
        ));
    }
}