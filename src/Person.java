public abstract class Person {
    private String name;       // 姓名（父类属性）
    private int age;           // 年龄（父类属性）
    private String idNumber;   // 身份证号（父类属性）

    // 默认构造器
    public Person() {}

    // 带参构造器
    public Person(String name, int age, String idNumber) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    // getter/setter 方法
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