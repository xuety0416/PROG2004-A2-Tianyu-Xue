public class AssignmentTwo {
    // 主方法：按顺序执行所有Part测试，直接运行即可看到完整效果
    public static void main(String[] args) {
        System.out.println("===================================== PROG2004 A2 作业测试启动 =====================================");
        System.out.println("（所有测试按 Part3→Part4A→Part4B→Part5→Part6→Part7 顺序执行）\n");

        partThree();    // Part3：等待队列管理（15分）
        partFourA();    // Part4A：骑行历史存储（15分）
        partFourB();    // Part4B：骑行记录排序（5分）
        partFive();     // Part5：骑行周期运行（10分）
        partSix();      // Part6：数据导出到CSV（7.5分）
        partSeven();    // Part7：从CSV导入数据（10分）

        System.out.println("\n===================================== PROG2004 A2 作业测试结束 =====================================");
    }

    // ==========================================================================
    // Part3：等待队列管理测试（基于Queue实现FIFO）
    // ==========================================================================
    public static void partThree() {
        System.out.println("===================================== Part3：等待队列管理测试 =====================================");

        // 新增：调用Ride默认构造器及setter方法（消除Ride类未使用提示）
        System.out.println("\n【额外测试1：Ride默认构造器及setter】");
        Ride defaultRide = new Ride(); // 调用Ride默认构造器
        defaultRide.setRideName("默认过山车"); // 调用setRideName()
        defaultRide.setMaxRider(5); // 调用setMaxRider()
        Employee defaultEmp = new Employee("默认运营员", 28, "110101199600000000", "EMP000", "Ride Operator");
        defaultRide.setOperator(defaultEmp); // 调用setOperator()
        System.out.println("默认骑行项目信息：" + defaultRide);

        // 1. 创建运营人员（Employee）- 原有带参构造器调用
        Employee coasterOperator = new Employee(
                "张三", 30, "110101199501011234",  // 父类Person属性
                "EMP001", "Ride Operator"          // 自身专属属性（员工ID、岗位）
        );

        // 新增：调用Employee默认构造器、setEmployeeId()、setPosition()（消除Employee类未使用提示）
        System.out.println("\n【额外测试2：Employee默认构造器及setter方法】");
        Employee tempOperator = new Employee(); // 调用默认构造器
        tempOperator.setName("临时员工");        // 调用父类setName()
        tempOperator.setAge(25);                // 调用父类setAge()
        tempOperator.setIdNumber("110101200001011234"); // 调用父类setIdNumber()
        tempOperator.setEmployeeId("EMP999");   // 调用setEmployeeId()
        tempOperator.setPosition("Assistant");  // 调用setPosition()
        System.out.println("临时员工信息：" + tempOperator); // 调用toString()，间接使用getter

        // 新增：调用Visitor默认构造器及setter方法（消除Visitor类未使用提示）
        System.out.println("\n【额外测试3：Visitor默认构造器及setter】");
        Visitor tempVisitor = new Visitor(); // 调用Visitor默认构造器
        tempVisitor.setName("临时访客");      // 调用父类Person的setName()
        tempVisitor.setAge(30);              // 调用父类Person的setAge()
        tempVisitor.setIdNumber("110101199000000000"); // 调用父类Person的setIdNumber()
        tempVisitor.setVisitorId("VIS999");  // 调用Visitor的setVisitorId()
        tempVisitor.setMembershipLevel("Gold"); // 调用Visitor的setMembershipLevel()
        System.out.println("临时访客信息：" + tempVisitor); // 调用toString()

        // 2. 创建骑行项目（过山车，单次最大载客8人）
        Ride rollerCoaster = new Ride("过山车", 8, coasterOperator);

        // 3. 创建5个访客对象（作业要求：至少5个）
        Visitor v1 = new Visitor("李四", 25, "110101199802021234", "VIS001", "Gold");
        Visitor v2 = new Visitor("王五", 18, "110101200503031234", "VIS002", "Standard");
        Visitor v3 = new Visitor("赵六", 35, "110101198804041234", "VIS003", "Platinum");
        Visitor v4 = new Visitor("孙七", 22, "110101200105051234", "VIS004", "Standard");
        Visitor v5 = new Visitor("周八", 28, "110101199606061234", "VIS005", "Gold");

        // 4. 向队列添加5个访客
        System.out.println("\n【步骤1：添加5个访客到队列】");
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // 5. 打印添加后的队列（验证添加效果）
        System.out.println("\n【步骤2：打印添加后的队列】");
        rollerCoaster.printQueue();

        // 6. 移除1个访客（FIFO原则，移除队首）
        System.out.println("\n【步骤3：移除队首访客】");
        rollerCoaster.removeVisitorFromQueue();

        // 7. 打印移除后的队列（验证移除效果）
        System.out.println("\n【步骤4：打印移除后的队列】");
        rollerCoaster.printQueue();

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part4A：骑行历史存储测试（基于LinkedList，必须用Iterator遍历）
    // ==========================================================================
    public static void partFourA() {
        System.out.println("===================================== Part4A：骑行历史存储测试 =====================================");

        // 1. 创建运营人员和骑行项目（摩天轮，单次最大载客12人）
        Employee ferrisOperator = new Employee("刘九", 28, "110101199707071234", "EMP002", "Ride Operator");
        Ride ferrisWheel = new Ride("摩天轮", 12, ferrisOperator);

        // 2. 创建5个访客对象（作业要求：至少5个）
        Visitor v1 = new Visitor("吴十", 24, "110101199908081234", "VIS006", "Standard");
        Visitor v2 = new Visitor("郑十一", 32, "110101199109091234", "VIS007", "Platinum");
        Visitor v3 = new Visitor("王十二", 20, "110101200310101234", "VIS008", "Gold");
        Visitor v4 = new Visitor("冯十三", 27, "110101199611111234", "VIS009", "Standard");
        Visitor v5 = new Visitor("陈十四", 19, "110101200412121234", "VIS010", "Gold");

        // 3. 向骑行历史添加5个访客
        System.out.println("\n【步骤1：添加5个访客到骑行历史】");
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);

        // 4. 打印骑行历史（必须用Iterator，作业硬性要求）
        System.out.println("\n【步骤2：打印骑行历史（Iterator遍历）】");
        ferrisWheel.printRideHistory();

        // 5. 检查指定访客是否在历史中（验证查询功能）
        Visitor checkVisitor = new Visitor("郑十一", 32, "110101199109091234", "VIS007", "Platinum");
        System.out.println("\n【步骤3：查询访客是否在历史中】");
        boolean isExists = ferrisWheel.checkVisitorFromHistory(checkVisitor);
        System.out.println("访客 " + checkVisitor.getName() + "（ID：" + checkVisitor.getVisitorId() + "）是否存在：" + (isExists ? "✅ 是" : "❌ 否"));

        // 6. 打印骑行历史总人数（验证统计功能）
        System.out.println("\n【步骤4：打印历史记录总数】");
        System.out.println("骑行历史记录总数：" + ferrisWheel.numberOfVisitors() + " 人");

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part4B：骑行记录排序测试（使用自定义Comparator）
    // ==========================================================================
    public static void partFourB() {
        System.out.println("===================================== Part4B：骑行记录排序测试 =====================================");

        // 1. 创建运营人员和骑行项目（激流勇进，单次最大载客6人）
        Employee logFlumeOperator = new Employee("黄十五", 33, "110101199201131234", "EMP003", "Ride Operator");
        Ride logFlume = new Ride("激流勇进", 6, logFlumeOperator);

        // 2. 创建5个不同会员等级、不同年龄的访客（确保排序有差异）
        Visitor v1 = new Visitor("杨十六", 25, "110101199802141234", "VIS011", "Gold");
        Visitor v2 = new Visitor("朱十七", 18, "110101200503151234", "VIS012", "Standard");
        Visitor v3 = new Visitor("胡十八", 35, "110101198804161234", "VIS013", "Platinum");
        Visitor v4 = new Visitor("林十九", 22, "110101200105171234", "VIS014", "Standard");
        Visitor v5 = new Visitor("郭二十", 28, "110101199606181234", "VIS015", "Gold");

        // 3. 向骑行历史添加访客
        System.out.println("\n【步骤1：添加访客到骑行历史】");
        logFlume.addVisitorToHistory(v1);
        logFlume.addVisitorToHistory(v2);
        logFlume.addVisitorToHistory(v3);
        logFlume.addVisitorToHistory(v4);
        logFlume.addVisitorToHistory(v5);

        // 4. 打印排序前的历史记录
        System.out.println("\n【步骤2：排序前的骑行历史】");
        logFlume.printRideHistory();

        // 5. 执行排序（使用自定义VisitorComparator）
        System.out.println("\n【步骤3：执行排序（Platinum > Gold > Standard，同等级按年龄升序）】");
        logFlume.sortRideHistory();

        // 6. 打印排序后的历史记录（验证排序效果）
        System.out.println("\n【步骤4：排序后的骑行历史】");
        logFlume.printRideHistory();

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part5：骑行周期运行测试（关联队列和历史记录）
    // ==========================================================================
    public static void partFive() {
        System.out.println("===================================== Part5：骑行周期运行测试 =====================================");

        Employee bumperCarOperator = new Employee("马二一", 26, "110101199708191234", "EMP004", "Ride Operator");
        // 注意：若Ride类属性名是maxRider，此处参数含义一致（无需修改，Ride构造器会处理）
        Ride bumperCars = new Ride("碰碰车", 3, bumperCarOperator);

        System.out.println("\n【步骤1：添加10个访客到等待队列】");
        for (int i = 1; i <= 10; i++) {
            Visitor visitor = new Visitor(
                    "访客" + i,
                    18 + i,
                    "11010120000" + i + "0" + i + "1234",
                    "VIS" + String.format("%03d", 16 + i),
                    i % 3 == 0 ? "Platinum" : (i % 2 == 0 ? "Gold" : "Standard")
            );
            bumperCars.addVisitorToQueue(visitor);
        }

        System.out.println("\n【步骤2：运行前的等待队列】");
        bumperCars.printQueue();
        System.out.println("运行前等待队列人数：" + bumperCars.getWaitingQueue().size());

        System.out.println("\n【步骤3：运行单次骑行】");
        bumperCars.runOneCycle();

        System.out.println("\n【步骤4：运行后的等待队列】");
        bumperCars.printQueue();
        System.out.println("运行后等待队列人数：" + bumperCars.getWaitingQueue().size());

        System.out.println("\n【步骤5：运行后的骑行历史】");
        bumperCars.printRideHistory();
        System.out.println("运行后骑行历史人数：" + bumperCars.numberOfVisitors());

        System.out.println("\n【步骤6：累计运行次数】");
        System.out.println("【碰碰车】累计运行次数：" + bumperCars.getNumOfCycles() + " 次");

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part6：数据导出到CSV文件测试
    // ==========================================================================
    public static void partSix() {
        System.out.println("===================================== Part6：CSV数据导出测试 =====================================");

        // 1. 创建运营人员和骑行项目（旋转木马，单次最大载客6人）
        Employee carouselOperator = new Employee("徐二二", 24, "110101199909201234", "EMP005", "Ride Operator");
        Ride carousel = new Ride("旋转木马", 6, carouselOperator);

        // 2. 创建5个访客，添加到骑行历史（作业要求：至少5个）
        System.out.println("\n【步骤1：添加5个访客到骑行历史】");
        Visitor v1 = new Visitor("何二三", 5, "110101201810211234", "VIS026", "Standard");
        Visitor v2 = new Visitor("高二四", 7, "110101201611221234", "VIS027", "Gold");
        Visitor v3 = new Visitor("罗二五", 6, "110101201712231234", "VIS028", "Standard");
        Visitor v4 = new Visitor("郑二六", 8, "110101201501241234", "VIS029", "Platinum");
        Visitor v5 = new Visitor("梁二七", 4, "110101201902251234", "VIS030", "Gold");
        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // 3. 打印骑行历史（导出前确认数据）
        System.out.println("\n【步骤2：导出前的骑行历史】");
        carousel.printRideHistory();

        // 4. 导出到CSV文件（文件路径：项目根目录/ride_history.csv）
        String exportFilePath = "ride_history.csv";
        System.out.println("\n【步骤3：导出数据到文件】");
        carousel.exportRideHistory(exportFilePath);

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part7：从CSV文件导入数据测试
    // ==========================================================================
    public static void partSeven() {
        System.out.println("===================================== Part7：CSV数据导入测试 =====================================");

        // 1. 创建运营人员和骑行项目（与导出时一致：旋转木马）
        Employee carouselOperator = new Employee("徐二二", 24, "110101199909201234", "EMP005", "Ride Operator");
        Ride carousel = new Ride("旋转木马", 6, carouselOperator);

        // 2. 从CSV文件导入数据（使用Part6导出的文件路径）
        String importFilePath = "ride_history.csv";
        System.out.println("\n【步骤1：从文件导入数据】");
        carousel.importRideHistory(importFilePath);

        // 3. 打印导入后的骑行历史（验证导入效果）
        System.out.println("\n【步骤2：导入后的骑行历史】");
        carousel.printRideHistory();

        // 4. 打印导入的记录总数
        System.out.println("\n【步骤3：导入记录统计】");
        System.out.println("成功导入记录数：" + carousel.numberOfVisitors() + " 条");

        System.out.println("====================================================================================================\n");
    }
}