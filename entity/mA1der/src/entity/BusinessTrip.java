package entity;

public class BusinessTrip {
    private static final double DAILY_ALLOWANCE_RATE = 23.00;
    private double transportationExpenses;
    private double numberOfDays;
    private String employeeAccount;

    public BusinessTrip() {
    }

    public BusinessTrip(String employeeAccount, double transportationExpenses, double numberOfDays) {
        this.employeeAccount = employeeAccount;
        this.transportationExpenses = transportationExpenses;
        this.numberOfDays = numberOfDays;
    }

    public double getTransportationExpenses() {
        return transportationExpenses;
    }

    public void setTransportationExpenses(double transportationExpenses) {
        this.transportationExpenses = transportationExpenses;
    }

    public double getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(double numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(String employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public double getTotal() {
        return transportationExpenses + DAILY_ALLOWANCE_RATE * numberOfDays;
    }

    public void show() {
        System.out.println("rate = " + DAILY_ALLOWANCE_RATE);
        System.out.println("account = " + employeeAccount);
        System.out.println("transport = " + transportationExpenses);
        System.out.println("days = " + numberOfDays);
        System.out.println("total = " + getTotal());
    }

    @Override
    public String toString() {
        return employeeAccount + ";" + transportationExpenses + ";" + numberOfDays + ";" + getTotal();
    }
}
