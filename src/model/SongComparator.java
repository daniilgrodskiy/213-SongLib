package model;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2){
        String name1 = song1.getName();
        String name2= song2.getName();
        int value1 = name1.compareTo(name2);
        if (value1 == 0) {
            String artist1 = song1.getArtist();
            String artist2= song2.getArtist();
            return artist1.compareTo(artist2);
        }
        return value1;
    }
}
