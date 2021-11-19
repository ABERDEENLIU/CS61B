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
                break;
            case "add":
                validateNumArgs("add", args, 2);
                Repository.stagingFile(args[1]);
                break;
            case "commit":
                if (args.length == 1) {
                    System.out.println("Please enter a commit message.");
                } else {
                validateNumArgs("commit", args, 2);
                Repository.commitFile(args[1]);}
                break;
            case "rm":
                // TODO: unstaged the file if it's currently staged
                break;
            case "log":
                validateNumArgs("log", args, 1);
                Repository.logDisplay();
                break;
            case "global-log":
                validateNumArgs("global-log", args, 1);
                Repository.globlelogDisplay();
                break;
            case "find" :
                validateNumArgs("find", args, 2);
                Repository.findcommitmessage(args[1]);
                break;
            case "status":
                // TODO: Display what branckes currently exist, and marks the current with *
                break;
            case "checkout":
                if (args.length == 3) {
                validateNumArgs("checkout", args, 3);
                Repository.checkoutFile(args[2]);}
                if (args.length == 4) {
                    validateNumArgs("checkout", args, 4);
                    Repository.checkoutCommit(args[1], args[3]);}
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
