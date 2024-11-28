package classes;

public class TransactionEvent {
    private String type;
    private double amount;
    private String platform;

    TransactionEvent(String type, double amount, String platform){
        this.type = type;
        this.amount = amount;
        this.platform = platform;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }
}
