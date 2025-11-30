import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; // 补充File类导入（导出CSV时用到）

public class Ride implements RideInterface {
    // 核心属性（Part1要求：至少3个，含1个Employee类型）
    private String rideName;       // 骑行项目名称（属性1）
    private int maxRider;          // 单次最大载客量（对应作业maxRider，属性2）
    private Employee operator;     // 运营人员（Employee类型，属性3）
    private final Queue<Visitor> waitingQueue = new LinkedList<>(); // 提示“可能为final”→添加final
    private final LinkedList<Visitor> rideHistory = new LinkedList<>(); // 提示“可能为final”→添加final
    private int numOfCycles = 0;   // Part5：累计运行次数

    // 构造器：修改maxCapacity为maxRider
    public Ride() {
        // 提示“构造函数从未使用”→在AssignmentTwo中调用（后续补充）
        this.rideName = "默认项目";
        this.maxRider = 1;
        this.operator = new Employee("默认员工", 20, "110101200000000000", "EMP000", "Default");
    }

    public Ride(String rideName, int maxRider, Employee operator) {
        this.rideName = rideName;
        // 提示“可被替换为Math.max”→优化为Math.max
        this.maxRider = Math.max(maxRider, 1);
        this.operator = operator;
    }

    // Getter/Setter：修改maxCapacity相关方法为maxRider
    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        // 提示“可被替换为Math.max”→优化为Math.max
        this.maxRider = Math.max(maxRider, 1);
        if (maxRider < 1) {
            System.out.println("⚠️  单次载客量不能小于1，已默认设为1");
        }
    }

    // 补充：rideName的getter/setter（避免属性访问缺失）
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    // 补充：operator的getter/setter
    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    // 错误2修复：添加getWaitingQueue()方法（AssignmentTwo中调用）
    public Queue<Visitor> getWaitingQueue() {
        return waitingQueue;
    }

    // 错误3修复：添加getNumOfCycles()方法（AssignmentTwo中调用）
    public int getNumOfCycles() {
        return numOfCycles;
    }

    // Part3：队列管理方法 - 实现RideInterface接口（之前省略导致方法缺失）
    @Override
    public void removeVisitorFromQueue() {
        if (!waitingQueue.isEmpty()) {
            Visitor removedVisitor = waitingQueue.poll(); // 移除队首元素（FIFO）
            System.out.println("✅ 访客 " + removedVisitor.getName() + "（ID：" + removedVisitor.getVisitorId() + "）已离开【" + getRideName() + "】队列"); // 调用getRideName()
        } else {
            System.out.println("❌ 错误：【" + getRideName() + "】队列为空，无法移除访客"); // 调用getRideName()
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n📋 【" + getRideName() + "】当前等待队列（共 " + waitingQueue.size() + " 人）："); // 调用getRideName()
        if (waitingQueue.isEmpty()) {
            System.out.println("   队列无访客");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingQueue) { // 增强for循环遍历队列
            System.out.println("   " + index + ". " + visitor);
            index++;
        }
    }

    // Part4A：骑行历史方法 - 实现RideInterface接口（之前省略导致方法缺失）
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 错误：访客对象为空，无法记录历史");
            return;
        }
        rideHistory.add(visitor); // 历史记录添加元素
        System.out.println("✅ 访客 " + visitor.getName() + "（ID：" + visitor.getVisitorId() + "）已记录到【" + getRideName() + "】骑行历史"); // 调用getRideName()
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size(); // 返回历史记录总数
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n📜 【" + getRideName() + "】骑行历史记录（共 " + rideHistory.size() + " 人）："); // 调用getRideName()
        if (rideHistory.isEmpty()) {
            System.out.println("   暂无骑行记录");
            return;
        }
        // 必须使用Iterator遍历（作业硬性要求）
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println("   " + index + ". " + visitor);
            index++;
        }
    }

    // 错误1修复：添加sortRideHistory()方法（Part4B排序用）
    public void sortRideHistory() {
        // 提示“Collections.sort可替换为List.sort”→优化为List.sort
        rideHistory.sort(new VisitorComparator());
        System.out.println("✅ 【" + getRideName() + "】骑行历史已按规则排序（Platinum > Gold > Standard，同等级按年龄升序）"); // 调用getRideName()
    }

    // 配套：Part4B所需的自定义Comparator（提示“可能为static”→添加static）
    static class VisitorComparator implements java.util.Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            // 第一步：按会员等级降序（Platinum > Gold > Standard）
            int levelPriority1 = getMembershipPriority(v1.getMembershipLevel());
            int levelPriority2 = getMembershipPriority(v2.getMembershipLevel());
            if (levelPriority2 != levelPriority1) {
                return levelPriority2 - levelPriority1; // 降序：优先级高的在前
            }
            // 第二步：会员等级相同时，按年龄升序
            return v1.getAge() - v2.getAge();
        }

        // 辅助方法：给会员等级分配优先级（数值越大优先级越高）
        private int getMembershipPriority(String membershipLevel) {
            // 提示“Switch可替换为增强switch”→优化为增强switch
            return switch (membershipLevel.trim()) {
                case "Platinum" -> 3;
                case "Gold" -> 2;
                case "Standard" -> 1;
                default -> 0; // 未知等级优先级最低
            };
        }
    }

    // Part4A：checkVisitorFromHistory（修改为基于身份证号判断，匹配Person类唯一标识）
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 错误：查询失败 - 访客对象为空");
            return false;
        }
        if (visitor.getIdNumber() == null || visitor.getIdNumber().trim().isEmpty()) {
            System.out.println("❌ 错误：查询失败 - 访客身份证号为空");
            return false;
        }
        // 提示“while循环可替换为增强for”→优化为增强for
        for (Visitor historyVisitor : rideHistory) {
            if (historyVisitor.getIdNumber() != null && historyVisitor.getIdNumber().trim().equals(visitor.getIdNumber().trim())) {
                System.out.println("✅ 查询成功 - 访客【" + visitor.getName() + "（身份证号：" + visitor.getIdNumber() + "）】存在于【" + getRideName() + "】骑行历史"); // 调用getRideName()
                return true;
            }
        }
        System.out.println("❌ 查询失败 - 访客【" + visitor.getName() + "（身份证号：" + visitor.getIdNumber() + "）】未找到【" + getRideName() + "】骑行记录"); // 调用getRideName()
        return false;
    }

    // Part5：runOneCycle（增强提示，确保operator为null时正确提示）
    @Override
    public void runOneCycle() {
        System.out.println("\n=== 🎢 【" + getRideName() + "】开始单次骑行周期 ==="); // 调用getRideName()

        // 校验1：无运营人员（作业Part5要求）
        if (operator == null) {
            System.out.println("❌ 骑行失败 - 未分配运营人员，无法启动");
            System.out.println("=== 🎢 【" + getRideName() + "】骑行周期终止 ==="); // 调用getRideName()
            return;
        }

        // 校验2：队列为空（作业Part5要求）
        if (waitingQueue.isEmpty()) {
            System.out.println("❌ 骑行失败 - 等待队列为空，无访客可乘坐");
            System.out.println("=== 🎢 【" + getRideName() + "】骑行周期终止 ==="); // 调用getRideName()
            return;
        }

        // 校验3：等待人数不足maxRider（作业隐含要求：按maxRider载客，不足时载客所有等待者）
        int availableVisitors = waitingQueue.size();
        int ridersThisCycle = Math.min(availableVisitors, getMaxRider()); // 调用getMaxRider()
        if (availableVisitors < getMaxRider()) { // 调用getMaxRider()
            System.out.println("⚠️  等待人数不足（当前" + availableVisitors + "人，单次最大载客" + getMaxRider() + "人），将载客所有等待者"); // 调用getMaxRider()
        }

        // 核心逻辑：队列→历史
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = waitingQueue.poll();
            rideHistory.add(rider);
            System.out.println("✅ 载客成功：" + rider);
        }

        numOfCycles++;
        System.out.println("=== 🎢 【" + getRideName() + "】骑行周期结束 - 本次载客" + ridersThisCycle + "人，累计运行" + getNumOfCycles() + "次 ==="); // 调用getRideName()、getNumOfCycles()
    }

    // Part6：导出CSV（增强错误提示，补充文件路径校验）
    public void exportRideHistory(String filePath) {
        System.out.println("\n=== 📤 导出【" + getRideName() + "】骑行历史到CSV：" + filePath + " ==="); // 调用getRideName()

        // 校验文件路径
        File file = new File(filePath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            System.out.println("❌ 导出失败 - 父目录不存在：" + file.getParentFile().getAbsolutePath());
            return;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            // 表头：包含Visitor所有属性（作业要求“所有访客详情”）
            bufferedWriter.write("访客ID,姓名,年龄,身份证号,会员等级");
            bufferedWriter.newLine();

            for (Visitor visitor : rideHistory) {
                // 处理空值（避免CSV字段缺失）
                String visitorId = visitor.getVisitorId() != null ? visitor.getVisitorId() : "";
                String name = visitor.getName() != null ? visitor.getName() : "";
                String age = visitor.getAge() >= 0 ? String.valueOf(visitor.getAge()) : "";
                String idNumber = visitor.getIdNumber() != null ? visitor.getIdNumber() : "";
                String membershipLevel = visitor.getMembershipLevel() != null ? visitor.getMembershipLevel() : "";

                String line = String.join(",", visitorId, name, age, idNumber, membershipLevel);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            System.out.println("✅ 导出成功 - 共导出" + rideHistory.size() + "条记录，文件路径：" + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ 导出失败 - " + e.getMessage() + "（请检查文件是否被占用或权限不足）");
        }
    }

    // Part7：导入CSV（增强错误提示，补充字段校验）
    public void importRideHistory(String filePath) {
        System.out.println("\n=== 📥 从CSV导入【" + getRideName() + "】骑行历史：" + filePath + " ==="); // 调用getRideName()

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ 导入失败 - 文件不存在：" + filePath);
            return;
        }
        if (!file.isFile()) {
            System.out.println("❌ 导入失败 - 不是有效文件：" + filePath);
            return;
        }

        rideHistory.clear(); // 清空现有历史（避免重复导入）
        int successCount = 0;
        int failCount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // 跳过表头
                }

                line = line.trim();
                if (line.isEmpty()) {
                    System.out.println("⚠️  第" + lineNumber + "行：空行，跳过");
                    failCount++;
                    continue;
                }

                String[] fields = line.split(",", -1); // -1：保留空字段
                if (fields.length != 5) {
                    System.out.println("⚠️  第" + lineNumber + "行：字段数量错误（需5个，实际" + fields.length + "个），内容：" + line);
                    failCount++;
                    continue;
                }

                // 解析字段（trim空值，处理格式错误）
                String visitorId = fields[0].trim();
                String name = fields[1].trim();

                int age; // 仅声明，不提前赋值
                try {
                    age = fields[2].trim().isEmpty() ? -1 : Integer.parseInt(fields[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️  第" + lineNumber + "行：年龄格式错误（需为数字），内容：" + fields[2]);
                    failCount++;
                    // 直接continue，无需给age赋值（因为后续代码不会执行）
                    continue;
                }
                String idNumber = fields[3].trim();
                String membershipLevel = fields[4].trim();

                // 校验必填字段（身份证号、访客ID不能为空）
                if (idNumber.isEmpty() || visitorId.isEmpty()) {
                    System.out.println("⚠️  第" + lineNumber + "行：访客ID或身份证号为空，跳过");
                    failCount++;
                    continue;
                }

                // 创建Visitor对象
                Visitor visitor = new Visitor(name, age, idNumber, visitorId, membershipLevel);
                rideHistory.add(visitor);
                successCount++;
                System.out.println("✅ 第" + lineNumber + "行：导入成功 - " + visitor);
            }

            System.out.println("=== 📥 导入完成 - 成功：" + successCount + "条，失败：" + failCount + "条，当前历史总记录：" + rideHistory.size() + " ===");
        } catch (IOException e) {
            System.out.println("❌ 导入失败 - " + e.getMessage() + "（请检查文件权限或格式）");
        }
    }

    // 其他方法（addVisitorToQueue）保持不变，仅补充提示优化
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ 错误：添加队列失败 - 访客对象为空");
            return;
        }
        if (visitor.getIdNumber() == null || visitor.getIdNumber().trim().isEmpty()) {
            System.out.println("❌ 错误：添加队列失败 - 访客身份证号为空（" + visitor.getVisitorId() + "-" + visitor.getName() + "）");
            return;
        }
        waitingQueue.offer(visitor);
        System.out.println("✅ 添加队列成功 - " + visitor + "（【" + getRideName() + "】队列）"); // 调用getRideName()
    }

    // 可选：重写toString()方法，方便打印Ride对象信息
    @Override
    public String toString() {
        return "Ride{" +
                "名称='" + getRideName() + '\'' + // 调用getRideName()
                ", 单次最大载客量=" + getMaxRider() + // 调用getMaxRider()
                ", 运营人员=" + (getOperator() != null ? getOperator().getName() : "无") + // 调用getOperator()
                ", 等待队列人数=" + getWaitingQueue().size() + // 调用getWaitingQueue()
                ", 累计骑行人数=" + numberOfVisitors() + // 调用numberOfVisitors()
                ", 累计运行次数=" + getNumOfCycles() + // 调用getNumOfCycles()
                '}';
    }
}