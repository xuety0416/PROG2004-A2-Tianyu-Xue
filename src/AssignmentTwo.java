/**
 * Main class for PROG2004 A2 Assignment, demonstrates all parts' functionality.
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("=== PROG2004 A2 - Part5: Ride Cycles Demo ===");

        // 创建Ride（过山车，12-60岁，每周期最大承载2人）
        Ride ride = new Ride("Roller Coaster", 12, 60, 2);
        // 创建测试游客
        Visitor v1 = new Visitor("Alice", 15, "V001", "123456");
        Visitor v2 = new Visitor("Bob", 18, "V002", "789012");
        Visitor v3 = new Visitor("Charlie", 25, "V003", "345678");
        Visitor v4 = new Visitor("Diana", 30, "V004", "567890");

        // 游客加入队列
        ride.addVisitorToQueue(v1);
        ride.addVisitorToQueue(v2);
        ride.addVisitorToQueue(v3);
        ride.addVisitorToQueue(v4);
        ride.printQueue();

        // 运行2个周期
        ride.runOneCycle();
        ride.printQueue(); // 剩余2人
        ride.runOneCycle();
        ride.printQueue(); // 队列为空

        // 打印骑行历史和周期数
        ride.printRideHistory();
        System.out.println("\nTotal cycles run: " + ride.getNumOfCycles());
    }
}