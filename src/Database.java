import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
*
* @author Madelyn Newcomb m1newc
* @version 1
*/
public class Database {
    /**
     * The scanner used to read the input file
     */
    private Scanner sc;
   
    /**
     * The BST that stores the Artists
     */
    private BST artistTree;
   
    /**
     * The BST that stores the Songs
     */
    private BST songTree;
    
    /**
     * The Memory Pool
     */
    private MainMemory mem;
    
    /**
     * The hashtable that stores the artists
     */
    private Hash artistHash;
    
    /**
     * The hashtable that stores the songs
     */
    private Hash songHash;    //or not
    
    /**
     * getter
     * @return the BST containing the Artists
     */
    public BST getArtistTree() {
        return artistTree;
    }
    /**
     * getter
     * @return the BST containing the Songs
     */
    public BST getSongTree() {
        return songTree;
    }
    /**
     * getter
     * @return the Memory Pool
     */
    public MainMemory getMem() {
        return mem;
    }
    /**
     * getter
     * @return the hashtable containing the Artists
     */
    public Hash getArtistHash() {
        return artistHash;
    }
    /**
     * getter 
     * @return the hashtable containing the Songs
     */
    public Hash getSongHash() {
        return songHash;
    }
        
    
    
    
    
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
            artistTree = new BST();
            songTree = new BST();
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
                if (artistHash.get(artistName) != -1) {
                    System.out.println("|" + artistName + 
                            "| duplicates a record already" 
                            + " in the Artist database.");
                    //artistDuplicate = true;
                    artistHandle = artistHash.get(artistName);
                }
                else {
                    artistHandle = mem.addEntryGetHandle(artistName);
                    artistHash.put(artistName, artistHandle);
                    if(artistHash.get(artistName) == artistHandle) {
                            System.out.println("|" + artistName + 
                            "| is added to the Artist database.");
                    }
                }
                if (songHash.get(songName) != -1) {
                    System.out.println("|" + songName + 
                            "| duplicates a record already" 
                            + " in the Song database.");
                    //songDuplicate = true;
                    songHandle = songHash.get(songName);
                }
                else {
                    songHandle = mem.addEntryGetHandle(songName);
                    songHash.put(songName, songHandle);
                    System.out.println("|" + songName + 
                            "| is added to the Song database.");
                }
                if (artistTree.searchTree(artistHandle) == null ||
                        !artistTree.searchTree(artistHandle).contains(songHandle)) {
                    artistTree.add(artistHandle, songHandle);
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
                if (songTree.searchTree(songHandle) == null ||
                        !songTree.searchTree(songHandle).contains(artistHandle)) {
                    songTree.add(songHandle, artistHandle);
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
        newSc.close();
    }
    
    /**
     * remove an artist from the tree
     * @param name the name of the artist
     */
    public void removeArtist(String name) {
        if (artistHash == null || artistHash.size() == 0 
                || artistHash.get(name.trim()) == -1 ) {
            System.out.println(
                    "|" + name + "| does not exist in the artist database.");
            return;
        }
        int handle = artistHash.get(name);
        //list the songs matched to this artist
        ArrayList<Integer> songs = artistTree.searchTree(handle);
       //System.out.println("-------------------------------------------------------------------");
       //System.out.println("----------------Artist removal------------------------------------");
       //System.out.println(songs.size());
        boolean deleted = false;
        if (songs != null) { //there are songs that map to the artist
            //for each song of that artist, get its handle, and delete its corresponding KVPair

          //  System.out.println("--------------------------Inside the If----------------------------");
            for (int i = 0; i < songs.size(); i++)
            { 
            //    System.out.println("--------------------------Inside the for----------------------------");
                int songHandle = songs.get(i);
                //if the song is paired in the tree
                if (songTree.searchTree(songHandle) != null 
                        && songTree.searchTree(songHandle).size() >= 1)
                { 
              //      System.out.println("--------------------------about to delete----------------------------");
                    this.delete(name, mem.getRecordValue(songHandle));
                    deleted = true;
//                    delete(name, mem.getRecordValue(songHandle).trim());
                    //return;
                }
            }
        }
        if (!deleted)
        {
            System.out.println("|" + name + "| is deleted from the Artist database.");
            artistTree.remove(handle, 0, true);                
            songTree.remove(0, handle, false);
            artistHash.remove(name);
            mem.killRecord(handle);
        }
    }
    /**
     * removes a song from the tree
     * @param name the song name
     */
    public void removeSong(String name) {
        if (songHash == null || songHash.size() == 0 
                || songHash.get(name.trim()) == -1 ) {
            System.out.println(
                    "|" + name + "| does not exist in the song database.");
            return;
        }
        int handle = songHash.get(name);
        ArrayList<Integer> artists = songTree.searchTree(handle);
        boolean deleted = false;
        if (artists != null) {
            for (int i = 0; i < artists.size(); i++)
            {
                
                //if (artists.get(i) != null) {
                int artistHandle = artists.get(i);
                if ( artistTree.searchTree(artistHandle) != null && artistTree.searchTree(artistHandle).size() >= 1)
                {
                    if (artistTree.searchTree(artistHandle) == null || 
                            artistTree.searchTree(artistHandle).size() <= 1)
                    {
                //        System.out.print("-------------------------song search ----------------Artist:" +mem.getRecordValue(artistHandle).trim()+ ".\n");
                  //      System.out.print("-------------------------song search ------------------Song:" +name+ ".\n");
                        this.delete(mem.getRecordValue(artistHandle).trim(),name);
                        deleted = true;
                        //return;
                    }
                }
                //}
            }
        }
        if(!deleted)
        {
            System.out.println("|" + name + "| is deleted from the Song database.");
            songTree.remove(handle, 0, true);                
            artistTree.remove(0, handle, false);        
            songHash.remove(name);
            mem.killRecord(handle);
        }
    }
    /**
     * print the artist tree followed by the song tree
     */
    public void viewTrees() {
        System.out.println("Printing artist tree:");
        artistTree.treeDump(true);
        System.out.println("Printing song tree:");
        songTree.treeDump(true);
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
            artistHandles = artistTree.treeDump(false);
            if (artistHandles != null ) {
                for (int pos = 0; pos < artistHandles.size(); pos++ ) {
                    int startPosInMem = artistHash.get(mem.getRecordValue(
                            artistHandles.get(pos)).trim());
                    if(startPosInMem >= 0) {
                        String artistName = mem.getRecordValue(startPosInMem);
                        System.out.println("|"  + artistName.trim() + "| " 
                        + artistHash.h(artistName.trim(), artistHash.getCapacity()));
                        totalArtists++;
                    }
                }
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
            songHandles = songTree.treeDump(false);
            if (songHandles != null ) {
                for (int pos = 0; pos < songHandles.size(); pos++ ) {
                    int startPosInMem = songHash.get(
                            mem.getRecordValue(songHandles.get(pos)).trim());
                    if (startPosInMem >= 0) {
                        String songName = mem.getRecordValue(startPosInMem);
                        System.out.println("|" + songName.trim() + "| "
                                + songHash.h(songName.trim(),songHash.getCapacity()));
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
        if (artistHash == null || artistHash.size() == 0 
                || artistHash.get(artist) == -1) {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
            return;
        }
        int handle = artistHash.get(artist);
        ArrayList<Integer> connectedHandles = null;
        ArrayList<String> test = new ArrayList<String>();
        if (artistTree != null) {
            connectedHandles = artistTree.searchTree(handle);
            if (connectedHandles != null) {
                for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
                    if (connectedHandles.get(entry) >= 0) {
                        if(test.isEmpty())
                        {
                            test.add(mem.readEntry(connectedHandles.get(entry)).trim());
                        }
                        else
                        {
                            if(!test.contains(mem.readEntry(connectedHandles.get(entry)).trim())) {
                                test.add(mem.readEntry(connectedHandles.get(entry)).trim());
                            }
                        }
                    }
                }
                for (int outs = 0; outs < test.size(); outs++)
                {
                    System.out.println("|" + test.get(outs).trim() + "|");
                }   
            }
        }
    }
    /**
     * lists all artists with this song
     * @param song the song name
     */
    public void listArtistsBySong(String song) {
        if (songHash == null || songHash.size() == 0 
                || songHash.get(song) == -1) {
            System.out.println(
                    "|" + song + "| does not exist in the song database.");
            return;
        }
        int handle = songHash.get(song);
        ArrayList<Integer> connectedHandles = null;
        ArrayList<String> test = new ArrayList<String>();
        if (songTree != null) {
            connectedHandles = songTree.searchTree(handle);
            if (connectedHandles != null) {
                for (int entry = 0; entry < connectedHandles.size(); entry++ ) {
                    if (connectedHandles.get(entry) >= 0) {
                        if(test.isEmpty())
                        {
                            test.add(mem.readEntry(connectedHandles.get(entry)).trim());
                        }
                        else
                        {
                            if(!test.contains(mem.readEntry(connectedHandles.get(entry)).trim())) {
                                test.add(mem.readEntry(connectedHandles.get(entry)).trim());
                            }
                        }
                    }
                }
                for (int outs = 0; outs < test.size(); outs++)
                {
                    System.out.println("|" + test.get(outs).trim() + "|");
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
        int aHandle = artistHash.get(artistName);
        int sHandle = songHash.get(songName);
        if (aHandle < 0) {
            System.out.println("|"
                    + artistName + "| does not exist in the artist database.");
            return;
        }
        if (sHandle < 0) {
            System.out.println("|"
                    + songName + "| does not exist in the song database.");
            return;
        }
        ArrayList<Integer> test = artistTree.searchTree(aHandle);
        ArrayList<Integer> test2 = songTree.searchTree(sHandle);
        if (test == null || test2 == null 
                || !test.contains(sHandle) || !test2.contains(aHandle))
        {
            System.out.println("The KVPair (|"
                    + artistName + "|,|" 
                    + songName + "|) was not found in the database.");
            System.out.println("The KVPair (|"
                    + songName + "|,|" 
                    + artistName + "|) was not found in the database.");
            return;
        }    
        boolean found = false;
        for (int i = 0; i < test.size(); i++) {
            if(test.get(i) == sHandle)
            {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("The KVPair (|"
                    + artistName + "|,|" 
                    + songName + "|) was not found in the database.");
        }
        found = false;
        for (int i = 0; i < test2.size(); i++) {
            if(test2.get(i) == aHandle)
            {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("The KVPair (|"
                    + songName + "|,|" 
                    + artistName + "|) was not found in the database.");
            return;
        }
        int artistDeleted = deleteEntry(artistTree, aHandle, sHandle, 
                artistName, songName); 
        int songDeleted = deleteEntry(songTree, sHandle, aHandle, 
                songName, artistName);
        if (artistDeleted == 2) {
            artistHash.remove(artistName.trim());
            System.out.println("|"  +  artistName  +  
                    "| is deleted from the Artist database.");
        }
        if (songDeleted == 2) {
            songHash.remove(songName.trim());
            System.out.println("|"  +  songName  +  
                    "| is deleted from the Song database.");
        }
        if  (artistDeleted == 2) {
            mem.killRecord(aHandle);
        }
        if (songDeleted == 2) {
            mem.killRecord(sHandle);
        }
     //   System.out.println("---------------------------songHandle----" +sHandle+"--------------------");

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
    public int deleteEntry(BST tree, int keyHandle, 
            int valueHandle, String keyName, String valueName) {
        if (tree.delete(keyHandle, 
                valueHandle)) {
            System.out.println("The KVPair (|"
                    + keyName + "|,|" 
                    + valueName + "|) is deleted from the tree.");
            return 2;
        }
        else if (tree.getRemoveSuccess()) {
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
