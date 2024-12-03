package classes;
import java.util.List;

public class FinancialAdvisor {

    // Provide saving advice to encourage better saving habits
    public static String giveSavingAdvice(Account account) {
        double balance = account.getBalance();

        if (balance < 10000) {
            return "Your balance is quite low. Focus on saving at least 10% of every income you receive and cut down on non-essential expenses.";
        } else if (balance < 50000) {
            return "You're doing better! Increase your savings rate to 20% and try automating your savings to stay consistent.";
        } else {
            return "Good job maintaining a healthy balance! Diversify your savings into a high-interest savings account or safe investments.";
        }
    }

    // Suggest a saving plan tailored to the user's income and balance
    public static String suggestSavingPlan(double monthlyIncome, Account account) {
        double balance = account.getBalance();
        double suggestedSavings = monthlyIncome * 0.2;

        return "Based on your income of " + monthlyIncome + ", we recommend saving at least " + suggestedSavings + 
               " per month. With your current balance of " + balance + ", you can reach your financial goals faster if you remain disciplined.";
    }

    // Provide actionable tips for saving more money
    public static String actionableSavingTips() {
        return "Here are some tips to save more money:\n" +
               "1. Create a budget and stick to it.\n" +
               "2. Avoid unnecessary subscriptions or memberships.\n" +
               "3. Plan meals to reduce food waste and eating out.\n" +
               "4. Use cash-back apps or discounts when shopping.\n" +
               "5. Set specific financial goals to stay motivated.";
    }


}

