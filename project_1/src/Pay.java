public class Pay {
    private double amount;
    private String paymentMethod;
    private String transactionId;

    public Pay(double amount, String paymentMethod, String transactionId) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
