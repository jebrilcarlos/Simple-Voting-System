import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//this is where file is handled and manipulated
public class FileHandling {
    private final File uFile = new File("users.txt");

    public int createFile () {
        int file = 0;
        try {
            uFile.createNewFile();
            return file + 1;
        } catch (IOException err) {
            System.out.println(err);
            return 0;
        }
    }
    //method to write in file, returns int to evaluate if saving success
    public int saveToFile (ArrayList<UserTemplate> users) {
        int recorded = 0;
        try { //FileWriter throws IOException hence try catch block use
            FileWriter fw = new FileWriter(uFile, true);
            for (UserTemplate u : users) {
                fw.write(u.getId() + "|" + u.getFirstName() + "|" + u.getMiddleName() + "|" + u.getLastName() + "|" + u.getAge() +  "|" + u.getVoteStatus() + "\n");
                recorded++;
            }
            fw.close(); //never forget to close
        } catch (IOException err) { System.out.println(err); }
        return recorded;
    }

    //method to evaluate duplicate id, returns bool to be evaluated in userHandling
    public boolean reviewDupID (String id) {
        String uID = null;
        try { //Scanner throws FileNotFoundException hence try catch block use
            Scanner sc = new Scanner(uFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) { continue; }
                String person[] = line.split("\\|");
                if (person.length >= 6) { uID = person[0].trim(); }
                if (uID.equals(id)) {
                    return true;
                }
            }
            sc.close(); //never forget to close
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }

        return false;
    }

    //method to load from file (usually for the login), returns the user format
    public UserTemplate loadUser (String id) {
        String uID = null;
        String fname, mname, lname;
        int age, voteStatus;
        try { //Scanner throws FileNotFoundException hence try catch block use
            Scanner sc = new Scanner(uFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) { continue; }
                String person[] = line.split("\\|");
                if (person.length >= 6) { uID = person[0].trim(); }
                if (uID.equals(id)) {
                    fname = person[1].trim();
                    mname = person[2].trim();
                    lname = person[3].trim();
                    age = Integer.parseInt(person[4].trim());
                    voteStatus = Integer.parseInt(person[5].trim());
                    sc.close();
                    return new UserTemplate(id, fname, mname, lname, age, voteStatus);
                }
            }
            sc.close(); //never forget to close
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }

        return null;
    }
}