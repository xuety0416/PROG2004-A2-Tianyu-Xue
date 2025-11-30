public abstract class Person {
    private String name;       // Name (parent class attribute)
    private int age;           // Age (parent class attribute)
    private String idNumber;   // ID Number (parent class attribute, e.g., ID Card Number)

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, int age, String idNumber) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    // getter/setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}