package client;

import commons.InitializationException;
import entities.Album;
import entities.Artist;
import entities.Genre;
import entities.Track;
import server.ServerInterface;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    public static void main(String... args) {
        try {
            ObjectPool.getPool().init();
        } catch (InitializationException e) {
            e.printStackTrace();
            return;
        }
        new Client().run();
    }

    private Scanner in;
    private ServerInterface serverInterface;

    private static final Pattern SAVE_TRACK = Pattern.compile("save track (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SAVE_ALBUM = Pattern.compile("save album (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SAVE_GENRE = Pattern.compile("save genre (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SAVE_ARTIST = Pattern.compile("save artist (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_TRACKS = Pattern.compile("get tracks like (\\w*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_ALBUMS = Pattern.compile("get albums like (\\w*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_GENRES = Pattern.compile("get genres like (\\w*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_ARTISTS = Pattern.compile("get artists like (\\w*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_TRACK = Pattern.compile("get track (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_ALBUM = Pattern.compile("get album (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_GENRE = Pattern.compile("get genre (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern GET_ARTIST = Pattern.compile("get artist (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_TRACK = Pattern.compile("delete track (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_ALBUM = Pattern.compile("delete album (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_GENRE = Pattern.compile("delete genre (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_ARTIST = Pattern.compile("delete artist (\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPDATE_TRACK = Pattern.compile("update track (\\d+) set title (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPDATE_ALBUM = Pattern.compile("update album (\\d+) set title (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPDATE_GENRE = Pattern.compile("update genre (\\d+) set name (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPDATE_ARTIST = Pattern.compile("update artist (\\d+) set name (\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern HELP = Pattern.compile("help", Pattern.CASE_INSENSITIVE);

    public Client() {
        in = new Scanner(System.in);
        serverInterface = ObjectPool.getPool().getServerInterface();
    }

    private void run() {
        while (true) {
            String query = in.nextLine();
            Matcher matcher;
            if ((matcher = HELP.matcher(query)).matches())
                help();
            else if ((matcher = SAVE_TRACK.matcher(query)).matches())
                saveTrack(matcher);
            else if ((matcher = SAVE_ALBUM.matcher(query)).matches())
                saveAlbum(matcher);
            else if ((matcher = SAVE_GENRE.matcher(query)).matches())
                saveGenre(matcher);
            else if ((matcher = SAVE_ARTIST.matcher(query)).matches())
                saveArtist(matcher);
            else if ((matcher = GET_TRACKS.matcher(query)).matches())
                getTracks(matcher);
            else if ((matcher = GET_ALBUMS.matcher(query)).matches())
                getAlbums(matcher);
            else if ((matcher = GET_GENRES.matcher(query)).matches())
                getGenres(matcher);
            else if ((matcher = GET_ARTISTS.matcher(query)).matches())
                getArtists(matcher);
            else if ((matcher = GET_TRACK.matcher(query)).matches())
                getTrack(matcher);
            else if ((matcher = GET_ALBUM.matcher(query)).matches())
                getAlbum(matcher);
            else if ((matcher = GET_GENRE.matcher(query)).matches())
                getGenre(matcher);
            else if ((matcher = GET_ARTIST.matcher(query)).matches())
                getArtist(matcher);
            else if ((matcher = DELETE_TRACK.matcher(query)).matches())
                deleteTrack(matcher);
            else if ((matcher = DELETE_ALBUM.matcher(query)).matches())
                deleteAlbum(matcher);
            else if ((matcher = DELETE_GENRE.matcher(query)).matches())
                deleteGenre(matcher);
            else if ((matcher = DELETE_ARTIST.matcher(query)).matches())
                deleteArtist(matcher);
            else if ((matcher = UPDATE_TRACK.matcher(query)).matches())
                updateTrack(matcher);
            else if ((matcher = UPDATE_ALBUM.matcher(query)).matches())
                updateAlbum(matcher);
            else if ((matcher = UPDATE_GENRE.matcher(query)).matches())
                updateGenre(matcher);
            else if ((matcher = UPDATE_ARTIST.matcher(query)).matches())
                updateArtist(matcher);
        }
    }

    private void help() {
        System.out.println(SAVE_TRACK.toString());
        System.out.println(SAVE_ALBUM.toString());
        System.out.println(SAVE_GENRE.toString());
        System.out.println(SAVE_ARTIST.toString());
        System.out.println(GET_TRACKS.toString());
        System.out.println(GET_ALBUMS.toString());
        System.out.println(GET_GENRES.toString());
        System.out.println(GET_ARTISTS.toString());
        System.out.println(GET_TRACK.toString());
        System.out.println(GET_ALBUM.toString());
        System.out.println(GET_GENRE.toString());
        System.out.println(GET_ARTIST.toString());
        System.out.println(UPDATE_TRACK.toString());
        System.out.println(UPDATE_ALBUM.toString());
        System.out.println(UPDATE_GENRE.toString());
        System.out.println(UPDATE_ARTIST.toString());
        System.out.println(DELETE_TRACK.toString());
        System.out.println(DELETE_ALBUM.toString());
        System.out.println(DELETE_GENRE.toString());
        System.out.println(DELETE_ARTIST.toString());
        System.out.println(HELP.toString());
    }
    
    private void saveTrack(Matcher matcher) {
        try {
            Track track = new Track();
            track.setTitle(matcher.group(1));
            track.setId(serverInterface.save(track));
            System.out.println(track);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void saveAlbum(Matcher matcher) {
        try {
            Album album = new Album();
            album.setTitle(matcher.group(1));
            album.setId(serverInterface.save(album));
            System.out.println(album);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void saveGenre(Matcher matcher) {
        try {
            Genre genre = new Genre();
            genre.setName(matcher.group(1));
            genre.setId(serverInterface.save(genre));
            System.out.println(genre);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void saveArtist(Matcher matcher) {
        try {
            Artist artist = new Artist();
            artist.setName(matcher.group(1));
            artist.setId(serverInterface.save(artist));
            System.out.println(artist);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void getTracks(Matcher matcher) {
        try {
            Track track = new Track();
            track.setTitle(matcher.group(1));
            List<Track> tracks = serverInterface.get(track);
            tracks.forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getAlbums(Matcher matcher) {
        try {
            Album album = new Album();
            album.setTitle(matcher.group(1));
            List<Album> albums = serverInterface.get(album);
            albums.forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getGenres(Matcher matcher) {
        try {
            Genre genre = new Genre();
            genre.setName(matcher.group(1));
            List<Genre> genres = serverInterface.get(genre);
            genres.forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getArtists(Matcher matcher) {
        try {
            Artist artist = new Artist();
            artist.setName(matcher.group(1));
            List<Artist> artists = serverInterface.get(artist);
            artists.forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getTrack(Matcher matcher) {
        try {
            Track track = new Track();
            track.setId(Integer.parseInt(matcher.group(1)));
            Track result = (Track) serverInterface.getById(track);
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void getAlbum(Matcher matcher) {
        try {
            Album album = new Album();
            album.setId(Integer.parseInt(matcher.group(1)));
            Album result = (Album) serverInterface.getById(album);
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void getGenre(Matcher matcher) {
        try {
            Genre genre = new Genre();
            genre.setId(Integer.parseInt(matcher.group(1)));
            Genre result = (Genre) serverInterface.getById(genre);
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void getArtist(Matcher matcher) {
        try {
            Artist artist = new Artist();
            artist.setId(Integer.parseInt(matcher.group(1)));
            Artist result = (Artist) serverInterface.getById(artist);
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteTrack(Matcher matcher) {
        try {
            Track track = new Track();
            track.setId(Integer.parseInt(matcher.group(1)));
            if (serverInterface.delete(track))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteAlbum(Matcher matcher) {
        try {
            Album album = new Album();
            album.setId(Integer.parseInt(matcher.group(1)));
            if (serverInterface.delete(album))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteGenre(Matcher matcher) {
        try {
            Genre genre = new Genre();
            genre.setId(Integer.parseInt(matcher.group(1)));
            if (serverInterface.delete(genre))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteArtist(Matcher matcher) {
        try {
            Artist artist = new Artist();
            artist.setId(Integer.parseInt(matcher.group(1)));
            if (serverInterface.delete(artist))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void updateTrack(Matcher matcher) {
        try {
            Track track = new Track();
            track.setId(Integer.parseInt(matcher.group(1)));
            track.setTitle(matcher.group(2));
            if (serverInterface.update(track))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void updateAlbum(Matcher matcher) {
        try {
            Album album = new Album();
            album.setId(Integer.parseInt(matcher.group(1)));
            album.setTitle(matcher.group(2));
            if (serverInterface.update(album))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void updateGenre(Matcher matcher) {
        try {
            Genre genre = new Genre();
            genre.setId(Integer.parseInt(matcher.group(1)));
            genre.setName(matcher.group(2));
            if (serverInterface.update(genre))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private void updateArtist(Matcher matcher) {
        try {
            Artist artist = new Artist();
            artist.setId(Integer.parseInt(matcher.group(1)));
            artist.setName(matcher.group(2));
            if (serverInterface.update(artist))
                System.out.println("Success");
            else
                System.out.println("Fail");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
