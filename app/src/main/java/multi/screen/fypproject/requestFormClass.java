package multi.screen.fypproject;

public class requestFormClass {
    String key;
    int id;
    String email;
    String firstName;
    String lastName;
    int kNumber;
    String item1;
    String item2;
    String item3;
    String time;
    String date;


    static int idCounter =1;

    public requestFormClass() {

    }

    public requestFormClass( String email, String firstName, String lastName,
                             int kNumber, String item1, String item2, String item3,String date,String time) {
        this.id = idCounter++;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kNumber = kNumber;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.time = time;
        this.date = date;
    }


    public requestFormClass( String email, String firstName, String lastName,
                            int kNumber, String item1, String item2, String item3) {
        this.id = idCounter++;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kNumber = kNumber;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public requestFormClass(String email, String firstName, String lastName, int kNumber, String item1) {
        this.id = idCounter++;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kNumber = kNumber;
        this.item1 = item1;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getkNumber() {
        return kNumber;
    }

    public void setkNumber(int kNumber) {
        this.kNumber = kNumber;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        requestFormClass.idCounter = idCounter;
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
}

