/**
 * Main class for PROG2004 A2 Assignment, demonstrates all parts' functionality.
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("=== PROG2004 A2 - Part6: Export Ride History Demo ===");

        // 创建Ride并添加测试历史
        Ride ride = new Ride("Ferris Wheel", 8, 70, 4);
        Visitor v1 = new Visitor("Alice", 15, "V001", "123456");
        Visitor v2 = new Visitor("Bob", 18, "V002", "789012");
        Visitor v3 = new Visitor("Charlie", 25, "V003", "345678");
        ride.addVisitorToHistory(v1);
        ride.addVisitorToHistory(v2);
        ride.addVisitorToHistory(v3);

        // 导出历史到CSV文件（文件保存在仓库根目录）
        ride.exportRideHistory("ride_history_export.csv");

        // 验证导出结果（打印历史）
        System.out.println("\nExported data preview:");
        ride.printRideHistory();
    }
}