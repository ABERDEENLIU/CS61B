package gitlet;

import java.io.IOException;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) throws IOException {
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.println("Please enter a command");
            System.exit(0);
        }

 /*       if (args.length > 1) {
            System.out.println("Incorrect operands");
            System.exit(0);
        } */

        // if in the wrong directory, print out "Not in an initialized Gitlet directory."

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                validateNumArgs("init", args, 1);
                Repository.setupPersistence();
                // TODO: handle the `init` command
                break;
            case "add":
                validateNumArgs("add", args, 2);
                Repository.stagingFile(args[1]);
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            case "commit":
                if (args.length == 1) {
                    System.out.println("Please enter a commit message.");
                }
                validateNumArgs("commit", args, 2);
                Repository.commitFile(args[1]);
                // TODO: saves a snaopshot of tracked filed in the curent commit
                break;
            case "rm":
                // TODO: unstaged the file if it's currently staged
                break;
            case "log":
                validateNumArgs("log", args, 1);
                Repository.logDisplay();
                // TODO: display information about each commit till the initial commit.
                break;
            case "global-log":
                // TODO: display every commit ever made (different branches)
                break;
            case "find" :
                // TODO: print our IDs of all commits with message
                break;
            case "status":
                // TODO: Display what branckes currently exist, and marks the current with *
                break;


 /*         case "checkout":
                // TODO:
                break;
            case "branch":
                // TODO:
                break;
            case "rm-branch":
                // TODO:
                break;
            case "reset":
                // TODO:
                break;
            case "merge":
                // TODO:
                break;

 */

            default:
                System.out.println("No command with that name exists.");
        }
    }

    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
