package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import model.Song;
import utils.Navigator;
import utils.SongMethods;
import widgets.AlertBox;
import widgets.ErrorBox;

import java.io.IOException;


public class MainPage {
    @FXML private ListView<Song> songsListView;
    @FXML private Text nameLabel;
    @FXML private Text artistLabel;
    @FXML private Text albumLabel;
    @FXML private Text yearLabel;

    private int selectedSongIndex = 0;
    private ObservableList<Song> songsList;

    public void initData() throws Exception {
        // Fill ListView with data
        songsList = FXCollections.observableArrayList(SongMethods.getSongs());
        songsListView.setItems(songsList);

        songsListView.getSelectionModel().selectedIndexProperty().addListener((abs, oldVal, newVal) -> showSong());
        songsListView.getSelectionModel().select(0);
    }

    public void showSong() {
        selectedSongIndex = songsListView.getSelectionModel().selectedIndexProperty().intValue();

        if (selectedSongIndex == -1) {
            nameLabel.setText("No songs added");
            artistLabel.setText("");
            albumLabel.setText("");
            yearLabel.setText("");
            return;
        }

        Song selectedSong = songsList.get(selectedSongIndex);
        nameLabel.setText(selectedSong.getName());
        artistLabel.setText(selectedSong.getArtist());
        albumLabel.setText(selectedSong.getAlbum());
        if (selectedSong.getYear() == -1) {
            yearLabel.setText("");
        } else {
            yearLabel.setText(String.valueOf(selectedSong.getYear()));
        }
    }

    // BUTTON METHODS
    public void editSong(ActionEvent event) throws IOException {

        //Gets source of action event
        Object node = event.getSource();
        Button button = (Button) node;
        System.out.println(button.getText());
        // Checks if "edit" button was clicked and if a song is selected
        if (button.getText().equals("Edit")) {
            if (songsList.size() == 0) {
                ErrorBox.display("There are no songs to edit.");
                return;
            }
            Navigator.navigateToEditSongPage(event, songsList.get(selectedSongIndex));
            return;
        }
        // If edit wasn't clicked then add song is called
        Navigator.navigateToEditSongPage(event, null);
    }

    public void deleteSong() throws Exception {
        final boolean shouldDelete = AlertBox.display("Delete Song","Are you sure you want to delete this song?");

        if (shouldDelete) {
            int indexToDelete = SongMethods.deleteSong(songsList.get(selectedSongIndex));
            songsList.remove(indexToDelete);

            if (songsList.size() > 0) {
                if (indexToDelete >= songsList.size()) {
                    songsListView.getSelectionModel().select(indexToDelete - 1);
                } else {
                    songsListView.getSelectionModel().select(indexToDelete);
                }
            }
        }
    }


}
