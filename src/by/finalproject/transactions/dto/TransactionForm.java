package by.finalproject.transactions.dto;

public class TransactionForm {

    private final int idFrom;
    private final int idTo;
    private final double amount;


    public TransactionForm(int idFrom, int idTo, double amount) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.amount = amount;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public double getAmount() {
        return amount;
    }
}
