import java.util.Scanner;

//upon launch, user will be redirected here
public class Menu {
    private final Utilities util = new Utilities();
    private final UserHandling uh = new UserHandling();
    private final Scanner sc = new Scanner(System.in);

    //main menu
    public void mainMenu () {
        int choice = 0;

        do {
            System.out.println("---------- Jebril's Voting System ----------\n");
            System.out.println("Welcome, user!\n"
                    + "\t1 = Sign up\n"
                    + "\t2 = Login\n"
                    + "\t3 = About\n"
                    + "\t0 = Exit\n");

            System.out.print("\n-> Enter Choice: ");
            try { //parsing integer throws NumberFormatException hence try catch block use
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 3 || choice < 0) { System.out.println("Not a valid choice."); continue; }
            } catch (NumberFormatException err) { System.out.println(err); continue; }

            switch (choice) {
                case 1: toFillUp(choice); break;
                case 2: if (toFillUp(choice)) { return; } break; //ends the menu loop if login successful (see line 18 in User Handling)
                case 3: aboutInfo(); break;
                case 0: System.out.println("Until next time!"); return;
            }
        } while (true);
    }

    //about section
    private void aboutInfo () {
        System.out.println("Voting System by Jebril Carlos\n");
        System.out.println("This system has the following features:\n"
                            + "\t- User Creation\n"
                            + "\t- User Login through ID / Name\n"
                            + "\t- Records are saved through file system\n"
                            + "\t- Simple and easy to navigate\n");
        util.pressEnterToContinue();
    }

    //method to call user handling class (see line 11 in User Handling)
    private boolean toFillUp (int choice) { return uh.fillUp(choice); }
}
