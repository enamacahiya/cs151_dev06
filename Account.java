package application;

import java.util.*;
import java.time.LocalDate;

public class Account {

    private String accName;
    private LocalDate openingDate;
    private double accBalance;

    public Account(TextField accName, LocalDate openingDate, double accBalance) {
        this.accName = accName.getText();
        this.openingDate = openingDate;
        this.accBalance = accBalance;
    }

    public ArrayList<String> getAccDetails() {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(accName);
        temp.add(openingDate.toString());
        temp.add(Double.toString(accBalance));
        return temp;
    }

}