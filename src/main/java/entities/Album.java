package entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Album implements Serializable {

    private int id;
    private String title;
    private Artist artist;
    private List<Track> tracks;

    public Album() {
        tracks = new LinkedList<>();
        id = -1;
    }

    public Album(String title) {
        this();
        this.title = title;
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
        artist.addAlbum(this);
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

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", title='" + title + '\'' + ", artist=" + artist + '}';
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Album)
                && (id == ((Album) obj).getId())
                && (title.equals(((Album) obj).getTitle()));
    }
}
