import java.util.Scanner;

//upon login, user will be redirected here
public class HomePage {
    private final Scanner sc = new Scanner(System.in);

    public void home (UserTemplate uf) {
        System.out.printf("\nWelcome, %s!\n\n", uf.getFirstName());

        System.out.println("1 = Vote now\n"
                        + "2 = Check my vote\n"
                        + "3 = View candidates\n"
                        + "4 = Logout\n");

        int choice;
        System.out.print("Enter choice: "); choice = Integer.parseInt(sc.nextLine());
    }
}