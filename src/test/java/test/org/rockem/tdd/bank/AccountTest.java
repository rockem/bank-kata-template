package test.org.rockem.tdd.bank;

import org.junit.Before;
import org.junit.Test;
import org.rockem.tdd.bank.Account;
import org.rockem.tdd.bank.Clock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Clock clock = mock(Clock.class);
    private final Account account = new Account(clock);

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(out));
        when(clock.now()).thenReturn("28/11/2016");
    }

    @Test
    public void depositSpecificAmount() throws Exception {
        account.deposit(500);
        verifyStatementIs(
                "DATE       | AMOUNT | BALANCE",
                "28/11/2016 | 500    | 500"
        );
    }

    private void verifyStatementIs(String... lines) {
        account.printStatement();
        assertThat(out.toString(), is(String.join("\n", Arrays.asList(lines)) + "\n"));
    }

    @Test
    public void multipleDeposits() throws Exception {
        account.deposit(500);
        account.deposit(200);
        verifyStatementIs(
                "DATE       | AMOUNT | BALANCE",
                "28/11/2016 | 500    | 500",
                "28/11/2016 | 200    | 700"
        );
    }

    @Test
    public void withdrawSpecificAmount() throws Exception {
        account.deposit(600);
        account.withdraw(300);
        verifyStatementIs(
                "DATE       | AMOUNT | BALANCE",
                "28/11/2016 | 600    | 600",
                "28/11/2016 | -300   | 300"
        );
    }

    @Test
    public void transactionsWithDifferentDates() throws Exception {
        when(clock.now()).thenReturn("01/12/2016");
        account.deposit(600);
        when(clock.now()).thenReturn("14/03/2016");
        account.withdraw(300);
        verifyStatementIs(
                "DATE       | AMOUNT | BALANCE",
                "01/12/2016 | 600    | 600",
                "14/03/2016 | -300   | 300"
        );
    }

}
