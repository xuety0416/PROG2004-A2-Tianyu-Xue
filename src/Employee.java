public class Employee extends Person {
    private String employeeId; // 员工ID（专属属性）
    private String position;   // 岗位类型（专属属性：如 Ride Operator）

    // 默认构造器
    public Employee() {}

    // 带参构造器（初始化父类+自身属性）
    public Employee(String name, int age, String idNumber, String employeeId, String position) {
        super(name, age, idNumber);
        this.employeeId = employeeId;
        this.position = position;
    }

    // getter/setter 方法
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // 重写 toString()，**主动调用getter方法**（消除“方法未使用”提示）
    @Override
    public String toString() {
        // 用getter代替直接访问属性
        return "Employee{ID=" + getEmployeeId() + ", 姓名=" + getName() + ", 岗位=" + getPosition() + "}";
    }
}