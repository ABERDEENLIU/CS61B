package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static gitlet.HEAD.fromHead;
import static gitlet.Utils.*;
import static gitlet.Staging.*;
import static gitlet.Commit.*;

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

        }
    }

    public static void commitFile(String name) {

        File inFile = join(STAGING_AREA, "status");
        if (!inFile.exists()) {
            System.out.println("No changes added to the commit.");
        } else {

            Staging status = fromFile("status");

            if (status.noCommit()) {
                System.out.println("No changes added to the commit.");
            }

            Commit currentCommit = new Commit(name);
            HEAD head = new HEAD(currentCommit);
            BRANCH Master = new BRANCH(currentCommit);
            head.saveHead();
            Master.saveBranch();
            currentCommit.saveCommit();
            status.clearStaging();
            status.saveFile();
        }
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

    public static void globlelogDisplay() {

        List<String> commitlist = plainFilenamesIn(COMMITS);

        for (String commithash : commitlist) {
            Commit commit = fromCommitHash(commithash);
            commit.printINFO();
        }
    }

    public static void findcommitmessage(String message) {

        List<String> commitlist = plainFilenamesIn(COMMITS);

        boolean found = false;
        for (String commithash : commitlist) {
            Commit commit = fromCommitHash(commithash);
            if (commit.getMessage().equals(message)) {
                System.out.println(commithash);
                found = true;
            }
        }
        if (! found) {
            System.out.println("ound no commit with that message.");
        }
    }

    public static void checkoutFile(String name) {
        HEAD head = fromHead("head");
        Commit current = head.CurrentCommit;
        Staging status = current.getFileList();
        TreeMap<String, String> commitfilelist = status.fileList;

        for (String filename: commitfilelist.keySet()) {
            if (filename.equals(name)) {
                restrictedDelete(name);
                File commitfile = join(BLOBS, commitfilelist.get(name));
                String content = readContentsAsString(commitfile);
                File writefile = join(CWD, name);
                writeContents(writefile, content);
            } else {
                System.out.println("File does not exist in that commit.");
            }
        }
    }

    public static void checkoutCommit(String commithashname, String name) {

        if (!checkCommitExit(commithashname)) {
            System.out.println("No commit with that id exists.");
        }

        Commit current = fromCommitHash(commithashname);
        Staging status = current.getFileList();
        TreeMap<String, String> commitfilelist = status.fileList;

        if (commitfilelist.containsKey(name)) {
            restrictedDelete(name);
            File commitfile = join(BLOBS, commitfilelist.get(name));
            String content = readContentsAsString(commitfile);
            File writefile = join(CWD, name);
            writeContents(writefile, content);
        } else {
            System.out.println("File does not exist in that commit.");
        }
    }

    public static boolean checkCommitExit(String commithashname) {
        File commitfile = join(COMMITS, commithashname);
        return commitfile.exists();
    }



}








    /* TODO: fill in the rest of this class. */

