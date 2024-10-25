package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	public void start(Stage primaryStage) {
		try {
			// Fonts
			Font normal = new Font("Georgia", 20);
			Font button = new Font("Calibri", 13);
			
			
			// SCENE FOR ACC STARTS HERE
			// Buttons
			Button returnbtn = new Button("return");
			Button savebtn = new Button("Save");
			Button cancelbtn = new Button("Cancel");
			
			// Create label shelf, and input shelf
			VBox labels = new VBox(22);
			VBox fields = new VBox(20);
			
			
			// Fields and Labels
			Label accPagelbl = new Label("Define New Account");
			accPagelbl.setFont(normal);
			
			Label accNamelbl = new Label("Account Name ");
			accNamelbl.setFont(normal);
			TextField accNamefld = new TextField();
			
			Label datelbl = new Label("Opening Date");
			datelbl.setFont(normal);
			DatePicker datepkr = new DatePicker();
			datepkr.setValue(LocalDate.now());
			
			
			Label ballbl = new Label("Opening Balance");
			ballbl.setFont(normal);
			TextField balnum = new TextField();
			balnum.setTextFormatter(new TextFormatter<Double>(new FixedDecimalConverter(2), 0.0));
			
			Label errorlbl = new Label("");
			
			
			// Put labels and fields into their style boxes
			labels.getChildren().addAll(accNamelbl, datelbl, ballbl);
			fields.getChildren().addAll(accNamefld, datepkr, balnum);
			
			// Style nodes
			HBox accDataHBox = new HBox(10);
			VBox accData = new VBox(10);
			HBox returnBox = new HBox(20);
			HBox dataBtns = new HBox(15);
			
			// Input into Style nodes
			accDataHBox.getChildren().addAll(labels, fields);
			accDataHBox.setAlignment(Pos.CENTER);
			accData.getChildren().add(accDataHBox);
			accData.setAlignment(Pos.CENTER);
			
			dataBtns.getChildren().addAll(savebtn, cancelbtn);
			dataBtns.setAlignment(Pos.CENTER);
			accData.getChildren().addAll(dataBtns, errorlbl);
			
			returnBox.getChildren().add(returnbtn);
			returnBox.setPadding(new Insets(10));
			
			// Sets BG colors
			returnBox.setStyle("-fx-background-color: #dcebfc;");
			
			//Scene creator for Account page
			BorderPane accRoot = new BorderPane();
			accRoot.setCenter(accData);
			accRoot.setTop(returnBox);
			
			Scene accountPage = new Scene(accRoot, 1000, 500);
			
			
			// SCENE FOR MAIN STARTS HERE
			
			// Navigation buttons
			Button home = new Button("house");
			Button notif = new Button("bell");
			
			// Menu buttons & labels
			Label acclbl = new Label("Accounts");
			Label tranlbl = new Label("Transactions");
			Button accbtn = new Button("Create New Account");
			Button tranbtn = new Button("Create Transaction");
			Button schedulebtn = new Button("Create Scheduled Transaction");
			Button upcomingbtn = new Button("Upcoming Transactions");
			Button reportbtn = new Button("Transaction Report");
			
			acclbl.setFont(normal);
			tranlbl.setFont(normal);
			accbtn.setFont(button);
			tranbtn.setFont(button);
			schedulebtn.setFont(button);
			upcomingbtn.setFont(button);
			reportbtn.setFont(button);
			
			// Account Info Display labels
			Label accBoxlbl = new Label("Accounts");
			Label accBoxNamelbl = new Label("Account Name");
			Label accBoxDatelbl = new Label("Opening Date");
			Label accBoxBallbl = new Label("Balance");
			
			accBoxlbl.setFont(normal);
			accBoxNamelbl.setFont(normal);
			accBoxDatelbl.setFont(normal);
			accBoxBallbl.setFont(normal);
			
			
			// Formatting Style Nodes
			AnchorPane nav = new AnchorPane();
			VBox menu = new VBox(5);
			VBox accountBox = new VBox();
			GridPane accLabelsBox = new GridPane();
			GridPane accDetailsBox = new GridPane();
			
			// Filling Style Nodes
			nav.getChildren().addAll(home, notif);
			menu.getChildren().addAll(acclbl, accbtn, tranlbl, tranbtn, schedulebtn,
										upcomingbtn, reportbtn);
			accLabelsBox.getChildren().addAll(accBoxlbl, accBoxNamelbl, accBoxDatelbl, accBoxBallbl);
			
			// Position Style Nodes
			GridPane.setConstraints(accBoxlbl, 1, 0);
			GridPane.setConstraints(accBoxNamelbl, 0, 1);
			GridPane.setConstraints(accBoxDatelbl, 1, 1);
			GridPane.setConstraints(accBoxBallbl, 2, 1);
			GridPane.setMargin(accBoxNamelbl, new Insets(20));
			GridPane.setMargin(accBoxDatelbl, new Insets(20));
			GridPane.setMargin(accBoxBallbl, new Insets(20));
			GridPane.setFillWidth(accBoxlbl, true);
			
			AnchorPane.setLeftAnchor(home, 10d);
			AnchorPane.setRightAnchor(notif, 10d);
			
			AnchorPane.setTopAnchor(home, 2d);
			AnchorPane.setTopAnchor(notif, 2d);
			nav.setPadding(new Insets(10));
			menu.setAlignment(Pos.TOP_LEFT);
			menu.setPadding(new Insets(10));
			accountBox.setAlignment(Pos.TOP_CENTER);
			accountBox.setPadding(new Insets(30));
			accDetailsBox.setAlignment(Pos.TOP_CENTER);
			accLabelsBox.setAlignment(Pos.TOP_CENTER);
			
			
			accountBox.getChildren().addAll(accLabelsBox, accDetailsBox);
			
			
			// Formatting Style Nodes
			BorderPane root = new BorderPane();
			
			// Set colors
			nav.setStyle("-fx-background-color: #dcebfc;");
			menu.setStyle("-fx-background-color: #f3deff;");
			
			// Add the constants in UI to root
			root.setLeft(menu);
			root.setTop(nav);
			root.setCenter(accountBox);
			
			//Scene creator (width, height)
			Scene scene = new Scene(root,1000,500);
			
			updateAccountList(accDetailsBox);
			
			//Transition button(s) implementation
			home.setOnAction(event -> primaryStage.setScene(scene));
			accbtn.setOnAction(event -> {
				primaryStage.setScene(accountPage);
				errorlbl.setText("");
				accNamefld.clear();
				datepkr.setValue(LocalDate.now());
				balnum.setText("0.00");
			});
			returnbtn.setOnAction(event -> primaryStage.setScene(scene));
			cancelbtn.setOnAction(event -> primaryStage.setScene(scene));
			savebtn.setOnAction(event -> {
				try {
					double passBalNum = Double.parseDouble(balnum.getText());
					
					
					if (!accNamefld.getCharacters().isEmpty()) {
						
						boolean newName = true;
						
						ArrayList<Account> accounts = fileReader();
						for (int i = 0; i < accounts.size(); i++) {
							ArrayList<String> accDetails = accounts.get(i).getAccDetails();
							if (accDetails.get(0).equals(accNamefld.getText())) {
								newName = false;
							}
						}
						if (newName) {
							Account newAcc = new Account(accNamefld.getText(), datepkr.getValue(), passBalNum);
							fileWriter(newAcc.getAccDetails());
							accNamefld.clear();
							datepkr.setValue(LocalDate.now());
							balnum.setText("0.00");
							primaryStage.setScene(scene);
							updateAccountList(accDetailsBox);
						}
						else {
							errorlbl.setText("Please enter a new account name");
						}
					}
					else {
						errorlbl.setText("Please enter an account name");
					}
				}
				catch (Exception e) {
					errorlbl.setText("Please enter valid balance");
				}
			}
			);
			// Adds scene into the stage and shows it
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public class FixedDecimalConverter extends DoubleStringConverter {

	    private final int decimalPlaces;

	    public FixedDecimalConverter(int decimalPlaces) {
	        this.decimalPlaces = decimalPlaces;
	    }

	    @Override
	    public String toString(Double value) {
	        return String.format("%." + decimalPlaces + "f", value);
	    }

	}
	
	public void fileCreator() {
		try {
		    File file = new File("filename.txt");
		    if (file.createNewFile()) {
		        System.out.println("File created: " + file.getName());
		    } else {
		        System.out.println("File already exists.");
		    }
		} catch (IOException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	
	public void fileWriter(ArrayList<String> data) {
		fileCreator();
		
		String CSV = "";
		CSV = String.join(",", data);
		
		//if duplicate account name return
		
		try {
		    FileWriter fw = new FileWriter("filename.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);

		    bw.write(CSV);
		    bw.newLine(); // Add a new line

		    bw.close();
		} catch (IOException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
	}
	
	public ArrayList<Account> fileReader() {	//Returns an array of Account objects read from the current file
		ArrayList<Account> accounts = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
			
			while(br.ready()) {
				String line = br.readLine();
				String[] splitLine = line.split(",");
				LocalDate test = LocalDate.parse(splitLine[1]);
				Double value = Double.parseDouble(splitLine[2]);
				Account tempAcc = new Account(splitLine[0], test, value);
				
				accounts.add(tempAcc);
			}
			
			
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}
		
		return accounts;
	}
	
	public void updateAccountList(GridPane table) {
		table.getChildren().clear();
		ArrayList<Account> accountList = fileReader();
		ArrayList<String> accountListStrings = new ArrayList<>();
		for (int i = 0; i < accountList.size(); i++) {
			// Formatted as Date, Name, Balance
			accountListStrings.add(accountList.get(i).getAccDetails().get(1) + "," +
									accountList.get(i).getAccDetails().get(0) + "," +
									accountList.get(i).getAccDetails().get(2));
		}
		Collections.sort(accountListStrings);
		for (int i = 0; i < accountList.size(); i++) {
			String[] accountDetails = accountListStrings.get(i).split(",");
			Label accountName = new Label(accountDetails[1]);
			Label accountDate = new Label(accountDetails[0]);
			Label accountBalance = new Label(accountDetails[2]);
			table.getChildren().addAll(accountName, accountDate, accountBalance);
			GridPane.setMargin(accountName, new Insets(0, 20, 0, 40));
			GridPane.setMargin(accountDate, new Insets(0, 20, 0, 100));
			GridPane.setMargin(accountBalance, new Insets(0, 0, 0, 80));
			GridPane.setConstraints(accountName, 0, i);
			GridPane.setConstraints(accountDate, 1, i);
			GridPane.setConstraints(accountBalance, 2, i);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
