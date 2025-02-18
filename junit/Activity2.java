import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Activity2
{
    @Test
    public void notEnoughFunds()
    {
        BankAccount account = new BankAccount(9);

        assertThrows(NotEnoughFundsException.class, () -> account.withdraw(10),
                "Balance must be greater than amount of withdrawal");
    }

    @Test
    public void enoughFunds()
    {
        BankAccount bankAccount = new BankAccount(100);

        assertDoesNotThrow(() -> bankAccount.withdraw(100));
    }
}
