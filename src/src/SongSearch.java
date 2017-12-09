//import sun.reflect.generics.tree.TypeTree;

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
    /*
     * constructor
     *
    public SongSearch() {}
      */  
    /**
     * main for the project
     * @param <twoThreeTree>
     * @param args the commandline arguments
     */
    public static void main(String[] args) {
        //if (args.length >= 3) {
        Database db = new Database(args[2], 
                Integer.parseInt(args[1]), Integer.parseInt(args[0]));
        db.beginParsing(args[2]);
        //}
        //else {
        //    System.out.println("Error: invalid input number of arguments");
       // }
    }

}
