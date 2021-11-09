package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;
import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class BRANCH implements Serializable{

    public Commit CurrentCommit;

    public BRANCH(Commit currentCommit) {
        this.CurrentCommit = currentCommit;
    }

    public static BRANCH fromBranch(String name) {
        File inFile = join(COMMITS, name);
        BRANCH temp = readObject(inFile, BRANCH.class);
        return temp;
    }

    public void saveBranch() {
        File outfile = join(COMMITS, "branch");
        writeObject(outfile, this);
    }


}