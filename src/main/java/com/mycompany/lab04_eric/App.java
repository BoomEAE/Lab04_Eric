package com.mycompany.lab04_eric;

//Github repo link: https://github.com/BoomEAE/Lab04_Eric.git

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import java.lang.Math;

/**
 * JavaFX App
 * @author Eirc Andre Evsei
 */
public class App extends Application {
    
    //Integers
    private static int numDays = 0;
    private static int isValidCounter = 0;
                //0: airfare amount, 1: car rental amount, 2: num of miles, 3: parking fees, 4: taxiChargesLabel, 5: conRegFees, 6: lodgingCharges
    private static double[] amounts = {0, 0, 0, 0, 0, 0, 0};
    
    //Count caps is a way for the program to know that each field as been filled 
    private static int[] countCaps = {0, 0, 0, 0, 0, 0, 0, 0};
    
    @Override
    public void start(Stage mainStage) {
        
        //Creating BorderPane that will be at the root of every node
        BorderPane root = new BorderPane();
        
        //Initial gridpane setup that will put nodes into grids
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        
        //Creating VBoxs
        VBox row1 = new VBox();
        VBox row2 = new VBox();
        VBox row3 = new VBox();
        VBox row4 = new VBox();
        VBox row5 = new VBox();
        VBox row6 = new VBox();
        VBox row7 = new VBox();
        VBox row8 = new VBox();
        VBox expensesPanel = new VBox();
        
        //Creating HBox
        HBox buttonsContainer = new HBox();
        
        //Labels
        Label numDaysLabel = new Label("Number of days on the trip:");
        Label validNumDaysLabel = new Label();
        Label amountAirfareLabel = new Label("Amount of airfare, if any:");
        Label validAmoutAirfareLabel = new Label();
        Label amoutCarRentalLabel = new Label("Amount of car rental fees, if any:");
        Label validAmoutCarRentalLabel = new Label();
        Label numMilesDrivenLabel = new Label("Number of miles driven, if a private vehicle was used:");
        Label validNumMilesDrivenLabel = new Label();
        Label amountParkingLabel = new Label("Amount of parking fees, if any:");
        Label validAmountParkingLabel = new Label();
        Label taxiChargesLabel = new Label("Amount of taxi charges, if any:");
        Label validTaxiChargesLabel = new Label();
        Label conRegFeeLabel = new Label("Conference or seminar registration fees, if any:");
        Label validConRegFeeLabel = new Label();
        Label lodgingChargesLabel = new Label("Lodging charges, per night:");
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
        
        //Button to calulate expenses
        Button calculateExpensesBtn = new Button("calculate");
        
        //Initiale style of the button
        calculateExpensesBtn.setStyle("-fx-background-radius: 2;-fx-background-color:#FFD700;");
        
        //Disable the button since not all fields are filled
        calculateExpensesBtn.setDisable(true);
        
        //Adding appropriate nodes to their VBox
        row1.getChildren().addAll(numDaysLabel, numDaysField, validNumDaysLabel);
        row2.getChildren().addAll(amountAirfareLabel, amoutAirfareField, validAmoutAirfareLabel);
        row3.getChildren().addAll(amoutCarRentalLabel, amoutCarRentalField, validAmoutCarRentalLabel);
        row4.getChildren().addAll(numMilesDrivenLabel, numMilesDrivenField, validNumMilesDrivenLabel);
        row5.getChildren().addAll(amountParkingLabel, amountParkingField, validAmountParkingLabel);
        row6.getChildren().addAll(taxiChargesLabel, taxiChargesField, validTaxiChargesLabel);
        row7.getChildren().addAll(conRegFeeLabel, conRegFeeFIeld, validConRegFeeLabel);
        row8.getChildren().addAll(lodgingChargesLabel, lodgingChagesField, validLodgingChagesLabel);
        expensesPanel.getChildren().addAll(totalExpensesLabel, totalAllowableLabel, excessLabel, amountSavedLabel);
        buttonsContainer.getChildren().addAll(calculateExpensesBtn);
        
        //Add to gridPane
        AddToMainContainer(gridPane, row1, row2, row3, row4, row5, row6, row7, row8, expensesPanel, buttonsContainer);
        
        //Each time you type in a field, it verifies and validates that field
        numDaysField.setOnKeyTyped(event -> ValidateNum(calculateExpensesBtn, numDaysField, validNumDaysLabel, 0));
        amoutAirfareField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutAirfareField, validAmoutAirfareLabel, 1));
        amoutCarRentalField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutCarRentalField, validAmoutCarRentalLabel, 2));
        numMilesDrivenField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, numMilesDrivenField, validNumMilesDrivenLabel, 3));
        amountParkingField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amountParkingField, validAmountParkingLabel, 4));
        taxiChargesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, taxiChargesField, validTaxiChargesLabel, 5));
        conRegFeeFIeld.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, conRegFeeFIeld, validConRegFeeLabel, 6));
        lodgingChagesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, lodgingChagesField, validLodgingChagesLabel, 7));
        
        //When calculate button is pressed, it will calculate the expenses
        calculateExpensesBtn.setOnAction(event -> {
            
            //Expenses calculation
            double totalExpenses = numDays + amounts[0] + amounts[1] + 0.27*amounts[2] + amounts[3] + amounts[4] + amounts[5] + (amounts[6] * 95.0);
            
            //Allowable expenses calculation
            double totalAllowable = (37.0 * numDays) + Math.min(amounts[3], (10.0 * numDays)) + Math.min(amounts[4], (20.0 * numDays)) + Math.min(amounts[6], (95.0 * numDays)) + (0.27 * numDays);
            
            double excess = 0.0;
            double saved = 0.0;
            
            if(totalExpenses > totalAllowable){
                excess = totalExpenses - totalAllowable;
            }else{
                saved = totalAllowable - totalExpenses;
            }
            
            //
            totalExpensesLabel.setText(String.format("Total expenses: %.2f$", totalExpenses));
            totalExpensesLabel.setStyle("-fx-text-fill:blue");
            totalAllowableLabel.setText(String.format("Total allowable: %.2f$", totalAllowable));
            totalAllowableLabel.setStyle("-fx-text-fill:blue");
            excessLabel.setText(String.format("Total excess: %.2f$", excess));
            excessLabel.setStyle("-fx-text-fill:blue");
            amountSavedLabel.setText(String.format("Total saved: %.2f$", saved));
            amountSavedLabel.setStyle("-fx-text-fill:blue");
            
        });
        
        //Center the gridPane to the center of the root
        root.setCenter(gridPane);
        
        //Create the scene and show it
        Scene scene = new Scene(root, 600, 800);
        mainStage.setScene(scene);
        mainStage.setTitle("Travel Expenses Calculator");
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    //Add nodes to the main container grid
    public static void AddToMainContainer(GridPane gridPane, VBox row1, VBox row2, VBox row3, VBox row4, VBox row5, VBox row6, VBox row7, VBox row8, VBox row9, HBox row10){
        gridPane.add(row1, 0, 0);
        gridPane.add(row2, 0, 1);
        gridPane.add(row3, 0, 2);
        gridPane.add(row4, 0, 3);
        gridPane.add(row5, 0, 4);
        gridPane.add(row6, 0, 5);
        gridPane.add(row7, 0, 6);
        gridPane.add(row8, 0, 7);
        gridPane.add(row9, 0, 8);
        gridPane.add(row10, 0, 9);
    }
    
    //Validate a certain number of days
    public static void ValidateNum(Button validateBtn, TextField textField, Label label, int countCapIndex){
        //We check for the lenght of the text field
        //if it's 0, then we can try to get the value that's contained inside
        //if that value is not a int, then we warn the user, reset the count for that specific field and change the text to red
        //If the textfield is of length 0, then -1 valid field, count reseted and label reseted to blank
        if(textField.getText().length() != 0){
            try{
                numDays = Integer.parseInt(textField.getText());
                if(countCaps[countCapIndex] != 1){
                    isValidCounter += 1;
                    countCaps[countCapIndex] = 1;
                    label.setText("Field as met the requirements and is valid");
                    label.setStyle("-fx-text-fill:green;");
                }
            }catch(NumberFormatException e){
                isValidCounter -= 1;
                countCaps[countCapIndex] = 0;
                label.setText("Invalid: not include special characters, letters &/or decimals");
                label.setStyle("-fx-text-fill:red;");
            }
        }else{
            isValidCounter -= 1;
            countCaps[countCapIndex] = 0;
            label.setText("");
        }
        
        //We validate that all fields have met the requirements
        ValidateFields(validateBtn);
    }
    
    //Validate an amount of something
    public static void ValidateAmount(Button validateBtn, TextField textField, Label label, int countCapIndex){
        
        //We check for the lenght of the text field
        //if it's 0, then we can try to get the value that's contained inside
        //if that value is not a double/int, then we warn the user, reset the count for that specific field and change the text to red
        //If the textfield is of length 0, then -1 valid field, count reseted and label reseted to blank
        if(textField.getText().length() != 0){
            try{
                amounts[countCapIndex-1] = Double.parseDouble(textField.getText());
                if(countCaps[countCapIndex] != 1){
                    isValidCounter += 1;
                    countCaps[countCapIndex] = 1;
                    label.setText("Field as met the requirements and is valid");
                    label.setStyle("-fx-text-fill:green;");
                }
            }catch(NumberFormatException e){
                isValidCounter -= 1;
                countCaps[countCapIndex] = 0;
                label.setText("Field invalid: not include letters &/or special characters");
                label.setStyle("-fx-text-fill:red;");
            }
        }else{
            isValidCounter -= 1;
            countCaps[countCapIndex] = 0;
            label.setText("");
        }
        
        //We validate that all fields have met the requirements
        ValidateFields(validateBtn);
    }
    
    //To validate that all textfields have been validated
    public static void ValidateFields(Button validateBtn){
        
        //If all fields are field and valid, we enable the button and change its style
        //if not, it stays disabled with the default style
        if(isValidCounter == 8){
            validateBtn.setDisable(false);
            validateBtn.setStyle("-fx-background-radius: 2;-fx-background-color:#FFD700;-fx-border-color:#2E7D32;-fx-border-width:2px;-fx-border-radius:8px;");
        }else if(isValidCounter < 8){
            validateBtn.setDisable(true);
            validateBtn.setStyle("-fx-background-radius: 2;-fx-background-color:#FFD700;");
        }
    }

}