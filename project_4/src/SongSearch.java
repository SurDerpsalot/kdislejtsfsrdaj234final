import sun.reflect.generics.tree.TypeTree;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * 
 * @author Madelyn Newcomb m1newc, Bradley Finagin bfin96
 * @version 11/11/2017
 *
 */
public class SongSearch {
    /**
     * constructor
     */
    public SongSearch() {}
        
    /**
     * main for the project
     * @param <twoThreeTree>
     * @param args the commandline arguments
     */
    public static void main(String[] args) {
        twoThreeTree<Integer, KVPair<Integer, Integer>> ttt = 
                new twoThreeTree<Integer, KVPair<Integer, Integer>>();
        KVPair<Integer, Integer> kV = new KVPair<Integer, Integer>(22, 222);
        ttt.insert(2, kV);
        kV = new KVPair<Integer, Integer>(33, 333);
        ttt.insert(3, kV);
        ttt.insert(4, new KVPair<Integer, Integer>(44, 444));
        KVPair<Integer, Integer> out = ttt.find(2);
        KVPair<Integer, Integer> out2 = ttt.find(4);
        System.out.println("Artist key: " + out.getKeyHandle().toString());
        System.out.println("Song key: " + out.getValueHandles().toString());
//        int outbad = ttt.find(2);
        
     }

}
