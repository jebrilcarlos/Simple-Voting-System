import java.util.Scanner;

//utility class (commonly used & useful methods)
public class Utilities {
    private static final Scanner sc = new Scanner(System.in);

    // Waits for the user to press Enter
    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
    }

}
