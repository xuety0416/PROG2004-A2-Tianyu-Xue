/**
 * Main class for PROG2004 A2 Assignment, demonstrates all parts' functionality.
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("=== PROG2004 A2 - Part7: Import Ride History Demo ===");

        // 创建新Ride（初始无历史）
        Ride ride = new Ride("Ferris Wheel", 8, 70, 4);
        System.out.println("Before import - History size: " + ride.countVisitorsInHistory());

        // 从CSV文件导入历史（使用提交6导出的 ride_history_export.csv 文件）
        ride.importRideHistory("ride_history_export.csv");

        // 验证导入结果
        System.out.println("After import - History size: " + ride.countVisitorsInHistory());
        System.out.println("\nImported ride history:");
        ride.printRideHistory();
    }
}