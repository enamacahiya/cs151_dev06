<<<<<<< HEAD
package application;

import java.util.ArrayList;

public class TransactionType implements CSVWritable{
	private String typeName;
	
	public TransactionType (String name) {
		this.typeName = name;
	}
	
	public TransactionType (ArrayList<String> data) {
		this.typeName = data.removeFirst();
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
=======
package application;

import java.util.ArrayList;

public class TransactionType implements CSVWritable{
	private String typeName;
	
	public TransactionType (String name) {
		this.typeName = name;
	}
	
	public TransactionType (ArrayList<String> data) {
		this.typeName = data.removeFirst();
	}
	
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> temp = new ArrayList<>();
		temp.add(typeName);
		return temp;
	}
}
>>>>>>> cf731218f242c9ad8613ea792b0d76eefb38799d
