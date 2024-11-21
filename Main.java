package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
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
import javafx.scene.layout.ColumnConstraints;
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
    ArrayList<ScheduledTransaction> scheTransactions = new ArrayList<ScheduledTransaction>();

    DAL accountDAL = new DAL("Accounts");
    DAL transactionotypeDAL = new DAL("TransactionTypes");
    DAL transactionDAL = new DAL("Transactions");
    DAL scheduledtransactionDAL = new DAL("ScheduledTransactions");

    @Override
    public void start(Stage stage) throws IOException {
        try {
            for (ArrayList<String> element : accountDAL.fileReader()) {
                accounts.add(new Account(element));
            }
            for (ArrayList<String> element : transactionotypeDAL.fileReader()) {
                transactiontypes.add(new TransactionType(element));
            }
            for (ArrayList<String> element : transactionDAL.fileReader()) {
                transactions.add(new Transaction(element));
            }
			for(ArrayList<String> element: scheduledtransactionDAL.fileReader()) {
				scheTransactions.add(new ScheduledTransaction(element));
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
            Button trantypebtn = new Button("Create Transaction Type");
            Button tranlistbtn  = new Button("Show Transactions");
            Button schedulebtn = new Button("Create Scheduled Transaction");
            Button upcomingbtn = new Button("Show Scheduled Transactions");


            acclbl.setFont(normal);
            tranlbl.setFont(normal);
            accbtn.setFont(button);
            tranbtn.setFont(button);
            schedulebtn.setFont(button);
            trantypebtn.setFont(button);
            upcomingbtn.setFont(button);
            tranlistbtn.setFont(button);

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
            
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(33.33);
            col1.setHalignment(HPos.CENTER);

            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(33.33);
            col2.setHalignment(HPos.CENTER);

            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(33.33);
            col3.setHalignment(HPos.CENTER);
            
            accLabelsBox.getColumnConstraints().addAll(col1, col2, col3);
            accDetailsBox.getColumnConstraints().addAll(col1, col2, col3);

            // Filling Style Nodes
            nav.getChildren().addAll(home, notif);
            menu.getChildren().addAll(acclbl, accbtn, tranlbl, tranbtn, trantypebtn,
            		tranlistbtn, schedulebtn, upcomingbtn);
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

            updateGridPane(accDetailsBox, "", accounts);

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
            schedulebtn.setOnAction(event -> {
                Scene scheTransPage = schestart(stage, null);
                primaryStage.setScene(scheTransPage);
                errorlbl.setText("");
                accNamefld.clear();
                datepkr.setValue(LocalDate.now());
                balnum.setText("0.00");
            });
            upcomingbtn.setOnAction(event -> {
            	Scene ShowScheTran = showScheStart(stage);
            	primaryStage.setScene(ShowScheTran);
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
            tranlistbtn.setOnAction(event -> {
            	Scene TranListPage = tranListStart(stage);
            	primaryStage.setScene(TranListPage);
            });
            savebtn.setOnAction(event -> {
                try {
                    double passBalNum = Double.parseDouble(balnum.getText());

                    if (!accNamefld.getText().isEmpty() && !accNamefld.getText().contains(",")) {

                        boolean newName = true;

                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).getAccName().equals(accNamefld.getText())) {
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
                            updateGridPane(accDetailsBox, "", accounts);
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
            for (TransactionType element : transactiontypes) {
                Tranaccfld.getItems().add(element.getTransactionName());
            }
            if (transactiontypes.size() > 0) {
                Tranaccfld.setValue(transactiontypes.get(0).getTransactionName());
            }

            Label Tranacclbl2 = new Label("Account Name");
            Tranacclbl2.setFont(normal);
            ComboBox<String> Tranaccfld2 = new ComboBox();
            for (Account element : accounts) {
                Tranaccfld2.getItems().add(element.getAccName());
            }
            if (accounts.size() > 0) {
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
                    if (Double.parseDouble(Tranpaynum.getText()) == 0.00
                            && Double.parseDouble(Trandepnum.getText()) == 0.00) {
                        Tranerrorlbl.setText("Payment or Deposit field empty");
                    } else {
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
                } else {
                    Tranerrorlbl.setText("Description invalid");
                }
            });

            return TransactionPage;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Scene tranListStart(Stage stage) {
        try {
            // Font
            Font smaller = new Font("Georgia", 15);
           
            // Return Button
            Button returnbtn = new Button("return");
            Button searchbtn = new Button("Search");
            
            TextField seachStringfld = new TextField();
            
            
            HBox header = new HBox(20);
            header.getChildren().addAll(returnbtn, searchbtn, seachStringfld);
            header.setPadding(new Insets(10));
            
            // Set background color
            header.setStyle("-fx-background-color: #dcebfc;");

            // Header Label
            Label tranBoxlbl = new Label("Transactions");
            tranBoxlbl.setFont(new Font("Georgia", 20));
            HBox tranListMainHeader = new HBox(tranBoxlbl);
            tranListMainHeader.setAlignment(Pos.CENTER);
            tranBoxlbl.setPadding(new Insets(20));
            
            // Creating Table Header Layout
            GridPane tranLabelsBox = new GridPane();
            
            // Column Constraints (percentages for consistent alignment)
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(16.67);
            col1.setHalignment(HPos.CENTER);  // acc name

            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(16.67);
            col2.setHalignment(HPos.CENTER); // transaction type

            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(16.67);
            col3.setHalignment(HPos.CENTER); // date

            ColumnConstraints col4 = new ColumnConstraints();
            col4.setPercentWidth(16.67);
            col4.setHalignment(HPos.CENTER); // desc

            ColumnConstraints col5 = new ColumnConstraints();
            col5.setPercentWidth(16.67);
            col5.setHalignment(HPos.CENTER); // payment amount

            ColumnConstraints col6 = new ColumnConstraints();
            col6.setPercentWidth(16.67);
            col6.setHalignment(HPos.CENTER); // deposit amount

            tranLabelsBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
            
            // Create Labels
            Label tranBoxNamelbl = new Label("Account Name");
            Label tranBoxTypelbl = new Label("Transaction Type");
            Label tranBoxDatebl = new Label("Date");
            Label tranBoxDesclbl = new Label("Description");
            Label tranBoxPaylbl = new Label("Payment Amount");
            Label tranBoxDeplbl = new Label("Deposit Amount");
            
            Label[] labelsList = {tranBoxNamelbl, tranBoxTypelbl, tranBoxDatebl, tranBoxDesclbl, tranBoxPaylbl, tranBoxDeplbl};
            for (Label i : labelsList) {
            	i.setFont(smaller);
            	i.setWrapText(true);
            }
        
            // Set headers
            tranLabelsBox.add(tranBoxNamelbl, 0, 0);
            tranLabelsBox.add(tranBoxTypelbl, 1, 0);
            tranLabelsBox.add(tranBoxDatebl, 2, 0);
            tranLabelsBox.add(tranBoxDesclbl, 3, 0);
            tranLabelsBox.add(tranBoxPaylbl, 4, 0);
            tranLabelsBox.add(tranBoxDeplbl, 5, 0);
            
            tranLabelsBox.setAlignment(Pos.CENTER);
            tranLabelsBox.setPadding(new Insets(10));
            
            // Creating Table
            GridPane tranDetailsBox = new GridPane();
            tranDetailsBox.setAlignment(Pos.CENTER);
            tranDetailsBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);

            // Populate table
            updateGridPane(tranDetailsBox, seachStringfld.getText(), transactions);

            // Table Creation
            VBox tranListBox = new VBox(10);
            tranListBox.getChildren().addAll(tranLabelsBox, tranDetailsBox);
            tranListBox.setAlignment(Pos.TOP_CENTER);
            tranListBox.setPadding(new Insets(20));

            // Main layout
            BorderPane main = new BorderPane();
            main.setTop(tranListMainHeader);
            main.setCenter(tranListBox);

            BorderPane root = new BorderPane();
            root.setTop(header);
            root.setCenter(main);
            
            searchbtn.setOnAction(event -> {
            	updateGridPane(tranDetailsBox, seachStringfld.getText(), transactions);
            });

            // Return button action
            returnbtn.setOnAction(event -> {
                stage.setScene(mainScene);
            });

            Scene TranListPage = new Scene(root, 1000, 500);
            return TranListPage;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Scene schestart(Stage stage, ScheduledTransaction editable) {
        // Fonts
        Font normal = new Font("Georgia", 20);

        // Buttons
        Button Tranreturnbtn = new Button("return");
        Button Transavebtn = new Button("save");
        Button Trancancelbtn = new Button("cancel");

        // Create label shelf, and input shelf
        Label Tranpagelbl = new Label("Define New Schedule Transaction");
        Tranpagelbl.setFont(normal);

        Label Transchlbl = new Label("Schedule's Name");
        Transchlbl.setFont(normal);
        TextField Transchfld = new TextField();

        Label Tranacclbl2 = new Label("Account");
        Tranacclbl2.setFont(normal);
        ComboBox<String> Tranaccfld2 = new ComboBox();
        for (Account element : accounts) {
            Tranaccfld2.getItems().add(element.getAccName());
        }
        if (accounts.size() > 0) {
            Tranaccfld2.setValue(accounts.get(0).getAccName());
        }

        Label Trantypelbl = new Label("Transaction Type");
        Trantypelbl.setFont(normal);
        ComboBox<String> Trantypefld = new ComboBox();
        for (TransactionType element : transactiontypes) {
            Trantypefld.getItems().add(element.getTransactionName());
        }
        if (transactiontypes.size() > 0) {
            Trantypefld.setValue(transactiontypes.get(0).getTransactionName());
        }

        Label Trandesclbl = new Label("Frequency");
        Trandesclbl.setFont(normal);
        ComboBox<String> comboBox = new ComboBox();
        ObservableList<String> options = FXCollections.observableArrayList(
                "Monthly");
        comboBox.setItems(options);
        comboBox.setValue("Monthly");

        Label Trandatelbl = new Label("Due Date");
        Trandatelbl.setFont(normal);
        TextField Trandatefld = new TextField();

        Label Tranpaylbl = new Label("Payment Amount");
        Tranpaylbl.setFont(normal);
        TextField Tranpaynum = new TextField();
        Tranpaynum.setTextFormatter(new TextFormatter<Double>(new FixedDecimalConverter(2), 0.0));

        Label Tranerrorlbl = new Label("");
        Tranerrorlbl.setStyle("-fx-text-fill: red;");

        VBox labels = new VBox(22);
        VBox fields = new VBox(20);

        // Put labels and fields into their style boxes

        labels.getChildren().addAll(Transchlbl, Tranacclbl2, Trantypelbl, Trandatelbl, Trandesclbl, Tranpaylbl);
        fields.getChildren().addAll(Transchfld, Tranaccfld2, Trantypefld, Trandatefld, comboBox, Tranpaynum);
        
        if(editable != null) {
        	ArrayList<String> temp = editable.toArrayList();
        	Transchfld.setText(temp.remove(0));
        	Tranaccfld2.setValue(temp.remove(0));
        	Trantypefld.setValue(temp.remove(0));
        	comboBox.setValue(temp.remove(0));
        	Trandatefld.setText(temp.remove(0));
        	Tranpaynum.setText(temp.remove(0));
        }

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

        Scene scheTransPage = new Scene(TranaccRoot, 1000, 500);
        Tranreturnbtn.setOnAction(event -> {
            stage.setScene(mainScene);
        });
        Trancancelbtn.setOnAction(event -> {
            stage.setScene(mainScene);
        });
        Transavebtn.setOnAction(event -> {
            try {
                String dueDateStr = Trandatefld.getText();
                String scheduleName = Transchfld.getText();
                double payment = 0.00;
                if (Transchfld.getText() != "" && !Transchfld.getText().contains(",")) {
                    if (Double.parseDouble(Tranpaynum.getText()) == 0.00) {
                        Tranerrorlbl.setText("Payment field empty");
                    }
                    
                    Boolean duplicate = false;
                    for(ScheduledTransaction element: scheTransactions) {
                    	if(element.getName().equals(scheduleName)) {
                    		duplicate = true;
                    	}
                    }
                    
                    if (editable == null && duplicate) {
                        Tranerrorlbl.setText("Schedule name already exists");
                        return;
                    }

                    int dueDate;
                    try {
                        dueDate = Integer.parseInt(dueDateStr);
                        if (dueDate < 1 || dueDate > 31) {
                            Tranerrorlbl.setText("Due daye must be between 1 and 31.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Tranerrorlbl.setText("Invalid due date");
                        return;
                    }
                    try {
                        payment = Double.parseDouble(Tranpaynum.getText());
                    } catch (NumberFormatException e) {
                        Tranerrorlbl.setText("Payment field invalid");
                        return;
                    }

                    ArrayList<String> scheduledData = new ArrayList<>();
                    scheduledData.add(Transchfld.getText());
                    scheduledData.add(Tranaccfld2.getValue());
                    scheduledData.add(Trantypefld.getValue());
                    scheduledData.add(comboBox.getValue());
                    scheduledData.add(Trandatefld.getText());
                    scheduledData.add(String.valueOf(payment));
                    
                    ScheduledTransaction temp = new ScheduledTransaction(scheduledData);
                    if(editable != null) {
                    	scheTransactions.remove(editable);
                    }
                    scheTransactions.add(temp);
                    

                    Tranerrorlbl.setText("Scheduled transaction saved!");

                    stage.setScene(mainScene);


                    Transchfld.clear();
                    Tranpaynum.setText("0.00");
                    Tranerrorlbl.setText("");
                    Trandatefld.clear();

                }
                else if (Transchfld.getText().contains(",")) {
                	Tranerrorlbl.setText("Schedule name is invalid");
                }
                else {
                    Tranerrorlbl.setText("Schedule name is empty");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return scheTransPage;

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

                for (TransactionType element : transactiontypes) {
                    if (element.getTransactionName().equals(TranType.getText())) {
                        newName = false;
                    }
                }

                if (newName) {
                    TransactionType newType = new TransactionType(TranType.getText());
                    transactiontypes.add(newType);
                    TranType.clear();
                    errorlbl.setText("");
                    stage.setScene(mainScene);
                } else {
                    errorlbl.setText("Please enter a unique type name.");
                }
            } else {
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

    public Scene showScheStart(Stage stage) {
        Font normal = new Font("Georgia", 20);
        
        Label titlelbl = new Label("Scheduled Transactions");
        Label scheNamelbl = new Label("Name");
        Label accNamelbl = new Label("Account");
        Label tranTypelbl = new Label("Type");
        Label frequencylbl = new Label("Frequency");
        Label dueDatelbl = new Label("Due Date");
        Label amountlbl = new Label("Amount");
        Label emptylbl = new Label("");
        titlelbl.setFont(normal);
        scheNamelbl.setFont(normal);
        accNamelbl.setFont(normal);
        tranTypelbl.setFont(normal);
        frequencylbl.setFont(normal);
        dueDatelbl.setFont(normal);
        amountlbl.setFont(normal);
        Button returnbtn = new Button("return");
        Button searchbtn = new Button("Search");
        
        TextField seachStringfld = new TextField();
        
        GridPane scheTranDetailsBox = new GridPane();
        GridPane scheTranLabelsBox = new GridPane();
        
        // Column Constraints (percentages for consistent alignment)
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(14.2);
        col1.setHalignment(HPos.CENTER);  // acc name

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(14.2);
        col2.setHalignment(HPos.CENTER); // transaction type

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(14.2);
        col3.setHalignment(HPos.CENTER); // date

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(14.2);
        col4.setHalignment(HPos.CENTER); // desc

        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(14.2);
        col5.setHalignment(HPos.CENTER); // payment amount

        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(14.2);
        col6.setHalignment(HPos.CENTER); // deposit amount
        
        ColumnConstraints col7 = new ColumnConstraints();
        col6.setPercentWidth(14.2);
        col6.setHalignment(HPos.CENTER); // button column

        scheTranLabelsBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        scheTranDetailsBox.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);
        scheTranDetailsBox.setVgap(10);
        
        scheTranLabelsBox.getChildren().addAll(scheNamelbl, accNamelbl, tranTypelbl, frequencylbl, dueDatelbl, amountlbl, emptylbl);
        
        // Position Style Nodes
        GridPane.setConstraints(scheNamelbl, 0, 1);
        GridPane.setConstraints(accNamelbl, 1, 1);
        GridPane.setConstraints(tranTypelbl, 2, 1);
        GridPane.setConstraints(frequencylbl, 3, 1);
        GridPane.setConstraints(dueDatelbl, 4, 1);
        GridPane.setConstraints(amountlbl, 5, 1);
        GridPane.setConstraints(emptylbl, 6, 1);
        GridPane.setMargin(scheNamelbl, new Insets(20));
        GridPane.setMargin(accNamelbl, new Insets(20));
        GridPane.setMargin(tranTypelbl, new Insets(20));
        GridPane.setMargin(frequencylbl, new Insets(20));
        GridPane.setMargin(dueDatelbl, new Insets(20));
        GridPane.setMargin(amountlbl, new Insets(20));
        GridPane.setMargin(emptylbl, new Insets(20));
        
        VBox scheTranBox = new VBox();
        scheTranBox.setAlignment(Pos.TOP_CENTER);
        scheTranBox.setPadding(new Insets(30));
        scheTranDetailsBox.setAlignment(Pos.TOP_CENTER);
        scheTranLabelsBox.setAlignment(Pos.TOP_CENTER);
        scheTranBox.getChildren().addAll(titlelbl, scheTranLabelsBox, scheTranDetailsBox);
        
        HBox returnBox = new HBox(20);
        
        returnBox.getChildren().addAll(returnbtn, searchbtn, seachStringfld);
        returnBox.setPadding(new Insets(10));
        
        updateGridPane(scheTranDetailsBox, seachStringfld.getText(), scheTransactions);
        
        for(int i = 0; i < scheTranDetailsBox.getRowCount(); ++i) {
        	Button button = new Button("EDIT");
        	scheTranDetailsBox.add(button, 6, i);
            button.setOnAction(event -> {
            	Scene scheTransPage = schestart(stage, scheTransactions.get(scheTranDetailsBox.getRowIndex(button)));
            	stage.setScene(scheTransPage);
            });
        }
        
        // Sets BG colors
        returnBox.setStyle("-fx-background-color: #dcebfc;");
        // Scene creator for Transaction page
        BorderPane scheTranRoot = new BorderPane();
        scheTranRoot.setTop(returnBox);
        scheTranRoot.setCenter(scheTranBox);
        
        Scene showScheTranPage = new Scene(scheTranRoot, 1000, 500);
        
        searchbtn.setOnAction(event -> {
        	updateGridPane(scheTranDetailsBox, seachStringfld.getText(), scheTransactions);
        });
        
        returnbtn.setOnAction(event -> {
            stage.setScene(mainScene);
        });
    	
    	return showScheTranPage;
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
    
    public void updateGridPane(GridPane table, String searchString, ArrayList<? extends CSVWritable> list) {
    	table.getChildren().clear();
    	Collections.sort(list);
    	for(int i = 0; i < list.size(); ++i) {
    		if(list.get(i).searchString().toLowerCase().contains(searchString.toLowerCase())) {
    			ArrayList<String> details = list.get(i).toArrayList();
    			for(int j = 0; j < details.size(); ++j) {
    				String str = details.get(j);
    				if(str.length() > 2 && str.charAt(str.length() - 2) == '.') {
    					str = str.concat("0");
    				}
    				Label temp = new Label(str);
    				table.getChildren().add(temp);
    				GridPane.setConstraints(temp, j, i);
    			}
    		}
    	}
    }
    
    @Override
    public void stop() {
        accountDAL.fileDelete();
        for (Account element : accounts) {
            accountDAL.fileWriter(element.toArrayList());
        }
        transactionotypeDAL.fileDelete();
        for (TransactionType element : transactiontypes) {
            transactionotypeDAL.fileWriter(element.toArrayList());
        }
        transactionDAL.fileDelete();
        for (Transaction element : transactions) {
            transactionDAL.fileWriter(element.toArrayList());
        }
    	scheduledtransactionDAL.fileDelete();
    	for(ScheduledTransaction element: scheTransactions) {
    		scheduledtransactionDAL.fileWriter(element.toArrayList());
    	}
    }

    public static void main(String[] args) {
        launch();
    }

}
