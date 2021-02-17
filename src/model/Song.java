package model;

import org.json.simple.JSONObject;

public class Song{
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

    //Compare method
    public int compare(Song song1, Song song2){
        int value1 = song1.name.compareTo(song2.name);
        if (value1 == 0) {
            return song1.artist.compareTo(song2.artist);
        }
        return value1;
    }



    // JSON Methods

    /**
     * Usage: JSONObject songJSONObject = song.toJSON();
     * @return
     */
    public JSONObject toJSON() {
        JSONObject songJSON = new JSONObject();
        songJSON.put("name", this.getName().trim());
        songJSON.put("artist", this.getArtist().trim());
        songJSON.put("album", this.getAlbum().trim());
        songJSON.put("year", this.getYear());
        return songJSON;
    }

    /**
     * Usage: Song song = Song.fromJSON(songJSONObject)
     * @param songJSON
     * @return
     */
    public static Song fromJSON(JSONObject songJSON) {
        return new Song(
                (String) songJSON.get("name"),
                (String) songJSON.get("artist"),
                (String) songJSON.get("album"),
                Integer.parseInt(songJSON.get("year").toString()));
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
                "\nArtist: " + getArtist();
    }
}
