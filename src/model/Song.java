package model;

import org.json.simple.JSONObject;

public class Song {
    private String name;
    private String artist;
    private String album;
    private int year;

    public Song(String name, String artist, String album, int year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }



    // JSON Methods

    /**
     * JSONObject songJSONObject = song.toJSON();
     * @return
     */
    public JSONObject toJSON() {
        JSONObject songJSON = new JSONObject();
        songJSON.put("name", this.getName());
        songJSON.put("artist", this.getArtist());
        songJSON.put("album", this.getAlbum());
        songJSON.put("year", this.getYear());
        return songJSON;
    }

    /**
     * Song song = Song.fromJSON(songJSONObject)
     * @param songJSON
     * @return
     */
    public static Song fromJSON(JSONObject songJSON) {
        return new Song(
                (String) songJSON.get("name"),
                (String) songJSON.get("artist"),
                (String) songJSON.get("album"),
                (Integer) songJSON.get("year"));
    }



    // GETTERS
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getYear() {
        return year;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "\nName: " + getName() +
                "\nArtist: " + getArtist() +
                "\nAlbum: " + getAlbum() +
                "\nYear: " + getYear();
    }
}
