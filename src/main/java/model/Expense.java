package model;

import java.time.LocalDate;

// Represents a single expense entry. Stores the category, amount spent, and the date of the expense.
public class Expense {
    // category of the expense
    private String category;

    //amount spent for the expense
    private double amount;

    // date when the expense occured
    private LocalDate date;

    // Expense object with category, amount, and date
    public Expense(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // returns the category of the expense
    public String getCategory() {
        return category;
    }

    //returns the amount spent
    public double getAmount() {
        return amount;
    }

    // returns the date of the expense
    public LocalDate getDate() {
        return date;
    }

    // returns a formatted string representation of the expense
    @Override
    public String toString() {
        return date + " | " + category + " | $" + amount;
    }
}
