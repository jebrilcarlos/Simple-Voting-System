import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//this is where file is handled and manipulated
public class FileHandling {
    private final File uFile = new File("users.txt");

    public int createFile () {
        int file = 0;
        try {
            uFile.createNewFile();
            return file + 1; // returns int for evaluation (see line 13 in User Handling)
        } catch (IOException err) {
            System.out.println(err);
            return 0;
        }
    }

    //method to write in file, returns int to evaluate if saving success
    public int saveToFile (UserTemplate u) {
        int recorded = 0;
        try { //FileWriter throws IOException hence try catch block use
            FileWriter fw = new FileWriter(uFile, true);
                fw.write(u.toString() + "\n");
                recorded++;
            fw.close(); //never forget to close
        } catch (IOException err) { System.out.println(err); }
        return recorded; //to evaluate if saving is a success (see line 69 in User Handling)
    }

    //method to evaluate duplicate id, returns bool to be evaluated in userHandling
    public boolean reviewDupID (String id) {
        String uID = null;
        try { //Scanner throws FileNotFoundException hence try catch block use
            Scanner sc = new Scanner(uFile);
            while (sc.hasNextLine()) { //reads through the file until it has \n
                String line = sc.nextLine(); //assigns entire line till \n
                if (line.isEmpty()) { continue; } //skips empty lines
                String person[] = line.split("\\|"); //divides the line into parts every |
                if (person.length >= 6) { uID = person[0].trim(); } //if a match is found, it assigns it to a variable
                if (uID.equals(id)) {
                    return true; //for evaluating if there is a dupe id (see line 34 in User Handling)
                }
            }
            sc.close(); //never forget to close
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }

        return false;
    }

    //method to load from file (usually for the login), returns the user format
    public UserTemplate loadUser (String id) {
        String uID = null; //ensures uID is empty firsthand
        String fname, mname, lname;
        int age, voteStatus;
        try { //Scanner throws FileNotFoundException hence try catch block use
            Scanner sc = new Scanner(uFile);
            while (sc.hasNextLine()) { //reads through the file until it has \n
                String line = sc.nextLine(); //assigns entire line till \n
                if (line.isEmpty()) { continue; } //skips empty lines
                String person[] = line.split("\\|"); //divides the line into parts every |
                if (person.length >= 6) { uID = person[0].trim(); } //if a match is found, it assigns it to a variable
                if (uID.equals(id)) {
                    fname = person[1].trim();
                    mname = person[2].trim();
                    lname = person[3].trim();
                    age = Integer.parseInt(person[4].trim());
                    voteStatus = Integer.parseInt(person[5].trim());

                    sc.close();
                    //returns the found user, but if not then null is returned (see line 77 in User Handling)
                    return new UserTemplate(id, fname, mname, lname, age, voteStatus);
                }
            }
            sc.close(); //never forget to close
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }

        return null;
    }
}