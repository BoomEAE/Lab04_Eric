package com.mycompany.lab04_eric;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;


/**
 * JavaFX App
 */
public class App extends Application {
    private static int numDays = 0;
    private static int amountAirfare = 0;
    private static int amountCarRental = 0;
    private static int numMilesDriven = 0;
    private static int amountParkingFees = 0;
    private static int amountTaxiCharges = 0;
    private static int conRegFees = 0;
    private static int lodgingCharges = 0;
    private static int isValidCounter = 0;
    
    @Override
    public void start(Stage mainStage) {
        
        //Creating BorderPane that will be at the root of every node
        BorderPane root = new BorderPane();
        
        //Creating the gridpane that will grid nodes
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        
        //Creating VBoxs
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        VBox vbox3 = new VBox();
        VBox vbox4 = new VBox();
        VBox vbox5 = new VBox();
        VBox vbox6 = new VBox();
        VBox vbox7 = new VBox();
        VBox vbox8 = new VBox();
        VBox expensesPanel = new VBox();
        
        //Creating HBox
        HBox buttonsContainer = new HBox();
        
        //Labels
        Label numDaysLabel = new Label("Number of days on the trip");
        Label validNumDaysLabel = new Label();
        Label amoutAirfareLabel = new Label("Amount of airfare, if any");
        Label validAmoutAirfareLabel = new Label();
        Label amoutCarRentalLabel = new Label("Amount of car rental fees, if any");
        Label validAmoutCarRentalLabel = new Label();
        Label numMilesDrivenLabel = new Label("Number of miles driven, if a private vehicle was used");
        Label validNumMilesDrivenLabel = new Label();
        Label amountParkingLabel = new Label("Amount of parking fees, if any");
        Label validAmountParkingLabel = new Label();
        Label taxiChargesLabel = new Label("Amount of taxi charges, if any");
        Label validTaxiChargesLabel = new Label();
        Label conRegFeeLabel = new Label("Conference or seminar registration fees, if any");
        Label validConRegFeeLabel = new Label();
        Label lodgingChagesLabel = new Label("Lodging charges, per night");
        Label validLodgingChagesLabel = new Label();
        Label totalExpensesLabel = new Label(); 
        Label totalAllowableLabel = new Label(); 
        Label excessLabel = new Label(); 
        Label amountSavedLabel = new Label();
        
        //TextFields
        TextField numDaysField = new TextField();
        TextField amoutAirfareField = new TextField();
        TextField amoutCarRentalField = new TextField();
        TextField numMilesDrivenField = new TextField();
        TextField amountParkingField = new TextField();
        TextField taxiChargesField = new TextField();
        TextField conRegFeeFIeld = new TextField();
        TextField lodgingChagesField = new TextField();
        
        //Buttons for validation
        Button calculateExpensesBtn = new Button("calculate");
        Button clearFieldsBtn = new Button("clear");
        
        //Adding appropriate nodes to their VBox
        vbox1.getChildren().addAll(numDaysLabel, numDaysField, validNumDaysLabel);
        vbox2.getChildren().addAll(amoutAirfareLabel, amoutAirfareField, validAmoutAirfareLabel);
        vbox3.getChildren().addAll(amoutCarRentalLabel, validAmoutCarRentalLabel, amoutCarRentalField);
        vbox4.getChildren().addAll(numMilesDrivenLabel, validNumMilesDrivenLabel, numMilesDrivenField);
        vbox5.getChildren().addAll(amountParkingLabel, validAmountParkingLabel, amountParkingField);
        vbox6.getChildren().addAll(taxiChargesLabel, validTaxiChargesLabel, taxiChargesField);
        vbox7.getChildren().addAll(conRegFeeLabel, validConRegFeeLabel, conRegFeeFIeld);
        vbox8.getChildren().addAll(lodgingChagesLabel, validLodgingChagesLabel, lodgingChagesField);
        expensesPanel.getChildren().addAll(totalExpensesLabel, totalAllowableLabel, excessLabel, amountSavedLabel);
        buttonsContainer.getChildren().addAll(calculateExpensesBtn, clearFieldsBtn);
        
        //Add to gridPane
        gridPane.add(vbox1, 0, 0);
        gridPane.add(vbox2, 0, 1);
        gridPane.add(vbox3, 0, 2);
        gridPane.add(vbox4, 0, 3);
        gridPane.add(vbox5, 0, 4);
        gridPane.add(vbox6, 0, 5);
        gridPane.add(vbox7, 0, 6);
        gridPane.add(vbox8, 0, 7);
        gridPane.add(expensesPanel, 0, 8);
        gridPane.add(buttonsContainer, 0, 9);
        
        //
        numDaysField.setOnKeyTyped(event -> ValidateNum(calculateExpensesBtn, numDaysField));
        amoutAirfareField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutAirfareField));
        amoutCarRentalField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutCarRentalField));
        numMilesDrivenField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, numMilesDrivenField));
        amountParkingField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amountParkingField));
        taxiChargesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, taxiChargesField));
        conRegFeeFIeld.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, conRegFeeFIeld));
        lodgingChagesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, lodgingChagesField));
        
        //
        calculateExpensesBtn.setOnAction(event -> {
            double totalExpenses = numDays + amountAirfare + amountCarRental+ 0.27*numMilesDriven+ amountParkingFees+ amountTaxiCharges+ conRegFees+ lodgingCharges;
            double totalAllowable = 1000.00;
            double excess = 0.0;
            double amountSaved = 0.0;
            
            if(amountParkingFees <= numDays * 10.0){
                amountSaved += (numDays * 10.0) - amountParkingFees;
            }else{
                excess += amountParkingFees - (numDays * 10.0);
            }
            
            if(amountTaxiCharges <= numDays * 20.0){
                amountSaved += (numDays * 20.0) - amountTaxiCharges;
            }else{
                excess += amountTaxiCharges - (numDays * 20.0);
            }
            
            if(lodgingCharges <= numDays * 95.0){
                amountSaved += (numDays * 95.0) - lodgingCharges;
            }else{
                excess += lodgingCharges - (numDays * 95.0);
            }
            
            totalExpensesLabel.setText("Total expenses: " + Double.toString(totalExpenses) + "$");
            totalExpensesLabel.setId("label2");
            totalAllowableLabel.setText("Total allowable: " + Double.toString(totalAllowable) + "$");
            totalAllowableLabel.setId("label2");
            excessLabel.setText("Total excess: " + Double.toString(excess) + "$");
            excessLabel.setId("label2");
            amountSavedLabel.setText("Total saved: " + Double.toString(amountSaved) + "$");
            amountSavedLabel.setId("label2");
            
        });
        
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void ValidateNum(Button validateBtn, TextField textField){
        try{
                numDays = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
    }
    
    public static void ValidateAmount(Button validateBtn, TextField textField){
        try{
            amountAirfare = Integer.parseInt(textField.getText());
            isValidCounter += 1;
        }catch(NumberFormatException e){
            System.out.println("BAD");
            isValidCounter -= 1;
        }
        ValidateFields(validateBtn);
    }
    
    public static void ValidateFields(Button validateBtn){
        if(isValidCounter == 8){
            validateBtn.setDisable(false);
        }else{
            validateBtn.setDisable(true);
        }
    }

}