public class FinancialForecasting {

    public static double calculateFutureValue(double presentValue, double annualGrowthRate, int numberOfYears) {
        if (numberOfYears < 0) {
            throw new IllegalArgumentException("Number of years cannot be negative.");
        }
        if (numberOfYears == 0) {
            return presentValue;
        }
        return presentValue * Math.pow((1 + annualGrowthRate), numberOfYears);
    }

    public static double calculateFutureValueAnnuity(double periodicPayment, double annualGrowthRate, int numberOfYears) {
        if (numberOfYears < 0 || periodicPayment < 0) {
            throw new IllegalArgumentException("Number of years and periodic payment cannot be negative.");
        }
        if (annualGrowthRate == 0) {
            return periodicPayment * numberOfYears;
        }
        return periodicPayment * ((Math.pow((1 + annualGrowthRate), numberOfYears) - 1) / annualGrowthRate);
    }


    public static void main(String[] args) {
        double initialInvestment = 1000.0;
        double growthRate = 0.05;
        int years1 = 10;

        System.out.println("--- Scenario 1: Future Value of Single Investment ---");
        double futureValue1 = calculateFutureValue(initialInvestment, growthRate, years1);
        System.out.printf("Initial Investment: $%.2f%n", initialInvestment);
        System.out.printf("Annual Growth Rate: %.2f%%%n", growthRate * 100);
        System.out.printf("Number of Years: %d%n", years1);
        System.out.printf("Future Value: $%.2f%n", futureValue1);

        double monthlyContribution = 100.0;
        double annualInterestRate = 0.06;
        int investmentYears = 5;

        double monthlyInterestRate = annualInterestRate / 12;
        int totalMonths = investmentYears * 12;

        System.out.println("\n--- Scenario 2: Future Value of Regular Contributions (Annuity) ---");
        System.out.printf("Monthly Contribution: $%.2f%n", monthlyContribution);
        System.out.printf("Annual Interest Rate: %.2f%%%n", annualInterestRate * 100);
        System.out.printf("Investment Period: %d years (%d months)%n", investmentYears, totalMonths);

        double futureValueAnnuity = calculateFutureValueAnnuity(monthlyContribution, monthlyInterestRate, totalMonths);
        System.out.printf("Future Value of Annuity: $%.2f%n", futureValueAnnuity);

        System.out.println("\n--- Edge Cases ---");
        try {
            calculateFutureValue(100, 0.05, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Future value for 0 years: $" + calculateFutureValue(500, 0.07, 0));
        System.out.println("Future value of annuity for 0 years: $" + calculateFutureValueAnnuity(50, 0.05, 0));
    }
}