public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("PROG2004 A2 Assignment - Part3: Waiting Queue");

        // 创建Ride和游客
        RideInterface rollerCoaster = new Ride("Roller Coaster", 12, 60);
        Visitor v1 = new Visitor("Alice", 15, "V001", "123456");
        Visitor v2 = new Visitor("Bob", 10, "V002", "789012"); // 年龄不达标
        Visitor v3 = new Visitor("Charlie", 20, "V003", "345678");

        // 演示队列操作
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.printQueue();

        rollerCoaster.removeVisitorFromQueue(v1);
        rollerCoaster.printQueue();
    }
}