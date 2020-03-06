package by.finalproject.transactions.model;

import java.sql.Timestamp;


public class Transaction {

    private Timestamp date_time;
    private int account_from_id;
    private int account_to_id;
    private double amount;
    private double amount2;
    private String title;
    private double comission;

    public Transaction(Timestamp date_time, int account_from_id, int account_to_id, double amount, double amount2, String title, double comission) {
        this.date_time = date_time;
        this.account_from_id = account_from_id;
        this.account_to_id = account_to_id;
        this.amount = amount;
        this.amount2 = amount2;
        this.title = title;
        this.comission = comission;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public int getAccount_from_id() {
        return account_from_id;
    }

    public void setAccount_from_id(int account_from_id) {
        this.account_from_id = account_from_id;
    }

    public int getAccount_to_id() {
        return account_to_id;
    }

    public void setAccount_to_id(int account_to_id) {
        this.account_to_id = account_to_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getComission() {
        return comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date_time=" + date_time +
                ", account_from_id=" + account_from_id +
                ", account_to_id=" + account_to_id +
                ", amount=" + amount +
                ", amount2=" + amount2 +
                ", title='" + title + '\'' +
                ", comission=" + comission +
                '}';
    }
}
