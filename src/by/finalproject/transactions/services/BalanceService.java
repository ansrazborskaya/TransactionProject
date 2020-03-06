package by.finalproject.transactions.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import by.finalproject.transactions.db.DBManager;
import by.finalproject.transactions.dto.AccountsForm;
import by.finalproject.transactions.dto.TransactionForm;
import by.finalproject.transactions.model.Account;
import by.finalproject.transactions.model.Transaction;


public class BalanceService {


    private static final String GET_ACCOUNTS = "select * from accounts;";
    private static final String SAVE_NEW_ACCOUNT  = "insert into accounts value (null, ?, ?);";
    private static final String GET_ACCOUNT = "select * from accounts where id = ?;";
    private static final String GET_COMISSION = "select comission from bank  where id = ?;";
    private static final String UPDATE_ACCOUNT = "update accounts set amount = ? where id = ?;";
    private static final String SAVE_NEW_TRANSACTION = "insert into transactions value (null, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_TRANSACTION = "select t.date_time, t.account_from_id as Sender, t.account_to_id as Receiver, a.amount as Balance, t.amount as SumOfTransaction, b.title, b.comission from transactions t join accounts a on (a.id = t.account_from_id) join bank b on (b.id = a.bank_id);";

    //получение всех счетов
    public List<Account> getAll() throws SQLException {

        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ACCOUNTS);

        List<Account> result = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            double amount = resultSet.getDouble(2);
            int bank_id = resultSet.getInt(3);

            result.add(new Account(id, amount, bank_id));
        }

        return result;
    }

    public void saveNewAccounts(AccountsForm form) throws SQLException {


        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(SAVE_NEW_ACCOUNT);

        preparedStatement.setDouble(1, form.getAmount());
        preparedStatement.setInt(2, form.getBank_id());

        preparedStatement.executeUpdate();
    }

    private Account getAccountFromRS(ResultSet _rs) throws SQLException {

        Account result = new Account();

        result.setId(_rs.getInt( "id" ) );
        result.setAmount( _rs.getDouble( "amount" ) );
        result.setBank_id( _rs.getInt( "bank_id" ) );

        return result;
    }

    //получение Account по id
    public Account getAccount(int _id)
    {
        Account result = null;

        try
        {
            PreparedStatement ps = DBManager.getConnection().prepareStatement( GET_ACCOUNT);
            ps.setInt( 1, _id);

            ResultSet rs = ps.executeQuery();

            if( rs.next() )
            {
                result = getAccountFromRS(rs);
            }
            ps.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return result;
    }

    public double getComission(int _bankId) {

        double comission = 0;

        try
        {
            PreparedStatement ps = DBManager.getConnection().prepareStatement( GET_COMISSION);
            ps.setInt( 1, _bankId);

            ResultSet rs = ps.executeQuery();

            if( rs.next() )
            {
                comission = rs.getDouble("comission");
            }
            ps.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return comission;
    }

    public void doTransaction (TransactionForm  _formTrans) {




        Account accountFrom;
        Account accountTo;

        double comission;

        boolean result = false;

        accountFrom = getAccount(_formTrans.getIdFrom());
        accountTo   = getAccount(_formTrans.getIdTo());

        comission = getComission(accountFrom.getBank_id());

        if (validateTransaction(accountFrom, accountTo, _formTrans.getAmount())) {

            result = true;

            if (accountFrom.getBank_id() == accountTo.getBank_id()) {
                accountFrom.setAmount(accountFrom.getAmount() - _formTrans.getAmount());
                accountTo.setAmount(accountTo.getAmount() + _formTrans.getAmount());
            }
            else {
                accountFrom.setAmount(accountFrom.getAmount() - _formTrans.getAmount() - (_formTrans.getAmount()/100 * comission));
                accountTo.setAmount(accountTo.getAmount() + _formTrans.getAmount());
            }
        }

    }

    public boolean validateTransaction(Account _accountFrom, Account _accountTo, double _amount) {

        boolean ret = true;

        if (_accountFrom.getAmount() < _amount) {
            ret = false;
            System.err.println("Sender has not enough money!!!");
        }

        return ret;
    }

    public void updateAccount (Account account) throws SQLException {

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(UPDATE_ACCOUNT);

        preparedStatement.setInt(1, account.getId());
        preparedStatement.setDouble(2, account.getAmount());


        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void saveTransaction (int _idFrom, int _idTo, double _amount, boolean _result) throws SQLException {

        String  result = "FAILED";

        PreparedStatement preparedStatement =
                DBManager.getConnection().prepareStatement(SAVE_NEW_TRANSACTION);

        preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(2, _idFrom);
        preparedStatement.setInt(3, _idTo);
        preparedStatement.setDouble(4, _amount);

        if (_result) {
            result = "DONE";
        }
        preparedStatement.setString(5, result);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

      public List<Transaction> getAllTransactions() throws SQLException {

        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_TRANSACTION);

        List<Transaction> result = new ArrayList<>();

        while (resultSet.next()) {

            Timestamp date_time = resultSet.getTimestamp(1);
            int account_from_id = resultSet.getInt(2);
            int account_to_id = resultSet.getInt(3);
            double amount = resultSet.getDouble(4);
            double amount2 = resultSet.getDouble(5);
            String title = resultSet.getString(6);
            double comission = resultSet.getDouble(7);

            result.add(new Transaction(date_time, account_from_id, account_to_id, amount, amount2, title, comission));
        }

        return result;
    }


    }




