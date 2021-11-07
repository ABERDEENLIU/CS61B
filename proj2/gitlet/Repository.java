package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import static gitlet.HEAD.fromHead;
import static gitlet.Utils.*;
import static gitlet.Staging.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File BLOBS = join(GITLET_DIR, "blobs");
    public static final File COMMITS = join(GITLET_DIR, "commits");
    public static final File STAGING_AREA = join(GITLET_DIR, "staging_area");


    public static void setupPersistence() throws IOException {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
        } else {
            GITLET_DIR.mkdir();
            BLOBS.mkdir();
            COMMITS.mkdir();
            STAGING_AREA.mkdir();

            Commit init_commit = new Commit();
            HEAD head = new HEAD(init_commit);
            head.saveHead();
            init_commit.saveCommit();
        }
    }

    public static void stagingFile(String name) throws IOException {

        if (!Staging.verifyExist(name)) {
            System.out.println("File does not exist.");
        } else {

            File f=join(STAGING_AREA, "status");
            Staging status;
            if (!f.exists()) {
                status = new Staging();
            } else {
                status = fromFile("status");}
            String hashcode = gethashcode(name);
            byte[] content = readContents(join(CWD, name));

            status.updateFile(name, hashcode);
            status.saveFile();
            writeContents(join(BLOBS, hashcode), content);

            status.printallkeys();
        }
    }

    public static void commitFile(String name) {
        Staging status = fromFile("status");

        if (status.noCommit()) {
            System.out.println("No changes added to the commit.");
        }

        Commit currentCommit = new Commit(name);
        HEAD head = new HEAD(currentCommit);
        head.saveHead();
        currentCommit.saveCommit();
        status.clearStaging();
    }

    public static void logDisplay() {

        HEAD head = fromHead("head");
        Commit current = head.CurrentCommit;

        while (current.getParentID() != null) {
            String parentID = current.getParentID();
            File inFile = join(COMMITS, parentID);
            Commit next = readObject(inFile, Commit.class);
            current.printINFO();
            current = next;

        }

        current.printINFO();
    }


    }








    /* TODO: fill in the rest of this class. */

