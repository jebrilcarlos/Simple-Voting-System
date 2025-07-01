
//template for the candidates (presidents, vice presidents, senators, etc.)
public class CandidateTemplate {
    private int ballot, votes;
    private String firstName, middleName, lastName;

    public CandidateTemplate(int ballot, String fname, String mname, String lname, int votes) {
        this.ballot = ballot;
        this.firstName = fname;
        this.middleName = mname;
        this.lastName = lname;
        this.votes = votes;
    }

    public int getBallot () {
        return ballot;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getMiddleName () {
        return middleName;
    }

    public String getLastName () {
        return lastName;
    }

    public int getVotes () {
        return votes;
    }

    public void setVotes (int votes) {
        this.votes = votes;
    }
}
