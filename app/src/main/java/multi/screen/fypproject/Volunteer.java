package multi.screen.fypproject;

public class Volunteer {
    String key;
    int id;
    String fName;
    String sName;
    String email;
    int age;
    int Knumber;

    static int idCounter =1;

    public Volunteer() {

    }

    public Volunteer(String fName, String sName, String email, int age, int knumber) {
        this.id = idCounter++;
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.age = age;
        this.Knumber = knumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getKnumber() {
        return Knumber;
    }

    public void setKnumber(int knumber) {
        Knumber = knumber;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Volunteer.idCounter = idCounter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
