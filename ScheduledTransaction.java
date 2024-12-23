package application;

import java.util.ArrayList;

public class ScheduledTransaction implements CSVWritable{
	private String schedName; // String of scheduled transaction name
	private String accName; // string of acc obj name
	private String transName; // string of TransType
	private String frequency = "Monthly";
    private Integer dueDate; // Day of month payment due
    private double paymentAmt;

	public ScheduledTransaction(String name, String accName, String tName, String time, Integer dueDate, double paymentAmt) {
		this.schedName = name;
		this.accName = accName;
		this.transName = tName;
		this.frequency = time;
		this.dueDate = dueDate;
		this.paymentAmt = paymentAmt;
	}
	
	public ScheduledTransaction(ArrayList<String> data) {
		this.schedName = data.remove(0);
	    this.accName = data.remove(0);
		this.transName  = data.remove(0);
		this.frequency = data.remove(0);
		this.dueDate = Integer.parseInt(data.remove(0).toString());
		this.paymentAmt = Double.parseDouble(data.remove(0).toString());
	}
	
	public String getName() {
		return schedName;
	}
	
	public String searchString() {
		return schedName;
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> data = new ArrayList<>();
		data.add(schedName);
        data.add(accName);
        data.add(transName);
        data.add(frequency);
        data.add(Integer.toString(dueDate));
        data.add(Double.toString(paymentAmt));
        return data;
	}
	
	@Override
	public int compareTo(CSVWritable object) {
		return Integer.compare(dueDate, ((ScheduledTransaction) object).dueDate);
	}
}