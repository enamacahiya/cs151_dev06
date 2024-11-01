package application;

import java.util.ArrayList;
import java.time.LocalDate;

public class Transaction implements CSVWritable {
	
	private Account acc; // account obj
	private String accName; // string of acc obj name
	private TransactionType transType; // transType obj
	private String transName; // string of TransType
    private LocalDate transDate;
    private String desc;
    private double paymentAmt;
    private double depositAmt;

	public Transaction(Account acc, TransactionType transType, LocalDate transDate, String desc, double paymentAmt, double depositAmt) {
		this.acc = acc;
		this.transType = transType;
		this.transDate = transDate;
		this.desc = desc;
		this.paymentAmt = paymentAmt;
		this.depositAmt = depositAmt;
	}
	
	public Transaction(ArrayList<String> data) {
	    this.accName = acc.getAccName();
		this.transName  = transType.getTransactionName();
		this.transDate = LocalDate.parse(data.removeFirst().toString());
		this.desc = data.removeFirst().toString();
		this.paymentAmt = Double.parseDouble(data.removeFirst().toString());
		this.depositAmt = Double.parseDouble(data.removeFirst().toString());
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
