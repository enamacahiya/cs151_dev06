package application;

import java.util.*;
import java.time.LocalDate;

public class Account implements CSVWritable {

    private String accName;
    private LocalDate openingDate;
    private double accBalance;

    public Account() {

    }

    public Account(String accName, LocalDate openingDate, double accBalance) {
        this.accName = accName;
        this.openingDate = openingDate;
        this.accBalance = accBalance;
    }

    public Account(ArrayList<String> data) {
        this.accName = data.remove(0).toString();
        this.openingDate = LocalDate.parse(data.remove(0).toString());
        this.accBalance = Double.parseDouble(data.remove(0).toString());
    }

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(accName);
        temp.add(openingDate.toString());
        temp.add(Double.toString(accBalance));
        return temp;
    }

    public String getAccName() {
        return this.accName;
    }

}