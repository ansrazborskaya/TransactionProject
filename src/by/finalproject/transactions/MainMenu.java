package by.finalproject.transactions;

import by.finalproject.transactions.dto.AccountsForm;
import by.finalproject.transactions.dto.TransactionForm;
import by.finalproject.transactions.services.BalanceService;
import by.finalproject.transactions.services.IOService;

import java.io.IOException;
import java.sql.SQLException;

public class MainMenu {

    private final IOService ioService;
    private final BalanceService balanceService;

    public MainMenu() {
        this.ioService = new IOService();
        this.balanceService = new BalanceService();
    }

    public void start() throws IOException {

        String menuInput = "";

        do {
            ioService.printLegend();
            try {
                menuInput = ioService.getString();
            } catch (IOException e) {
                System.err.println("ERROR: Bad input");
            }

            switch (menuInput) {
                case "1":
                    try {
                        balanceService.getAll().forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    try {
                        AccountsForm form = getAccountsFormFromConsole();
                        balanceService.saveNewAccounts(form);
                    } catch (IOException | NumberFormatException e) {
                        System.err.println("Invalid input");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "3":
                    try {
                        TransactionForm formTrans = getTransactionFormFromConsole();
                        balanceService.doTransaction(formTrans);
                    } catch (IOException | NumberFormatException ea) {
                        System.err.println("Invalid input");
                    }
                    break;
                case "4":
                    try {
                        balanceService.getAllTransactions().forEach(System.out::println);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "e":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.err.println("Bad input");
                    break;
            }

        } while (!menuInput.equals(IOService.EXIT_CHAR));
    }


    private AccountsForm getAccountsFormFromConsole() throws IOException, NumberFormatException {

        System.out.println("Enter amount:");
        Double amount = ioService.getDouble();
        System.out.println("Enter bank_id:");
        int bank_id = ioService.getInt();

        return new AccountsForm(amount, bank_id);
    }

    private TransactionForm getTransactionFormFromConsole() throws IOException, NumberFormatException {

        System.out.println("Enter IdFrom:");
        int idFrom = ioService.getInt();
        System.out.println("Enter IdTo:");
        int idTo = ioService.getInt();
        System.out.println("Enter amount:");
        Double amount = ioService.getDouble();

        return new TransactionForm(idFrom, idTo, amount);
    }
}




