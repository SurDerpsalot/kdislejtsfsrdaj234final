import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
    private Scanner sc; //the scanner for the input file
    BST<Integer, Integer> tree;
//    BST<Integer, Integer> songTree;
    MainMemory mem;
    Hash artistHash;
    Hash songHash;    
    /**
     * constructor
     */
    public Database(String filename, int sizeMem, int sizeHash) {
        try {
            sc = new Scanner(new File(filename));
            mem = new MainMemory(sizeMem);
            tree = null;
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
                String songName = artistName.substring(artistName.indexOf("<SEP>")+5);
                artistName = artistName.substring(0, artistName.indexOf("<SEP>"));
                int artistHandle = addEntryGetHandle(artistName);
                int songHandle = addEntryGetHandle(songName);
                artistHash.put(artistName, artistHandle);
                songHash.put(songName, songHandle);
                break;
            case "list" :
                topic = sc.next();
                name = sc.next();           
                if(topic.compareTo("artist") == 0) {
                    listSongsByArtist(name);
                }
                else if(topic.compareTo("song") == 0) {
                    listArtistsBySong(name);
                }
                else { //TODO: add error condition
                    
                }
                break;
            case "remove" :
                topic = sc.next();
                name = sc.next();           
                if(topic.compareTo("artist") == 0) {
                    removeArtist(name);
                }
                else if(topic.compareTo("song") == 0) {
                    removeSong(name);
                }
                else { //TODO: add error condition
                    
                }
                break;
            case "print" :
                topic = sc.next();
                if(topic.compareTo("artists") == 0) {
                    viewArtists();
                }
                else if(topic.compareTo("songs") == 0) {
                    viewSongs();
                }
                else if(topic.compareTo("tree") == 0) {
                    viewTrees();
                }
                else { //TODO: add error condition
                    
                }
                break;
            default:
                System.out.println("Unrecognized input " + cmd);
                break;
        } //end switch
    }
    /**
     * adds an entry to the main memory and returns the corresponding handle
     * @param text the string to add to memory
     * @return the handle in memory
     */
    public int addEntryGetHandle(String text) {
        byte[] recordlength = new byte[3 + text.length()];
        recordlength[0] = 1;
        recordlength[1] = (byte)((3 + text.length()) >> 4);
        recordlength[2] = (byte)((3 + text.length()) & 0xF);
        for(int i = 0; i < text.length(); i++) {
            recordlength[i + 3] = (byte)text.charAt(i);
        }
        return mem.addToTheFill(recordlength);
    }
    
    public void removeArtist(String name) {
        int handle = artistHash.get(name);
        tree.remove(handle, 0, true);                
        tree.remove(0, handle, false);
        mem.getBuff()[handle] = 0; //turns off the flag
    }
    
    public void removeSong(String name) {
        int handle = songHash.get(name);
        tree.remove(handle, 0, true);                
        tree.remove(0, handle, false);        
        mem.getBuff()[handle] = 0; //turns off the flag
    }
    /**
     * print the artist tree followed by the song tree
     */
    public void viewTrees() {
        
    }
    /**
     * print the artists
     */
    public void viewArtists() {
        
    }
    /**
     * print the songs
     */
    public void viewSongs() {
        
    }
    
    public void listSongsByArtist(String artist) {
        int handle = artistHash.get(artist);
        ArrayList<Integer> connectedHandles = tree.searchTree(handle);
        for(int entry = 0; entry < connectedHandles.size(); entry++) {
            System.out.println(mem.readEntry(connectedHandles.get(entry)));
        }
    }

    public void listArtistsBySong(String song) {
        int handle = songHash.get(song);
        ArrayList<Integer> connectedHandles = tree.searchTree(handle);
        for(int entry = 0; entry < connectedHandles.size(); entry++) {
            System.out.println(mem.readEntry(connectedHandles.get(entry)));
        }
        
    }

    public class Hash extends HashMap<String, Integer> {
        protected String name;
        protected int handle;
        protected HashMap<String, Integer> hm;
        /**
         * constructor
         */
        public Hash(int sizeHash) {
            name = null;
            handle = -1;
            hm = new HashMap<String, Integer>(sizeHash, (float)0.5);
        }
        public int hashCode() {
            return h(name, handle);
        }
        
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
         * @param s
         * @param m
         * @return
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
                    sum += c[k] * mult;
                    mult *= 256;
                }
            }
            char[] c = s.substring(intLength * 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++)
            {
                sum += c[k] * mult;
                mult *= 256;
            }
            return (int)(Math.abs(sum) % m);
        }

        
    }
    
}
