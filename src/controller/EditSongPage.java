package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utils.*;
import model.Song;
import utils.SongMethods;
import widgets.ErrorBox;

public class EditSongPage {

    private EditType editType;
    private Scene songLibScene;

    // Components
    @FXML private Text titleText;
    @FXML private TextField nameInput;
    @FXML private TextField artistInput;
    @FXML private TextField albumInput;
    @FXML private TextField yearInput;
    @FXML private Button submitButton;
    @FXML private Button backButton;


    public void initData(Song song) {
        if (song != null) {
            // Old song
            editType = EditType.UPDATE;

            titleText.setText("Edit song");
            nameInput.setText(song.getName());
            artistInput.setText(song.getArtist());
            albumInput.setText(song.getAlbum());
            yearInput.setText(String.valueOf(song.getYear()));
            submitButton.setText("Save");
        } else {
            // New song
            editType = EditType.CREATE;

            titleText.setText("Add song");
            submitButton.setText("Create");
        }
    }

    public boolean handleSubmitButtonClick(ActionEvent event) throws IllegalArgumentException, Exception {
        // CHECKING YEAR
        try {
            // Make sure year is a positive integer
            int year = Integer.parseInt(yearInput.getText());

            if (!(year > 0)) {
                throw new IllegalArgumentException();
            }
        } catch(NumberFormatException e) {
            ErrorBox.display("Invalid year. Year must be a positive number.");
            return false;
        } catch(IllegalArgumentException e) {
            ErrorBox.display("Invalid year. Year must be greater than 0.");
            return false;
        }

        // Validator.validateSong(song);

        // ADDING/SAVING SONG
        Song song = new Song(nameInput.getText(), artistInput.getText(), albumInput.getText(), Integer.parseInt(yearInput.getText()));

        System.out.println(song.toString());

        if (SongMethods.saveSong(song, editType)) {
            // Navigate back to main page
            Navigator.navigateToMainPage(event);
            return true;
        }

        ErrorBox.display("A song like this already exists. Please try again.");
        return false;

    }

    public void handleBackButtonClick(ActionEvent event) throws Exception {
        // Navigate back to main page
        Navigator.navigateToMainPage(event);
    }



}
