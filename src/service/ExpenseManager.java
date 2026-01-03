package service;

import model.Expense;

import java.util.*;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense e) {
        expenses.add(e);
    }

    public double getTotalExpense() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public Map<String, Double> getExpenseByCategory() {
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    public void showExpenseTrend() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.");
            return;
        }

        expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate))
                .forEach(System.out::println);
    }

}
