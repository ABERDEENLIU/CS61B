package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;
import java.util.Map;
import static gitlet.Repository.*;
import static gitlet.HEAD.*;

import static gitlet.Staging.fromFile;
import static gitlet.Utils.join;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {

    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String hashname;
    private String parentID;
    private String Author;
    private String date;
    private Staging filelist;


    /* TODO: fill in the rest of this class. */


    public Commit() {

 //       this.date = "00:00:00 UTC, Thursday, 1 January 1970";
        this.message = "initial commit";
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 1, 1, 0, 0, 0);
        this.date = cal.getTime().toString();
 //       DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
 //       this.date = df.format(new Date());
        this.hashname = sha1(this.message, this.date);
    }



    //private String message;
    //private String hashname;
    //private String parentID;
    //private String Author;
    //private String date;
    //private Staging filelist;
    public Commit(String message) {

        Staging status = fromFile("status");
        HEAD head = fromHead("head");
        Commit current = head.CurrentCommit;
        String filelistname = "";
        String filelisthascode = "";

        this.message = message;
        this.parentID = current.hashname;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime().toString();
        this.filelist = status;
        for (String i: status.fileList.keySet()) {
            filelistname += i;
            filelisthascode += status.fileList.get(i);
        }
        this.hashname = sha1(this.message, this.parentID, this.date, filelistname, filelisthascode);
    }


    public void saveCommit() {
        File outfile = new File(COMMITS, this.hashname);
        writeObject(outfile, this);
    }

    public String getDate() {
        return this.date;
    }

    public String getHashname() {
        return this.hashname;
    }

    public String getParentID() {
        return this.parentID;
    }

    public void printINFO() {
        System.out.println("===");
        System.out.println("commit " + hashname);
        System.out.println("Date: " + date);
        System.out.println(message);
        System.out.println("\n");
    }
}
