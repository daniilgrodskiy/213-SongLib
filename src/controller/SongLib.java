package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

import javafx.application.*;


public class SongLib extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML loader setup
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MainPage.fxml"));
        Parent mainPageScene = loader.load();

        // Find the controller and call initData method
        MainPage controller = loader.getController();
        controller.initData();

        // Set the scene
        primaryStage.setScene(new Scene(mainPageScene, 800, 500));
        primaryStage.setTitle("SongLib");
        primaryStage.show();
    }
}
