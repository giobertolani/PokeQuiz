package com.giorgia.pokequiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.util.Optional;
import java.util.random.RandomGenerator;

public class PokeController  {
    @FXML private Button playButton;
    @FXML private Button rulesButton;
    @FXML private Button exitButton;
    @FXML private TextField addNameG;
    @FXML private Button nameAdded;

    protected RandomGenerator rnd = RandomGenerator.getDefault();
    public static String nameGamer;

    @FXML
    private void initialize() {
        //when a new name is added
        nameAdded.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //to get the name of the new gamer
                nameGamer = addNameG.getText();
            }
        });
        //if now inserted a random one is generated
        if (nameGamer == null || nameGamer.trim().isEmpty()) {
            int randomNumber = rnd.nextInt(1000) + 1;   //random number between 1 and 1000
            nameGamer = "Player" + randomNumber;
        }

        //to open the quiz screen when I click on PLAY NOW
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisStage.close();  //close the home window

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

        //to open the rules screen when I click on RULES
        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisStage.close();  //close the home window

                    //open the quiz window
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rules-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Exit the game when I click on EXIT
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Do you really want to exit?");
                alert.setContentText("If you click OK the game will shut down, if you click CANCEL you will go back to the game");
                alert.getButtonTypes().addAll(ButtonType.CANCEL);

                Optional<ButtonType> opt = alert.showAndWait();
                if (opt.isPresent() && opt.get() == ButtonType.OK) {
                    Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisStage.close();  //Exit the game
                }
            }
        });
    }
}
