package com.skypay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDeposit() {
        Account account = new Account();
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.deposit(2000);
        account.withdraw(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testInsufficientFunds() {
        Account account = new Account();
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200));
    }

    @Test
    public void testPrintStatement() {
        Account account = new Account();
        account.deposit(1000, "10/01/2012"); 
        account.deposit(2000, "13/01/2012"); 
        account.withdraw(500, "14/01/2012"); 

        assertEquals(3, account.getTransactions().size());
        assertEquals(2500, account.getBalance());
    }

    @Test
    public void testAcceptanceCriteria() {
        Account account = new Account();
        account.deposit(1000, "10/01/2012");
        account.deposit(2000, "13/01/2012");
        account.withdraw(500, "14/01/2012");
        account.printStatement(); 
    }
}