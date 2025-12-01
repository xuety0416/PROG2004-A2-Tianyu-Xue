import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; // Add File class import (used for CSV export)

public class Ride implements RideInterface {
    // Core attributes (Part1 requirement: at least 3, including 1 Employee type)
    private String rideName;       // Ride name (attribute 1)
    private int maxRider;          // Maximum riders per cycle (matches assignment's maxRider, attribute 2)
    private Employee operator;     // Ride operator (Employee type, attribute 3)
    private final Queue<Visitor> waitingQueue = new LinkedList<>(); // Hint "may be final" → add final
    private final LinkedList<Visitor> rideHistory = new LinkedList<>(); // Hint "may be final" → add final
    private int numOfCycles = 0;   // Part5: Total number of operating cycles

    public Ride() {
        // Hint "constructor is never used" → called in AssignmentTwo (supplemented later)
        this.rideName = "Default Ride";
        this.maxRider = 1;
        this.operator = new Employee("Default Employee", 20, "110101200000000000", "EMP000", "Default");
    }

    public Ride(String rideName, int maxRider, Employee operator) {
        this.rideName = rideName;
        this.maxRider = Math.max(maxRider, 1);
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = Math.max(maxRider, 1);
        if (maxRider < 1) {
            System.out.println("⚠️  Maximum riders cannot be less than 1, defaulted to 1");
        }
    }

    // Supplement: getter/setter for rideName (avoid missing attribute access)
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    // Supplement: getter/setter for operator
    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public Queue<Visitor> getWaitingQueue() {
        return waitingQueue;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // Part3: Queue management methods - implement RideInterface
    @Override
    public void removeVisitorFromQueue() {
        if (!waitingQueue.isEmpty()) {
            Visitor removedVisitor = waitingQueue.poll(); // Remove head element (FIFO)
            System.out.println("✅ Visitor " + removedVisitor.getName() + " (ID: " + removedVisitor.getVisitorId() + ") has left the [" + getRideName() + "] queue"); // Call getRideName()
        } else {
            System.out.println("❌ Error: [" + getRideName() + "] queue is empty, cannot remove visitor"); // Call getRideName()
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n📋 Current Waiting Queue for [" + getRideName() + "] (Total " + waitingQueue.size() + " visitors):"); // Call getRideName()
        if (waitingQueue.isEmpty()) {
            System.out.println("   No visitors in queue");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingQueue) { // Enhanced for loop to traverse queue
            System.out.println("   " + index + ". " + visitor);
            index++;
        }
    }

    // Part4A: Ride history methods - implement RideInterface
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Visitor object is null, cannot record to history");
            return;
        }
        rideHistory.add(visitor); // Add element to history
        System.out.println("✅ Visitor " + visitor.getName() + " (ID: " + visitor.getVisitorId() + ") has been recorded to [" + getRideName() + "] ride history"); // Call getRideName()
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size(); // Return total number of history records
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n📜 Ride History for [" + getRideName() + "] (Total " + rideHistory.size() + " visitors):"); // Call getRideName()
        if (rideHistory.isEmpty()) {
            System.out.println("   No ride records available");
            return;
        }
        // Traverse using Iterator (assignment mandatory requirement)
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println("   " + index + ". " + visitor);
            index++;
        }
    }

    public void sortRideHistory() {
        rideHistory.sort(new VisitorComparator());
        System.out.println("✅ [" + getRideName() + "] ride history has been sorted by rules (Platinum > Gold > Standard, ascending age for same level)"); // Call getRideName()
    }

    static class VisitorComparator implements java.util.Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            // Step 1: Sort by membership level descending (Platinum > Gold > Standard)
            int levelPriority1 = getMembershipPriority(v1.getMembershipLevel());
            int levelPriority2 = getMembershipPriority(v2.getMembershipLevel());
            if (levelPriority2 != levelPriority1) {
                return levelPriority2 - levelPriority1; // Descending: higher priority first
            }
            // Step 2: If membership levels are the same, sort by age ascending
            return v1.getAge() - v2.getAge();
        }

        // Helper method: Assign priority to membership level (higher value = higher priority)
        private int getMembershipPriority(String membershipLevel) {
            return switch (membershipLevel.trim()) {
                case "Platinum" -> 3;
                case "Gold" -> 2;
                case "Standard" -> 1;
                default -> 0; // Unknown level has lowest priority
            };
        }
    }

    // Part4A: checkVisitorFromHistory (modified to judge by ID number, matching Person class unique identifier)
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Query failed - Visitor object is null");
            return false;
        }
        if (visitor.getIdNumber() == null || visitor.getIdNumber().trim().isEmpty()) {
            System.out.println("❌ Error: Query failed - Visitor ID number is empty");
            return false;
        }
        // Hint "while loop can be replaced with enhanced for" → optimized to enhanced for
        for (Visitor historyVisitor : rideHistory) {
            if (historyVisitor.getIdNumber() != null && historyVisitor.getIdNumber().trim().equals(visitor.getIdNumber().trim())) {
                System.out.println("✅ Query successful - Visitor [" + visitor.getName() + " (ID Number: " + visitor.getIdNumber() + ")] exists in [" + getRideName() + "] ride history"); // Call getRideName()
                return true;
            }
        }
        System.out.println("❌ Query failed - Visitor [" + visitor.getName() + " (ID Number: " + visitor.getIdNumber() + ")] has no ride records in [" + getRideName() + "]"); // Call getRideName()
        return false;
    }

    // Part5: runOneCycle (enhanced prompts, ensure correct prompt when operator is null)
    @Override
    public void runOneCycle() {
        System.out.println("\n=== 🎢 [" + getRideName() + "] Starting Single Ride Cycle ==="); // Call getRideName()

        // Validation 1: No operator assigned (Part5 requirement)
        if (operator == null) {
            System.out.println("❌ Ride failed - No operator assigned, cannot start");
            System.out.println("=== 🎢 [" + getRideName() + "] Ride Cycle Terminated ==="); // Call getRideName()
            return;
        }

        // Validation 2: Empty waiting queue (Part5 requirement)
        if (waitingQueue.isEmpty()) {
            System.out.println("❌ Ride failed - Waiting queue is empty, no visitors to ride");
            System.out.println("=== 🎢 [" + getRideName() + "] Ride Cycle Terminated ==="); // Call getRideName()
            return;
        }

        // Validation 3: Insufficient waiting visitors (implied requirement: carry maxRider, or all waiting if insufficient)
        int availableVisitors = waitingQueue.size();
        int ridersThisCycle = Math.min(availableVisitors, getMaxRider()); // Call getMaxRider()
        if (availableVisitors < getMaxRider()) { // Call getMaxRider()
            System.out.println("⚠️  Insufficient waiting visitors (current: " + availableVisitors + ", max per cycle: " + getMaxRider() + "), will carry all waiting visitors"); // Call getMaxRider()
        }

        // Core logic: Queue → History
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = waitingQueue.poll();
            rideHistory.add(rider);
            System.out.println("✅ Successfully boarded: " + rider);
        }

        numOfCycles++;
        System.out.println("=== 🎢 [" + getRideName() + "] Ride Cycle Completed - Boarded " + ridersThisCycle + " visitors, total cycles: " + getNumOfCycles() + " ==="); // Call getRideName(), getNumOfCycles()
    }

    // Part6: Export to CSV (enhanced error prompts, added file path validation)
    public void exportRideHistory(String filePath) {
        System.out.println("\n=== 📤 Exporting [" + getRideName() + "] Ride History to CSV: " + filePath + " ==="); // Call getRideName()

        // Validate file path
        File file = new File(filePath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            System.out.println("❌ Export failed - Parent directory does not exist: " + file.getParentFile().getAbsolutePath());
            return;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            // CSV Header: Include all Visitor attributes (assignment requirement: "all visitor details")
            bufferedWriter.write("Visitor ID,Name,Age,ID Number,Membership Level");
            bufferedWriter.newLine();

            for (Visitor visitor : rideHistory) {
                // Handle null values (avoid missing CSV fields)
                String visitorId = visitor.getVisitorId() != null ? visitor.getVisitorId() : "";
                String name = visitor.getName() != null ? visitor.getName() : "";
                String age = visitor.getAge() >= 0 ? String.valueOf(visitor.getAge()) : "";
                String idNumber = visitor.getIdNumber() != null ? visitor.getIdNumber() : "";
                String membershipLevel = visitor.getMembershipLevel() != null ? visitor.getMembershipLevel() : "";

                String line = String.join(",", visitorId, name, age, idNumber, membershipLevel);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            System.out.println("✅ Export successful - Exported " + rideHistory.size() + " records, File path: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Export failed - " + e.getMessage() + " (Please check if the file is in use or permission is sufficient)");
        }
    }

    // Part7: Import from CSV (enhanced error prompts, added field validation)
    public void importRideHistory(String filePath) {
        System.out.println("\n=== 📥 Importing [" + getRideName() + "] Ride History from CSV: " + filePath + " ==="); // Call getRideName()

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ Import failed - File does not exist: " + filePath);
            return;
        }
        if (!file.isFile()) {
            System.out.println("❌ Import failed - Not a valid file: " + filePath);
            return;
        }

        rideHistory.clear(); // Clear existing history (avoid duplicate imports)
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
                    continue; // Skip header line
                }

                line = line.trim();
                if (line.isEmpty()) {
                    System.out.println("⚠️  Line " + lineNumber + ": Empty line, skipped");
                    failCount++;
                    continue;
                }

                String[] fields = line.split(",", -1); // -1: Preserve empty fields
                if (fields.length != 5) {
                    System.out.println("⚠️  Line " + lineNumber + ": Invalid number of fields (required: 5, actual: " + fields.length + "), Content: " + line);
                    failCount++;
                    continue;
                }

                String visitorId = fields[0].trim();
                String name = fields[1].trim();

                int age;
                try {
                    age = fields[2].trim().isEmpty() ? -1 : Integer.parseInt(fields[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️  Line " + lineNumber + ": Invalid age format (must be a number), Content: " + fields[2]);
                    failCount++;
                    // Directly continue, no need to assign age (subsequent code won't execute)
                    continue;
                }
                String idNumber = fields[3].trim();
                String membershipLevel = fields[4].trim();

                // Validate required fields (ID Number and Visitor ID cannot be empty)
                if (idNumber.isEmpty() || visitorId.isEmpty()) {
                    System.out.println("⚠️  Line " + lineNumber + ": Visitor ID or ID Number is empty, skipped");
                    failCount++;
                    continue;
                }

                // Create Visitor object
                Visitor visitor = new Visitor(name, age, idNumber, visitorId, membershipLevel);
                rideHistory.add(visitor);
                successCount++;
                System.out.println("✅ Line " + lineNumber + ": Import successful - " + visitor);
            }

            System.out.println("=== 📥 Import Completed - Success: " + successCount + " records, Failed: " + failCount + " records, Current total history records: " + rideHistory.size() + " ===");
        } catch (IOException e) {
            System.out.println("❌ Import failed - " + e.getMessage() + " (Please check file permissions or format)");
        }
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Failed to add to queue - Visitor object is null");
            return;
        }
        if (visitor.getIdNumber() == null || visitor.getIdNumber().trim().isEmpty()) {
            System.out.println("❌ Error: Failed to add to queue - Visitor ID Number is empty (" + visitor.getVisitorId() + "-" + visitor.getName() + ")");
            return;
        }
        waitingQueue.offer(visitor);
        System.out.println("✅ Successfully added to queue - " + visitor + " ([" + getRideName() + "] queue)"); // Call getRideName()
    }

    @Override
    public String toString() {
        return "Ride{" +
                "name='" + getRideName() + '\'' + // Call getRideName()
                ", maxRidersPerCycle=" + getMaxRider() + // Call getMaxRider()
                ", operator=" + (getOperator() != null ? getOperator().getName() : "None") + // Call getOperator()
                ", waitingQueueSize=" + getWaitingQueue().size() + // Call getWaitingQueue()
                ", totalRiders=" + numberOfVisitors() + // Call numberOfVisitors()
                ", totalCycles=" + getNumOfCycles() + // Call getNumOfCycles()
                '}';
    }
}