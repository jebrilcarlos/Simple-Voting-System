import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//this is where file is handled
public class FileHandling {
    private final File uFile = new File("users.txt");

    public int saveToFile (ArrayList<UserFormat> users) {
        int recorded = 0;
        try {
            FileWriter fw = new FileWriter(uFile, true);
            for (UserFormat u : users) {
                fw.write(u.getId() + "|" + u.getFirstName() + "|" + u.getLastName() + "|" + u.getAge() + "\n");
                recorded++;
            }
            fw.close();
        } catch (IOException err) { System.out.println(err); }
        return recorded;
    }

    public void readFile () {
        try {
            Scanner sc = new Scanner(uFile);
            sc.useDelimiter("\\|"); // Set '|' as the delimiter
            while (sc.hasNext()) {
                String segment = sc.next();
                System.out.println("Read segment: " + segment.trim()); // .trim() removes leading/trailing whitespace
            }
            sc.close();
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }
    }

    public UserFormat loadUser (String id) {
        String uID = null;
        String fname, lname;
        int age;
        try {
            Scanner sc = new Scanner(uFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) { continue; }
                String person[] = line.split("\\|");
                if (person.length >= 4) { uID = person[0].trim(); }
                if (uID.equals(id)) {
                    fname = person[1].trim();
                    lname = person[2].trim();
                    age = Integer.parseInt(person[3].trim());
                    sc.close();
                    return new UserFormat(id, fname, lname, age);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) { System.err.println("File not found: " + e.getMessage()); }

        return null;
    }

}
