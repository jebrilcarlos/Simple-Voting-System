import java.util.Scanner;

public class Menu {
    private final UserHandling uh = new UserHandling();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu () {
        int choice = 0;
        System.out.println("---------- Jebril's Voting System ----------\n");
        System.out.println("Welcome, user!\n"
                            + "\t1 = Sign up\n"
                            + "\t2 = Login\n"
                            + "\t3 = About\n"
                            + "\t0 = Exit\n");

        do {
            System.out.print("\n-> Enter Choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 3 || choice < 0) { System.out.println("Not a valid choice."); continue; }
            } catch (NumberFormatException err) { System.out.println(err); continue; }

            break;
        } while (true);

        switch (choice) {
            case 1:
            case 2: toFillUp(choice); break;
            case 3: aboutInfo(); break;
            case 0: System.out.println("Until next time!"); break;
            default: System.out.println("This is not a valid choice."); break;
        }

    }

    private void aboutInfo () {
        System.out.println("Voting System by Jebril Carlos\n");
        System.out.println("This system has the following features:\n"
                            + "\t- User Creation\n"
                            + "\t- User Login through ID / Name\n"
                            + "\t- Records are saved through file system\n"
                            + "\t- Simple and easy to navigate\n");
    }

    private void toFillUp (int choice) { uh.fillUp(choice); }
}
