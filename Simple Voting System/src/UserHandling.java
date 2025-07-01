import java.util.ArrayList;
import java.util.Scanner;

//this handles user signup and login
public class UserHandling {
    private UserTemplate user = null;
    private final Utilities util = new Utilities();
    private final FileHandling fh = new FileHandling();
    private final Scanner sc = new Scanner(System.in);

    //handles user input, calls upon other methods, returns bool to evaluate menu loop
    public boolean fillUp (int choice) {
        //checks if file is created successfully (see line 12 in FileHandling)
        if (fh.createFile() != 1) { System.out.println("Can't create file."); return false;}
        String id = enterID(choice);

        //evaluates choice in menu (see line 28 in menu)
        if (choice == 1) { if (enterOtherInfo(id)) { signUp(); } return false; }
        if (choice == 2) { while (!logIn(id)) { id = enterID(choice); } return true; }

        //return false if not logged in for menu loop (see line 12 in menu)
        return false;
    }

    //input id, returns the id input
    private String enterID (int choice) {
        String id;
        do {
            System.out.print("Please enter your ID (8 characters): "); id = sc.nextLine();
            //length of id should be 8, if not then repeat
            if (id.length() != 8) { System.out.println("ID must be 8 characters."); continue; }

            //checks if id already exits in record (see line 36 in File Handling)
            if (choice == 1) {
                boolean dupe = fh.reviewDupID(id);
                if (dupe) { System.out.println("User with that ID already exists."); continue; }
            }
            break;
        } while (true);
        //returns id (see line 14)
        return id;
    }

    //input other user info, returns the user count to be evaluated
    private boolean enterOtherInfo (String id) {
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

        user = new UserTemplate(id, fname, mname, lname, age, voteStatus);
        //returns true for evaluation (line 15)
        return true;
    }

    //method to call file handling (write to file)
    private void signUp () {
        //evaluates if record is saved in file (see line 24 in File Handling)
        int result = fh.saveToFile(user);
        if (result < 0) { System.out.println("There was a problem while trying to save."); return; }
        System.out.println("User recorded successfully.\n"); util.pressEnterToContinue();
    }

    //method to call file handling (read from file)
    private boolean logIn (String id) {
        //assigns and evaluates if user exists (see line 56 in File Handling)
        user = fh.loadUser(id);
        if (user == null) {
            System.out.println("User with that ID not found.");
            util.pressEnterToContinue(); return false; }
        //calls upon homepage and passes the user
        HomePage hp = new HomePage(); hp.home(user);

        //returns true if login is successful (see line 18)
        return true;
    }
}
