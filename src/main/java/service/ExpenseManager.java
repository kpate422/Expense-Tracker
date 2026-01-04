package service;

import model.Expense;

import java.util.*;
import java.util.stream.Collectors;

// Manages a collection of expenses and provides calculations and insights
public class ExpenseManager {
    // stores all expense entries in memory
    private List<Expense> expenses;

    // initialize the ExpenseManager with an empty expense list
    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    // adds a new expense to the list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // calculates the total amount spent across all expenses
    public double getTotalExpense() {
        // returns the total expense amount
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    // groups expenses by category, sums their amount, and returns the result sorted from highest to lowest
    public Map<String, Double> getExpenseByCategorySorted() {
        // return a map of category to total amount spent (sorted descending)
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // displays expenses sorted by date to show spending trends over time
    public void showExpenseTrend() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.");
            return;
        }

        expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate))
                .forEach(System.out::println);
    }

    // finds the category with the highest total spending
    public String getHighestCategory() {
        // returns the category name with the highest spending, or none if no data
        return getExpenseByCategorySorted().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");
    }

    // finds the category with the lowest total spending
    public String getLowestCategory() {
        // returns the category name with the lowest spending or none if no data
        return getExpenseByCategorySorted().entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");
    }

}
