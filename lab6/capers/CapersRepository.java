package capers;

// import net.sf.saxon.trans.SymbolicName;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static capers.Dog.DOG_FOLDER;
import static capers.Dog.fromFile;
import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPER_FOLDER = join(CWD, ".capers");
    static final File STORY = join(CAPER_FOLDER, "story");
     // TODO Hint: look at the `join`
    //      function in Utils



    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() throws IOException {
        CAPER_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();
        STORY.createNewFile();

    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String current = readContentsAsString(STORY);
        Utils.writeContents(STORY, current, text, "\n");
        String tobeprint = Utils.readContentsAsString(STORY);
        System.out.println(tobeprint);
        // TODO
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog a = new Dog(name, breed, age);
        System.out.println(a.toString());
        a.saveDog();
        // TODO
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog a = fromFile(name);
        a.haveBirthday();
        a.saveDog();
        // TODO
    }
}
