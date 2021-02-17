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
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;
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
        Song selectedSong = songsList.get(selectedSongIndex);
        nameLabel.setText(selectedSong.getName());
        artistLabel.setText(selectedSong.getArtist());
        albumLabel.setText(selectedSong.getAlbum());
        yearLabel.setText(String.valueOf(selectedSong.getYear()));

    }

    // BUTTON METHODS
    public void editSong(ActionEvent event) throws IOException {
        try {
            Navigator.navigateToEditSongPage(event, songsList.get(selectedSongIndex));
        } catch(Exception e) {
            // TODO: Fix this so that 'Edit' button doesn't navigate if no task is selected
            Navigator.navigateToEditSongPage(event, null);
        }
    }

    public void deleteSong() {
        final boolean shouldDelete = AlertBox.display("Delete Song","Are you sure you want to delete this song?");

        if (shouldDelete) SongMethods.deleteSong(songsList.get(selectedSongIndex));
    }


}
