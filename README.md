***Expense Tracker***

Project Overview:
A simple Java command-line application to track personal expenses. Users can add expenses, view totals, see expenses by category, analyze trends, and identify highest/lowest spending categories.

Features:
Add expense with validation:
    * Category must contain letters
    * Amount must be numeric and greater than 0
    * Date must be in YYYY-MM-DD format
Expenses sorted by category totals (highest → lowest)
Show total expense, expense by category, and expense trend
Identify highest and lowest spending categories
Menu-driven interface for easy user interaction
Graceful handling of invalid input

Classes / Structure:
- Expense — stores category, amount, date
- ExpenseManager — manages expenses and performs calculations
- Main — command-line interface for user input

How to Run:
Build & compile: mvn clean compile

Run the application: java -cp target/classes Main

How to Test: mvn test

Tests cover:
Total expense calculation
Expense totals by category
Highest and lowest spending categories
