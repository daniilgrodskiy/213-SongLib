package utils;

import controller.EditSongPage;
import controller.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Song;

import java.io.IOException;

public class Navigator {
    public static void navigateToMainPage(ActionEvent event) throws IOException, Exception {
        // Navigate back to main page
        FXMLLoader loader = new FXMLLoader();

        // To be able to statically use this method, we must add "Navigator.class.getClass()"
        loader.setLocation(Navigator.class.getResource("../view/MainPage.fxml"));
        Parent mainPageScene = loader.load();

        // Find the controller and call initData method
        MainPage controller = loader.getController();
        controller.initData();

        // Show scene
        // Gets the current window (avoids using 'Stage primaryStage' thing
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(mainPageScene, 800, 500));
        window.show();
    }

    public static void navigateToEditSongPage(ActionEvent event, Song selectedSong) throws IOException {
        // Used to find out if it was the "Add" or the "Edit" button that was clicked
        Object node = event.getSource();
        Button button = (Button) node;
        System.out.println(button.getText().equals("Edit"));

        // FXML loader setup
        FXMLLoader loader = new FXMLLoader();

        // To be able to statically use this method, we must add "Navigator.class"
        loader.setLocation(Navigator.class.getResource("../view/EditSong.fxml"));
        Parent mainPageScene = loader.load();

        // Find the controller and call initData method
        EditSongPage controller = loader.getController();

        // If button pressed is "Add Song" make selectedSong null, if not pass through selectedSong
        if (button.getText().equals("Add Song")) selectedSong = null;
        controller.initData(selectedSong);

        // Show scene
        // Gets the current window (avoids using 'Stage primaryStage' thing
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(mainPageScene, 800, 500));
        window.show();
    }
}
