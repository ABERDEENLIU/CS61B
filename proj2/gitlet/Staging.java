package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

import static gitlet.Repository.*;

import static gitlet.Utils.*;

public class Staging implements Serializable{

    public TreeMap<String, String> fileList;

    public Staging() {
        this.fileList = new TreeMap<>();
    }

    public static boolean verifyExist(String name) {
        if (join(CWD, name).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean alreadyinCommit(String name) {
        return false;
    }

    // 1. readin a file as byte[]: static byte[] readContents(File file)
    // 2. sha-1 hash the file in byte[] to get the file name;
    // 3. writes out the byte[] using the hased code as name: static void writeContents(File file, Object... contents)
    public static String gethashcode(String name){
        byte[] temp = readContents(join(CWD, name));
        String hashcode = sha1(temp);
        return hashcode;
    }

    public static Staging fromFile(String name) {
            File inFile = join(STAGING_AREA, name);
            Staging temp = readObject(inFile, Staging.class);
            return temp;
    }

    public void updateFile(String name, String hashcode) {
            fileList.put(name, hashcode);
    }

    public void saveFile() {
        File outfile = join(STAGING_AREA, "status");
        writeObject(outfile, this);
    }

    public boolean containsFile(String name) {
        return fileList.containsKey(name);
    }

    public String getcode(String name) {
        return fileList.get(name);
    }

    public boolean noCommit() {
        return fileList.isEmpty();
    }

    public void clearStaging() {
        fileList.clear();
    }

    public void printallkeys() {
        for (String i : fileList.keySet()) {
            System.out.println(i);
            System.out.println(fileList.get(i));
        }
    }

}
