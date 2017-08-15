package entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class Genre implements Serializable {

    private int id;
    private String name;
    private Set<Track> tracks;

    public Genre() {
        tracks = Collections.emptySet();
        id = -1;
    }

    public Genre(String name) {
        this();
        this.name = name;
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

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Genre)
                && (id == ((Genre) obj).getId())
                && (name.equals(((Genre) obj).getName()));
    }
}
