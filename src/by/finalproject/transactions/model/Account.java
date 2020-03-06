package by.finalproject.transactions.model;

public class Account {

    private int id;
    private double amount;
    private int bank_id;


    public Account() {
    }

    public Account(int id, double amount, int bank_id) {
        this.id = id;
        this.amount = amount;
        this.bank_id = bank_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", bank_id=" + bank_id +
                '}';
    }
}
