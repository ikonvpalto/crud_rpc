package entities;

import java.util.Collections;
import java.util.Set;

public class Track {

    private int id;
    private String title;
    private Album album;
    private Set<Genre> genres;

    public Track() {
        genres = Collections.emptySet();
        id = -1;
    }

    public Track(String title) {
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public boolean addGenre(Genre genre) {
        return genres.add(genre);
    }

    public boolean removeGenre(Genre genre) {
        return genres.remove(genre);
    }

    @Override
    public String toString() {
        return "Track{" + "id=" + id + ", title='" + title + '\'' + ", album=" + album + '}';
    }
}
