package application;

import java.util.ArrayList;
import java.time.LocalDate;

public class Transaction implements CSVWritable {

    private String accName; // string of acc obj name
    private String transName; // string of TransType
    private LocalDate transDate;
    private String desc;
    private double paymentAmt;
    private double depositAmt;

    public Transaction(String name, String tName, LocalDate transDate, String desc, double paymentAmt,
            double depositAmt) {
        this.accName = name;
        this.transName = tName;
        this.transDate = transDate;
        this.desc = desc;
        this.paymentAmt = paymentAmt;
        this.depositAmt = depositAmt;
    }

    public Transaction(ArrayList<String> data) {
        this.accName = data.remove(0);
        this.transName = data.remove(0);
        this.transDate = LocalDate.parse(data.remove(0).toString());
        this.desc = data.remove(0);
        this.paymentAmt = Double.parseDouble(data.remove(0).toString());
        this.depositAmt = Double.parseDouble(data.remove(0).toString());
    }

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(accName);
        temp.add(transName);
        temp.add(transDate.toString());
        temp.add(desc);
        temp.add(Double.toString(paymentAmt));
        temp.add(Double.toString(depositAmt));
        return temp;
    }

}