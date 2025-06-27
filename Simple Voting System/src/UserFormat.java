//this serves as a structure for users
public class UserFormat {
    private String id;
    private String firstName, lastName;
    private int age;

    public UserFormat(String id, String fName, String lName, int age){
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public int getAge () {
        return age;
    }

}
