package application;

import java.util.*;
import java.time.LocalDate;
import javafx.scene.control.TextField;

public class Account {
    
    private String accName;
    private LocalDate openingDate;
    private double accBalance;
    
    public Account(String accName, LocalDate openingDate, double accBalance) {
        this.accName = accName;
        this.openingDate = openingDate;
        this.accBalance = accBalance;
    }
    
    public ArrayList<String> getAccDetails() {
    	ArrayList<String> temp = new ArrayList<>();
        temp.add(accName);
        temp.add(openingDate.toString());
        temp.add(Double.toString(accBalance));
        return temp;
    }
    
}
