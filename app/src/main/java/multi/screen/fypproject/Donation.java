package multi.screen.fypproject;

public class Donation {
    String key;
    int id;
    String fName;
    String sName;
    String email;
    String time;
    String date;



    static int idCounter =1;

    public Donation() {

    }

    public Donation( String fName, String sName, String email, String time, String date) {
        this.id = idCounter++;
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.time = time;
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Donation.idCounter = idCounter;
    }
}
