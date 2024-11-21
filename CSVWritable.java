package application;

import java.util.ArrayList;

public interface CSVWritable extends Comparable<CSVWritable>{
    ArrayList<String> toArrayList();

	int compareTo(CSVWritable object);
	
	String searchString();
}