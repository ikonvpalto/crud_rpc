package entities;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Album {

    private int id;
    private String title;
    private Artist artist;
    private List<Track> tracks;

    public Album() {
        tracks = Collections.emptyList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public boolean addTrack(Track track) {
        return tracks.add(track);
    }

    public boolean removeTrack(Track track) {
        return tracks.remove(track);
    }
}
