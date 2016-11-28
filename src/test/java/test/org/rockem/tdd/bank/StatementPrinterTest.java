package test.org.rockem.tdd.bank;

import org.junit.Test;
import org.rockem.tdd.bank.Printer;
import org.rockem.tdd.bank.StatementPrinter;
import org.rockem.tdd.bank.Transaction;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StatementPrinterTest {


    private Printer printer = mock(Printer.class);

    @Test
    public void shouldBeAbleToCallPrintMultipleTimesProperly() throws Exception {
        StatementPrinter statementPrinter = new StatementPrinter(printer);
        statementPrinter.print(asList(new Transaction(3, "DATE")));
        verify(printer).printLine(String.format(StatementPrinter.LINE_FORMAT, "DATE", 3, 3));
        statementPrinter.print(asList(new Transaction(12, "DATE")));
        verify(printer).printLine(String.format(StatementPrinter.LINE_FORMAT, "DATE", 12, 12));
    }
}