import java.util.ArrayList;
import java.util.Scanner;

//this handles user functions
public class UserHandling {
    private final ArrayList<UserFormat> users = new ArrayList<UserFormat>();
    private final FileHandling fh = new FileHandling();
    private final Scanner sc = new Scanner(System.in);


    private int userCount () { return users.size(); }

    public void fillUp (int choice) {
        String id = enterID();
        if (choice != 2) {
            int before = userCount(), after = enterOtherInfo(id);
            if (before < after) { signUp(); }
        }
        logIn(id);
    }

    private String enterID () {
        String id;
        do {
            System.out.print("Please enter your ID (8 characters): "); id = sc.nextLine();
            if (id.length() != 8) { System.out.println("ID must be 8 characters."); continue; }
            break;
        } while (true);
        return id;
    }

    private int enterOtherInfo (String id) {
        String fname, lname; int age;
        do {
            System.out.print("Please input your age: ");
            try {
                age = Integer.parseInt(sc.nextLine());
                if (age < 18) { System.out.println("You must be 18+."); continue; }
            } catch (NumberFormatException err) { System.out.println(err); continue; }

            System.out.print("First Name: "); fname = sc.nextLine();
            System.out.print("Last Name: "); lname = sc.nextLine();

            break;
        } while (true);

        users.add(new UserFormat(id, fname, lname, age));
        return userCount();
    }

    private void signUp () {
        int result = fh.saveToFile(users);
        if (result < 0) { System.out.println("There was a problem while trying to save."); return; }
        System.out.println("User recorded successfully.");
    }

    private void logIn (String id) {
        UserFormat uf = fh.loadUser(id);
        if (uf == null) { System.out.println("User with that ID not found."); return; }
        users.add(uf);
        HomePage hp = new HomePage(); hp.home(uf);
    }
}
