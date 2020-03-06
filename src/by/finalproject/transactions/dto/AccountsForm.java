package by.finalproject.transactions.dto;

public class AccountsForm {

    private final double amount;

    private final int bank_id;

    public AccountsForm(Double amount, int bank_id) {
        this.amount = amount;
        this.bank_id = bank_id;
    }

    public double getAmount() {
        return amount;
    }

    public int getBank_id() {
        return bank_id;
    }
}
