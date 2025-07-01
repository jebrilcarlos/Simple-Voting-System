import java.util.ArrayList;
import java.util.Scanner;

//this handles user signup and login
public class UserHandling {
    private final ArrayList<UserTemplate> users = new ArrayList<UserTemplate>();
    private final FileHandling fh = new FileHandling();
    private final Scanner sc = new Scanner(System.in);

    //counts array list size
    private int userCount () { return users.size(); }

    //handles user input, calls upon other methods, returns bool to evaluate menu loop
    public boolean fillUp (int choice) {
        if (fh.createFile() != 1) {
            System.out.println("Can't create file.");
            return false;
        }

        String id = enterID(choice);
        if (choice == 1) {
            int before = userCount(), after = enterOtherInfo(id);
            if (before < after) { signUp(); }
            return false;
        }
        if (choice == 2) {
            logIn(id);
            return true;
        }

        return false;
    }

    //input id, returns the id input
    private String enterID (int choice) {
        String id;
        do {
            System.out.print("Please enter your ID (8 characters): "); id = sc.nextLine();
            if (id.length() != 8) { System.out.println("ID must be 8 characters."); continue; }

            if (choice == 1) {
                boolean dupe = fh.reviewDupID(id);
                if (dupe) {
                    System.out.println("User with that ID already exists.");
                    continue;
                }
            }
            break;
        } while (true);
        return id;
    }

    //input other user info, returns the user count to be evaluated
    private int enterOtherInfo (String id) {
        String fname, mname, lname; int age, voteStatus;
        do {
            System.out.print("Please input your age: ");
            try { //parsing integer throws NumberFormatException hence try catch block use
                age = Integer.parseInt(sc.nextLine());
                if (age < 18) { System.out.println("You must be 18+."); continue; }
            } catch (NumberFormatException err) { System.out.println(err); continue; }

            System.out.print("First Name: "); fname = sc.nextLine();
            System.out.print("Middle Name: "); mname = sc.nextLine();
            System.out.print("Last Name: "); lname = sc.nextLine();
            voteStatus = 0;

            break;
        } while (true);

        users.add(new UserTemplate(id, fname, mname, lname, age, voteStatus));
        return userCount();
    }

    //method to call file handling (write to file)
    private void signUp () {
        int result = fh.saveToFile(users);
        if (result < 0) { System.out.println("There was a problem while trying to save."); return; }
        System.out.println("User recorded successfully.\n");
    }

    //method to call file handling (read from file)
    private void logIn (String id) {
        UserTemplate uf = fh.loadUser(id);
        if (uf == null) { System.out.println("User with that ID not found."); return; }
        users.add(uf);
        HomePage hp = new HomePage(); hp.home(uf);
    }
}
