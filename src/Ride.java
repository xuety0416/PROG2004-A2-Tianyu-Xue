import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ride class implements RideInterface, contains waiting queue, ride history, cycle, and export functions.
 * Added Part6: CSV export for ride history
 */
public class Ride implements RideInterface {
    // 原有属性、构造器、Getter/Setter、队列/历史/周期方法（无变动）

    // Part6核心方法：导出骑行历史到CSV文件（含异常处理）
    public void exportRideHistory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入CSV表头
            writer.write("VisitorID,Name,Age,ContactNumber");
            writer.newLine();

            // 写入历史数据
            for (Visitor v : rideHistory) {
                writer.write(String.format("%s,%s,%d,%s",
                        v.getVisitorId(),
                        v.getName(),
                        v.getAge(),
                        v.getContactNumber()));
                writer.newLine();
            }
            System.out.println("Successfully exported ride history to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error exporting ride history: " + e.getMessage());
        }
    }
}