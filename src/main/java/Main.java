import model.Expense;
import service.ExpenseManager;

import java.time.LocalDate;
import java.util.Scanner;

// Provides a command line interface for users to add expenses and view spending.
public class Main {
    public static void main(String[] args) {
        // scanner used to read user input from the console
        Scanner scanner = new Scanner(System.in);
        // manages all expense-related operations
        ExpenseManager manager = new ExpenseManager();

        // Optional seed data
        manager.addExpense(new Expense("Food", 12.5, LocalDate.of(2026,1,1)));
        manager.addExpense(new Expense("Transport", 5.0, LocalDate.of(2026,1,2)));
        manager.addExpense(new Expense("Entertainment", 20.0, LocalDate.of(2026,1,3)));

        //main application loop
        while (true) {
            System.out.println("\n1. Add Expense\n2. Show Total\n3. Show By Category\n4. Show Expense Trend (by date)\n5. Show Highest/Lowest\n6. Exit");
            System.out.print("Choose an option: ");

            // read menu choice as a string to prevent input mismatch errors
            String input = scanner.nextLine().trim();
            int choice = 0;

            // validate menu input
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue; // go back to menu
            }

            // switch cases for menu options
            switch (choice) {
                // add a new expense
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine().trim();

                    // validate category input
                    if(category.isEmpty() || category.matches("\\d+")) {
                        System.out.println("Category must contain letters and cannot be just numbers.");
                        break;
                    }

                    // normalize category for consistent storage
                    category = category.toLowerCase();
                    System.out.print("Enter amount: ");
                    double amount = 0;
                    try {
                        amount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a number.");
                        break;
                    }
                    // ensure amount is positive
                    if(amount <= 0) {
                        System.out.println("Amount must be greater than 0.");
                        break;
                    }
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date;
                    try {
                        date = LocalDate.parse(scanner.nextLine());
                    } catch(Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }
                    manager.addExpense(new Expense(category, amount, date));
                    System.out.println("Expense successfully added!");
                    break;

                    // display total expenses
                case 2:
                    System.out.println("Total Expense: $" + manager.getTotalExpense());
                    break;

                    // display expenses group by category
                case 3:
                    System.out.println("Expenses by Category (highest to lowest):");
                    manager.getExpenseByCategorySorted().forEach(
                            (categories, total) -> System.out.println(categories + ": $" + total)
                    );
                    break;

                    // display expense trend over time
                case 4:
                    System.out.println("Expense Trend (by date):");
                    manager.showExpenseTrend();
                    break;

                    // display highest and lowest spending categories
                case 5:
                    System.out.println("Highest Spending Category: " + manager.getHighestCategory());
                    System.out.println("Lowest Spending Category: " + manager.getLowestCategory());
                    break;

                    // exit the application
                case 6:
                    System.out.println("Exiting...");
                    return;

                    // default
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
