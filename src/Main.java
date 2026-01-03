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
            System.out.println("\n1. Add Expense\n2. Show Total");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    manager.addExpense(new Expense(category, amount, date));
                    System.out.print("Expense successfully added!");
                    break;
                case 2:
                    System.out.println("Total Expense: $" + manager.getTotalExpense());
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
