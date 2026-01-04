import model.Expense;
import service.ExpenseManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        // Optional seed data
        manager.addExpense(new Expense("Food", 12.5, LocalDate.of(2026,1,1)));
        manager.addExpense(new Expense("Transport", 5.0, LocalDate.of(2026,1,2)));
        manager.addExpense(new Expense("Entertainment", 20.0, LocalDate.of(2026,1,3)));

        while (true) {
            System.out.println("\n1. Add Expense\n2. Show Total\n3. Show By Category\n4. Show Expense Trend (by date)\n5. Show Highest/Lowest\n6. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();
            int choice = 0;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue; // go back to menu
            }


            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine().trim();

                    if(category.isEmpty() || category.matches("\\d+")) {
                        System.out.println("Category must contain letters and cannot be just numbers.");
                        break;
                    }

                    category = category.toLowerCase(); // normalize
                    System.out.print("Enter amount: ");
                    double amount = 0;
                    try {
                        amount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a number.");
                        break;
                    }
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
                case 2:
                    System.out.println("Total Expense: $" + manager.getTotalExpense());
                    break;
                case 3:
                    System.out.println("Expenses by Category (highest to lowest):");
                    manager.getExpenseByCategorySorted().forEach(
                            (categories, total) -> System.out.println(categories + ": $" + total)
                    );
                    break;
                case 4:
                    System.out.println("Expense Trend (by date):");
                    manager.showExpenseTrend();
                    break;
                case 5:
                    System.out.println("Highest Spending Category: " + manager.getHighestCategory());
                    System.out.println("Lowest Spending Category: " + manager.getLowestCategory());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
