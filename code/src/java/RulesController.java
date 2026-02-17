package com.giorgia.pokequiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class RulesController {

    @FXML private Button startNow;
    @FXML private Button goBack;

    @FXML
    private void initialize() {

        //to open the quiz screen when I click on START NOW
        startNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage thisStage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
                    thisStage.close();  //close the rules window

                    //open the quiz window
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //To go back to the home screen when I click on GO BACK
        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage thisStage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
                    thisStage.close();  //close the rules window

                    //open the home window
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("poke-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
