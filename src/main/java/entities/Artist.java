package entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Artist implements Serializable{

    private int id;
    private String name;
    private List<Album> albums;

    public Artist() {
        albums = Collections.emptyList();
        id = -1;
    }

    public Artist(String name) {
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public boolean addAlbum(Album album) {
        return albums.add(album);
    }

    public boolean removeAlbum(Album album) {
        return albums.remove(album);
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Artist)
                && (id == ((Artist) obj).getId())
                && (name.equals(((Artist) obj).getName()));
    }
}
