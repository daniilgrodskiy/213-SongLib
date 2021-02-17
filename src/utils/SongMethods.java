package utils;
import model.Song;
import org.json.simple.*;
import org.json.simple.parser.*;
import widgets.ErrorBox;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Collections;

public class SongMethods{

    static private final String JSON_FILE_PATH = "src/data/data.json";

    public static List<Song> getSongs() throws Exception {
        try {
            List<Song> songs = new ArrayList<>();

            Object parsedObject = new JSONParser().parse(new FileReader(JSON_FILE_PATH));

            System.out.println("All songs: " + parsedObject);

            JSONArray jsonArray = (JSONArray) parsedObject;

            for (Object o : jsonArray) {
                songs.add(Song.fromJSON((JSONObject) o));
            }

            return songs;
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            return new ArrayList<>();
        }
    }

    public static boolean saveSong(Song oldSong, Song newSong) throws IllegalStateException, Exception {
        try {
            List<Song> songs = getSongs();

            // ERROR CHECK
            if (oldSong == null) {
                // Adding new song
                for (Song song : songs) {
                    if (song.getName().equals(newSong.getName()) && song.getArtist().equals(newSong.getArtist())) {
                        // ERROR
                        ErrorBox.display("This song and artist already exist together.");
                        return false;
                    }
                }
            } else {
                // Editing an old song
                for (Song song : songs) {
                    if (song.getName().equals(newSong.getName()) && song.getArtist().equals(newSong.getArtist()) &&
                            !(oldSong.getName().equals(newSong.getName()) && oldSong.getArtist().equals(newSong.getArtist()))) {
                        // TODO: Figure out how to edit a song that is referencing an old song (when newSong == oldSong)
                        // ERROR
                        ErrorBox.display("This song and artist already exist together.");
                        return false;
                    }
                }

            }

            if (oldSong != null) {
                songs.remove(deleteSong(oldSong));
            }

            songs.add(newSong);

            JSONArray jsonArray = new JSONArray();

            for (Song s : songs) {
                jsonArray.add(s.toJSON());
            }

            System.out.println(jsonArray);
            PrintWriter pw = new PrintWriter(JSON_FILE_PATH);
            pw.write(jsonArray.toJSONString());

            pw.flush();
            pw.close();

            return true;
        } catch(IllegalStateException e) {
            return false;
        }

    }

    public static int deleteSong(Song song) throws Exception {
        try {
            // TODO: Fix the edit code to actually save/create a song
            // Creating a new song

            List<Song> songs = getSongs();

            int indexToDelete = 0;

            for (int i = 0; i < songs.size(); i++) {
                Song currentSong = songs.get(i);
                if (currentSong.getName().equals(song.getName()) && currentSong.getArtist().equals(song.getArtist())) {
                    indexToDelete = i;
                }
            }

            songs.remove(indexToDelete);

            JSONArray jsonArray = new JSONArray();

            for (Song s : songs) {
                jsonArray.add(s.toJSON());
            }

            System.out.println(jsonArray);
            PrintWriter pw = new PrintWriter(JSON_FILE_PATH);
            pw.write(jsonArray.toJSONString());

            pw.flush();
            pw.close();

            return indexToDelete;
        } catch(IllegalStateException e) {
            return -1;
        }
    }
}
