package com.mycompany.lab04_eric;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        
        //Creating HBoxs
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();
        HBox hbox5 = new HBox();
        HBox hbox6 = new HBox();
        HBox hbox7 = new HBox();
        HBox hbox8 = new HBox();
        
        //Labels
        Label numDaysLabel = new Label("Number of days on the trip");
        Label amoutAirfareLabel = new Label("Amount of airfare, if any");
        Label amoutCarRentalLabel = new Label("Amount of car rental fees, if any");
        Label numMilesDrivenLabel = new Label("Number of miles driven, if a private vehicle was used");
        Label amountParkingLabel = new Label("Amount of parking fees, if any");
        Label taxiChargesLabel = new Label("Amount of taxi charges, if any");
        Label conRegFeeLabel = new Label("Conference or seminar registration fees, if any");
        Label lodgingChagesLabel = new Label("Lodging charges, per night");
        
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
        Button numDaysBtn = new Button("Validate");
        Button amoutAirfareBtn = new Button("Validate");
        Button amoutCarRentalBtn = new Button("Validate");
        Button numMilesDrivenBtn = new Button("Validate");
        Button amountParkingBtn = new Button("Validate");
        Button taxiChargesBtn = new Button("Validate");
        Button conRegFeeBtn = new Button("Validate");
        Button lodgingChagesBtn = new Button("Validate");
        Button validateExpenses = new Button("Validate");
        validateExpenses.setDisable(true);
        
        //
        validateExpenses.setOnKeyTyped(event -> ValidateFields(validateExpenses));
        
        //Adding to hboxs
        hbox1.getChildren().addAll(numDaysField, numDaysBtn);
        hbox2.getChildren().addAll(amoutAirfareField, amoutAirfareBtn);
        hbox3.getChildren().addAll(amoutCarRentalField, amoutCarRentalBtn);
        hbox4.getChildren().addAll(numMilesDrivenField, numMilesDrivenBtn);
        hbox5.getChildren().addAll(amountParkingField, amountParkingBtn);
        hbox6.getChildren().addAll(taxiChargesField, taxiChargesBtn);
        hbox7.getChildren().addAll(conRegFeeFIeld, conRegFeeBtn);
        hbox8.getChildren().addAll(lodgingChagesField, lodgingChagesBtn);
        
        //Add to gridPane
        gridPane.add(numDaysLabel, 0, 0);
        gridPane.add(hbox1, 0, 1);
        gridPane.add(amoutAirfareLabel, 0, 2);
        gridPane.add(hbox2, 0, 3);
        gridPane.add(amoutCarRentalLabel, 0, 4);
        gridPane.add(hbox3, 0, 5);
        gridPane.add(numMilesDrivenLabel, 0, 6);
        gridPane.add(hbox4, 0, 7);
        gridPane.add(amountParkingLabel, 0, 8);
        gridPane.add(hbox5, 0, 9);
        gridPane.add(taxiChargesLabel, 0, 10);
        gridPane.add(hbox6, 0, 11);
        gridPane.add(conRegFeeLabel, 0, 12);
        gridPane.add(hbox7, 0, 13);
        gridPane.add(lodgingChagesLabel, 0, 14);
        gridPane.add(hbox8, 0, 15);
        gridPane.add(validateExpenses, 0, 16);
        
        //
        ValidateNumDays(numDaysBtn, validateExpenses, numDaysField);
        ValidateAmountAirfare(amoutAirfareBtn, validateExpenses, amoutAirfareField);
        validateAmountCarRental(amoutCarRentalBtn, validateExpenses, amoutCarRentalField);
        validateNumMilesDriven(numMilesDrivenBtn, validateExpenses, numMilesDrivenField);
        validateAmountParking(amountParkingBtn, validateExpenses, amountParkingField);
        validateAmountTaxiCharges(taxiChargesBtn, validateExpenses, taxiChargesField);
        validateConRegFees(conRegFeeBtn, validateExpenses, conRegFeeFIeld);
        validateLodgingCharges(lodgingChagesBtn, validateExpenses, lodgingChagesField);
        
        //
        validateExpenses.setOnAction(event -> {
            double totalExpenses = numDays + amountAirfare + amountCarRental+ 0.27*numMilesDriven+ amountParkingFees+ amountTaxiCharges+ conRegFees+ lodgingCharges;
            double totalAllowable = 1000.00;
            double excess = 0;
            double amountSaved = 0;
            
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
            
            System.out.println(totalExpenses);
            System.out.println(totalAllowable);
            System.out.println(excess);
            System.out.println(amountSaved);
            
        });
        
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root, 500, 500);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void ValidateNumDays(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                numDays = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void ValidateAmountAirfare(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                amountAirfare = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateAmountCarRental(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                amountCarRental = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateNumMilesDriven(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                numMilesDriven = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateAmountParking(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                amountParkingFees = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateAmountTaxiCharges(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                amountTaxiCharges = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateConRegFees(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                conRegFees = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void validateLodgingCharges(Button validateFeldBtn, Button validateBtn, TextField textField){
        validateFeldBtn.setOnAction(event -> {
            try{
                lodgingCharges = Integer.parseInt(textField.getText());
                isValidCounter += 1;
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
            }
            ValidateFields(validateBtn);
        });
    }
    
    public static void ValidateFields(Button validateBtn){
        if(isValidCounter == 8){
            validateBtn.setDisable(false);
        }else{
            validateBtn.setDisable(true);
        }
    }

}