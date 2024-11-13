package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class Main extends Application {
    private Scene mainScene;
    
    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<TransactionType> transactiontypes = new ArrayList<TransactionType>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    
    DAL accountDAL = new DAL("Accounts");
    DAL transactionotypeDAL = new DAL("TransactionTypes");
    DAL transactionDAL = new DAL("Transactions");
    DAL scheduledtransactionDAL = new DAL("ScheduledTransactions");

    @Override
    public void start(Stage stage) throws IOException {
        try {
			for(ArrayList<String> element: accountDAL.fileReader()) {
				accounts.add(new Account(element));
			}
			for(ArrayList<String> element: transactionotypeDAL.fileReader()) {
				transactiontypes.add(new TransactionType(element));
			}
			for(ArrayList<String> element: transactionDAL.fileReader()) {
				transactions.add(new Transaction(element));
			}
        	
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

            // Scene creator for Account page
            BorderPane accRoot = new BorderPane();
            accRoot.setCenter(accData);
            accRoot.setTop(returnBox);

            Scene accountPage = new Scene(accRoot, 1000, 500);
            
            Scene TranTypePage = tranTypeStart(stage);

            // SCENE FOR MAIN STARTS HERE

            // Navigation buttons
            Button home = new Button("house");
            Button notif = new Button("bell");

            // Menu buttons & labels
            Label acclbl = new Label("Accounts");
            Label tranlbl = new Label("Transactions");
            Button accbtn = new Button("Create New Account");
            Button tranbtn = new Button("Enter Transaction");
            Button schedulebtn = new Button("Create Scheduled Transaction");
            Button trantypebtn = new Button("Create Transaction Type");
            Button upcomingbtn = new Button("Upcoming Transactions");
            Button reportbtn = new Button("Transaction Report");

            acclbl.setFont(normal);
            tranlbl.setFont(normal);
            accbtn.setFont(button);
            tranbtn.setFont(button);
            schedulebtn.setFont(button);
            trantypebtn.setFont(button);
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
                    trantypebtn, upcomingbtn, reportbtn);
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

            // Scene creator (width, height)
            mainScene = new Scene(root, 1000, 500);
            stage.setScene(mainScene);

            updateAccountList(accDetailsBox);

            Stage primaryStage = stage;
            // Transition button(s) implementation
            home.setOnAction(event -> primaryStage.setScene(mainScene));
            tranbtn.setOnAction(event -> {
            	Scene TransactionPage = transtart(stage);
                primaryStage.setScene(TransactionPage);
                errorlbl.setText("");
                accNamefld.clear();
                datepkr.setValue(LocalDate.now());
                balnum.setText("0.00");
            });
            accbtn.setOnAction(event -> {
                primaryStage.setScene(accountPage);
                errorlbl.setText("");
                accNamefld.clear();
                datepkr.setValue(LocalDate.now());
                balnum.setText("0.00");
            });
            returnbtn.setOnAction(event -> primaryStage.setScene(mainScene));
            cancelbtn.setOnAction(event -> primaryStage.setScene(mainScene));
            trantypebtn.setOnAction(event -> {
            	primaryStage.setScene(TranTypePage);
            });
            savebtn.setOnAction(event -> {
                try {
                    double passBalNum = Double.parseDouble(balnum.getText());

                    if (!accNamefld.getText().isEmpty() && !accNamefld.getText().contains(",")) {

                        boolean newName = true;
						
                        for (int i = 0; i < accounts.size(); i++) {
                        	if(accounts.get(i).getAccName().equals(accNamefld.getText())) {
                        		newName = false;
                        	}
                        }
                        if (newName) {
                            Account newAcc = new Account(accNamefld.getText(), datepkr.getValue(), passBalNum);
                            accounts.add(newAcc);
                            accNamefld.clear();
                            datepkr.setValue(LocalDate.now());
                            balnum.setText("0.00");
                            primaryStage.setScene(mainScene);
                            updateAccountList(accDetailsBox);
                        } else {
                            errorlbl.setText("Please enter a new account name");
                        }
                    } else {
                        errorlbl.setText("Please enter a valid account name");
                    }
                } catch (Exception e) {
                    errorlbl.setText("Please enter valid balance");
                }
            });
            // Adds scene into the stage and shows it
            primaryStage.setScene(mainScene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Scene transtart(Stage stage) {
        try {
            // Fonts
            Font normal = new Font("Georgia", 20);

            // SCENE FOR Tran STARTS HERE
            // Buttons
            Button Tranreturnbtn = new Button("return");
            Button Transavebtn = new Button("save");
            Button Trancancelbtn = new Button("cancel");

            // Create label shelf, and input shelf
            Label Tranpagelbl = new Label("Define New Transaction");
            Tranpagelbl.setFont(normal);
            
            Label Tranacclbl = new Label("Transaction Type");
            Tranacclbl.setFont(normal);
            ComboBox<String> Tranaccfld = new ComboBox();
            for(TransactionType element: transactiontypes) {
            	Tranaccfld.getItems().add(element.getTransactionName());
            }
            if(transactiontypes.size() > 0) {
            	Tranaccfld.setValue(transactiontypes.get(0).getTransactionName());
            }

            Label Tranacclbl2 = new Label("Account Name");
            Tranacclbl2.setFont(normal);
            ComboBox<String> Tranaccfld2 = new ComboBox();
            for(Account element: accounts) {
            	Tranaccfld2.getItems().add(element.getAccName());
            }
            if(accounts.size() > 0) {
            	Tranaccfld2.setValue(accounts.get(0).getAccName());
            }
            
            Label Trandatelbl = new Label("Transaction Date");
            Trandatelbl.setFont(normal);
            DatePicker Trandatepkr = new DatePicker();
            Trandatepkr.setValue(LocalDate.now());
            
            Label Trandesclbl = new Label("Transaction description");
            Trandesclbl.setFont(normal);
            TextField Trandescfld = new TextField();

            Label Tranpaylbl = new Label("Payment Amount");
            Tranpaylbl.setFont(normal);
            TextField Tranpaynum = new TextField();
            Tranpaynum.setTextFormatter(new TextFormatter<Double>(new FixedDecimalConverter(2), 0.0));
            
            Label Trandeplbl = new Label("Deposit Amount");
            Trandeplbl.setFont(normal);
            TextField Trandepnum = new TextField();
            Trandepnum.setTextFormatter(new TextFormatter<Double>(new FixedDecimalConverter(2), 0.0));

            Label Tranerrorlbl = new Label("");

            VBox labels = new VBox(22);
            VBox fields = new VBox(20);

            // Put labels and fields into their style boxes

            labels.getChildren().addAll(Tranacclbl2, Tranacclbl, Trandatelbl, Trandesclbl, Tranpaylbl, Trandeplbl);
            fields.getChildren().addAll(Tranaccfld2, Tranaccfld, Trandatepkr, Trandescfld, Tranpaynum, Trandepnum);

            // Style nodes
            HBox TranaccDataHBox = new HBox(10);
            VBox TranaccData = new VBox(10);
            HBox TranreturnBox = new HBox(20);
            HBox TrandataBtns = new HBox(15);

            // Input into Style nodes
            TranaccDataHBox.getChildren().addAll(labels, fields);
            TranaccDataHBox.setAlignment(Pos.CENTER);
            TranaccData.getChildren().add(TranaccDataHBox);
            TranaccData.setAlignment(Pos.CENTER);

            TrandataBtns.getChildren().addAll(Transavebtn, Trancancelbtn);
            TrandataBtns.setAlignment(Pos.CENTER);
            TranaccData.getChildren().addAll(TrandataBtns, Tranerrorlbl);

            TranreturnBox.getChildren().add(Tranreturnbtn);
            TranreturnBox.setPadding(new Insets(10));

            // Sets BG colors
            TranreturnBox.setStyle("-fx-background-color: #dcebfc;");
            // Scene creator for Transaction page
            BorderPane TranaccRoot = new BorderPane();
            TranaccRoot.setCenter(TranaccData);
            TranaccRoot.setTop(TranreturnBox);

            Scene TransactionPage = new Scene(TranaccRoot, 1000, 500);
            Tranreturnbtn.setOnAction(event -> {
                stage.setScene(mainScene);
            });
            Trancancelbtn.setOnAction(event -> {
                stage.setScene(mainScene);
            });
            Transavebtn.setOnAction(event -> {
            	if (Trandescfld.getText() != "" && !Trandescfld.getText().contains(",")) {
            		if(Double.parseDouble(Tranpaynum.getText()) == 0.00
            				&& Double.parseDouble(Trandepnum.getText()) == 0.00) {
            			Tranerrorlbl.setText("Payment or Deposit field empty");
            		}
            		else {
            			Transaction newTran = new Transaction(Tranaccfld2.getValue(), Tranaccfld.getValue(), 
            					Trandatepkr.getValue(), Trandescfld.getText(), 
            					Double.parseDouble(Tranpaynum.getText()), Double.parseDouble(Trandepnum.getText()));
            			transactions.add(newTran);
            			
            			Trandescfld.clear();
            			Tranpaynum.setText("0.00");
            			Trandepnum.setText("0.00");
            			Tranerrorlbl.setText("");
            			
            			stage.setScene(mainScene);
            		}
            	}
            	else {
            		Tranerrorlbl.setText("Description invalid");
            	}
            });

            return TransactionPage;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Scene tranTypeStart(Stage stage) {
    	Font normal = new Font("Georgia", 20);
    	
    	Button savebtn = new Button("save");
    	Button cancelbtn = new Button("cancel");
    	Button returnbtn = new Button("return");
    	
    	HBox header = new HBox(20);
    	
    	header.getChildren().add(returnbtn);
        header.setPadding(new Insets(10));

        // Sets BG colors
        header.setStyle("-fx-background-color: #dcebfc;");
    	
        Label TranTypeHeader = new Label("New Transaction Type");
        TranTypeHeader.setFont(normal);
        TranTypeHeader.setPadding(new Insets(30));
        
        HBox TranTypeMainHeader = new HBox();
        TranTypeMainHeader.getChildren().add(TranTypeHeader);
        TranTypeMainHeader.setAlignment(Pos.CENTER);
        
        TranTypeHeader.setAlignment(Pos.CENTER);
        
    	Label TranTypeName = new Label("Type Name");
    	TranTypeName.setFont(normal);
    	
    	TextField TranType = new TextField();
    	
    	Label errorlbl = new Label();
    	
    	HBox buttonShelf = new HBox(10);
    	buttonShelf.getChildren().addAll(savebtn, cancelbtn);
    	buttonShelf.setAlignment(Pos.CENTER);
    	
    	HBox infoShelf = new HBox(10);
    	infoShelf.getChildren().addAll(TranTypeName, TranType);
    	infoShelf.setAlignment(Pos.CENTER);
    	
    	VBox mainShelf = new VBox(10);
    	mainShelf.getChildren().addAll(infoShelf, buttonShelf, errorlbl);
    	mainShelf.setAlignment(Pos.CENTER);
    	
    	BorderPane main = new BorderPane();
    	main.setTop(TranTypeMainHeader);
    	main.setCenter(mainShelf);
    	
    	BorderPane root = new BorderPane();
    	
    	root.setTop(header);
    	root.setCenter(main);
    	
    	savebtn.setOnAction(event -> {
    		if (TranType.getText() != "" && !TranType.getText().contains(",")) {
    			boolean newName = true;
    			
    			for(TransactionType element: transactiontypes) {
    				if(element.getTransactionName().equals(TranType.getText())) {
    					newName = false;
    				}
    			}
                
                if (newName) {
                	TransactionType newType = new TransactionType(TranType.getText());
                    transactiontypes.add(newType);
                    TranType.clear();
                    errorlbl.setText("");
                	stage.setScene(mainScene);
                }
                else {
                	errorlbl.setText("Please enter a unique type name.");
                }
    		}
    		else {
    			errorlbl.setText("Please enter a valid type name.");
    		}
    	});
    	cancelbtn.setOnAction(event -> {
    		errorlbl.setText("");
    		TranType.clear();
    		stage.setScene(mainScene);
    	});
    	returnbtn.setOnAction(event -> {
    		errorlbl.setText("");
    		TranType.clear();
    		stage.setScene(mainScene);
    	});
    	
    	Scene TranTypePage = new Scene(root, 1000, 500);
    	
    	return TranTypePage;
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

    public void updateAccountList(GridPane table) {
        table.getChildren().clear();
        ArrayList<String> accountListStrings = new ArrayList<>();

        for (int i = 0; i < accounts.size(); i++) {
            // Formatted as Date, Name, Balance
            accountListStrings.add((accounts.get(i).toArrayList().get(1) + "," +
            		accounts.get(i).toArrayList().get(0) + "," +
            		accounts.get(i).toArrayList().get(2)));
        }

        Collections.sort(accountListStrings);
        for (int i = 0; i < accounts.size(); i++) {
            String[] accountDetails = accountListStrings.get(i).split(",");
            Label accountName = new Label(accountDetails[1]);
            Label accountDate = new Label(accountDetails[0]);
            String accountBalString = accountDetails[2];
            if (accountDetails[2].length() == 3) {
            	accountBalString = accountBalString + "0";
            }
            Label accountBalance = new Label(accountBalString);
            table.getChildren().addAll(accountName, accountDate, accountBalance);
            GridPane.setMargin(accountName, new Insets(0, 20, 0, 40));
            GridPane.setMargin(accountDate, new Insets(0, 20, 0, 80));
            GridPane.setMargin(accountBalance, new Insets(0, 0, 0, 80));
            GridPane.setConstraints(accountName, 0, i);
            GridPane.setConstraints(accountDate, 1, i);
            GridPane.setConstraints(accountBalance, 2, i);
        }
    }
    
    @Override
    public void stop() {
    	accountDAL.fileDelete();
    	for(Account element: accounts) {
    		accountDAL.fileWriter(element.toArrayList());
    	}
    	transactionotypeDAL.fileDelete();
    	for(TransactionType element: transactiontypes) {
    		transactionotypeDAL.fileWriter(element.toArrayList());
    	}
    	transactionDAL.fileDelete();
    	for(Transaction element: transactions) {
    		transactionDAL.fileWriter(element.toArrayList());
    	}
    }

    public static void main(String[] args) {
        launch();
    }

}