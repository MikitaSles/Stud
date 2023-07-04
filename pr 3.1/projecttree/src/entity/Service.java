package entity;

public class Service {
    private String name;
    private double totalCost;
    private int numberOfUsers;

    public Service(String name, double totalCost, int numberOfUsers) {
        this.name = name;
        this.totalCost = totalCost;
        this.numberOfUsers = numberOfUsers;
    }

    public String getName() {
        return name;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public double getPricePerUser() {
        return Math.ceil(totalCost / numberOfUsers);
    }
}
