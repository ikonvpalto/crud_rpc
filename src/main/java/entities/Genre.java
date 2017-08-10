package entities;

import java.util.Collections;
import java.util.Set;

public class Genre {

    private int id;
    private String name;
    private Set<Track> tracks;

    public Genre() {
        tracks = Collections.emptySet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public boolean addTrack(Track track) {
        return tracks.add(track);
    }

    public boolean removeTrack(Track track) {
        return tracks.remove(track);
    }
}
