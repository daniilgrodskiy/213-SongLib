package utils;
import model.Song;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SongMethods {

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

    public static boolean saveSong(Song song, EditType editType) throws IllegalStateException, Exception {
        try {
            if (editType == EditType.CREATE) {
                // TODO: Fix the edit code to actually save/create a song
                // Creating a new song

                List<Song> songs = getSongs();

                songs.add(song);

                JSONArray jsonArray = new JSONArray();

                for (Song s : songs) {
                    jsonArray.add(s.toJSON());
                }

                System.out.println(jsonArray);
                PrintWriter pw = new PrintWriter(JSON_FILE_PATH);
                pw.write(jsonArray.toJSONString());

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
