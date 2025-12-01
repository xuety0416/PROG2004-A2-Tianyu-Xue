public class Employee extends Person {
    private String employeeId; // Employee ID (exclusive attribute)
    private String position;   // Position type (exclusive attribute: e.g., Ride Operator)

    // Default constructor
    public Employee() {}

    // Parameterized constructor (initialize parent class + own attributes)
    public Employee(String name, int age, String idNumber, String employeeId, String position) {
        super(name, age, idNumber);
        this.employeeId = employeeId;
        this.position = position;
    }

    // getter/setter methods
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

    @Override
    public String toString() {
        // Use getter instead of direct attribute access
        return "Employee{ID=" + getEmployeeId() + ", Name=" + getName() + ", Position=" + getPosition() + "}";
    }
}