package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;
import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class HEAD implements Serializable{

    public Commit CurrentCommit;

    public HEAD(Commit currentCommit) {
        this.CurrentCommit = currentCommit;
    }

    public static HEAD fromHead(String name) {
        File inFile = join(COMMITS, name);
        HEAD temp = readObject(inFile, HEAD.class);
        return temp;
    }

    public void saveHead() {
        File outfile = join(COMMITS, "head");
        writeObject(outfile, this);
    }


}
