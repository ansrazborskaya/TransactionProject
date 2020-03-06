package by.finalproject.transactions.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOService {

    public static final String EXIT_CHAR = "e";
    private static final String MENU_LEGEND =
                    "1. Print all accounts\n" +
                    "2. Add new account\n" +
                    "3. Do transaction\n" +
                    "4. Print all transactions\n" +
                    EXIT_CHAR + ". exit\n";

    private BufferedReader rdr;

    public IOService() {
        this.rdr = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getString() throws IOException {

        return rdr.readLine();
    }

    public int getInt() throws IOException, NumberFormatException {

        return Integer.parseInt(getString());
    }

    public double getDouble() throws IOException, NumberFormatException {

        return Double.parseDouble(getString());
    }

    public void printLegend() {

        System.out.println(MENU_LEGEND);
    }

}
