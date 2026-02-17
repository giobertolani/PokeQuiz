package com.giorgia.pokequiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ResultController {
    @FXML private Label finalResult;
    @FXML private Label markText;
    @FXML private Label correctText;
    @FXML private Label incorrectText;
    @FXML private ProgressIndicator correctAnswers;
    @FXML private ProgressIndicator wrongAnswers;
    @FXML private Button theEnd;

    @FXML private TableView<DBController.Gamer> Chart;
    @FXML private TableColumn<DBController.Gamer, String> nameColumn;
    @FXML private TableColumn<DBController.Gamer, Integer> scoreColumn;
    @FXML private Button showChart;

    @FXML
    private void initialize() {
        //to create the table into the database if not exists
        DBController.createTableChart();
        DBController db = new DBController();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));  // Nome dalla classe Gamer
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));  // Punteggio dalla classe Gamer

        //add the new gamer to the chart
        db.addGamerAndScore(PokeController.nameGamer, QuizController.correct);

        //show the chart if the button CHECK CHART is pressed
        showChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //to get the chart from the database
                ObservableList<DBController.Gamer> ch = FXCollections.observableArrayList(db.getChart());
                //to set the TableView with the data
                Chart.setItems(ch);
                Chart.setVisible(true);
            }
        });

        finalResult.setText(String.valueOf(QuizController.correct) + "/10");
        markText.setText(String.valueOf(QuizController.correct) + " marks scored!");

        correctText.setText("Correct answers: " + String.valueOf(QuizController.correct));
        correctAnswers.setProgress((float) QuizController.correct/10);

        incorrectText.setText("Incorrect answers: " + String.valueOf(QuizController.wrong));
        wrongAnswers.setProgress((float) QuizController.wrong/10);

        //to close the game when the button THE END :) is pressed
        theEnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisStage.close();  //Exit the game
            }
        });
    }
}
