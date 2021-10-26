package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.println("Please enter a command");
            System.exit(0);
        }

        if (args.length > 1) {
            System.out.println("Incorrect operands");
            System.exit(0);
        }

        // if in the wrong directory, print out "Not in an initialized Gitlet directory."

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            case "commit":
                // TODO: saves a snaopshot of tracked filed in the curent commit
                break;
            case "rm":
                // TODO: unstaged the file if it's currently staged
                break;
            case "log":
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


 /*           case "checkout":
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
}
