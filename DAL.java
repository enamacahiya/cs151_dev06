package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DAL {
	private File file;
	
	public DAL (String className) {
		file = new File(className + ".csv");
		try {
			file.createNewFile();
		}
		catch(IOException e) {
			System.out.println("File creation error: " + className);
		}
	}
	
	public void fileDelete() {
		file.delete();
	}
	
	public void fileWriter(ArrayList<String> data) {
		String CSV = "";
		CSV = String.join(",", data);
		
		try {
			FileWriter fw = new FileWriter(file.getName(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(CSV);
			bw.newLine();
			
			bw.close();
		}
		catch (IOException e) {
			System.out.println("File writing error in " + file.getName());
		}
	}
	
	public ArrayList<ArrayList<String>> fileReader() {
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getName()));
			
			while(br.ready()) {
				String line = br.readLine();
				ArrayList<String> splitline = new ArrayList<>(Arrays.asList(line.split(",")));
				data.add(splitline);
			}
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
		
		return data;
	}
}
