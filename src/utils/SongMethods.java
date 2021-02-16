package utils;
import model.Song;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SongMethods {

    static private final String JSON_FILE_PATH = SongMethods.class.getResource("../data/data.json").getPath();

    public static List<Song> getSongs() throws Exception {
        try {
            List<Song> songs = new ArrayList<>();

            Object parsedObject = new JSONParser().parse(new FileReader(JSON_FILE_PATH));
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

    public static boolean saveSong(Song song, EditType editType) throws IllegalStateException, IOException {
        try {
            if (editType == EditType.CREATE) {
                // Creating a new song
                JSONObject songJSON = song.toJSON();

                PrintWriter pw = new PrintWriter(JSON_FILE_PATH);
                pw.write(songJSON.toJSONString());

                pw.flush();
                pw.close();
            } else {
                // Editing an old song
                throw new IllegalStateException("Song already exists.");
            }
            return true;
        } catch(IllegalStateException e) {
            return false;
        }

    }

    public static boolean deleteSong(Song song) {
        return false;
    }
}
