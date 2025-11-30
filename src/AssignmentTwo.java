public class AssignmentTwo {
    // Main method: Execute all Part tests in order, run directly to see complete results
    public static void main(String[] args) {
        System.out.println("===================================== PROG2004 A2 Assignment Test Started =====================================");
        System.out.println("(All tests execute in order: Part3→Part4A→Part4B→Part5→Part6→Part7)\n");

        partThree();    // Part3: Waiting Queue Management (15 marks)
        partFourA();    // Part4A: Ride History Storage (15 marks)
        partFourB();    // Part4B: Ride Record Sorting (5 marks)
        partFive();     // Part5: Ride Cycle Operation (10 marks)
        partSix();      // Part6: Export Data to CSV (7.5 marks)
        partSeven();    // Part7: Import Data from CSV (10 marks)

        System.out.println("\n===================================== PROG2004 A2 Assignment Test Completed =====================================");
    }

    // ==========================================================================
    // Part3: Waiting Queue Management Test (FIFO based on Queue)
    // ==========================================================================
    public static void partThree() {
        System.out.println("===================================== Part3: Waiting Queue Management Test =====================================");

        // Additional: Call Ride default constructor and setter methods (eliminate unused class warning)
        System.out.println("\n[Extra Test 1: Ride Default Constructor & Setters]");
        Ride defaultRide = new Ride(); // Call Ride default constructor
        defaultRide.setRideName("Default Roller Coaster"); // Call setRideName()
        defaultRide.setMaxRider(5); // Call setMaxRider()
        Employee defaultEmp = new Employee("Default Operator", 28, "110101199600000000", "EMP000", "Ride Operator");
        defaultRide.setOperator(defaultEmp); // Call setOperator()
        System.out.println("Default Ride Info: " + defaultRide);

        // 1. Create Employee (using existing parameterized constructor)
        Employee coasterOperator = new Employee(
                "Zhang San", 30, "110101199501011234",  // Parent class Person attributes
                "EMP001", "Ride Operator"                  // Own attributes (employee ID, position)
        );

        System.out.println("\n[Extra Test 2: Employee Default Constructor & Setters]");
        Employee tempOperator = new Employee(); // Call default constructor
        tempOperator.setName("Temporary Employee"); // Call parent class setName()
        tempOperator.setAge(25); // Call parent class setAge()
        tempOperator.setIdNumber("110101200001011234"); // Call parent class setIdNumber()
        tempOperator.setEmployeeId("EMP999"); // Call setEmployeeId()
        tempOperator.setPosition("Assistant"); // Call setPosition()
        System.out.println("Temporary Employee Info: " + tempOperator); // Call toString(), indirectly use getters

        System.out.println("\n[Extra Test 3: Visitor Default Constructor & Setters]");
        Visitor tempVisitor = new Visitor(); // Call Visitor default constructor
        tempVisitor.setName("Temporary Visitor"); // Call parent class Person's setName()
        tempVisitor.setAge(30); // Call parent class Person's setAge()
        tempVisitor.setIdNumber("110101199000000000"); // Call parent class Person's setIdNumber()
        tempVisitor.setVisitorId("VIS999"); // Call Visitor's setVisitorId()
        tempVisitor.setMembershipLevel("Gold"); // Call Visitor's setMembershipLevel()
        System.out.println("Temporary Visitor Info: " + tempVisitor); // Call toString()

        // 2. Create Ride (Roller Coaster, max 8 riders per cycle)
        Ride rollerCoaster = new Ride("Roller Coaster", 8, coasterOperator);

        // 3. Create 5 Visitor objects (assignment requirement: at least 5)
        Visitor v1 = new Visitor("Li Si", 25, "110101199802021234", "VIS001", "Gold");
        Visitor v2 = new Visitor("Wang Wu", 18, "110101200503031234", "VIS002", "Standard");
        Visitor v3 = new Visitor("Zhao Liu", 35, "110101198804041234", "VIS003", "Platinum");
        Visitor v4 = new Visitor("Sun Qi", 22, "110101200105051234", "VIS004", "Standard");
        Visitor v5 = new Visitor("Zhou Ba", 28, "110101199606061234", "VIS005", "Gold");

        // 4. Add 5 visitors to the queue
        System.out.println("\n[Step 1: Add 5 visitors to the queue]");
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // 5. Print the queue after addition (verify add functionality)
        System.out.println("\n[Step 2: Print queue after addition]");
        rollerCoaster.printQueue();

        // 6. Remove 1 visitor (FIFO principle, remove head of queue)
        System.out.println("\n[Step 3: Remove head visitor from queue]");
        rollerCoaster.removeVisitorFromQueue();

        // 7. Print the queue after removal (verify remove functionality)
        System.out.println("\n[Step 4: Print queue after removal]");
        rollerCoaster.printQueue();

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part4A: Ride History Storage Test (based on LinkedList, must use Iterator for traversal)
    // ==========================================================================
    public static void partFourA() {
        System.out.println("===================================== Part4A: Ride History Storage Test =====================================");

        // 1. Create Employee and Ride (Ferris Wheel, max 12 riders per cycle)
        Employee ferrisOperator = new Employee("Liu Jiu", 28, "110101199707071234", "EMP002", "Ride Operator");
        Ride ferrisWheel = new Ride("Ferris Wheel", 12, ferrisOperator);

        // 2. Create 5 Visitor objects (assignment requirement: at least 5)
        Visitor v1 = new Visitor("Wu Shi", 24, "110101199908081234", "VIS006", "Standard");
        Visitor v2 = new Visitor("Zheng Shi Yi", 32, "110101199109091234", "VIS007", "Platinum");
        Visitor v3 = new Visitor("Wang Shi Er", 20, "110101200310101234", "VIS008", "Gold");
        Visitor v4 = new Visitor("Feng Shi San", 27, "110101199611111234", "VIS009", "Standard");
        Visitor v5 = new Visitor("Chen Shi Si", 19, "110101200412121234", "VIS010", "Gold");

        // 3. Add 5 visitors to ride history
        System.out.println("\n[Step 1: Add 5 visitors to ride history]");
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);

        // 4. Print ride history (must use Iterator, assignment mandatory requirement)
        System.out.println("\n[Step 2: Print ride history (Iterator traversal)]");
        ferrisWheel.printRideHistory();

        // 5. Check if specified visitor exists in history (verify query functionality)
        Visitor checkVisitor = new Visitor("Zheng Shi Yi", 32, "110101199109091234", "VIS007", "Platinum");
        System.out.println("\n[Step 3: Check if visitor exists in history]");
        boolean isExists = ferrisWheel.checkVisitorFromHistory(checkVisitor);
        System.out.println("Does visitor " + checkVisitor.getName() + " (ID: " + checkVisitor.getVisitorId() + ") exist: " + (isExists ? "✅ Yes" : "❌ No"));

        // 6. Print total number of visitors in history (verify statistics functionality)
        System.out.println("\n[Step 4: Print total number of history records]");
        System.out.println("Total number of ride history records: " + ferrisWheel.numberOfVisitors() + " visitors");

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part4B: Ride Record Sorting Test (using custom Comparator)
    // ==========================================================================
    public static void partFourB() {
        System.out.println("===================================== Part4B: Ride Record Sorting Test =====================================");

        // 1. Create Employee and Ride (Log Flume, max 6 riders per cycle)
        Employee logFlumeOperator = new Employee("Huang Shi Wu", 33, "110101199201131234", "EMP003", "Ride Operator");
        Ride logFlume = new Ride("Log Flume", 6, logFlumeOperator);

        // 2. Create 5 visitors with different membership levels and ages (ensure sorting difference)
        Visitor v1 = new Visitor("Yang Shi Liu", 25, "110101199802141234", "VIS011", "Gold");
        Visitor v2 = new Visitor("Zhu Shi Qi", 18, "110101200503151234", "VIS012", "Standard");
        Visitor v3 = new Visitor("Hu Shi Ba", 35, "110101198804161234", "VIS013", "Platinum");
        Visitor v4 = new Visitor("Lin Shi Jiu", 22, "110101200105171234", "VIS014", "Standard");
        Visitor v5 = new Visitor("Guo Er Shi", 28, "110101199606181234", "VIS015", "Gold");

        // 3. Add visitors to ride history
        System.out.println("\n[Step 1: Add visitors to ride history]");
        logFlume.addVisitorToHistory(v1);
        logFlume.addVisitorToHistory(v2);
        logFlume.addVisitorToHistory(v3);
        logFlume.addVisitorToHistory(v4);
        logFlume.addVisitorToHistory(v5);

        // 4. Print ride history before sorting
        System.out.println("\n[Step 2: Ride history before sorting]");
        logFlume.printRideHistory();

        // 5. Execute sorting (using custom VisitorComparator)
        System.out.println("\n[Step 3: Execute sorting (Platinum > Gold > Standard, ascending age for same level)]");
        logFlume.sortRideHistory();

        // 6. Print ride history after sorting (verify sorting effect)
        System.out.println("\n[Step 4: Ride history after sorting]");
        logFlume.printRideHistory();

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part5: Ride Cycle Operation Test (associate queue and history records)
    // ==========================================================================
    public static void partFive() {
        System.out.println("===================================== Part5: Ride Cycle Operation Test =====================================");

        Employee bumperCarOperator = new Employee("Ma Er Yi", 26, "110101199708191234", "EMP004", "Ride Operator");
        // Note: If Ride class attribute name is maxRider, parameter meaning is consistent (no modification needed, Ride constructor handles it)
        Ride bumperCars = new Ride("Bumper Cars", 3, bumperCarOperator);

        System.out.println("\n[Step 1: Add 10 visitors to waiting queue]");
        for (int i = 1; i <= 10; i++) {
            Visitor visitor = new Visitor(
                    "Visitor" + i,
                    18 + i,
                    "11010120000" + i + "0" + i + "1234",
                    "VIS" + String.format("%03d", 16 + i),
                    i % 3 == 0 ? "Platinum" : (i % 2 == 0 ? "Gold" : "Standard")
            );
            bumperCars.addVisitorToQueue(visitor);
        }

        System.out.println("\n[Step 2: Waiting queue before operation]");
        bumperCars.printQueue();
        System.out.println("Number of visitors in queue before operation: " + bumperCars.getWaitingQueue().size());

        System.out.println("\n[Step 3: Run one ride cycle]");
        bumperCars.runOneCycle();

        System.out.println("\n[Step 4: Waiting queue after operation]");
        bumperCars.printQueue();
        System.out.println("Number of visitors in queue after operation: " + bumperCars.getWaitingQueue().size());

        System.out.println("\n[Step 5: Ride history after operation]");
        bumperCars.printRideHistory();
        System.out.println("Number of visitors in ride history after operation: " + bumperCars.numberOfVisitors());

        System.out.println("\n[Step 6: Total number of cycles operated]");
        System.out.println("[Bumper Cars] Total operating cycles: " + bumperCars.getNumOfCycles() + " times");

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part6: Export Data to CSV File Test
    // ==========================================================================
    public static void partSix() {
        System.out.println("===================================== Part6: CSV Data Export Test =====================================");

        // 1. Create Employee and Ride (Carousel, max 6 riders per cycle)
        Employee carouselOperator = new Employee("Xu Er Er", 24, "110101199909201234", "EMP005", "Ride Operator");
        Ride carousel = new Ride("Carousel", 6, carouselOperator);

        // 2. Create 5 visitors and add to ride history (assignment requirement: at least 5)
        System.out.println("\n[Step 1: Add 5 visitors to ride history]");
        Visitor v1 = new Visitor("He Er San", 5, "110101201810211234", "VIS026", "Standard");
        Visitor v2 = new Visitor("Gao Er Si", 7, "110101201611221234", "VIS027", "Gold");
        Visitor v3 = new Visitor("Luo Er Wu", 6, "110101201712231234", "VIS028", "Standard");
        Visitor v4 = new Visitor("Zheng Er Liu", 8, "110101201501241234", "VIS029", "Platinum");
        Visitor v5 = new Visitor("Liang Er Qi", 4, "110101201902251234", "VIS030", "Gold");
        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // 3. Print ride history (confirm data before export)
        System.out.println("\n[Step 2: Ride history before export]");
        carousel.printRideHistory();

        // 4. Export to CSV file (file path: project root directory/ride_history.csv)
        String exportFilePath = "ride_history.csv";
        System.out.println("\n[Step 3: Export data to file]");
        carousel.exportRideHistory(exportFilePath);

        System.out.println("====================================================================================================\n");
    }

    // ==========================================================================
    // Part7: Import Data from CSV File Test
    // ==========================================================================
    public static void partSeven() {
        System.out.println("===================================== Part7: CSV Data Import Test =====================================");

        // 1. Create Employee and Ride (same as export: Carousel)
        Employee carouselOperator = new Employee("Xu Er Er", 24, "110101199909201234", "EMP005", "Ride Operator");
        Ride carousel = new Ride("Carousel", 6, carouselOperator);

        // 2. Import data from CSV file (use the same file path as Part6 export)
        String importFilePath = "ride_history.csv";
        System.out.println("\n[Step 1: Import data from file]");
        carousel.importRideHistory(importFilePath);

        // 3. Print ride history after import (verify import effect)
        System.out.println("\n[Step 2: Ride history after import]");
        carousel.printRideHistory();

        // 4. Print total number of imported records
        System.out.println("\n[Step 3: Imported records statistics]");
        System.out.println("Number of successfully imported records: " + carousel.numberOfVisitors() + " entries");

        System.out.println("====================================================================================================\n");
    }
}