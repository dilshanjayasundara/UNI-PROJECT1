package com.food_company.task_4;

import com.food_company.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.food_company.FoodQueue;
import com.food_company.Customer;



public class HelloController {
    @FXML
    private Label c1Q1;
    @FXML
    public Label c1Q2;
    @FXML
    public Label c2Q1;
    @FXML
    public Label c2Q2;
    @FXML
    public Label c2Q3;
    @FXML
    public Label c3Q1;
    @FXML
    public Label c3Q2;
    @FXML
    public Label c3Q3;
    @FXML
    public Label c3Q4;
    @FXML
    public Label c3Q5;

    @FXML
    public ImageView c1q1;
    @FXML
    public ImageView c1q2;
    @FXML
    public ImageView c2q1;
    @FXML
    public ImageView c2q2;
    @FXML
    public ImageView c2q3;
    @FXML
    public ImageView c3q1;
    @FXML
    public ImageView c3q2;
    @FXML
    public ImageView c3q3;
    @FXML
    public ImageView c3q4;
    @FXML
    public ImageView c3q5;

    @FXML
    protected void onSearchButtonClick(ActionEvent stage) {

        c1Q1.setText(FoodQueue.cashier1array[0]);
        c1Q2.setText(FoodQueue.cashier1array[1]);
        c2Q1.setText(FoodQueue.cashier2array[0]);
        c2Q2.setText(FoodQueue.cashier2array[1]);
        c2Q3.setText(FoodQueue.cashier2array[2]);
        c3Q1.setText(FoodQueue.cashier3array[0]);
        c3Q2.setText(FoodQueue.cashier3array[1]);
        c3Q3.setText(FoodQueue.cashier3array[2]);
        c3Q4.setText(FoodQueue.cashier3array[3]);
        c3Q5.setText(FoodQueue.cashier3array[4]);
    }
    @FXML
    protected void onViewButtonClick(ActionEvent stage) {
        if (c1Q1.getText() == null) {c1q1.setImage(null);}
        if (c1Q2.getText() == null) {c1q2.setImage(null);}
        if (c2Q1.getText() == null) {c2q1.setImage(null);}
        if (c2Q2.getText() == null) {c2q2.setImage(null);}
        if (c2Q3.getText() == null) {c2q3.setImage(null);}
        if (c3Q1.getText() == null) {c3q1.setImage(null);}
        if (c3Q2.getText() == null) {c3q2.setImage(null);}
        if (c3Q3.getText() == null) {c3q3.setImage(null);}
        if (c3Q4.getText() == null) {c3q4.setImage(null);}
        if (c3Q5.getText() == null) {c3q5.setImage(null);}
    }
}