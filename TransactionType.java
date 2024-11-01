package application;

import java.util.ArrayList;

public class TransactionType implements CSVWritable{
	private String typeName;
	
	public TransactionType (String name) {
		this.typeName = name;
	}
	
	public TransactionType (ArrayList<String> data) {
		this.typeName = data.remove(0);
	}
	
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> temp = new ArrayList<>();
		temp.add(typeName);
		return temp;
	}
	
	public String getTransactionName() {
		return this.typeName;
	}
}
