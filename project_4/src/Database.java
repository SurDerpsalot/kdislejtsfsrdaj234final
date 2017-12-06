import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
*
* @author Madelyn Newcomb m1newc
* @version 1
*/
public class Database {
    private Scanner sc; //the scanner for the input file
    protected BST<Integer, Integer> artistTree;
    protected BST<Integer, Integer> songTree;
    protected MainMemory mem;
    protected Hash artistHash;
    protected Hash songHash;    
    /**
     * constructor
     * @param filename the input file name
     * @param sizeMem the memory's initial size
     * @param sizeHash the hash maps' initial size
     */
    public Database(String filename, int sizeMem, int sizeHash) {
        try {
            sc = new Scanner(new File(filename));
            mem = new MainMemory(sizeMem);
            artistTree = new BST<Integer, Integer>();
            songTree = new BST<Integer, Integer>();
            artistHash = new Hash(sizeHash);
            songHash = new Hash(sizeHash);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * beginParsing reads and executes the input file commands and runs.
     * @param filename is the name of the input file to parse
     */
    public void beginParsing(String filename) {
        try  {
           //Open our file with read/write access
            while (sc.hasNext()) { //While the scanner has information to read
                String cmd = sc.nextLine(); //Read the next term
                readCommand(cmd);
            } //end while loop
            sc.close();
        } //end try block
        catch (Exception e)  {
            e.printStackTrace();
        } //end catch block
    } //end beginParsing function
    /**
     * interpret the command
     * @param cmd the first line
     */
    public void readCommand(String cmd) {
        String name;
        String topic;
        Scanner newSc = new Scanner(cmd);
        String todo = newSc.next();
        switch(todo) {
            case "insert" ://Found an insert command
                String artistName = newSc.nextLine().trim();
                String songName = artistName.substring(
                        artistName.indexOf("<SEP>") + 5);
                artistName = artistName.substring(
                        0, artistName.indexOf("<SEP>"));
                int artistHandle = 0;
                int songHandle = 0;
                boolean artistDuplicate = false;
                boolean songDuplicate = false;
                if (artistHash.containsKey(artistName)) {
                    System.out.println("|" + artistName + 
                            "| duplicates a record already" 
                            + " in the Artist database.");
                    artistDuplicate = true;
                    artistHandle = artistHash.get(artistName);
                }
                else {
                    artistHandle = mem.addEntryGetHandle(artistName);
                    artistHash.put(artistName, artistHandle);
                    System.out.println("|" + artistName + 
                            "| is added to the Artist database.");
                }
                if (songHash.containsKey(songName)) {
                    System.out.println("|" + songName + 
                            "| duplicates a record already" 
                            + " in the Song database.");
                    songDuplicate = true;
                    songHandle = songHash.get(songName);
                }
                else {
                    songHandle = mem.addEntryGetHandle(songName);
                    songHash.put(songName, songHandle);
                    System.out.println("|" + songName + 
                            "| is added to the Song database.");
                }
                if (!artistDuplicate) {
                    System.out.println("The KVPair (|"
                        + artistName + "|,|" 
                        + songName + "|),(" 
                        + artistHandle + "," 
                        + songHandle + ") is added to the tree.");
                }
                else {
                    System.out.println("The KVPair (|"
                            + artistName + "|,|" 
                            + songName + "|),(" 
                            + artistHandle + "," 
                            + songHandle 
                            + ") duplicates a record already in the tree.");
                }
                if (!songDuplicate) {
                    System.out.println("The KVPair (|"
                        + songName + "|,|" 
                        + artistName + "|),(" 
                        + songHandle + "," 
                        + artistHandle + ") is added to the tree.");
                }
                else {
                    System.out.println("The KVPair (|"
                            + songName + "|,|" 
                            + artistName + "|),(" 
                            + songHandle + "," 
                            + artistHandle 
                            + ") duplicates a record already in the tree.");
                }
                artistTree.setTempNode(artistHandle, songHandle);
                artistTree.setRootNode(artistTree.insert(artistTree.getRoot(),
                        artistTree.getTemp()));
                songTree.setTempNode(songHandle, artistHandle);
                songTree.setRootNode(songTree.insert(songTree.getRoot(),
                        songTree.getTemp()));
                break;
            case "list" :
                topic = newSc.next();
                name = newSc.nextLine().trim();           
                if (topic.compareTo("artist") == 0) {
                    listSongsByArtist(name);
                }
                else if (topic.compareTo("song") == 0) {
                    listArtistsBySong(name);
                }
                else { //TODO: add error condition
                    System.out.println("Error: bad input " + topic);
                }
                break;
            case "delete" ://Found an insert command
                artistName = newSc.nextLine().trim();
                songName = artistName.substring(
                        artistName.indexOf("<SEP>") + 5);
                artistName = artistName.substring(0, 
                        artistName.indexOf("<SEP>"));
                delete(artistName, songName);
                break;
            case "remove" :
                topic = newSc.next();
                name = newSc.nextLine().trim();
                if (topic.compareTo("artist") == 0) {
                    removeArtist(name);
                }
                else if (topic.compareTo("song") == 0) {
                    removeSong(name);
                }
                else { //TODO: add error condition
                    System.out.println("Error: bad input " + topic);
                }
                break;
            case "print" :
                topic = newSc.next();
                if (topic.compareTo("artist") == 0) {
                    viewArtists();
                }
                else if (topic.compareTo("song") == 0) {
                    viewSongs();
                }
                else if (topic.compareTo("tree") == 0) {
                    viewTrees();
                }
                else { //TODO: add error condition
                    System.out.println("Error: bad input " + topic);
                }
                break;
            default:
                System.out.println("Unrecognized input "  +  cmd);
                break;
        } //end switch
    }
    
    /**
     * remove an artist from the tree
     * @param name the name of the artist
     */
    public void removeArtist(String name) {
        if (artistHash == null || artistHash.isEmpty() 
                || !artistHash.containsKey(name) 
                || artistTree == null || artistTree.isEmpty()) {
            System.out.println(
                    "|" + name + "| does not exist in the artist database.");
            return;
        }
        int handle = artistHash.get(name);
        artistTree.remove(handle, 0, true);                
        songTree.remove(0, handle, false);
        mem.getBuff()[handle] = 0; //turns off the flag
    }
    /**
     * removes a song from the tree
     * @param name the song name
     */
    public void removeSong(String name) {
        if (songHash == null || songHash.isEmpty() 
                || !songHash.containsKey(name) 
                || songTree == null || songTree.isEmpty()) {
            System.out.println(
                    "|" + name + "| does not exist in the song database.");
            return;
        }
        int handle = songHash.get(name);
        songTree.remove(handle, 0, true);                
        artistTree.remove(0, handle, false);        
        mem.getBuff()[handle] = 0; //turns off the flag
    }
    /**
     * print the artist tree followed by the song tree
     */
    public void viewTrees() {
        System.out.println("Printing artist tree:");
        System.out.println("Printing song tree:");
       // viewArtists();
       // viewSongs();
    }
    /**
     * print the artists in order of their handles
     */
    public void viewArtists() {
        int totalArtists = 0;
        ArrayList<Integer> artistHandles = null;
        if (artistTree != null && artistTree.getRoot() != null) {
            artistHandles = artistTree.treeDump();
            if (artistHandles != null ) {
                for (int pos = 0; pos < artistHandles.size(); pos++ ) {
                    int startPosInMem = artistHash.get(mem.getRecordValue(artistHandles.get(pos)));
                    String artistName = mem.getRecordValue(startPosInMem);
                    System.out.println("|"  + artistName + "|");
                }
                totalArtists = artistHandles.size();
            }
        }
        System.out.println("total artists: " + String.valueOf(totalArtists));
    }
    
    
    /**
     * print the songs
     */
    public void viewSongs() {
        int totalSongs = 0;
        ArrayList<Integer> songHandles = null;
        if (songTree != null && songTree.getRoot() != null) {
            songHandles = songTree.treeDump();
            if (songHandles != null ) {
                for (int pos = 0; pos < songHandles.size(); pos++ ) {
                    int startPosInMem = songHash.get(
                            mem.getRecordValue(songHandles.get(pos)));
                    if (startPosInMem >= 0) {
                        String songName = mem.getRecordValue(startPosInMem);
                        System.out.println("|" + songName + "|");
                    }
                }
                totalSongs = songHandles.size();
            }
        }
        System.out.println("total songs: " + String.valueOf(totalSongs));
    }
    
    /**
     * list
     * @param artist is the artist whose songs we want to list
     */
    public void listSongsByArtist(String artist) {
        if (artistHash == null || artistHash.isEmpty() 
                || !artistHash.containsKey(artist)) {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
            return;
        }
        int handle = artistHash.get(artist);
        ArrayList<Integer> connectedHandles = null;
        if (artistTree != null) {
            connectedHandles = artistTree.searchTree(handle);
            if (connectedHandles != null) {
                for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
                    if (connectedHandles.get(entry) >= 0) {
                        System.out.println(
                            mem.readEntry(connectedHandles.get(entry)));
                    }
                }
            }
        }
    }
    /**
     * lists all artists with this song
     * @param song the song name
     */
    public void listArtistsBySong(String song) {
        if (songHash == null || songHash.isEmpty() 
                || !songHash.containsKey(song)) {
            System.out.println(
                    "|" + song + "| does not exist in the song database.");
            return;
        }
        int handle = songHash.get(song);
        if (artistTree != null) {
            ArrayList<Integer> connectedHandles = null;
            connectedHandles = artistTree.searchTree(handle);
            if (connectedHandles != null) {
                for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
                    System.out.println(
                            mem.readEntry(connectedHandles.get(entry)));
                }
            }        
        }
    }
    /**
     * deletes an artist-song combination from the trees 
     * and the main memory when applicable
     * @param artistName the name of the artist
     * @param songName the name of the song
     */
    public void delete(String artistName, String songName) {
        boolean validEntry = true; // a good value to delete
        int aHandle = artistHash.containsKey(artistName) ?
                artistHash.get(artistName) : -1;
        int sHandle = songHash.containsKey(songName) ?
                songHash.get(songName) : -1;
        if (aHandle == -1 || mem.getRecordFlag(aHandle) != 1 ) {
            System.out.println("|"  +  artistName  +  
                    "| does not exist in the artist database.");
            validEntry = false;
        }
        if (sHandle == -1 || mem.getRecordFlag(sHandle) != 1) {
            System.out.println("|"  +  songName  +  
                    "| does not exist in the song database.");
            validEntry = false;
        }
        if (validEntry) {
            int artistDeleted = deleteEntry(artistTree, artistHash, songHash, 
                    artistName, songName); 
            int songDeleted = deleteEntry(songTree, songHash, artistHash, 
                    songName, artistName);
            if (artistDeleted == 2) {
                System.out.println("|"  +  artistName  +  
                        "| is deleted from the artist database.");
                mem.killRecord(artistHash.get(artistName));
            }
            if (songDeleted == 2) {
                System.out.println("|"  +  songName  +  
                        "| is deleted from the song database.");
                mem.killRecord(songHash.get(songName));
            }
        }
    }
    
    /**
     * deletes a single tree entry
     * @param tree is which tree to search
     * @param keyHash is the key's hash table
     * @param valueHash is the value's hash table
     * @param keyName is the name of the key to delete an entry for
     * @param valueName is the value to delete
     * @return true if the key will be deleted
     */
    public int deleteEntry(BST<Integer, Integer> tree, Hash keyHash, 
            Hash valueHash, String keyName, String valueName) {
        if (tree.delete(keyHash.get(keyName), 
                valueHash.get(valueName))) {
            System.out.println("The KVPair (|"
                    + keyName + "|,|" 
                    + valueName + "|) is deleted from the tree.");
            return 2;
        }
        else if (tree.removeSuccess) {
            System.out.println("The KVPair (|"
                    + keyName + "|,|" 
                    + valueName + "|) is deleted from the tree.");
            return 1;
        }
        else {
            return 0;
        }
    }

    
}
