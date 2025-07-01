//this serves as a structure for users
public class UserTemplate {
    private String id;
    private String firstName, middleName, lastName;
    private int age,voteStatus;
    /**
        vote status
            -1 = voided
            0 = not voted
            1 = voted
    **/

    //constructor
    public UserTemplate(String id, String fName, String mname, String lName, int age, int voteStatus){
        this.id = id;
        this.firstName = fName;
        this.middleName = mname;
        this.lastName = lName;
        this.age = age;
        this.voteStatus = voteStatus;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName () {
        return middleName;
    }

    public String getLastName () {
        return lastName;
    }

    public int getAge () {
        return age;
    }

    public int getVoteStatus () {
        return voteStatus;
    }

}
