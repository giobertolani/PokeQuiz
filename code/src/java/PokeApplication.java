package com.giorgia.pokequiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PokeApplication extends Application {
    @Override
    public void start(Stage primayStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PokeApplication.class.getResource("poke-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primayStage.setTitle("PokeQuiz");
        primayStage.setScene(scene);
        primayStage.show();

    }

    public static void main(String[] args) { launch(); }
}
