package com.mycompany.lab04_eric;

import javafx.application.Application;
import javafx.geometry.Pos;
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
    
    //
    private static int numDays = 0;
    private static int isValidCounter = 0;
    private static double[] amounts = {0, 0, 0, 0, 0, 0, 0};
    private static int[] countCaps = {0, 0, 0, 0, 0, 0, 0, 0};
    
    @Override
    public void start(Stage mainStage) {
        
        //Creating BorderPane that will be at the root of every node
        BorderPane root = new BorderPane();
        
        //Creating VBoxs
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);
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
        Label numDaysLabel = new Label("Number of days on the trip:");
        Label validNumDaysLabel = new Label();
        Label amoutAirfareLabel = new Label("Amount of airfare, if any:");
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
        Label lodgingChagesLabel = new Label("Lodging charges, per night:");
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
        calculateExpensesBtn.setStyle("-fx-background-radius: 2;-fx-background-color:#FFD700;");
        calculateExpensesBtn.setDisable(true);
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
        mainContainer.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, expensesPanel, buttonsContainer);
        
        //
        numDaysField.setOnKeyTyped(event -> ValidateNum(calculateExpensesBtn, numDaysField, 0));
        amoutAirfareField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutAirfareField, 1));
        amoutCarRentalField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amoutCarRentalField, 2));
        numMilesDrivenField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, numMilesDrivenField, 3));
        amountParkingField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, amountParkingField, 4));
        taxiChargesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, taxiChargesField, 5));
        conRegFeeFIeld.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, conRegFeeFIeld, 6));
        lodgingChagesField.setOnKeyTyped(event -> ValidateAmount(calculateExpensesBtn, lodgingChagesField, 7));
        
        //
        calculateExpensesBtn.setOnAction(event -> {
            double totalExpenses = numDays + amounts[0] + amounts[1] + 0.27*amounts[2] + amounts[3] + amounts[4] + amounts[5] + amounts[6];
            double totalAllowable = 1000.00;
            double excess = 0.0;
            double amountSaved = 0.0;
            
            if(amounts[3] <= numDays * 10.0){
                amountSaved += (numDays * 10.0) - amounts[3];
            }else{
                excess += amounts[3] - (numDays * 10.0);
            }
            
            if(amounts[4] <= numDays * 20.0){
                amountSaved += (numDays * 20.0) - amounts[4];
            }else{
                excess += amounts[4] - (numDays * 20.0);
            }
            
            if(amounts[6] <= numDays * 95.0){
                amountSaved += (numDays * 95.0) - amounts[6];
            }else{
                excess += amounts[6] - (numDays * 95.0);
            }
            
            totalExpensesLabel.setText("Total expenses: " + Double.toString(totalExpenses) + "$");
            totalExpensesLabel.setStyle("-fx-text-fill:red");
            totalAllowableLabel.setText("Total allowable: " + Double.toString(totalAllowable) + "$");
            totalAllowableLabel.setStyle("-fx-text-fill:red");
            excessLabel.setText("Total excess: " + Double.toString(excess) + "$");
            excessLabel.setStyle("-fx-text-fill:red");
            amountSavedLabel.setText("Total saved: " + Double.toString(amountSaved) + "$");
            amountSavedLabel.setStyle("-fx-text-fill:red");
            
        });
        
        root.setCenter(mainContainer);
        Scene scene = new Scene(root, 600, 800);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void ValidateNum(Button validateBtn, TextField textField, int countCapIndex){
        if(textField.getText().length() != 0){
            try{
                numDays = Integer.parseInt(textField.getText());
                if(countCaps[countCapIndex] != 1){
                    isValidCounter += 1;
                    countCaps[countCapIndex] = 1;
                }
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
                countCaps[countCapIndex] = 0;
            }
        }else{
            isValidCounter -= 1;
            countCaps[countCapIndex] = 0;
        }
        ValidateFields(validateBtn);
    }
    
    public static void ValidateAmount(Button validateBtn, TextField textField, int countCapIndex){
        if(textField.getText().length() != 0){
            try{
                amounts[countCapIndex-1] = Double.parseDouble(textField.getText());
                if(countCaps[countCapIndex] != 1){
                    isValidCounter += 1;
                    countCaps[countCapIndex] = 1;
                }
            }catch(NumberFormatException e){
                System.out.println("BAD");
                isValidCounter -= 1;
                countCaps[countCapIndex] = 0;
            }
        }else{
            isValidCounter -= 1;
            countCaps[countCapIndex] = 0;
        }
        ValidateFields(validateBtn);
    }
    
    public static void ValidateFields(Button validateBtn){
        if(isValidCounter == 8){
            validateBtn.setDisable(false);
            validateBtn.setId("btn1");
        }else if(isValidCounter < 8){
            validateBtn.setDisable(true);
            validateBtn.setId("btn2");
        }
    }

}