import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ride class implements RideInterface, contains queue, history, cycle, export and import functions.
 * Added Part7: CSV import for ride history
 */
public class Ride implements RideInterface {
    // 原有属性、构造器、Getter/Setter、所有已实现方法（无变动）

    // Part7核心方法：从CSV文件导入骑行历史（含异常处理）
    public void importRideHistory(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // 跳过CSV表头

            // 读取每行数据并创建Visitor对象
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String visitorId = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String contactNumber = parts[3];

                    Visitor visitor = new Visitor(name, age, visitorId, contactNumber);
                    addVisitorToHistory(visitor); // 添加到骑行历史
                }
            }
            System.out.println("Successfully imported ride history from: " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error importing: File not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing age: " + e.getMessage());
        }
    }
}