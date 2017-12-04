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
    private BST<Integer, Integer> artistTree;
    private BST<Integer, Integer> songTree;
    private MainMemory mem;
    private Hash artistHash;
    private Hash songHash;    
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
            artistTree = null;
            songTree = null;
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
                String cmd = sc.next(); //Read the next term
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
        switch(cmd) {
            case "insert" ://Found an insert command
                String artistName = sc.nextLine();
                String songName = artistName.substring(
                        artistName.indexOf("<SEP>") + 5);
                artistName = artistName.substring(
                        0, artistName.indexOf("<SEP>"));
                int artistHandle = addEntryGetHandle(artistName);
                int songHandle = addEntryGetHandle(songName);
                artistHash.put(artistName, artistHandle);
                songHash.put(songName, songHandle);
                break;
            case "list" :
                topic = sc.next();
                name = sc.next();           
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
                artistName = sc.nextLine();
                songName = artistName.substring(
                        artistName.indexOf("<SEP>") + 5);
                artistName = artistName.substring(0, 
                        artistName.indexOf("<SEP>"));
                delete(artistName, songName);
                break;
            case "remove" :
                topic = sc.next();
                name = sc.next();           
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
                topic = sc.next();
                if (topic.compareTo("artists") == 0) {
                    viewArtists();
                }
                else if (topic.compareTo("songs") == 0) {
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
     * adds an entry to the main memory and returns the corresponding handle
     * @param text the string to add to memory
     * @return the handle in memory
     */
    public int addEntryGetHandle(String text) {
        byte[] recordlength = new byte[3  +  text.length()];
        recordlength[0] = 1;
        recordlength[1] = (byte)((3  +  text.length()) >> 4);
        recordlength[2] = (byte)((3  +  text.length()) & 0xF);
        for (int i = 0; i < text.length(); i++ ) {
            recordlength[i  +  3] = (byte)text.charAt(i);
        }
        return mem.addToTheFill(recordlength);
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
                    "There are no artists to remove by that name.");
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
            System.out.println("There are no songs to remove by that name.");
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
        viewArtists();
        viewSongs();
    }
    /**
     * print the artists in order of their handles
     */
    public void viewArtists() {
        ArrayList<Integer> artistHandles = null;
        if (artistTree == null) {
            System.out.println("There are no artists.");
        }
        else {
            System.out.println("Artists:");
            artistHandles = artistTree.treeDump();
            if (artistHandles == null ) { return; }
            for (int pos = 0; pos < artistHandles.size(); pos++ ) {
                int startPosInMem = artistHash.get(artistHandles.get(pos));
                String artistName = mem.getRecordValue(startPosInMem);
                System.out.println(artistName);
            }
        }
    }
    
    
    /**
     * print the songs
     */
    public void viewSongs() {
        if (songTree == null) {
            System.out.println("There are no songs.");
        }
        else {
            System.out.println("Songs:");
            ArrayList<Integer> songHandles = songTree.treeDump();
            if (songHandles == null ) { return; }
            for (int pos = 0; pos < songHandles.size(); pos++ ) {
                int startPosInMem = songHash.get(songHandles.get(pos));
                String songName = mem.getRecordValue(startPosInMem);
                System.out.println(songName);
            }
        }
    }
    
    /**
     * list
     * @param artist is the artist whose songs we want to list
     */
    public void listSongsByArtist(String artist) {
        if (artistHash == null || artistHash.isEmpty() 
                || !artistHash.containsKey(artist)) {
            System.out.println(
                    "There are no artists to search by that name.");
            return;
        }
        int handle = artistHash.get(artist);
        ArrayList<Integer> connectedHandles = artistTree.searchTree(handle);
        for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
            System.out.println(mem.readEntry(connectedHandles.get(entry)));
        }
    }
    /**
     * lists all artists with this song
     * @param song the song name
     */
    public void listArtistsBySong(String song) {
        if (songHash == null || songHash.isEmpty() 
                || !songHash.containsKey(song)) {
            System.out.println("There are no songs to search by that name.");
            return;
        }
        int handle = songHash.get(song);
        ArrayList<Integer> connectedHandles = songTree.searchTree(handle);
        for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
            System.out.println(mem.readEntry(connectedHandles.get(entry)));
        }
        
    }
    /**
     * deletes an artist-song combination
     * @param artistName the name of the artist
     * @param songName the name of the song
     */
    public void delete(String artistName, String songName) {
        if (!artistHash.containsKey(artistName) || artistTree == null) {
            System.out.println("|"  +  artistName  +  
                    "| does not exist in the artist database.");
        }
        else if (!songHash.containsKey(songName) || songTree == null) {
            System.out.println("|"  +  songName  +  
                    "| does not exist in the song database.");
        }
        else {
            deleteEntry(artistTree, artistHash, songHash, 
                    artistName, songName);
            deleteEntry(songTree, songHash, artistHash, 
                    songName, artistName);
        }
    }
    
    /**
     * deletes a single tree entry
     * @param tree is which tree to search
     * @param keyHash is the key's hash table
     * @param valueHash is the value's hash table
     * @param keyName is the name of the key to delete an entry for
     * @param valueName is the value to delete
     */
    public void deleteEntry(BST<Integer, Integer> tree, Hash keyHash, 
            Hash valueHash, String keyName, String valueName) {
        ArrayList<Integer> values = tree.searchTree(
                keyHash.get(keyName).intValue());
        int handleToDelete = valueHash.get(valueName).intValue();
        for (int pos = 0; pos < values.size(); pos++ ) {
            if (values.get(pos) == handleToDelete) {
                values.remove(pos);
            }
        
        }
    }
    /**
     * a class that wraps HashMap
     * @author maden
     *
     */
    public class Hash extends HashMap<String, Integer> {
        private String name;
        private int handle;
        private HashMap<String, Integer> hm;
        /**
         * constructor
         * @param sizeHash the initial hash table's size
         */
        public Hash(int sizeHash) {
            name = null;
            handle = -1;
            hm = new HashMap<String, Integer>(sizeHash, (float)0.5);
        }
        /**
         * the hashCode
         * @return the index in the hash table
         */
        public int hashCode() {
            return h(name, handle);
        }
        /**
         * checks if the objects are equal
         * @param o is the object to compare
         * @return true if equals
         */
        public boolean equals(Object o) {
            if (o instanceof KVPair<?, ?>) {
                KVPair<?, ?> other = (KVPair<?, ?>) o;
                return (other.getKeyHandle().toString()
                      .compareTo(String.valueOf(handle)) == 0);
            }
            return false;
        }
        /**
         * from openDSA, this is the hash function
         * @param s the string
         * @param m the location
         * @return the handle
         */
        public int h(String s, int m)
        {
            int intLength = s.length() / 4;
            long sum = 0;
            for (int j = 0; j < intLength; j++)
            {
                char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
                long mult = 1;
                for (int k = 0; k < c.length; k++)
                {
                    sum  += c[k] * mult;
                    mult *= 256;
                }
            }
            char[] c = s.substring(intLength * 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++ )
            {
                sum  += c[k] * mult;
                mult *= 256;
            }
            return (int)(Math.abs(sum) % m);
        }

        
    }
    
}
