package service;
import model.Expense;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseManagerTest {

    @Test
    void testTotalExpense() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense(new Expense("Food", 10, LocalDate.now()));
        manager.addExpense(new Expense("Food", 20, LocalDate.now()));

        assertEquals(30, manager.getTotalExpense());
    }

    @Test
    void testExpenseByCategory() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense(new Expense("Food", 10, LocalDate.now()));
        manager.addExpense(new Expense("Transport", 5, LocalDate.now()));

        Map<String, Double> result = manager.getExpenseByCategorySorted();
        assertEquals(10, result.get("Food"));
        assertEquals(5, result.get("Transport"));
    }

    @Test
    void testHighestAndLowestCategory() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense(new Expense("Food", 50, LocalDate.now()));
        manager.addExpense(new Expense("Transport", 10, LocalDate.now()));

        assertEquals("Food", manager.getHighestCategory());
        assertEquals("Transport", manager.getLowestCategory());
    }
}