package com.giorgia.pokequiz;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.util.Duration;


public class QuizController {
    @FXML private Label question;
    @FXML private Label timeLeft;
    @FXML private Button optionOne;
    @FXML private Button optionTwo;
    @FXML private Button optionThree;
    @FXML private Button optionFour;

    int count = 1;      //count the questions shown
    static int correct = 0;     //correct answers given
    static int wrong = 0;       //wrong answers give

    final int timeLimit = 12;     //time in seconds to answer the current question
    private Timeline timeline;      //timeline for the timer
    private int timeRemaining;      //time remaining to answer

    @FXML
    private void initialize() {
        loadNextQuestion();
    }

    //method to load and show the next question
    private void loadNextQuestion() {
        //I stop the former timer if it's still going on
        if (timeline != null)
            timeline.stop();

        //reset of the time for each question called
        timeRemaining = timeLimit;
        timeLeft.setText("Time left: " + timeRemaining + " seconds");

        //start the new timer
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
            timeLeft.setText("Time left: " + timeRemaining + " seconds");

            //If time is up, it will pass to the next question
            if (timeRemaining <= 0) {
                wrong++;

                //checks if there are other question
                if (count < 10) {
                    count++;
                    loadNextQuestion();
                } else
                    showResults();
            }
        }));

        //I run the timer every second
        timeline.setCycleCount(timeLimit);      //Repeat for each second of the time limit
        timeline.play();

        switch (count) {
            case 1:
                question.setText("Number 1: What's the name of the main character of the anime?");
                optionOne.setText("Brock");
                optionTwo.setText("Ash Ketchum");
                optionThree.setText("prof.Oak");
                optionFour.setText("Lucy");
                break;
            case 2:
                question.setText("Number 2: How many seasons has the anime?");
                optionOne.setText("19");
                optionTwo.setText("20");
                optionThree.setText("21");
                optionFour.setText("22");
                break;
            case 3:
                question.setText("Number 3: Which are the three versions of the first videogame launched in 1996?");
                optionOne.setText("Red, Blue and Yellow");
                optionTwo.setText("Gold, Silver and Crystal");
                optionThree.setText("Ruby, Sapphire and Emerald");
                optionFour.setText("Diamond, Pearl and Platinum");
                break;
            case 4:
                question.setText("Number 4: What is a Pokédex?");
                optionOne.setText("It's a handheld electronic encyclopedia that gives information about all Pokémon");
                optionTwo.setText("It's a smartphone used to take photos of cute Pokémon");
                optionThree.setText("It's a tool used to catch a Pokémon during a battle");
                optionFour.setText("It's a handheld electronic encyclopedia that gives information about plants eaten by Pokémon");
                break;
            case 5:
                question.setText("Number 5: Which of these Pokémon is type Ice?");
                optionOne.setText("Bulbasaur");
                optionTwo.setText("Vulpix");
                optionThree.setText("Butterfree");
                optionFour.setText("Chikorita");
                break;
            case 6:
                question.setText("Number 6: Which is the most powerful Legendary Pokémon?");
                optionOne.setText("Rayquaza");
                optionTwo.setText("Giratina");
                optionThree.setText("Arceus");
                optionFour.setText("Mewtwo");
                break;
            case 7:
                question.setText("Number 7: A group of scientists discovered a new protein inside the human body that transmits the impulses from the eye to the brain. What is its name?");
                optionOne.setText("Pikachurina");
                optionTwo.setText("Giratinina");
                optionThree.setText("Electrikina");
                optionFour.setText("Blitzlina");
                break;
            case 8:
                question.setText("Number 8: which types does Wingull belong to?");
                optionOne.setText("Psychic and fairy");
                optionTwo.setText("Normal and flying");
                optionThree.setText("Flying");
                optionFour.setText("Water and flying");
                break;
            case 9:
                question.setText("Number 9: The name 'Pokémon' comes from the union of two common words:");
                optionOne.setText("Poker and daemon");
                optionTwo.setText("Pokeweeds and cinnamon");
                optionThree.setText("Poket and monster");
                optionFour.setText("Unspoke and monstrous");
                break;
            case 10:
                question.setText("Number 10: Which is the rarest Pokémon playing card ever created?");
                optionOne.setText("Pokémon Base Set Shadowless 1st edition Holo Charizard 1999");
                optionTwo.setText("Pokémon Galaxy Star Holo Blastoise 1998");
                optionThree.setText("Pokémon Neo Genesis 1a edizione Holo Lugia 2000");
                optionFour.setText("Pikachu-Holo Illustrator 1998");
                break;
        }
    }

    private boolean checkAnswer(String answer) {
        switch (count) {
            case 1:
                return answer.equals("Ash Ketchum");
            case 2:
                return answer.equals("22");
            case 3:
                return answer.equals("Red, Blue and Yellow");
            case 4:
                return answer.equals("It's a handheld electronic encyclopedia that gives information about all Pokémon");
            case 5:
                return answer.equals("Vulpix");
            case 6:
                return answer.equals("Arceus");
            case 7:
                return answer.equals("Pikachurina");
            case 8:
                return answer.equals("Water and flying");
            case 9:
                return answer.equals("Poket and monster");
            case 10:
                return answer.equals("Pikachu-Holo Illustrator 1998");

            default:
                return false;
        }
    }

    //when option one is clicked
    @FXML
    public void optOneClicked(ActionEvent event) {
        //check if this is the correct answer for the current question
        if (checkAnswer(optionOne.getText()))
            correct++;
        else
            wrong++;

        //When I click on an answer I need to stop the timer
        timeline.stop();

        //check if there's another question
        if (count == 10) {
            showResults();  //shows the result if it's the last question
        } else {
            count++;
            loadNextQuestion();     //It shows the following question
        }
    }

    //when option two is clicked
    @FXML
    public void optTwoClicked(ActionEvent event) {
        if (checkAnswer(optionTwo.getText()))
            correct++;
        else
            wrong++;

        timeline.stop();
        if (count == 10) {
            showResults();
        } else {
            count++;
            loadNextQuestion();
        }
    }

    //when option three is clicked
    @FXML
    public void optThreeClicked(ActionEvent event) {
        if (checkAnswer(optionThree.getText()))
            correct++;
        else
            wrong++;

        timeline.stop();
        if (count == 10) {
            showResults();
        } else {
            count++;
            loadNextQuestion();
        }
    }

    //when option four is clicked
    @FXML
    public void optFourClicked(ActionEvent event) {
        if (checkAnswer(optionFour.getText()))
            correct++;
        else
            wrong++;

        timeline.stop();
        if (count == 10) {
            showResults();
        } else {
            count++;
            loadNextQuestion();
        }
    }

    //Opens the panel with the results
    private void showResults() {
        try {
            Stage thisStage = (Stage) question.getScene().getWindow();
            thisStage.close();   //close the quiz window

            //open the result window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}