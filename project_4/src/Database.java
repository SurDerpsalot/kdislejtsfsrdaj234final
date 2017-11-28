import java.util.HashMap;

public class Database {
    BST<Integer, Integer> artistTree;
    BST<Integer, Integer> songTree;
    mainMemory mem;
    HashMap<String, Integer> artistHash;
    HashMap<String, Integer> songHash;
    /**
     * constructor
     */
    Database(int size) {
        mem = new mainMemory(size);
        artistTree = null;
        songTree = null;
        
    }
    
    
    public void addEntry(String artist, String song) {
        
    }
    
    public void removeArtist(String name) {
        
    }
    
    public void removeSong(String name) {
        
    }
    
    public void viewTree() {
        
    }
    
    public void viewArtists() {
        
    }
    
    public void viewSongs() {
        
    }
    
    public void listSongsByArtist(String artist) {
        
    }

    public void listArtistsBySong(String song) {
        
    }

}
